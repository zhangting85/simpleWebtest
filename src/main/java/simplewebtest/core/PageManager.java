package simplewebtest.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageManager {
	/**
	 * 每个PageManager都要负责记住当前这个页面的driver
	 * 其实这个driver是来自访问当前页面的TestCase
	 * it saves the driver which is from the test case who visit this page
	 */
	protected WebDriver driver;
	
	/**
	 * PageManager的主要功能，从TestCase的manager里取我们要用来打开当前页面的driver
	 * to get the driver
	 */
	public WebDriver getDriver(){	
			driver=TestCase.manager.getDriver();
			return driver;
	}
	
	/**
	 * PageManager的主要功能，调用selenium PageFactory初始化页面
	 * to init the page
	 */
	public void initPage(Page page){
		PageFactory.initElements(getDriver(), page);
	}
	

}
