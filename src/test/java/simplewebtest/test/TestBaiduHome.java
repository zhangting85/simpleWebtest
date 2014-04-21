package simplewebtest.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class TestBaiduHome {
	
	@Test
	public void searchSomething(){

		WebDriver driver=new FirefoxDriver();//打开Firefox； open firefox
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//设置最大等待时间为10秒，页面10秒内不加载成功即报超时。set waiting max is 10 seconds		
		driver.get("http://www.baidu.com");//打开百度open the url
		driver.findElement(By.id("kw1")).sendKeys("GitHub");//输入搜索关键字“GitHub"；input search keyword
		driver.findElement(By.id("su1")).click();//点击搜索按钮click the search button
						
		String aResult=driver.findElement(By.xpath(".//*[@id='4']/h3/a")).getText();//取第四条搜索结果的标题。 get the text of 4th search result
		assert aResult.toLowerCase().contains("github");//做断言 assertion
		driver.findElement(By.xpath(".//*[@id='4']/h3/a")).click();//打开第四个搜索结果。Open the 4th search result on baidu
		
		//获取所有窗口的handle，然后逐个切换，直到切换到最新窗口 switch to the new window
		for(String winHandle : driver.getWindowHandles()){     
            driver.switchTo().window(winHandle);     
            }     
		
		String aTitle=driver.getTitle();//取新窗口的title
		System.out.println("current widnow title is:"+aTitle);//打出来看看
		assert aTitle.toLowerCase().contains("github");//断言
		
	}
}
