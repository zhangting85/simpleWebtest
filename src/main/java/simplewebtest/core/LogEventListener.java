package simplewebtest.core;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import simplewebtest.core.TestCase.DriverManager;


/**
 * 用一个类扩展web driver自带的事件监听器，可以实现许多有趣的功能。
 * 比如自动log
 * a customer event listener
 */
public class LogEventListener implements WebDriverEventListener {
	private Log log = LogFactory.getLog(this.getClass());
	 	
	    private By lastFindBy;
	    private String originalValue;
	 
	    public void beforeNavigateTo(String url, WebDriver selenium){
	        log.info("WebDriver navigating to:'"+url+"'");
	    }
	 
	    public void beforeChangeValueOf(WebElement element, WebDriver selenium){
	        originalValue = element.getAttribute("value");
	    }
	 
	    public void afterChangeValueOf(WebElement element, WebDriver selenium){
	        log.info("WebDriver changing value in element found "+lastFindBy+" from '"+originalValue+"' to '"+element.getAttribute("value")+"'");
	    }
	 
	    public void beforeFindBy(By by, WebElement element, WebDriver selenium){
	    	lastFindBy = by;
	    }
	    //这个以后要改掉。这段不是很好用
	    public void onException(Throwable error, WebDriver selenium){
	        if (error.getClass().equals(NoSuchElementException.class)){
	            log.error("WebDriver error: Element not found "+lastFindBy);
	        } else {
	            log.error("WebDriver error:", error);
	        }
	    }
	 
	    public void beforeNavigateBack(WebDriver selenium){}
	    public void beforeNavigateForward(WebDriver selenium){}
	    public void beforeClickOn(WebElement element, WebDriver selenium){}
	    public void beforeScript(String script, WebDriver selenium){}
	    public void afterClickOn(WebElement element, WebDriver selenium){
	    	String locator=element.toString().split("-> ")[1];//为了使log更短
	    	log.info("WebDriver clicking on:'"+locator.substring(0, locator.length()-1)+"'");
	    }
	    public void afterFindBy(By by, WebElement element, WebDriver selenium){}
	    public void afterNavigateBack(WebDriver selenium){}
	    public void afterNavigateForward(WebDriver selenium){}
	    public void afterNavigateTo(String url, WebDriver selenium){}
	    public void afterScript(String script, WebDriver selenium){}
	 

	
}
