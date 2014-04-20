package simplewebtest.core;

import org.openqa.selenium.support.PageFactory;

import simplewebtest.core.TestCase.DriverManager;

public class Page {
	/**
	 * 构造方法，被所有Page的子类继承，所以每个页面都可以通过自动调用这个方法来初始化页面对象 
	 * it auto calls by all sub-page
	 */
	public Page() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}

}
