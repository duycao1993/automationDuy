package util;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.NoSuchElementException;

public class HomePage {
    private Logger log = Logger.getLogger(HomePage.class);

    WebDriver driver;

    @FindBy(xpath = "//div[@id='__next']/div/div[2]/ol/b")
    WebElement highlightBoardLocator;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //private By highlightBoardLocator = By.xpath("//div[@id='__next']/div/div[2]/ol");

    public WebElement getHighlightBoardByLocation(HomePageHighLightBoard location){
        //List<WebElement> highLightBoardElements = driver.findElement(highlightBoardLocator).findElements(By.tagName("li"));
        List<WebElement> highLightBoardElements;
        WebElement highLightElement = null;
        try{
            log.info("Start method");
            highLightBoardElements = highlightBoardLocator.findElements(By.tagName("li"));
            highLightElement = highLightBoardElements.get(location.getIndex());
        } catch (Exception e){
            log.error(e);
            throw new NoSuchElementException();
        }

        return highLightElement;
    }
}
