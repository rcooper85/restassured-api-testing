package configuration;

import java.io.*;
import java.util.Properties;

public class Config {

    File file;
    FileInputStream fis;
    Properties prop;

    /**
     * A constructor that instantiates a Config object with the loaded properties file.
     * @throws IOException
     */
    public Config() throws IOException {
        initialConfig();
    }

    /**
     * A method that loads in the properties file and creates a Properties object.
     * @throws IOException
     */
    public void initialConfig() throws IOException {
        file = new File("C:\\Users\\Ross\\Desktop\\Repos\\restassured-api-testing\\src\\main\\resources\\api.properties");
        fis = new FileInputStream(file);
        prop = new Properties();
        prop.load(fis);
    }

    /**
     * A method that returns a Key from the properties file
     * @param key The Key that you wish to obtain from the properties file.
     * @return A String value of the specified Key.
     */
    public String readProperty(String key) {
         return prop.getProperty(key);
    }


}
