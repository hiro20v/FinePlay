package controllers.resetuser;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static play.test.Helpers.FIREFOX;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;
import static test.Condition.anyConditionOf;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import controllers.registuser.RegistPage;
import controllers.registuser.provisional.CompletePage;
import controllers.user.IndexPage;
import test.Appender;
import test.BrowserLocaleFilter;
import test.Captures;
import test.Condition;
import test.Counter;
import test.Filter;
import test.LoggerNames;

@Deprecated
public class ResetUserTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testApply() {

		@SuppressWarnings("serial")
		final List<Condition> conditions = Arrays.asList(new Condition[]{ //
				new Condition("", Locale.US, new HashMap<String, Object>() {
					{
						put("title.signin", "Sign in | fine✿play");

						put("title.registuser", "Regist user | fine✿play");
						put("registuser.please_regist", "Please regist user.");
						put("registuser.provisional.complete", "Provisional regist completion");
						put("registuser.regular.complete", "Regular regist completion");

						put("title.resetuser", "Reset password | fine✿play");
						put("resetuser.please_reset", "Please request reset password.");
						put("resetuser.request.complete", "Reset password request completion");
						put("resetuser.please_change", "Please input new password.");
						put("resetuser.change.complete", "Reset password completion");

						put("title.home", "Home | fine✿play");
					}
				}), //
				new Condition("", Locale.JAPAN, new HashMap<String, Object>() {
					{
						put("title.signin", "サインイン | ファイン✿プレイ");

						put("title.registuser", "ユーザー登録 | ファイン✿プレイ");
						put("registuser.please_regist", "ユーザー登録してください。");
						put("registuser.provisional.complete", "仮登録完了");
						put("registuser.regular.complete", "本登録完了");

						put("title.resetuser", "パスワードリセット | ファイン✿プレイ");
						put("resetuser.please_reset", "パスワードリセットを要求してください。");
						put("resetuser.request.complete", "パスワードリセット要求完了");
						put("resetuser.please_change", "新規のパスワードを入力してください。");
						put("resetuser.change.complete", "パスワードリセット完了");

						put("title.home", "ホーム | ファイン✿プレイ");
					}
				}) //
		});

		final Path capturePath = Captures.getFolderPath();

