import Environement.Configuration;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import util.HomePage;
import util.HomePageHighLightBoard;

import java.util.logging.Logger;

public class HighLightBoardTest {
    private Logger log = Logger.getLogger(HighLightBoardTest.class.toString());
    private static WebDriver driver;

    @BeforeClass
    public static void setUpBeforeClass(){
        System.setProperty("webdriver.chrome.driver", Configuration.getInstance().rootPath+ "/src/library/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized");
        //options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @Before
    public void setUp(){
        driver.get("http://tinhte.vn");
        driver.manage().window().fullscreen();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test(timeout = 20000)
    public void testHightLight(){
        HomePage hp = new HomePage();
        Assert.assertTrue(hp.getHighlightBoardByLocation(driver, HomePageHighLightBoard.TopHighLightRight).getText().contains("Dìm hàng mod"));
    }
}
