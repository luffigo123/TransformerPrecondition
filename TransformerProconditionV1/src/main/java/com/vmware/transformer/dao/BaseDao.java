package com.vmware.transformer.dao;


public interface BaseDao {
	
	public void add(Object obj, String url);
	
	public void delete(String url);
	
	public void modify(Object obj,String url);
	
	public String query(String url);

}
