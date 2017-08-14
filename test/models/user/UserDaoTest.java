package models.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static play.test.Helpers.running;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.utils.Locales;
import common.utils.Sessions;
import models.user.User.Role;
import models.user.User.Theme;
import play.db.jpa.JPAApi;
import play.mvc.Http;
import play.mvc.Http.Cookie;
import play.mvc.Http.RequestBuilder;
import play.test.Helpers;
import play.test.WithApplication;

public class UserDaoTest extends WithApplication {

	private JPAApi jpaApi;

	private UserDao userDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		jpaApi = instanceOf(JPAApi.class);

		userDao = new UserDao();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCRUD() {

		running(Helpers.testServer(), () -> {

			final Http.Context context = getMockContext(Locale.US);

			Http.Context.current.set(context);

			final EntityManager manager = jpaApi.em();

			manager.getTransaction().begin();

			final User createUser = new User();
			createUser.setUserId("test@company.com");
			createUser.setPassword("test1!aA");
			createUser.setRoles(EnumSet.of(Role.CUSTOMER));
			createUser.setLocale(Locale.US);
			createUser.setZoneId(ZoneId.of("UTC"));
			createUser.setTheme(Theme.DEFAULT);
			createUser.setExpireDateTime(LocalDateTime.MAX);
			try {

				userDao.create(manager, createUser);
			} catch (final Exception e) {

				fail("");
			}

			long count;
			try {

				count = userDao.count(manager, (builder, query) -> {

					final Root<User> root = query.from(User.class);
					query.select(builder.count(root));
					query.where(builder.equal(root.get(User_.userId), createUser.getUserId()));
				});

				assertThat("", 1L, is(count));
			} catch (final Exception e1) {

				fail("");
			}

			User readUser = null;
			try {

				readUser = userDao.read(manager, (builder, query) -> {

					final Root<User> root = query.from(User.class);
					query.where(builder.equal(root.get(User_.userId), createUser.getUserId()));
				});
			} catch (final Exception e) {

				fail("");
			}

			final User updateUser = readUser;
			updateUser.setRoles(EnumSet.of(Role.ADMIN));
			try {

				userDao.update(manager, updateUser);
			} catch (final Exception e) {

				fail("");
			}

			final User deleteUser = updateUser;
			try {

				userDao.delete(manager, deleteUser);
			} catch (final Exception e) {

				fail("");
			}

			try {

				count = userDao.count(manager, (builder, query) -> {

					final Root<User> root = query.from(User.class);
					query.select(builder.count(root));
					query.where(builder.equal(root.get(User_.userId), deleteUser.getUserId()));
				});

				assertThat("", 0L, is(count));
			} catch (final Exception e1) {

				fail("");
			}

			manager.getTransaction().commit();

			Http.Context.current.remove();
		});
	}

	@Test
	public void testRead1() {

		running(Helpers.testServer(), () -> {

			final Http.Context context = getMockContext(Locale.US);

			Http.Context.current.set(context);

			final EntityManager manager = jpaApi.em();

			manager.getTransaction().begin();

			final User readUser = new User();
			readUser.setUserId("test@company.com");
			readUser.setPassword("test1!aA");
			readUser.setRoles(EnumSet.of(Role.CUSTOMER));
			readUser.setLocale(Locale.US);
			readUser.setZoneId(ZoneId.of("UTC"));
			readUser.setTheme(Theme.DEFAULT);
			readUser.setExpireDateTime(LocalDateTime.MAX);
			try {

				userDao.create(manager, readUser);
			} catch (final Exception e) {

				fail("");
			}

			try {

				final User user = userDao.read(manager, User.class);
				fail("");
			} catch (final NonUniqueResultException e) {

				assertTrue(true);
			}

			userDao.delete(manager, readUser);

			manager.getTransaction().commit();

			Http.Context.current.remove();
		});
	}

	@Test
	public void testRead2() {

		running(Helpers.testServer(), () -> {

			final Http.Context context = getMockContext(Locale.US);

			Http.Context.current.set(context);

			final EntityManager manager = jpaApi.em();

			manager.getTransaction().begin();

			final User readUser = new User();
			readUser.setUserId("test@company.com");
			readUser.setPassword("test1!aA");
			readUser.setRoles(EnumSet.of(Role.CUSTOMER));
			readUser.setLocale(Locale.US);
			readUser.setZoneId(ZoneId.of("UTC"));
			readUser.setTheme(Theme.DEFAULT);
			readUser.setExpireDateTime(LocalDateTime.MAX);

			userDao.create(manager, readUser);

			final User user = userDao.read(manager, User.class, (builder, query) -> {

				final Root<models.user.User> root = query.from(models.user.User.class);

				final List<Predicate> predicates = new ArrayList<>();
				predicates.add(builder.equal(root.get(User_.userId), "test@company.com"));

				query.where(predicates.toArray(new Predicate[0]));
			});

			assertThat("", "test@company.com", is(user.getUserId()));

			userDao.delete(manager, readUser);

			manager.getTransaction().commit();

			Http.Context.current.remove();
		});
	}

