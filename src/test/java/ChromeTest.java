import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import io.github.bonigarcia.wdm.WebDriverManager;
public class ChromeTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
        // Navegador
//        WebDriver driver;

    @BeforeEach
    void setUp() {

        driver = new ChromeDriver();
        String dir = System.getProperty("user.dir"); // ruta del proyecto
        String driverUrl = "/drivers/chromedriver.exe";
        String url = dir + driverUrl;
        System.setProperty("webdriver.chrome.driver", url);

    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
@DisplayName("Verificamos que abre la web correcta con el titulo esperado")
    @Test
    void testTrueWeb(){
        // Abrir página web
        driver.get("https://www.imdb.com/");
        // Obtener el texto de la etiqueta <title> de la página web que hemos abierto
        String title = driver.getTitle();


        // Verificar que el texto del título coincide con lo que queremos
        assertEquals("IMDb: Puntuaciones, reseñas y dónde ver las mejores películas y programas de televisión", title);
    }


}
