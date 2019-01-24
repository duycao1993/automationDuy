package Environement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class Configuration {
    Logger log = Logger.getLogger(Configuration.class.toString());

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    private String rootPath = System.getProperty("user.dir");
    private String dataPath;
    private String reportPath;
    private String webSite;

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    private String browser;
    private int timeOut;
    public static Configuration Instance;

    private Configuration() {
        Properties prop = getProps();
        dataPath = rootPath + prop.getProperty("DATA_INPUT_PATH");
        reportPath = rootPath + prop.getProperty("REPORT_PATH") + "/";
    }

    public static Configuration getInstance(){
        if(Instance == null){
            Instance = new Configuration();
        }
        return Instance;
    }



    private Properties getProps(){
        Properties prop = null;
        try {
            prop = new Properties();
            String propFileName = "config.properties";

            prop.load(new FileInputStream(propFileName));
        } catch (IOException e){
            log.throwing(Configuration.class.toString(), "getProps" , e);
        }
        return prop;
    }
}
