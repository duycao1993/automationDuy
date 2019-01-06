package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
//    public void isElementPresent(WebDriver driver, By element, int maxDuration){
//        new FluentWait<>(driver).pollingEvery(Duration.ofSeconds(1)).withTimeout(Duration.ofSeconds(maxDuration)).until(ExpectedConditions.presenceOfElementLocated(element));
//    }
    public boolean isElementPresent2(WebDriver driver, By element, int maxDuration){
        try{
            new WebDriverWait(driver, maxDuration).until(ExpectedConditions.presenceOfElementLocated(element));
            return true;
        } catch (Exception e){
            return false;
        }

    }
}
