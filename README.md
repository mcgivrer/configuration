# README

The `Configuration` class is a very simple helper to retrieve configuration value from the `res/configuration.properties` file.

## API

The propose API contains the following  public helpers:

- `get(String key, String defaultValue)` to retrieve a String value. if not found, the default value is returned.
- `getInteger(String key, Integer defaultValue)` to retrieve an int value. if not found, the default value is returned.
- `getFloat(String key, Float defaultValue)` to retrieve an float value. if not found, the default value is returned.
- `getBoolean(String key, Boolean defaultValue)` to retrieve an boolean value. if not found, the default value is returned.
- `getDate(String key, Date defaultValue)` to retrieve an Date value. if not found, the default value is returned. the date format is "yyyy-mm-dd hh:mm:ss" (Java format is "y-M-d h:m:s").

## Test ?

To see resulting API access test, just execute the `Configuration#main(String[])` method.

```text
Loaded configuration from file:
--
int:1234
float:123,559998
boolean:true
string :My new configuration String
date:Wed May 22 23:00:00 CEST 2019
```