package com.vmware.transformer.utils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpReq {
	
	private CloseableHttpClient httpclient = null;
	private String vsmIP = null;
	private String vsmUsername = null;
	private String vsmPassword = null;
	
	//2015-12-10 add | X-XSRF-TOKEN
	private String XSRF_TOKEN = "";
	private ArrayList<String> JSESSIONIDList = null ;
	
	public HttpReq(){
		httpclient = AvoidSSLTrust.generateClient();
		vsmIP = DefaultEnvironment.VSMIP;
		vsmUsername = DefaultEnvironment.VSMUserName;
		vsmPassword = DefaultEnvironment.VSMPassword;
	}
	/**
	 * login nsx manager
	 * @param httpclient
	 */
	public void login(){
		HttpPost httpPost = new HttpPost("https://" + this.vsmIP + "/j_spring_security_check");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("j_username",vsmUsername));
		params.add(new BasicNameValuePair("j_password",vsmPassword));

		UrlEncodedFormEntity postEntity = null;;
		try {
			postEntity = new UrlEncodedFormEntity(params, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		httpPost.setEntity(postEntity);
System.out.println("request line------------" + httpPost.getRequestLine());

			try {
				HttpResponse httpResponse = httpclient.execute(httpPost);
//2015-12-10 get the X-XSRF-TOKEN
				XSRF_TOKEN = this.getToken(httpResponse);
//2015-12-21 set jsessionID list
				JSESSIONIDList =  this.getJSESSIONIDList(httpResponse);
				
				this.printResponse(httpResponse);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * Http get request
	 * @param url
	 * @return
	 */
	public String sendGetRequest(String url){
		this.login();
		HttpGet httpget = new HttpGet(url);
//add X-XSRF-TOKEN to the header
httpget.setHeader("X-XSRF-TOKEN", XSRF_TOKEN);
for(String jseesionId : JSESSIONIDList){
	httpget.setHeader("JSESSIONID",jseesionId);
}

        System.out.println("Executing request " + httpget.getRequestLine());
        CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpget);

		} catch (IOException e) {
			e.printStackTrace();
		}
System.out.println("----------------------------------------");
System.out.println(response.getStatusLine());
        HttpEntity entity = response.getEntity();
        String queryInfo = null;
		try {
//			queryInfo = EntityUtils.toString(entity);
//Fei edit on 2016-10-10
			queryInfo = EntityUtils.toString(entity,"UTF-8");
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
System.out.println(queryInfo);
        return queryInfo;
	}
	
	
	/**
	 * Send Http Post request
	 * @param content
	 * @param url
	 * @param headers
	 */
	@SuppressWarnings("rawtypes")
	public void sendPostRequest(String content, String url, HashMap<String,String> headers){
		this.login();
		StringEntity stringEntity = new StringEntity(content,"UTF-8");
		HttpPost httpPost = new HttpPost(url);
//add X-XSRF-TOKEN to the header
httpPost.setHeader("X-XSRF-TOKEN", XSRF_TOKEN);	
for(String jseesionId : JSESSIONIDList){
	httpPost.setHeader("JSESSIONID",jseesionId);
}
		Iterator it = headers.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry me = (Map.Entry)it.next();
			httpPost.setHeader(me.getKey().toString(), me.getValue().toString());
		}
		httpPost.setEntity(stringEntity);
	
System.out.println("request line------------" + httpPost.getRequestLine());
		
		try {
			HttpResponse httpResponse = httpclient.execute(httpPost);


this.printResponse(httpResponse);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Send Http delete request
	 * @param url
	 */
	public void sendDeleteRequest(String url){
		this.login();
		HttpDelete httpdel = new HttpDelete(url);
//add X-XSRF-TOKEN to the header
httpdel.setHeader("X-XSRF-TOKEN", XSRF_TOKEN);
for(String jseesionId : JSESSIONIDList){
	httpdel.setHeader("JSESSIONID",jseesionId);
}
        System.out.println("Executing request " + httpdel.getRequestLine());
        CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = httpclient.execute(httpdel);

this.printResponse(httpResponse);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Send Http Put request
	 * @param content
	 * @param url
	 * @param headers
	 */
	@SuppressWarnings("rawtypes")
	public void sendPutRequest(String content, String url, HashMap<String,String> headers){
		this.login();
		StringEntity stringEntity = new StringEntity(content,"UTF-8");
		HttpPut httpPut = new HttpPut(url);
//add X-XSRF-TOKEN to the header
httpPut.setHeader("X-XSRF-TOKEN", XSRF_TOKEN);
for(String jseesionId : JSESSIONIDList){
	httpPut.setHeader("JSESSIONID",jseesionId);
}
		Iterator it = headers.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry me = (Map.Entry)it.next();
			httpPut.setHeader(me.getKey().toString(), me.getValue().toString());
		}
		httpPut.setEntity(stringEntity);
		
System.out.println("request line------------" + httpPut.getRequestLine());
		
		try {
			HttpResponse httpResponse = httpclient.execute(httpPut);
this.printResponse(httpResponse);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * print response information
	 * @param httpResponse
	 * @throws ParseException
	 * @throws IOException
	 */
	 public void printResponse(HttpResponse httpResponse) throws ParseException, IOException{
		    // 获取响应消息实体
		    HttpEntity entity = httpResponse.getEntity();
		    // 响应状态
		    System.out.println("status:" + httpResponse.getStatusLine());
		    System.out.println("headers:");
		    HeaderIterator iterator = httpResponse.headerIterator();
		    while (iterator.hasNext()) {
		      System.out.println("\t" + iterator.next());
		    }
		    	    
		    // 判断响应实体是否为空
		    if (entity != null) {
		      String responseString = EntityUtils.toString(entity);
		      System.out.println("response length:" + responseString.length());
		      System.out.println("response content:"
		          + responseString.replace("\r\n", ""));
		    }
	}
	 
	 
	 public String getToken(HttpResponse httpResponse){
		 String token = "";
		 // 响应状态
		 System.out.println("status:" + httpResponse.getStatusLine());
		 System.out.println("headers:");
		  
		 HeaderIterator headers = httpResponse.headerIterator();
		 String set_Cookie = "";
		 while(headers.hasNext()){
			 Header header =  headers.nextHeader();
			 if(header.getName().equalsIgnoreCase("Set-Cookie")){
System.out.println(header.getValue());
				set_Cookie = header.getValue();
				if(set_Cookie.contains("XSRF-TOKEN")){
					token = set_Cookie.substring("XSRF-TOKEN=".length(),set_Cookie.indexOf(";"));
					return token;
				}else{
					
				}
			 }else{
			 }
		 }
		 return token;
	 }
	
	 public ArrayList<String> getJSESSIONIDList(HttpResponse httpResponse){
		 ArrayList<String> jsessionIdList = new ArrayList<String>();
		 String jsessionId = "";
		 // 响应状态
		 System.out.println("status:" + httpResponse.getStatusLine());
		 System.out.println("headers:");
		  
		 HeaderIterator headers = httpResponse.headerIterator();
		 String set_Cookie = "";
		 while(headers.hasNext()){
			 Header header =  headers.nextHeader();
			 if(header.getName().equalsIgnoreCase("Set-Cookie")){
System.out.println(header.getValue());
				set_Cookie = header.getValue();
				if(set_Cookie.contains("JSESSIONID")){
					jsessionId = set_Cookie.substring("JSESSIONID=".length(),set_Cookie.indexOf(";"));
					jsessionIdList.add(jsessionId);
				}else{
					
				}
			 }else{
			 }
		 }
		 return jsessionIdList;
	 }
	 

}
