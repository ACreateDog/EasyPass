package com.easypass.Controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

//import io.goeasy.GoEasy;
import net.sf.json.JSONObject;

public class NotifiToOtherController {
	public static void main(String[] args) throws Exception {
        JSONObject jsonObject = MsgMutally.send("我是王飞");
		
        System.out.println(jsonObject);
	}
	public static JSONObject send(String content) {
		JSONObject object = new JSONObject();
		try {
			object =  MsgMutally.send(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
}
class MsgMutally{
	protected static  String appkey =  "edcd2e5d-284b-47fa-a08e-3deabedaff1e";
	private  static String  channel = "my_channel";
	private static String path = "https://goeasy.io/goeasy/publish";
	
	public static JSONObject send(String content) throws Exception {
		JSONObject object = null;
		String params = "appkey="+appkey+"&channel="+channel+"&content="+content;
		object = JSONObject.fromObject(HttpClient.post(path, params));
		
		return object;
	}
	public static JSONObject send(String content,String snum) throws Exception {
		JSONObject object = null;
		
		String params = "appkey="+appkey+"&channel="+channel+"&content="+content+"/"+snum;
		object = JSONObject.fromObject(HttpClient.post(path, params));
		
		return object;
	}
}
class HttpClient {
    //发送一个GET请求
    public static String get(String path) throws Exception{
        HttpURLConnection httpConn=null;
        BufferedReader in=null;
        try {
            URL url=new URL(path);
            httpConn=(HttpURLConnection)url.openConnection();
             
            //读取响应
            if(httpConn.getResponseCode()==HttpURLConnection.HTTP_OK){
                StringBuffer content=new StringBuffer();
                String tempStr="";
                in=new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                while((tempStr=in.readLine())!=null){
                    content.append(tempStr);
                }
                return content.toString();
            }else{
                throw new Exception("请求出现了问题!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            in.close();
            httpConn.disconnect();
        }
        return null;
    }
    //发送一个GET请求,参数形式key1=value1&key2=value2...
    public static String post(String path,String params) throws Exception{
        HttpURLConnection httpConn=null;
        BufferedReader in=null;
        PrintWriter out=null;
        try {
            URL url=new URL(path);
            httpConn=(HttpURLConnection)url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
             
            //发送post请求参数
            out=new PrintWriter(httpConn.getOutputStream());
            out.println(params);
            out.flush();
             
            //读取响应
            if(httpConn.getResponseCode()==HttpURLConnection.HTTP_OK){
                StringBuffer content=new StringBuffer();
                String tempStr="";
                in=new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                while((tempStr=in.readLine())!=null){
                    content.append(tempStr);
                }
                return content.toString();
            }else{
                throw new Exception("请求出现了问题!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            in.close();
            out.close();
            httpConn.disconnect();
        }
        return null;
    }
}
