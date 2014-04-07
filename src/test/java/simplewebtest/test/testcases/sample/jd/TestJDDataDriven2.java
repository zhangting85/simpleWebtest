package simplewebtest.test.testcases.sample.jd;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import simplewebtest.core.TestCase;
import simplewebtest.core.page.sample.jd.JDHomepage;

public class TestJDDataDriven2 extends TestCase {

	
	
	
	/**
	 * JD首页上搜索一个商品 主要介绍定位某个商品名称的N种写法
	 * 这里用了DataProvider
	 * （不会用DataProvier不要说自己懂testNG）
	 */
	@Test(dataProvider="product_to_search")
	public void searchProduct(String keyword) throws InterruptedException {
		
		log.info("keyword="+keyword);
		String actual_procut_name= new JDHomepage().init().searchHeader.search(keyword).getProductNameByIndexMethodTwo(1);
		log.info("actual_procut_name="+actual_procut_name);
		assert(actual_procut_name.contains(keyword));
		log.info("--------------------------------");
	}

	/**
	 * 一个返回Object2维数组的DataProvider
	 */
	@DataProvider(name="product_to_search")
	public Object[][] createData1() {
		 return new Object[][] {
		   { "巧克力" },
		   { "饼"},
		   { "核桃"},
		 };
		}
	
}
