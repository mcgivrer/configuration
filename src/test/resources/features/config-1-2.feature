Feature: Reading multi-file

	Scenario Outline: 
	
	  Given multiple configuration files 
	  When I read <key> config
	  Then the <typed> value is <value>
	
	  Examples:
	    | key           | typed    | value                               |
	    | String.value  | String   | This is a configuration2 file value |
	    | Boolean.value | Boolean  | True                                |
	    | Integer.value | Integer  | 4567                                |
	    | Float.value   | Float    | 456.78f                             |
	    | Date.value    | Date     | 2023-03-23 23:23:23                 |
	    