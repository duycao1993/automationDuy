import Environement.Configuration;
import org.junit.*;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import util.HomePage;
import util.HomePageHighLightBoard;

import java.util.Collection;
import java.util.logging.Logger;

public class HighLightBoardTest {
    private Logger log = Logger.getLogger(HighLightBoardTest.class.toString());
    private static WebDriver driver;
    private ChromeOptions options;

    @BeforeClass
    public static void setUpBeforeClass(){

        //options.addArguments("--start-maximized");
        //options.addArguments("--headless");

    }

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", Configuration.getInstance().getRootPath()+ "/src/library/chromedriver.exe");
        options = new ChromeOptions();
        //options.addArguments("--headless");
        //driver = new ChromeDriver(options);


        //driver.get("http://tinhte.vn");
        //driver.manage().window().fullscreen();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Ignore
    @Test
    public void testHightLight(){
        HomePage hp = new HomePage(driver);
        Assert.assertTrue("Verify the highlight board",  hp.getHighlightBoardByLocation(HomePageHighLightBoard.TopHighLightRight).getText().contains("Dìm hàng mod"));
    }
}
