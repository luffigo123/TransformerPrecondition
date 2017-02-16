package com.vmware.transformer.utils;

public class ThreadSharedData {
	public String value = null;
	public String deltaValue = null;
	public String markString = null;
	public boolean completed = false;

	private static ThreadSharedData threadSharedData = null;
	
	private ThreadSharedData() {
		super();
	}
	
	public static synchronized ThreadSharedData getInstance(){
		if(threadSharedData == null){
			threadSharedData = new ThreadSharedData();
		}
		return threadSharedData;
	}
	
}
