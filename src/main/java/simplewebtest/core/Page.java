package simplewebtest.core;

public class Page {
	/**
	 * 每个页面我都给他配备一个经理，负责创建页面和取操作页面的driver； each page has a manager 
	 */
	protected PageManager manager;

	/**
	 * 构造方法，被所有Page的子类继承，所以每个页面都可以通过自动调用这个方法来初始化页面对象 it auto calls by all
	 * sub-page
	 * 1.首先给这个页面创建一个manager
	 * 2.然后用PageFactory初始化这个页面
	 * 1.it will create a new manger for this page
	 * 2.it will call page factory's init method to init the page
	 * 
	 */
	public Page() {
		manager = new PageManager();
		manager.initPage(this);
	}

}
