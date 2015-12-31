package com.loong.util.config;

/** 
 * 读取配置文件
 */
import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @author advance
 * 
 */
public class ConfigHelper {
	public ConfigHelper() {
	}

	/**
	 * 读取配置文件
	 * 
	 * @param strfile
	 * @return
	 */
	public static Configuration getConfig(BaseConfiguration configuration) {
		CompositeConfiguration config = new CompositeConfiguration();
		config.addConfiguration(configuration);
		return config;
	}

	/**
	 * 读取Properties配置文件
	 * 
	 * @param strFileName
	 * @return
	 * @throws ConfigurationException
	 */
	public static Configuration getPropertiesConfig(String strFileName) throws ConfigurationException {
		BaseConfiguration configuration = new PropertiesConfiguration(strFileName);
		return getConfig(configuration);
	}

}
