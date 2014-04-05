package simplewebtest.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	private Log log = LogFactory.getLog(this.getClass());
	/**
	 * 决定这个TestCase是用什么浏览器的driver来执行。
	 * 由于设置了BeforeMethod标签，这个方法将由TestNG在每个TestMethod被执行前调用。
	 * private使他不会被别人调用。
	 * 他将接收一个传入参数表示浏览器种类。
	 * 最后告诉manager我要新建及保存的driver的类型。
	 * runs by testNG, it will be run before every test method to decide the driver type 
	 * @param browser：从testng的xml文件传入的浏览器名称。 默认值为firefox
	 */
	@BeforeMethod(alwaysRun=true)
	@Parameters("brwoser")
	protected void setDriver(@Optional("firefox") String browser){
	//打印类名
	log.info("TestCase = "+ this.getClass().getSimpleName());
	manager.setDriver(browser);
	}

}
