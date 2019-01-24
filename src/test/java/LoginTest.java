import Environement.Configuration;
import Verification.VerificationUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utilities.*;
import util.LoginPage;
import utilities.ExceptionUtil.PasswordIncorrectException;
import utilities.ExceptionUtil.SizeViolationException;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@RunWith(Parameterized.class)
public class LoginTest {
    private static WebDriver driver;
    private static ExcelReader er;
    private StopWatch couting;

    private int testIndex;
    private String userName;
    private String passWord;
    private String expectedResult;

    @Parameterized.Parameters
    public static Collection getData(){
        er = new ExcelReader();
        return er.readDataExcel(Configuration.getInstance().getDataPath());
    }

    public LoginTest(int testIndex, String userName, String passWord, String expectedResult) {
        this.testIndex = testIndex;
        this.userName = userName;
        this.passWord = passWord;
        this.expectedResult = expectedResult;
    }

    @BeforeClass
    public static void setUpBeforeClass(){
        System.setProperty("webdriver.chrome.driver", Configuration.getInstance().getRootPath()+ "/src/library/chromedriver.exe");
    }

    @Before
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized");
        //options.addArguments("--headless");
        driver = new ChromeDriver(options);
        couting = new StopWatch();
        couting.start();
        driver.get(Configuration.getInstance().getWebSite());
    }

    @After
    public void tearDown(){
        TestUtil testUtil = new TestUtil(driver);
        testUtil.takeScreenShot(String.valueOf(this.testIndex));
        driver.quit();
    }

    @Test
    public void loginTest() {
        LoginPage loginPage = new LoginPage(driver);
        VerificationUtil verify = new VerificationUtil();
        List result;
        Exception errorMessage = null;
        try{
            loginPage.login(driver,userName,passWord);
        } catch (Exception e){
            errorMessage = e;
        }
        result = verify.verifyLogin(errorMessage, expectedResult);
        couting.stop();

        ExcelWriter writer = new ExcelWriter(Configuration.getInstance().getDataPath());

        writer.writeReport(this.testIndex, result, couting.getTime()/1000);
    }
}
