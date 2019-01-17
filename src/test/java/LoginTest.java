import Environement.Configuration;
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
import utilities.ExcelReader;
import utilities.ExcelWriter;
import utilities.TestData;
import util.LoginPage;
import utilities.TestUtil;

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

    @Parameterized.Parameters
    public static Collection getData(){
        er = new ExcelReader();
        return er.readDataExcel(Configuration.getInstance().getDataPath());
    }

    public LoginTest(int testIndex, String userName, String passWord) {
        this.testIndex = testIndex;
        this.userName = userName;
        this.passWord = passWord;
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
    public void loginTest(){
        LoginPage loginPage = new LoginPage(driver);
        boolean isCorrect = loginPage.login(driver, userName, passWord);
        couting.stop();
        ExcelWriter writer = new ExcelWriter();
        writer.writeReport(Configuration.getInstance().getDataPath(),
                putDataIntoMap(null, String.valueOf(isCorrect), couting.getTime()/1000));
        Assert.assertTrue(isCorrect);
    }

    private HashMap<Integer, List<String>> putDataIntoMap(String result, String status, long executeTime){
        HashMap<Integer, List<String>> map = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(result);
        values.add(status);
        values.add(String.valueOf(executeTime));
        map.put(this.testIndex, values);

        return map;
    }
}
