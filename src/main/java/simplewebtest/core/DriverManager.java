package simplewebtest.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DriverManager {
	/**
	 * 每个DriverManager只管理一个driver，所以他是static的 
	 * shares the same web driver
	 */
	private static WebDriver driver;
	/**
	 * 根据TestCase的要求来新建一个driver并保存起来
	 * crate and saves the driver according to the browser type
	 */
	public void setDriver(String browser){
		if (browser.equals("firefox")){

			 driver = new EventFiringWebDriver(new FirefoxDriver()).register(new LogEventListener());
		}
		//有需求的同学自己在这里添加IE等浏览器的支持
		//you can add ie/chrome or other driver here
	}
	
	/**
	 * 取刚才setDriver时存入的driver
	 * get the driver
	 */
	public WebDriver getDriver(){
		return driver;
	}
	
	/**
	 * 关浏览器，Windows上需要在这里杀进程的步骤
	 * quit the driver
	 */
	public void quitDriver(){
		getDriver().quit();
	}

}
