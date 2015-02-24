package pl.poznan.put.cs.wykop.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by dk994 on 04.12.14.
 */
public class ConfigManager {
    private Properties properties;

    protected ConfigManager(){
        try {
            properties = new Properties();
            InputStream propStream = ClassLoader.getSystemResourceAsStream("wykop.properties");
            properties.load(propStream);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private static ConfigManager configManager = null;


    public synchronized static ConfigManager getConfigManager(){
        if(configManager == null){
            configManager = new ConfigManager();
        }
        return configManager;
    }

    public Properties getProperties() {
        return properties;
    }

    public Object getProperty(String name){
        return properties.get(name);
    }

}
