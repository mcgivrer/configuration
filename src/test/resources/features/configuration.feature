Feature: load configuration
	
	The configuration file is read by the configuraiton object and serve value from helpers.
	
	Scenario: Read String from default configuration
		Given a default configuration object
		When I get the 'String.value'
		Then string value is 'My new configuration String'		
	
	Scenario: Read Boolean from default configuration
		Given a default configuration object
		When I get the 'Boolean.value'
		Then boolean value is 'true'

	Scenario: Read Integer from default configuration
		Given a default configuration object
		When I get the 'Integer.value'
		Then integer value is 'true'
		 
	Scenario: Read Float from default configuration
		Given a default configuration object
		When I get the 'Float.value'
		Then float value is '1234.56f'
		 
	Scenario: Read Date from default configuration
		Given a default configuration object
		When I get the 'Date.value'
		Then date value is '2019-05-22 23:00:00'	 