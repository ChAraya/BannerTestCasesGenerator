package cl.api.banner.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

public class PropertyReader {

	private String propertyName = null;
	private Properties props;

//This is constructor to pass property file name during object creation.

	public PropertyReader(String propertyName) {
		this.propertyName = propertyName;
	}

//private method created to load property file 
	private void loadProperty() {
//propertyName is fileName
		try {
			props = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + File.separator + "resources"
					+ File.separator + propertyName + ".properties");
			props.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//Public method created to access outside class.This method load property file and store Property file data in HashMap 

	public HashMap<String, String> getPropertyAsHashMap() {
		loadProperty();
		HashMap<String, String> map = new HashMap<String, String>();

		for (Entry<Object, Object> entry : props.entrySet()) {
			map.put((String) entry.getKey(), (String) entry.getValue());
		}
		return map;
	}
}
