package controllers.manage.user;

import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.utils.Binaries;
import common.utils.CSVs;
import common.utils.DateTimes;
import models.base.EntityDao;
import models.manage.user.ReadFormContent;
import models.system.System.Permission;
import models.system.System.PermissionsAllowed;
import models.user.User.Role;
import models.user.User_;
import play.data.Form;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.filters.csrf.RequireCSRFCheck;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import javax.annotation.Nonnull;
import play.i18n.Lang;
import play.i18n.MessagesApi;
import play.mvc.Http.Request;
import play.mvc.Security.Authenticated;

public class Read extends Controller {

	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Inject
	private MessagesApi messagesApi;

	@Inject
	private JPAApi jpaApi;

	@Inject
	private FormFactory formFactory;

	private final EntityDao<models.user.User> userDao = new EntityDao<models.user.User>() {
	};

	@Authenticated(common.core.Authenticator.class)
	@PermissionsAllowed(value = { Permission.MANAGE })
	public Result index(@Nonnull final Request request) {

		final Messages messages = messagesApi.preferred(request);
		final Lang lang = messages.lang();

		return jpaApi.withTransaction(manager -> {

			final ReadFormContent readFormContent = new ReadFormContent();
			readFormContent.setRoles(Arrays.asList(Role.values()));
			readFormContent.setMaxResult(String.valueOf(1000));

			final List<models.user.User> users = readList(request, manager, readFormContent, 0);

			final Form<ReadFormContent> readForm = formFactory.form(ReadFormContent.class).fill(readFormContent);

			return ok(views.html.manage.user.index.render(readForm, users, request, lang, messages));
		});
	}

	@Authenticated(common.core.Authenticator.class)
	@PermissionsAllowed(value = { Permission.MANAGE })
	@RequireCSRFCheck
	public Result read(@Nonnull final Request request) {

		final Messages messages = messagesApi.preferred(request);
		final Lang lang = messages.lang();

		return jpaApi.withTransaction(manager -> {

			final Form<ReadFormContent> readForm = formFactory.form(ReadFormContent.class).bindFromRequest(request);
			if (!readForm.hasErrors()) {

				final ReadFormContent readFormContent = readForm.get();
				if (readFormContent.getRoles() == null) {

					readFormContent.setRoles(Collections.emptyList());
				}

				final List<models.user.User> users = readList(request, manager, readFormContent, 0);

				return ok(views.html.manage.user.index.render(readForm, Collections.unmodifiableList(users), request, lang, messages));
			} else {

				return failureRead(readForm, request, lang, messages);
			}
		});
	}

	@Authenticated(common.core.Authenticator.class)
	@PermissionsAllowed(value = { Permission.MANAGE })
	@RequireCSRFCheck
	public Result download(@Nonnull final Request request) {

		final Messages messages = messagesApi.preferred(request);
		final Lang lang = messages.lang();

		final Result result = jpaApi.withTransaction(manager -> {

			final Form<ReadFormContent> downloadForm = formFactory.form(ReadFormContent.class).bindFromRequest(request);

			if (!downloadForm.hasErrors()) {

				final ReadFormContent downloadFormContent = downloadForm.get();
				if (downloadFormContent.getRoles() == null) {

					downloadFormContent.setRoles(Collections.emptyList());
				}

				final List<models.user.User> downloadUsers = readList(request, manager, downloadFormContent, 0);
				downloadUsers.stream().forEach(user -> user.beforeWrite(messages));
				final String csv = CSVs.toCSV(models.user.User.getHeaders(), models.user.User.getWriteCellProcessors(), downloadUsers);

				return ok(Binaries.concat(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }, csv.getBytes(StandardCharsets.UTF_8)))//
						.as(Http.MimeTypes.BINARY)//
						.withHeader(Http.HeaderNames.CONTENT_DISPOSITION, "attachment;filename=users.csv");
			} else {

				return failureRead(downloadForm, request, lang, messages);
			}
		});

		return result;
	}

	private List<models.user.User> readList(@Nonnull final Request request, final EntityManager manager, final ReadFormContent readFormContent, int startPosition) {

		final String userId = readFormContent.getUserId();
		final List<Role> roles = readFormContent.getRoles();
		final LocalDateTime expireFrom = readFormContent.getExpireFrom() == null //
				? null//
				: DateTimes.toServerDateTime(request, LocalDateTime.of(readFormContent.getExpireFrom(), LocalTime.MIN));
		final LocalDateTime expireTo = readFormContent.getExpireTo() == null //
				? null//
				: DateTimes.toServerDateTime(request, LocalDateTime.of(readFormContent.getExpireTo(), LocalTime.MAX));
		final int maxResult = Integer.parseInt(readFormContent.getMaxResult());

		return userDao.readList(manager, models.user.User.class, (builder, query) -> {

			final Root<models.user.User> root = query.from(models.user.User.class);

			final List<Predicate> predicates = new ArrayList<>();

			if (userId != null && !userId.isEmpty()) {

				predicates.add(builder.like(root.get(User_.userId), "%" + userId + "%"));
			}

			if (expireFrom != null) {

				predicates.add(builder.greaterThanOrEqualTo(root.get(User_.expireDateTime), expireFrom));
			}
			if (expireTo != null) {

				predicates.add(builder.lessThanOrEqualTo(root.get(User_.expireDateTime), expireTo));
			}

			final SetJoin<models.user.User, Role> rolesJoin = root.joinSet(models.user.User_.ROLES);
			predicates.add(rolesJoin.in(roles));

			query.where(predicates.toArray(new Predicate[0])).distinct(true);
		}, parameters -> {

			parameters.setFirstResult(startPosition).setMaxResults(maxResult);
		});
	}

	private Result failureRead(final Form<ReadFormContent> searchForm, final Request request, final Lang lang, final Messages messages) {

		final List<models.user.User> users = Collections.emptyList();
		return badRequest(views.html.manage.user.index.render(searchForm, users, request, lang, messages));
	}
}
