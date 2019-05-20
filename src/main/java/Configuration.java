import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * This `Configuration` class load properties from
 * `res/configuration.properties` file. some provided helpers will retrieve:
 * 
 * - String value `Configuration.get(String key, String default)` - int value
 * `Configuration.getInteger(String key, int default)` - float value
 * `Configuration.getFloat(String key, float default)` - boolean value
 * `Configuration.getBoolean(String key, boolean default)` - Date value
 * `Configuration.getDate(String key, Date default)`
 *
 * @author McGivrer
 * @see https://gist.github.com/McGivrer
 * @see https://twitter.com/McGivrer
 */
public class Configuration {

	private static final String DEFAULT_CONFIGURATION_FILE_NAME = "/res/configuration.properties";
	private String filepath;
	private static final String UNKNOWN_CONFIG_KEY = "UNKNOWN_CONFIG_KEY";

	private static Configuration instance;

	private Properties props;

	/**
	 * Initialize configuration with the default file `configuration.properties`.
	 */
	public Configuration() {

		filepath = DEFAULT_CONFIGURATION_FILE_NAME;
		props = new Properties();
		load();
		instance = this;
	}

	/**
	 * Initialize configuration with the `newFilePath` file.
	 * 
	 * @param newFilePath the path of the properties file to be loaded.
	 */
	public Configuration(String newFilePath) {
		filepath = newFilePath;
		props = new Properties();
		load();
		instance = this;
	}

	/**
	 * Initialize configuration with the `newFilePath` file.
	 * 
	 * @param newFilePath the path of the properties file to be loaded.
	 */
	public Configuration(String[] newFilePaths) {
		props = new Properties();
		for(String newFilePath : newFilePaths) {
			filepath = newFilePath;
			load();
		}
		instance = this;
	}

	
	/**
	 * Load configuration from `filepath` file.
	 */
	private void load() {
		try {
			props.load(Configuration.class.getResourceAsStream(filepath));
		} catch (IOException e) {
			System.err.println("Unable to read configuration file");
			System.exit(-1);
		}
	}

	/**
	 * retrieve a value from configuraiton.properties file.
	 * 
	 * @param key key configuration to be retrieved.
	 * 
	 * @return String value
	 */
	private String getConfig(String key) {
		if (props.containsKey(key)) {
			return props.getProperty(key);
		} else {
			return UNKNOWN_CONFIG_KEY;
		}
	}

	/**
	 * retrieve a <code>int</code> value for configuration <code>key</code>.
	 * 
	 * @param key
	 * @return
	 */
	public static int getInteger(String key, int defaultValue) {
		String value = Configuration.instance.getConfig(key);
		if (value.equals(UNKNOWN_CONFIG_KEY)) {
			return defaultValue;
		}
		return Integer.parseInt(value);
	}

	/**
	 * retrieve a <code>float</code> value for configuration <code>key</code>.
	 * 
	 * @param key
	 * @return
	 */
	public static float getFloat(String key, float defaultValue) {
		String value = Configuration.instance.getConfig(key);
		if (value.equals(UNKNOWN_CONFIG_KEY)) {
			return defaultValue;
		}
		return Float.parseFloat(value);
	}

	/**
	 * retrieve a <code>String</code> value for configuration <code>key</code>.
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key, String defaultValue) {
		String value = Configuration.instance.getConfig(key);
		if (value.equals(UNKNOWN_CONFIG_KEY)) {
			return defaultValue;
		}
		return value;
	}

	/**
	 * retrieve a <code>Date</code> value for configuration <code>key</code>.
	 * 
	 * @param key
	 * @return
	 */
	public static Date getDate(String key, Date defaultValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("y-M-d h:m:s");
		String value = Configuration.instance.getConfig(key);
		if (value.equals(UNKNOWN_CONFIG_KEY)) {
			return defaultValue;
		}
		try {
			return sdf.parse(value);
		} catch (ParseException e) {
			return defaultValue;
		}
	}

	/**
	 * retrieve a <code>Boolean</code> value for configuration <code>key</code>.
	 * 
	 * @param key
	 * @return
	 */
	public static Boolean getBoolean(String key, boolean defaultValue) {
		String value = Configuration.instance.getConfig(key);
		if (value.equals(UNKNOWN_CONFIG_KEY)) {
			return defaultValue;
		}
		return Boolean.parseBoolean(value);
	}

	/**
	 * request a configuration reload from file.
	 */
	public static void reload() {
		Configuration.instance.load();
	}

	public static void main(String[] argc) {

		Configuration cfg1 = new Configuration();
		int intValue = Configuration.getInteger("Integer.value", 1000);
		boolean boolValue = Configuration.getBoolean("Boolean.value", false);
		float floatValue = Configuration.getFloat("Float.value", 1000.0f);
		Date dateValue = Configuration.getDate("Date.value", new Date());
		String strValue = Configuration.get("String.value", "default value");

		System.out.println(
				String.format("\n--\nLoaded configuration from default file:\n--\nint:%d\nfloat:%f\nboolean:%b\nstring :%s\ndate:%s",
						intValue, floatValue, boolValue, strValue, dateValue.toString()));
		
		
		Configuration cfg2 = new Configuration("/res/custom.properties");
		String strCustomValue = Configuration.get("custom.value", "default custom value");

		System.out.println(
				String.format("\n--\nLoaded configuration from file:%s\n--\nstring :%s","/res/custom.properties",strCustomValue));

		String[] configFiles = new String[] {"/res/configuration-1.properties","/res/configuration-2.properties"};
		Configuration cfg3 = new Configuration(configFiles);
		int int1Value = Configuration.getInteger("Integer.value", 1000);
		boolean bool1Value = Configuration.getBoolean("Boolean.value", false);
		float float2Value = Configuration.getFloat("Float.value", 1000.0f);
		Date date2Value = Configuration.getDate("Date.value", new Date());
		String str2Value = Configuration.get("String.value", "default value");


		System.out.println(
				String.format("\n--\nLoaded configuration from files:[%s,%s]\n--\nint:%d\nfloat:%f\nboolean:%b\nstring :%s\ndate:%s",
						configFiles[0], configFiles[1],
						int1Value, float2Value, bool1Value, str2Value, date2Value.toString()));
		
	}

}
