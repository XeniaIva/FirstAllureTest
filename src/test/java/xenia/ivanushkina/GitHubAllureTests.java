package xenia.ivanushkina;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class GitHubAllureTests {
    private static final String baseUrl = "https://github.com/";
    private static final String repoName = "qa-guru/allure-notifications";
    private static final String issueName = "Null pointer exception after executing command";

    @Test
    public void selenideGitHubAllureTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(baseUrl);
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repoName);
        $(".header-search-input").submit();
        $(linkText(repoName)).click();
        $("#issues-tab").click();
        $(linkText(issueName)).click();
        $(".gh-header-title").shouldHave(text(issueName));
    }

    @Test
    public void lambdaGitHubAllureTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу GitHub", () -> {
            open(baseUrl);
        });
        step("Ищем репозиторий " + repoName, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(repoName);
            $(".header-search-input").submit();
        });
        step("Кликаем по ссылке репозитория " + repoName, () -> {
            $(linkText(repoName)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Кликаем по issue с заголовком " + issueName, () -> {
            $(linkText(issueName)).click();
        });
        step("Проверяем, что заголовок страницы с issue совпадает с " + issueName, ()->{
            $(".gh-header-title").shouldHave(text(issueName));
        });
    }

    @Test
    public void stepsGitHubAllureTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        GitHubSteps steps = new GitHubSteps();

        steps.openMainPage(baseUrl);
        steps.searchForRepository(repoName);
        steps.clickOnRepositoryLink(repoName);
        steps.openIssuesTab();
        steps.clickOnIssueLink(issueName);
        steps.checkIssueHeaderText(issueName);
    }

}
