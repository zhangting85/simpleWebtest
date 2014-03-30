package simplewebtest.test.potestcases;

import org.testng.annotations.Test;

import simplewebtest.core.TestCase;
import simplewebtest.core.page.sample.JDHomepage;

public class TestJDSearch extends TestCase {

/**
 *JD首页上搜索一个商品
 *未完待续。
 */
	@Test
	public void testaaa(){
		JDHomepage home=new JDHomepage();
		home.init().searchHeader.search("GitHub");
	}
	
}
