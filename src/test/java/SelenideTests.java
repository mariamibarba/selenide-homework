import com.codeborne.selenide.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTests {
    public void SelenideTests() {
        baseUrl = "http://the-internet.herokuapp.com";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
        timeout=20000;
        holdBrowserOpen=false;
        screenshots=false;
        baseUrl = "http://the-internet.herokuapp.com";
        reopenBrowserOnFail = true;
        fastSetValue=true;
        assertionMode=AssertionMode.SOFT;
        fileDownload=FileDownloadMode.HTTPGET;
//        reportsFolder="src/main/resources/failedScreens";
//        downloadsFolder="src/main/resources/images";

    }
    @Test
    public void handleDropDowns(){
        open("http://the-internet.herokuapp.com/checkboxes");
//        $(by("type", "checkbox")).setSelected(true);
//        $("input[type='checkbox']").shouldHave(attribute("type", "checkbox"));

        ElementsCollection checkboxes = $$("input[type=checkbox]");
        checkboxes.get(0).click();
        for (SelenideElement checkbox :
                checkboxes) {
            checkbox.shouldHave(type("checkbox"));
        };

    }
    @Test
    public void dropdowntest(){
        open("http://the-internet.herokuapp.com/dropdown");
        SelenideElement selectElement = $("#dropdown").shouldHave(text("Please select an option"));
        selectElement.getSelectedOption().shouldHave(text("Please select an option"));
        $("#dropdown").selectOptionContainingText("Option 2");
        $("#dropdown").getSelectedOption().shouldHave(matchText("Option 2"),value("2"));


    }

    @Test
    public void userForms(){
        open("https://demoqa.com/text-box");

        SelenideElement fullNameInput = $("#userName");
        SelenideElement emailInput = $(byId("userEmail"));
        SelenideElement currentAddressInput = $(byXpath("//*[@id=\"currentAddress\"]"));
        SelenideElement permanentAddressInput = $("#permanentAddress");

        // Fill in the form fields
        fullNameInput.setValue("Mariami");
        emailInput.setValue("mariamibarba@gmail.com");
        currentAddressInput.setValue("meggobrobis 4 a");
        permanentAddressInput.setValue("aschvkhvjgc");


        // Click the submit button
        $("#submit").scrollTo().click();

        // Validate the result with Collection Assertion
        $$("#output p").shouldHave(CollectionCondition.exactTexts("Name:Mariami",
                "Email:mariamibarba@gmail.com",
                "Current Address :meggobrobis 4 a",
                "Permananet Address :aschvkhvjgc")
        );

    }


}