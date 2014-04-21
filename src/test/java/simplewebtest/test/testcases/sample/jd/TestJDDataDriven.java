package simplewebtest.test.testcases.sample.jd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import simplewebtest.core.TestCase;
import simplewebtest.core.page.sample.jd.JDHomepage;

public class TestJDDataDriven extends TestCase {

	
	
	
	/**
	 * JD首页上搜索一个商品 主要介绍定位某个商品名称的N种写法
	 * 这里用了DataProvider
	 * （不会用DataProvier不要说自己懂testNG）
	 */
	@Test(dataProvider="product_to_search")
	public void searchProduct(String keyword) throws InterruptedException {
		
		log.info("keyword="+keyword);
		//方法链。方法链并不会导致调试困难，因为我是先写线性脚本后重构成这种脚本this is a method chain
		String actual_procut_name= new JDHomepage().init().searchHeader.search(keyword).getProductNameByIndexMethodTwo(1);
		log.info("actual_procut_name="+actual_procut_name);
		assert(actual_procut_name.contains(keyword));
		log.info("--------------------------------");
	}

	/**
	 * 一个返回iterator的的DataProvider
	 */
	@DataProvider(name="product_to_search")
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
	            data.add(new Object[]{line});
	        }
	        return data.iterator();

		}
	
}
