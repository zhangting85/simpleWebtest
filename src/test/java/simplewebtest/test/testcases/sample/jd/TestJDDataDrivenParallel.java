package simplewebtest.test.testcases.sample.jd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import simplewebtest.core.TestCase;
import simplewebtest.core.page.sample.jd.JDHomepage;

public class TestJDDataDrivenParallel extends TestCase {

	
	
	
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
	 * 用了DataProvider并且是并行的。但是这个是运行不通过的。因为WebDriver不是线程安全的。
	 * 稍后我会再修复这个。。。暂时先这样吧。
	 * 
	 * 2014-4-20：更新了DriverManager，现在这个框架里的WebDriver是线程安全的了，所以这个脚本就可以并行执行4个测试数据了。
	 * 详见DriverManager类
	 * 
	 */
	@DataProvider(name="product_to_search",parallel = true)
	public Iterator<Object[]> createData1() {
		//一个Array
		String[] astringarray={"巧克力","饼","糕","蛋"};
		//转成一个String的List
		List<String> lines=Arrays.asList(astringarray);
	     
		//一个Object的空的List
		List<Object[]> data = new ArrayList<Object[]>();
	       
		//往空List里放东西，然后返回
		for (String line :lines )
	        {
	            data.add(new Object[]{line});//加了一个FirefoxDriver进去哦
	        }
	        return data.iterator();

		}
	
}
