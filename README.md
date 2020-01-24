# WiproWeatherApp

Time taken for test
3 hours 30 minutes

About Code:
For API, Retrofit is used.

Main Activity is the launcher screen which is situated in package called common.
global package contains all the API and constants for the project
adapter package contains recyclerview adapter
helperClasses package contains all models required for serializing the JSON from API for retrofit
retrofitAPI package contains Interface and service builder class for retofit. 
The intetface is where all the URL extensions are provided.
service package has the class which was suppose to track and retrieve GPS co-ordinates if app ran on real devices.
settings package contains information about the settings page. 

Settings class is basically used to change the app settings, 
currently it has one toggle button to change the output of the weather in Imperial or Metric units as user desires.
Though no UI is made for displaying the weather which is retrievd when clicked on "Current Location", but the retireved information can be
seen as a JSON output in logcat or from the Run window which can be opened using Alt + 4. Different location units can be seen by
selecting imperial or metric unit.

On MainActivity there is a button called "Five Day Forecast", on clicking into it, user will see weather for a day with 
five different intervals (interval of every three hours.)
At the moment I am displaying Weather, temperature, wind speed, humidity, date 

I planned in making different section of different weather type, i.e for Wind, Rain and other stuff but was not able to do in given time
but I believe I will be able to finish that section in another hour or so.
For this functionality I used, com.aurelhubert.ahbottomnavigation library from git (https://github.com/aurelhubert/ahbottomnavigation)

If more time was permitted,
I would have focused more in proper UI, displaying of Current location weather, and make user select different city to know the weather.
I would also focus more in proper icons and more graphical way of doing it.
From the code side, I would focus more on abstaction of code and also loading or waiting messages for users making it more user friendly.



