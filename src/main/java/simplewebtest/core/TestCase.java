package simplewebtest.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
/**
 * 所有TestNG TestCase都继承这个类。
 * 这个类的功能是让testNG可以设置他的driver类型
 * 以及由他的成员driver manager来操作这个case要用到的driver
 */
public class TestCase {

	/**
	 * 打log用的对象,this表示具体的子类。
	 * print log
	 */
	protected Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * 决定这个TestCase是用什么浏览器的driver来执行。
	 * 由于设置了BeforeMethod标签，这个方法将由TestNG在每个TestMethod被执行前调用。
	 * 他将接收一个从TestNG的xml文件传入的参数表示浏览器种类。
	 * 告诉manager我要新建的driver的类型。
	 * runs by testNG, it will be run before every test method to decide the driver type 
	 * @param browser：从testng的xml文件传入的浏览器名称。 默认值为firefox
	 */
	@BeforeMethod(alwaysRun=true)
	@Parameters("brwoser")
	protected void testMethodStart(@Optional("firefox") String browser){
	DriverManager.setDriver(browser);
	}
	
	/**
	 * 在一个测试方法结束时再打一遍名字关闭driver，这部分可以根据需要调整
	 * runs by testNG, it will be run after every test method to close the driver
	 */
	@AfterMethod(alwaysRun=true)
	protected void testMethodEnd(){
	DriverManager.quitDriver();;
	}
	
	/**
	 * 打印类名。建议一个CASE只放一个方法。
	 * Print Class Name
	 */
	@BeforeClass(alwaysRun=true)
	protected void testCaseStart(){
	//打印分隔符
	log.info("#####################################################");
	//打印类名
	log.info("\\/\\/\\/\\/\\/\\/---TestCase = "+ this.getClass().getSimpleName()+"---\\/\\/\\/\\/\\/\\/");
	}
	
	/**
	 * 再次打印类名
	 * Print Class Name again and separator
	 */
	@AfterClass(alwaysRun=true)
	protected void testCaseEnd(){
	//打印类名
	log.info("/\\/\\/\\/\\/\\/\\---TestCase = "+ this.getClass().getSimpleName()+"---/\\/\\/\\/\\/\\/\\");
	//打印分隔符
	log.info("#####################################################");
	}
	
	/**
	 * 静态内部类。因为把这些driver相关的东西直接放在TestCase类里，我感觉从逻辑上说不通。引入一个静态内部类来解决。
	 */
	public static class DriverManager {
		/**
		 * 每个DriverManager只管理一个driver，所以他是static的 
		 * shares the same web driver
		 */
		public static WebDriver driver;
		/**
		 * 根据TestCase的要求来新建一个driver并保存起来
		 * crate and saves the driver according to the browser type
		 */
		public static void setDriver(String browser){
			if (browser.equals("firefox")){
				 driver = new EventFiringWebDriver(new FirefoxDriver()).register(new LogEventListener());
			}
			//有需求的同学自己在这里添加IE等浏览器的支持
			//you can add ie/chrome or other driver here
		}		
		
		/**
		 * 关浏览器，Windows上需要在这里杀进程的步骤
		 * quit the driver
		 */
		public static void quitDriver(){
			driver.quit();
		}

	}
}
