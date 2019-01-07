package Environement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class Configuration {
    Logger log = Logger.getLogger(Configuration.class.toString());

    public String rootPath = System.getProperty("user.dir");
    public String dataPath;
    public String reportPath;
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
