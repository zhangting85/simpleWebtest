package simplewebtest.core;

import java.util.concurrent.TimeUnit;

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
 * 这个类的功能是：
 * 1，让testNG可以设置测试执行的浏览器类型
 * 2，提供一个log变量可以在每个case里直接用
 * 3，在测试用例开始和结束时打log标明case名称
 * 4，在测试方法开始和结束时启动和销毁浏览器
 * 5，由内部嵌套类DriverManager来负责创建和分配具体的driver
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
	DriverManager.setupDriver(browser);
	}
	
	/**
	 * 在一个测试方法结束时关闭driver
	 * runs by testNG, it will be run after every test method to close the driver
	 */
	@AfterMethod(alwaysRun=true)
	protected void testMethodEnd(){
	DriverManager.quitDriver();
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
	 * 静态内部类。因为如果把这些driver相关的东西直接放在TestCase类里，从逻辑上说不通。引入一个静态内部类来解决。
	 */
	public static class DriverManager {
		/**
		 * 每个DriverManager只管理一个driver，所以他是static的，但是我引入ThreadLocal来处理多线程
		 * shares the same web driver and use thread local to handle the multi-thread
		 */
		public static ThreadLocal<WebDriver> ThreadDriver=new ThreadLocal<WebDriver>() ;
		/**
		 * 当TestCase要设置浏览器类型时其实只是设置了browserType这个字段。而真正的新建driver是在Page类需要用到driver时
		 * Page的构造方法里调用getDriver，然后getDriver检测发现当前线程没有driver时才会真正新建一个driver.
		 */
		public static String browserType;
		
		/**
		 * 如果当前进程没有绑定driver，创建一个然后绑定上，如果已经有了就直接返回
		 * create a driver for this thread if not exist. or return it directly
		 */
		public static WebDriver getDriver(){
			WebDriver driver= DriverManager.ThreadDriver.get();
			if (driver==null){
				if (browserType.equals("firefox")){
					 driver = new EventFiringWebDriver(new FirefoxDriver()).register(new LogEventListener());
					 ThreadDriver.set(driver);
					//找东西前等三秒wait 3 second for every find by
				    DriverManager.getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				}
				//有需求的同学自己在这里添加IE等浏览器的支持
				//you can add ie/chrome or other driver here
				}
			return driver;
		}
		
		
		/**
		 * 根据TestCase的要求来指定浏览器类型但不创建他
		 * save the browser type but not create it
		 * @param browser:浏览器名字
		 */
		public static void setupDriver(String browser){
			browserType=browser;

		}		
	
		/**
		 * 关浏览器，Windows上需要在这里杀进程的步骤
		 * quit the driver
		 */
		public static void quitDriver(){
			getDriver().quit();
			DriverManager.ThreadDriver.set(null);
		}

	}
}
