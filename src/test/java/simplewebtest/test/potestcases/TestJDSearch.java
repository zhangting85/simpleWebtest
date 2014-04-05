package simplewebtest.test.potestcases;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import simplewebtest.core.TestCase;
import simplewebtest.core.page.sample.JDHomepage;
import simplewebtest.test.TestWebdriverEnv;

public class TestJDSearch extends TestCase {

	/**
	 * JD首页上搜索一个商品 未完待续。
	 */
	@Test
	public void testaaa() {

		Logger logger = Logger.getLogger(TestWebdriverEnv.class);
		logger.debug("start");
		JDHomepage home = new JDHomepage();
		home.init().searchHeader.search("GitHub");
	}

}
