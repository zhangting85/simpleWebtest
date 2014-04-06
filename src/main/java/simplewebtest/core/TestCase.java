package simplewebtest.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	 * 这里DriverManager会在每个TestCase被新建的时候跟着一起新建。同一个case只有一个manager，所以是static
	 * 而且以后我们就可以直接TestCase.manager来调用他了
	 * shares the same driver manager
	 */
	public static DriverManager manager=new DriverManager();
	/**
	 * 打log用的对象。
	 * print log
	 */
	protected Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * 决定这个TestCase是用什么浏览器的driver来执行。
	 * 由于设置了BeforeMethod标签，这个方法将由TestNG在每个TestMethod被执行前调用。
	 * 他将接收一个从TestNG的xml文件传入的参数表示浏览器种类。
	 * 告诉manager我要新建及保存的driver的类型。
	 * runs by testNG, it will be run before every test method to decide the driver type 
	 * @param browser：从testng的xml文件传入的浏览器名称。 默认值为firefox
	 */
	@BeforeMethod(alwaysRun=true)
	@Parameters("brwoser")
	protected void testMethodStart(@Optional("firefox") String browser){
	manager.setDriver(browser);
	}
	
	/**
	 * 在一个测试方法结束时再打一遍名字关闭driver，这部分可以根据需要调整
	 * runs by testNG, it will be run after every test method to close the driver
	 */
	@AfterMethod(alwaysRun=true)
	protected void testMethodEnd(){
	manager.getDriver().quit();
	}
	
	/**
	 * 打印类名。建议一个CASE只放一个方法。
	 * Print Class Name
	 */
	@BeforeClass(alwaysRun=true)
	protected void testCaseStart(){
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
	
}