		conditions.stream().filter(new Filter()).filter(new BrowserLocaleFilter()).forEach(condition -> {

			running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, browser -> {

				final Counter counter = new Counter(3);
				final Locale locale = condition.getLocale();

				final ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(LoggerNames.PLAY_MAILER);

				final Appender testAppender = new Appender();
				root.addAppender(testAppender);

				final IndexPage indexPage = new IndexPage(browser);
				browser.goTo(indexPage.getUrl());
				indexPage.isAt();
				assertThat("", browser.window().title(), anyConditionOf(conditions, "title.signin", String.class));
				indexPage.takeScreenshot(capturePath, locale, counter, "Sign In");
				indexPage.clickRegistUser();

				final RegistPage registPage = new RegistPage(browser);
				browser.await().atMost(10, TimeUnit.SECONDS).until(() -> browser.url().startsWith(registPage.getUrl()));
				registPage.isAt();
				assertThat("", registPage.contentTitle(), is(condition.get("registuser.please_regist").toString()));
				registPage.inputUserId("reset@example.com");
				assertThat("", registPage.getUserId(), is("reset@example.com"));
				registPage.inputPassword("1!aAregist");
				assertThat("", registPage.getPassword(), is("1!aAregist"));
				registPage.inputRePassword("1!aAregist");
				assertThat("", registPage.getRePassword(), is("1!aAregist"));
				registPage.takeScreenshot(capturePath, locale, counter, "Regist User - Input");
				registPage.clickApply();

				final CompletePage provisionalCompletePage = new CompletePage(browser);
				browser.await().atMost(10, TimeUnit.SECONDS).until(() -> browser.url().startsWith(provisionalCompletePage.getUrl()));
				provisionalCompletePage.isAt();
				assertThat("", provisionalCompletePage.contentTitle(), is(condition.get("registuser.provisional.complete").toString()));
				provisionalCompletePage.takeScreenshot(capturePath, locale, counter, "Provisional - Compleate");
				final String provisionalMailLog = testAppender.getEvents().stream()//
						.filter(event -> event.getFormattedMessage().contains("<html"))//
						.findFirst()//
						.map(event -> event.getFormattedMessage())//
						.get();
				final Element registLink = Jsoup.parse(provisionalMailLog).select("a[href ^= http://localhost:9000/registuser/regular/]").get(0);
				final String regularUrl = registLink.attr("href").replace("http://localhost:9000", "http://localhost:3333");
				provisionalCompletePage.clickTop();

				final IndexPage indexPage2 = new IndexPage(browser);
				browser.await().atMost(10, TimeUnit.SECONDS).until(() -> browser.url().startsWith(indexPage2.getUrl()));
				indexPage2.isAt();
				assertThat("", browser.window().title(), is((String) condition.get("title.signin")));
				indexPage2.takeScreenshot(capturePath, locale, counter, "Sign In - After Provisional.png");
				browser.goTo(regularUrl);

				final controllers.registuser.reqular.CompletePage reqularCompletePage = new controllers.registuser.reqular.CompletePage(browser);
				testAppender.clearEvents();
				browser.await().atMost(10, TimeUnit.SECONDS).until(() -> browser.url().startsWith(reqularCompletePage.getUrl()));
				reqularCompletePage.isAt();
				assertThat("", reqularCompletePage.contentTitle(), is((String) condition.get("registuser.regular.complete")));
				reqularCompletePage.takeScreenshot(capturePath, locale, counter, "Regular - Compleate");
				reqularCompletePage.clickTop();

				final IndexPage indexPage3 = new IndexPage(browser);
				browser.await().atMost(10, TimeUnit.SECONDS).until(() -> browser.url().startsWith(indexPage3.getUrl()));
				indexPage3.isAt();
				assertThat("", browser.window().title(), is((String) condition.get("title.signin")));
				indexPage3.takeScreenshot(capturePath, locale, counter, "Sign In - After Regular");
				indexPage3.clickResetUser();

				final ResetPage resetPage = new ResetPage(browser);
				browser.await().atMost(10, TimeUnit.SECONDS).until(() -> browser.url().startsWith(resetPage.getUrl()));
				resetPage.isAt();
				assertThat("", resetPage.contentTitle(), is(condition.get("resetuser.please_reset").toString()));
				resetPage.inputUserId("reset@example.com");
				assertThat("", resetPage.getUserId(), is("reset@example.com"));
				resetPage.takeScreenshot(capturePath, locale, counter, "Reset User - Input");
				resetPage.clickApply();

				final controllers.resetuser.request.CompletePage requestCompletePage = new controllers.resetuser.request.CompletePage(browser);
				browser.await().atMost(10, TimeUnit.SECONDS).until(() -> browser.url().startsWith(requestCompletePage.getUrl()));
				requestCompletePage.isAt();
				assertThat("", requestCompletePage.contentTitle(), is(condition.get("resetuser.request.complete").toString()));
				requestCompletePage.takeScreenshot(capturePath, locale, counter, "Request - Compleate");
				final String requestMailLog = testAppender.getEvents().stream()//
						.filter(event -> event.getFormattedMessage().contains("<html"))//
						.findFirst()//
						.map(event -> event.getFormattedMessage())//
						.get();
				final Element changeLink = Jsoup.parse(requestMailLog).select("a[href ^=http://localhost:9000/resetuser/owner/]").get(0);
				final String changeUrl = changeLink.attr("href").replace("http://localhost:9000", "http://localhost:3333");
				requestCompletePage.clickTop();

				final IndexPage indexPage4 = new IndexPage(browser);
				browser.await().atMost(10, TimeUnit.SECONDS).until(() -> browser.url().startsWith(indexPage4.getUrl()));
				indexPage4.isAt();
				indexPage4.takeScreenshot(capturePath, locale, counter, "Sign In - After Request");
				browser.goTo(changeUrl);

				final controllers.resetuser.change.ChangePage changePage = new controllers.resetuser.change.ChangePage(browser);
				browser.await().atMost(10, TimeUnit.SECONDS).until(() -> browser.url().startsWith(changePage.getUrl()));
				testAppender.clearEvents();
				changePage.isAt();
				assertThat("", changePage.contentTitle(), is(condition.get("resetuser.please_change").toString()));
				changePage.inputPassword("1!aAreset_2");
				assertThat("", changePage.getPassword(), is("1!aAreset_2"));
				changePage.inputRePassword("1!aAreset_2");
				assertThat("", changePage.getRePassword(), is("1!aAreset_2"));
				changePage.takeScreenshot(capturePath, locale, counter, "Reset User - Input");
				changePage.clickChange();

				final controllers.resetuser.change.CompletePage changeCompletePage = new controllers.resetuser.change.CompletePage(browser);
				browser.await().atMost(10, TimeUnit.SECONDS).until(() -> browser.url().startsWith(changeCompletePage.getUrl()));
				changeCompletePage.isAt();
				assertThat("", changeCompletePage.contentTitle(), is(condition.get("resetuser.change.complete").toString()));
				changeCompletePage.takeScreenshot(capturePath, locale, counter, "Change - Compleate");
				final String changeMailLog = testAppender.getEvents().stream()//
						.filter(event -> event.getFormattedMessage().contains("<html"))//
						.findFirst()//
						.map(event -> event.getFormattedMessage())//
						.get();
				final Element topLink = Jsoup.parse(changeMailLog).select("a[href =http://localhost:9000]").get(0);
				final String topUrl = topLink.attr("href").replace("http://localhost:9000", "http://localhost:3333");
				changeCompletePage.clickTop();

				final IndexPage indexPage5 = new IndexPage(browser);
				browser.await().atMost(10, TimeUnit.SECONDS).until(() -> browser.url().startsWith(indexPage5.getUrl()));
				indexPage5.isAt();
				indexPage5.takeScreenshot(capturePath, locale, counter, "Sign In - After Change");
				browser.goTo(topUrl);

				indexPage5.takeScreenshot(capturePath, locale, counter, "Sign In - After Change 2");

				indexPage5.inputUserId("reset@example.com");
				indexPage5.inputPassword("1!aAreset_2");
				indexPage5.takeScreenshot(capturePath, locale, counter, "Sign In - Input");
				indexPage5.clickSignIn();

				indexPage5.takeScreenshot(capturePath, locale, counter, "Sign In - Success");
			});
		});
	}
}
