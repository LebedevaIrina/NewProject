package ru.otus;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Arrays;
import java.util.Collection;

public class FirstTest {
    static final Logger logger = LogManager.getLogger(FirstTest.class);


    @RunWith(value = Parameterized.class)
    public static class JunitTest {

        public String baseUrl = "http://otus.ru";
        public static WebDriver webDriver;
        private String str;


        public JunitTest(String str) {
            this.str = str;
        }

        @BeforeClass
        public static void setup() {
            WebDriverManager.firefoxdriver().setup();
            WebDriverManager.chromedriver().setup();
        }

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            Object[][] data = new Object[][] { {"FirefoxDriver"}, {"ChromeDriver"} };
            return Arrays.asList(data);
        }

        @Test
        public void runTest() {
            if (str == "FirefoxDriver"){
                webDriver = new FirefoxDriver();
                logger.info("Firefox driver begin");
            }else{
                webDriver = new ChromeDriver();
                logger.info("Chrome driver begin");
            }
            try {
                webDriver.get(baseUrl);
                String title = webDriver.getTitle();
                Assert.assertTrue(title.equals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям"));
            }catch (Exception ex){
                logger.error("Тест провален");
            }
        }

        @AfterClass
        public static void closeBrowser() {
            if(webDriver != null){
                logger.info("Тест завершен");
            }
        }
    }

}
