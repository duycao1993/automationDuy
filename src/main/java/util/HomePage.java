package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.logging.Logger;

public class HomePage {
    private Logger log = Logger.getLogger(HomePage.class.toString());

    private By highlightBoardLocator = By.xpath("//div[@id='__next']/div/div[2]/ol");

    public WebElement getHighlightBoardByLocation(WebDriver driver, HomePageHighLightBoard location){
        List<WebElement> highLightBoardElements = driver.findElement(highlightBoardLocator).findElements(By.tagName("li"));
        return highLightBoardElements.get(location.getIndex());
    }
}
