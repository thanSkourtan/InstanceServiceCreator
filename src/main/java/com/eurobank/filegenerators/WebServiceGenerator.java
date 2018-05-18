package com.eurobank.filegenerators;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by v-askourtaniotis on 18/5/2018. mailTo: thanskourtan@gmail.com
 */
public class WebServiceGenerator {
    private static WebServiceGenerator engine = new WebServiceGenerator();
    private Template template=null;
    Map<String, Object> dataMap = new HashMap<String, Object>();
    public WebServiceGenerator()
    {
        init();
    }
    private void init()
    {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        try {
            cfg.setDirectoryForTemplateLoading(new File("src\\main\\resources"));
            template = cfg.getTemplate("BReq.ftl");
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static WebServiceGenerator get()
    {
        return engine;
    }
    public WebServiceGenerator buildData()
    {
        dataMap.put("package", this.getClass().getPackage()+";");
        dataMap.put("name", "HelloWorldservice");
        dataMap.put("return", "String");
        dataMap.put("methodname", "hello");
        dataMap.put("params", "String name");
        dataMap.put("body", "String res= \"Hi\" + name;\n System.out.println(res);");
        dataMap.put("val", "res;");
        System.out.println("Preparing Data");
        return engine;
    }
    public void  writeFile() {
        Writer file = null;
        try {
            file = new FileWriter(new File("src\\main\\resources\\HelloWorldservice.java"));
            template.process(dataMap, file);
            file.flush();
            System.out.println("Success");
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (IOException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
