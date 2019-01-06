import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import util.loginPage;

public class LoginTest {
    static WebDriver driver;

    @BeforeClass
    public static void setUpBeforeClass(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Admin\\IdeaProjects\\automationtesting\\src\\library/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @Before
    public void setUp(){
        //driver = new FirefoxDriver();
        driver.get("http://tinhte.vn");
    }

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void loginTest(){
        loginPage login = new loginPage();
        Assert.assertTrue(login.login(driver, "duy.ct1861@gmail.com", "Hanh3091996"));
    }
}
