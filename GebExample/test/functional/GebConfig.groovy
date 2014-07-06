/*
 This is the Geb configuration file.
 
 See: http://www.gebish.org/manual/current/configuration.html
*/

import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.chrome.ChromeDriver

driver = { new ChromeDriver() }

environments {
 
 // run as “grails -Dgeb.env=chrome test-app”
 // See: http://code.google.com/p/selenium/wiki/ChromeDriver
 chrome {
	 //Get Chrome Drive from http://chromedriver.storage.googleapis.com/index.html
	 //Then set driver here
	 System.setProperty('webdriver.chrome.driver', '/Users/Aaron/Downloads/chromedriver')
	 driver = { new ChromeDriver() }
 }
 
 // run as “grails -Dgeb.env=firefox test-app”
 // See: http://code.google.com/p/selenium/wiki/FirefoxDriver
 firefox {
	 driver = { new FirefoxDriver() }
 }

}