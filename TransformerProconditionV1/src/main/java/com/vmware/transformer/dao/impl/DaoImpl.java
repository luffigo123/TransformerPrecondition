package com.vmware.transformer.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.vmware.transformer.dao.BaseDao;
import com.vmware.transformer.utils.HttpReqUtils;
import com.vmware.transformer.utils.JacksonUtils;

@Component(value="dao")
public class DaoImpl implements BaseDao {

	@Override
	public void add(Object obj, String url) {
		 HttpReqUtils httpreq = new HttpReqUtils();
//		 String content = ConvertJavaJson.convertToJsonByGson(obj);
		 JacksonUtils jacksonUtil = new JacksonUtils();
		 String content =jacksonUtil.toJson(obj);
		 
		 
		 HashMap<String,String> headers = new HashMap<String,String>();
		 headers.put("Content-Type", "application/json");
		 httpreq.sendPostRequest(content, url, headers);
	}

	@Override
	public void delete(String url) {
		 HttpReqUtils httpreq = new HttpReqUtils();
		 httpreq.sendDeleteRequest(url);

	}

	@Override
	public void modify(Object obj, String url) {
		 HttpReqUtils httpreq = new HttpReqUtils();
//		 String content = ConvertJavaJson.convertToJsonByGson(obj);
		 JacksonUtils jacksonUtil = new JacksonUtils();
		 String content =jacksonUtil.toJson(obj);
		 HashMap<String,String> headers = new HashMap<String,String>();
		 headers.put("Content-Type", "application/json"); 
		 httpreq.sendPutRequest(content, url, headers);
	}

	@Override
	public String query(String url) {
		 HttpReqUtils httpreq = new HttpReqUtils();
		 return httpreq.sendGetRequest(url);
	}

}
