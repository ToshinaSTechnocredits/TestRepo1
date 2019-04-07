package com.tc.ak.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileOperation 
{
	Properties prop;
	public PropertyFileOperation(String filePath) throws IOException
	{	
		File file = new File(filePath);	//opened
		FileInputStream inStream = new FileInputStream(file);	//readable mode
		prop = new Properties();	//object creation
		prop.load(inStream);	//property file load in memory
	}
	
	public String readData(String key)
	{
		String value = prop.getProperty(key);	//passing key and it will get the value
		return value;	//returns value
	}
}
