import Environement.Configuration;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utilities.ExcelReader;
import utilities.TestData;
import util.LoginPage;

import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class LoginTest {
    private static WebDriver driver;
    private static ExcelReader er;

    private int testIndex;
    private String userName;
    private String passWord;

    @Parameterized.Parameters
    public static Collection getData(){
        er = new ExcelReader();
        return er.readDataExcel(Configuration.getInstance().dataPath);
    }

    public LoginTest(int testIndex, String userName, String passWord) {
        super();
        this.testIndex = testIndex;
        this.userName = userName;
        this.passWord = passWord;
    }

    @BeforeClass
    public static void setUpBeforeClass(){
        System.setProperty("webdriver.chrome.driver", Configuration.getInstance().rootPath+ "/src/library/chromedriver.exe");
    }

    @Before
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized");
        //options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("https://accounts.chotot.com");
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void loginTest(){
        LoginPage loginPage = new LoginPage(driver);
        boolean isCorrect = loginPage.login(driver, userName, passWord);
        Assert.assertTrue(isCorrect);
    }
}
