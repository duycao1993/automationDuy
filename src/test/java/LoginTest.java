import Environement.Configuration;
import Verification.ENUM.StatusEnum;
import Verification.VerificationUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import Utilities.*;
import TestAction.LoginPage;
import Utilities.ExcelUtil.ExcelReader;
import Utilities.ExcelUtil.ExcelWriter;

import java.io.File;
import java.util.Collection;
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
    private static ExcelWriter writer;

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
        writer = new ExcelWriter(Configuration.getInstance().getDataPath());
        try{
            FileUtils.cleanDirectory(new File(Configuration.getInstance().getReportPath() + "/screenshots/"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Before
    public void setUp(){
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized");
        options.addArguments("--headless");
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

    @Test(timeout = 30000)
    public void loginTest() {
        LoginPage loginPage = new LoginPage(driver);
        Exception errorMessage = null;
        try{
            loginPage.login(driver,userName,passWord);
        } catch (Exception e){
            errorMessage = e;
        }
        VerificationUtil verify = new VerificationUtil();
        List result = verify.verifyLogin(errorMessage, expectedResult);
        couting.stop();


        writer.writeReport(this.testIndex, result, ((double) couting.getTime())/1000);

        Assert.assertEquals(result.get(0), StatusEnum.Passed.toString());
    }
}
