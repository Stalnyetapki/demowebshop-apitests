package com.tricentis.demowebshop;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {


    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://demowebshop.tricentis.com";
        Configuration.baseUrl = "https://demowebshop.tricentis.com";

    }
}
