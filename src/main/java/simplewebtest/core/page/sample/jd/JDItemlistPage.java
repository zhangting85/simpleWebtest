package simplewebtest.core.page.sample.jd;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import simplewebtest.core.Page;
/**
 *京东搜索商品结果页
 */
public class JDItemlistPage extends Page {
	
	
	/**
	 *先找所有商品的父亲节点plist
	 */
	@FindBy(id = "plist")
	public WebElement productList;
	
	/**
	 *直接找第一个商品，XPATH表达式过长，无法阅读。（你会看得头疼吗？我会。。。）
	 *注意这个xpath是由firepath自动生成的，冗余过度。如果你要用xpath，一定要会自己写
	 *插件太傻，别依赖他。
	 */
	@FindBy(xpath = ".//*[@id='plist']/ul/li[1]/div/div[2]/a")
	public WebElement firstproduct;
	
	/**
	 *预先定位所有product
	 *get all products, suggested to use this way
	 */
	@FindBy(xpath = ".//*[@id='plist']//li")
	public List<WebElement> products;
	
	/**
	 *先找父亲plist，让父亲来找儿子，这种写法也是可以的，但是也不是特别好（这一定不是强迫症）
	 *但是这个方法只能找第一个商品，想找第二个商品要再写一个方法。不推荐。	 
	 */
	public String getFirstProductName() {
		return productList.findElement(By.xpath("//div[@class='p-name'][1]//a")).getText();
	}
	
	/**
	 *先找父亲plist，让父亲来找儿子，但是加了一个传入参数告诉父亲要找第几个儿子，也就是第几个商品。（圣斗士吗，这么多儿子）
	 *这样我写一次可以找到这个页面上任意一个商品了，京东的网页设计特别适合自动化，可能你要测的网站不是这么工整。
	 *这里的重点是：Xpath表达式是一个字符串，你可以随意拼接。所以传入参数number可以插进去。
	 *suggested
	 */
	public String getProductNameByIndexMethodOne(int number) {
		return productList.findElement(By.xpath("//div[@class='p-name']["+number+"]//a")).getText();
	}

	/**
	 *一次性找出所有product，然后取第几个，我喜欢从1开始所以number-1，仅个人喜好。
	 *接着对找到的product执行getProductNameOf方法来获取名字
	 *suggested
	 */
	public String getProductNameByIndexMethodTwo(int number) {	
		return getProductNameOf(products.get(number-1));
	}
	
	private String getProductNameOf(WebElement product)
	{
		return product.findElement(By.className("p-name")).getText();
		
	}
	
	
}
