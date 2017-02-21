package com.vmware.transformer.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.vmware.transformer.dao.BaseDao;

@Component(value="service")
public class Service {
	
	private BaseDao dao;
	
	public Service() {
		super();
	}
	
	
	public Service(BaseDao dao) {
		super();
		this.dao = dao;
	}

	public void addObject(Object obj, String url){
		dao.add(obj, url);
	}
	
	public void deleteObject(String url){
		dao.delete(url);
	}
	
	public void modifyObject(Object obj, String url){
		dao.modify(obj, url);
	}
	
	public String queryObject(String url){
		return dao.query(url);
	}
	
	public BaseDao getDao() {
		return dao;
	}

	@Resource(name="dao")
	public void setDao(BaseDao dao) {
		this.dao = dao;
	}

}