	@Test
	public void testRead3() {

		running(Helpers.testServer(), () -> {

			final Http.Context context = getMockContext(Locale.US);

			Http.Context.current.set(context);

			final EntityManager manager = jpaApi.em();

			manager.getTransaction().begin();

			final User readUser = new User();
			readUser.setUserId("test@company.com");
			readUser.setPassword("test1!aA");
			readUser.setRoles(EnumSet.of(Role.CUSTOMER));
			readUser.setLocale(Locale.US);
			readUser.setZoneId(ZoneId.of("UTC"));
			readUser.setTheme(Theme.DEFAULT);
			readUser.setExpireDateTime(LocalDateTime.MAX);

			userDao.create(manager, readUser);

			final User user = userDao.read(manager, User.class, (builder, query) -> {

				final Root<models.user.User> root = query.from(models.user.User.class);

				final List<Predicate> predicates = new ArrayList<>();
				predicates.add(builder.equal(root.get(User_.userId), "test@company.com"));

				query.where(predicates.toArray(new Predicate[0]));
			}, parameters -> {

				parameters.setFirstResult(0);
			});

			assertThat("", "test@company.com", is(user.getUserId()));

			userDao.delete(manager, readUser);

			manager.getTransaction().commit();

			Http.Context.current.remove();
		});
	}

	@Test
	public void testReadList1() {

		running(Helpers.testServer(), () -> {

			final Http.Context context = getMockContext(Locale.US);

			Http.Context.current.set(context);

			final EntityManager manager = jpaApi.em();

			manager.getTransaction().begin();

			final User readUser = new User();
			readUser.setUserId("test@company.com");
			readUser.setPassword("test1!aA");
			readUser.setRoles(EnumSet.of(Role.CUSTOMER));
			readUser.setLocale(Locale.US);
			readUser.setZoneId(ZoneId.of("UTC"));
			readUser.setTheme(Theme.DEFAULT);
			readUser.setExpireDateTime(LocalDateTime.MAX);
			try {

				userDao.create(manager, readUser);
			} catch (final Exception e) {

				fail("");
			}

			final List<User> users = userDao.readList(manager, User.class);

			final List<User> filterUsers = users.stream().filter(user -> "test@company.com".equals(user.getUserId())).collect(Collectors.toList());
			assertThat("", 1, is(filterUsers.size()));
			assertThat("", "test@company.com", is(filterUsers.get(0).getUserId()));

			userDao.delete(manager, readUser);

			manager.getTransaction().commit();

			Http.Context.current.remove();
		});
	}

	@Test
	public void testReadList2() {

		running(Helpers.testServer(), () -> {

			final Http.Context context = getMockContext(Locale.US);

			Http.Context.current.set(context);

			final EntityManager manager = jpaApi.em();

			manager.getTransaction().begin();

			final User readUser = new User();
			readUser.setUserId("test@company.com");
			readUser.setPassword("test1!aA");
			readUser.setRoles(EnumSet.of(Role.CUSTOMER));
			readUser.setLocale(Locale.US);
			readUser.setZoneId(ZoneId.of("UTC"));
			readUser.setTheme(Theme.DEFAULT);
			readUser.setExpireDateTime(LocalDateTime.MAX);

			userDao.create(manager, readUser);

			final List<User> users = userDao.readList(manager, User.class, (builder, query) -> {

				final Root<models.user.User> root = query.from(models.user.User.class);

				final List<Predicate> predicates = new ArrayList<>();
				predicates.add(builder.equal(root.get(User_.userId), "test@company.com"));

				query.where(predicates.toArray(new Predicate[0]));
			});

			assertThat("", 1, is(users.size()));
			assertThat("", "test@company.com", is(users.get(0).getUserId()));

			userDao.delete(manager, readUser);

			manager.getTransaction().commit();

			Http.Context.current.remove();
		});
	}

	@Test
	public void testReadList3() {

		running(Helpers.testServer(), () -> {

			final Http.Context context = getMockContext(Locale.US);

			Http.Context.current.set(context);

			final EntityManager manager = jpaApi.em();

			manager.getTransaction().begin();

			final User readUser = new User();
			readUser.setUserId("test@company.com");
			readUser.setPassword("test1!aA");
			readUser.setRoles(EnumSet.of(Role.CUSTOMER));
			readUser.setLocale(Locale.US);
			readUser.setZoneId(ZoneId.of("UTC"));
			readUser.setTheme(Theme.DEFAULT);
			readUser.setExpireDateTime(LocalDateTime.MAX);

			userDao.create(manager, readUser);

			final List<User> users = userDao.readList(manager, User.class, (builder, query) -> {

				final Root<models.user.User> root = query.from(models.user.User.class);

				final List<Predicate> predicates = new ArrayList<>();
				predicates.add(builder.equal(root.get(User_.userId), "test@company.com"));

				query.where(predicates.toArray(new Predicate[0]));
			}, parameters -> {

				parameters.setFirstResult(0);
			});

			assertThat("", 1, is(users.size()));
			assertThat("", "test@company.com", is(users.get(0).getUserId()));

			userDao.delete(manager, readUser);

			manager.getTransaction().commit();

			Http.Context.current.remove();
		});
	}

	private Http.Context getMockContext(final Locale locale) {

		final RequestBuilder builder = new RequestBuilder();
		builder.header("User-Agent", "mocked user-agent");
		builder.session(User.USERID, "mockUser");
		builder.session(User.ROLES, Sessions.toValue(Arrays.asList(new Role[]{Role.ADMIN})));
		builder.session(models.user.User.THEME, Theme.DEFAULT.name());
		builder.session(User.ZONEID, ZoneId.of("UTC").getId());
		builder.cookie(Cookie.builder(models.user.User.THEME, Theme.DEFAULT.name()).build());

		final Http.Context mockContext = Helpers.httpContext(builder.build());
		mockContext.changeLang(Locales.toLang(locale));

		final EntityManagerFactory factory = Persistence.createEntityManagerFactory("defaultPersistenceUnit");
		final EntityManager manager = factory.createEntityManager();

		final Deque<EntityManager> ems = new ArrayDeque<>();
		ems.push(manager);
		mockContext.args.put("entityManagerContext", ems);

		return mockContext;
	}
}
