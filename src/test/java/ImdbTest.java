import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//import java.util.Properties;
import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ImdbTest {


    // Navegador
    WebDriver driver;
    // login y password

 InputStream input = ImdbTest.class.getClassLoader().getResourceAsStream("login.properties");{

        Properties prop = new Properties();

        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
////Parte Ejercicio 2
    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        String dir = System.getProperty("user.dir"); // ruta del proyecto
        String driverUrl = "/drivers/chromedriver.exe";
        String url = dir + driverUrl;
        System.setProperty("webdriver.chrome.driver", url);
        Properties prop = new Properties();
        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Tag("User")
    @DisplayName("Login IMB")
    @Test
    void LoginTest() {
        // Abrir página web
        driver.manage().window().maximize();
        InputStream input = ImdbTest.class.getClassLoader().getResourceAsStream("login.properties");
        {

            Properties prop = new Properties();



            try {
                prop.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String user=(prop.getProperty("db.user"));
            String pass=(prop.getProperty("db.password"));
            String url=(prop.getProperty("db.url"));

            driver.get(url);
            WebElement sesion = driver.findElement(By.linkText("Iniciar sesión"));
            sesion.click();
            driver.findElement(By.linkText("Sign in with IMDb")).click();
            driver.findElement(By.id("ap_email")).sendKeys(user);
            driver.findElement(By.id("ap_password")).sendKeys(pass);
            driver.findElement(By.id("signInSubmit")).click();

           WebElement button= driver.findElement(By.cssSelector("span.imdb-header__account-toggle--logged-in"));
           assertEquals("david", button.getText());



        }
    }
    @DisplayName("Cerramos sesión")
    @Test
    void LogoutTest() {
     LoginTest();

     driver.findElement(By.cssSelector("label.navbar__flyout__text-button-after-mobile:nth-child(2)")).click();
     driver.findElement(By.cssSelector(".imdb-header-account-menu__sign-out > span:nth-child(1)")).click();
        WebElement sesion = driver.findElement(By.linkText("Iniciar sesión"));
        assertTrue(sesion.isDisplayed());


    }
}
