package simplewebtest.core.page.module.sample.jd;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import simplewebtest.core.Page;
import simplewebtest.core.page.sample.jd.JDItemlistPage;


/**
 * 页面模块。此处表示京东各页面上方共享的搜索条
 * 他本身也可以看做是一个页面
 * 并以组合（composite）的形式嵌入外部网页，注意不要滥用继承
 * this page module is composite to the outer page
 */
public class SearchHeaderModule extends Page {

	/**
	 * PageFactory的写法，用标签来定义web elment的查找 define how to find a webelment by
	 * annotation
	 */
	@FindBy(id = "key")
	WebElement searchInput;

	@FindBy(xpath = "//input[@value='搜索']")
	WebElement searchButton;

	/**
	 * 搜索一个关键字,先输入文字，再按搜索按钮 search a keyword
	 * 
	 * @param keyword
	 *            ：搜索关键字
	 * @return 返回一个JDItemlistPage
	 */
	public JDItemlistPage search(String keyword) {
		searchInput.clear();
		searchInput.sendKeys(keyword);
		searchButton.click();
		return new JDItemlistPage();
	}
}
