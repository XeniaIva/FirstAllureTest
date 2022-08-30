package xenia.ivanushkina;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class GitHubSteps {

    @Step("Открываем главную страницу GitHub")
    public void openMainPage(String url) {
        open(url);
    }

    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repo);
        $(".header-search-input").submit();
    }

    @Step("Кликаем по ссылке репозитория {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открываем таб Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Кликаем по issue с заголовком {issue}")
    public void clickOnIssueLink(String issue) {
        $(linkText(issue)).click();
    }

    @Step("\"Проверяем, что заголовок страницы с issue совпадает с {issue}")
    public void checkIssueHeaderText (String issue) {
        $(".gh-header-title").shouldHave(text(issue));
    }
}
