package genericUtility_Methods;
import java.io.*;
import java.util.Properties;

public class PropertiesFileUtility {
	
	public String togetDataFromPropertiesFile(String key) throws IOException
	{
		FileInputStream fis=new FileInputStream("./src/test/resources/CommonData.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String value=prop.getProperty(key);
		return value;
	}

}
