package javacode;


public class singletontest {

	private singletontest()
	{
		
	}
	private class singletontesthelper{
		private static final singletontest test=new singletontest();
		
	}
	public singletontest getInstance()
	{
		System.out.println("Before returning Signletone object");
		return singletontesthelper.test;
	}
}
