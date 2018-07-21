package com.thanskourtan.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by v-askourtaniotis on 19/6/2018. mailTo: thanskourtan@gmail.com
 */
public class PropertiesGetter {

    public Properties props;

    public PropertiesGetter () throws IOException {
        this.props = new Properties();
        String resourceName = "application.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        }
    }

}
