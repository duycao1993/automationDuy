package utilities;

import Environement.Configuration;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class TestUtil {
    private Logger log = Logger.getLogger(TestUtil.class);
    private WebDriver driver;

    public TestUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenShot(String imageName){
        try{
            Thread.sleep(1000);
            String imagePath = Configuration.getInstance().getReportPath() + "/screenshots/"+ imageName + ".png";
            File currentFile = new File(imagePath);
            if(currentFile.exists()){
                currentFile.delete();
            }
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(imagePath));
        } catch (Exception e){
            log.error(e);
        }

    }
}
