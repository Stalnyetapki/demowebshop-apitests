package com.tricentis.demowebshop;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.tricentis.demowebshop.TestData.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class DemoWebShopTests extends TestBase{

    @Test
    @DisplayName("Check User Info changing")
    void changeUserInfoTest() {

        step("Send request to change user info", () -> {
            given()
                    .log().uri()
                    .contentType("application/x-www-form-urlencoded")
                    .cookie("NOPCOMMERCE.AUTH", cookieValue)
                    .formParam("__RequestVerificationToken", requestVerificationToken)
                    .formParam("Gender", gender)
                    .formParam("FirstName", firstName)
                    .formParam("LastName", lastName)
                    .formParam("Email", email)
                    .formParam("save-info-button", "save")
            .when()
                    .post("/customer/info")
            .then()
                    .log().all()
                    .statusCode(302);
        });

        step("0pen minimal content to set cookies", () -> {
            open("/Themes/DefaultClean/Content/images/logo.png");
        });

        step("Set cookie to browser", () -> {
            getWebDriver().manage().addCookie(new Cookie("NOPCOMMERCE.AUTH", cookieValue));
        });

        step("Check that user info was changed", () -> {
            open("/customer/info");
            $("input[value='" + gender + "']").shouldHave(Condition.attribute("checked", "checked"));
            $("#FirstName").shouldHave(Condition.attribute("value", firstName));
            $("#LastName").shouldHave(Condition.attribute("value", lastName));
            $("#Email").shouldHave(Condition.attribute("value", email));
        });



    }
}
