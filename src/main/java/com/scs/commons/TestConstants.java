package com.scs.commons;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class TestConstants {
	
	public static final String userName=FunctionLibrary.readPropertyFile("user.username");
	public static final String password=FunctionLibrary.readPropertyFile("user.password");
	
	
	static File file;
   
    static void loadProperties(Properties p)throws IOException
    {
        FileInputStream fi=new FileInputStream(file);
        p.load(fi);
        fi.close();
        System.out.println("After Loading properties: " + p);
    }

    public static void writeToProperty(String key,String value)throws IOException
    {
    
    	String propertPath=FunctionLibrary.getUserHomePath()+"/src/main/resources/global.properties";
        Properties properties = new Properties();
        InputStream in = new FileInputStream(propertPath);
        properties.load(in);
        in.close();
        try(OutputStream outputStream = new FileOutputStream(propertPath)){  
            properties.setProperty(key,value);
            properties.store(outputStream, null);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
}
