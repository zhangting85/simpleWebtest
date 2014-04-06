package simplewebtest.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class TestWebdriverEnv {

	@Test
	public void checkEnv(){
		//首先打一个hello world来测试一你的IDE里testng的插件是否已经安装好
		//check if you success installed testng on your IDE
		System.out.println("Hello World, TestNG");
		
		//看看你电脑上能不能正确用firefox driver启动firefox
		//check if firefox driver runs successfully on your computer
		WebDriver driver=new FirefoxDriver();
		driver.get("https://github.com/zhangting85/simpleWebtest");
	}
	
	
	
}
