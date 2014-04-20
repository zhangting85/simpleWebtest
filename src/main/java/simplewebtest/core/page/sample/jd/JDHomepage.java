package simplewebtest.core.page.sample.jd;

import simplewebtest.core.Page;
import simplewebtest.core.TestCase.DriverManager;
import simplewebtest.core.page.module.sample.jd.SearchHeaderModule;
/**
 *京东首页
 */
public class JDHomepage extends Page {
/**
 *URL常量，很少用到，一般在起始页用，有时放到配置文件里去统一管理
 */
private static final String URL="http://www.jd.com";

/**
 *可供重用的页面模块，作为成员对象在显示这个模块的页面中保存。
 *这里用了组合的写法（composite）,注意不要滥用继承。
 */
public SearchHeaderModule searchHeader=new SearchHeaderModule();

/**
 * 只有homepage之类的起始页才必要有这个init方法用来打开URL。
 * return this 表示执行完毕之后页面仍旧在本页。
 * 如果留在本页，并有页面刷新，就要return new JDHomepage
 * 如果没有页面刷新等页面改变，就return this
 * 如果跳转到其他页面，就return new xxxPage
 * 这样写的好处，是每个方法的return语句上明确了页面跳转的预期结果
 * Only the start page of a test case should has this init method
 * @return return this means no page refresh and stay on this page after this method
 * return new JDHomepage means stay on this page and has a page refresh
 * return new xxxPage means page redirects after this method
 */
public JDHomepage init(){
	DriverManager.getDriver().get(URL);
	return this;
}



}
