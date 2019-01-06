import Environement.Configuration;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utilities.ExcelReader;
import utilities.TestData;
import util.LoginPage;
import java.util.List;

public class LoginTest {
    private static WebDriver driver;
    private ExcelReader er;
    private List<TestData> scenario;

    @BeforeClass
    public static void setUpBeforeClass(){
        Configuration conf = new Configuration();

        System.setProperty("webdriver.chrome.driver", Configuration.rootPath+ "/src/library/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @Before
    public void setUp(){
        er = new ExcelReader();
        scenario = er.readDataExcel(Configuration.dataPath);

        driver.get("http://tinhte.vn");

    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void loginTest(){
        boolean result = true;
        for(TestData data : scenario){
            LoginPage loginPage = new LoginPage();
            result = loginPage.login(driver, data.getUserName(), data.getPassWord());
        }
        Assert.assertTrue(result);
    }
}
