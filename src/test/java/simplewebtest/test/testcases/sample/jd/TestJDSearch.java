package simplewebtest.test.testcases.sample.jd;

import org.testng.annotations.Test;

import simplewebtest.core.TestCase;
import simplewebtest.core.page.sample.jd.JDHomepage;
import simplewebtest.core.page.sample.jd.JDItemlistPage;

public class TestJDSearch extends TestCase {

	/**
	 * JD首页上搜索一个商品 主要介绍定位某个商品名称的N种写法
	 */
	@Test
	public void searchProduct()  {
		log.info("这是测试方法里的第一句打印的log");
			JDHomepage home = new JDHomepage();

		
		//结果页面the expected result page 
		JDItemlistPage resultPage=home.init().searchHeader.search("巧克力");
		//actual result: 用四种方法找出第一个商品名字，作为实际结果.（回字有五种写法:P）
	
		String product_1= resultPage.firstproduct.getText();//不推荐，但偶尔有适用场景
		String product_2= resultPage.getFirstProductName();//不推荐，但偶尔有适用场景
		String product_3= resultPage.getProductNameByIndexMethodOne(1);//推荐写法，但你方法名字不要这么长
		String product_4= resultPage.getProductNameByIndexMethodTwo(1);//推荐写法，但你方法名字不要这么长
		
		log.info("第一个商品名字（用第一种取法）= "+product_1);
		log.info("第一个商品名字（用第二种取法）= "+product_2);
		log.info("第一个商品名字（用第三种取法）= "+product_3);
		log.info("第一个商品名字（用第四种取法）= "+product_4);
		
		//不加asseriton你的case永远是pass
		assert(product_1.contains("巧克力"));
		assert(product_1.equals(product_2));
		assert(product_1.equals(product_3));
		assert(product_1.equals(product_4));

		log.info("这是测试方法里的最后一句打印的log");
	}

}
