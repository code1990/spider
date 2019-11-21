package com.util;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author Administrator
 */
public class HttpClientUtil {


    /*   //发送 POST 请求
       String sr=httpServerTest.sendPost("http://10.92.130.191:9109/", "get/xxx");
       System.out.println(sr);*/
    //获取令牌
    public static String sendGet(String url, String authorization, String host) throws Exception {
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            URLConnection conn = getUrlConnection(url,authorization,host);
            // 建立实际的连接
            conn.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }  finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result.toString();
    }


    /**
     * 将参数拼接到url之后
     *
     * @return
     */
    public static String buildUrlByParam(String url, Map<String, String> params) throws IOException {

        if (params != null && params.size() > 0) {
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            String paramsStr = EntityUtils.toString(new UrlEncodedFormEntity(parameters, Consts.UTF_8));
            url += "?" + paramsStr;
        }

        return url;

    }

    /**
     * 发送http post 请求: 表单模式
     *
     * @param url
     * @param header
     * @param params
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, String> header, Map<String, String> params) throws Exception {

        HttpClient httpClient = new DefaultHttpClient();

        //设置请求头信息
        HttpPost httpost = new HttpPost(url);

        for (Map.Entry<String, String> entry : header.entrySet()) {
            httpost.addHeader(entry.getKey(), entry.getValue());

        }

        try {

            if (params != null && params.size() > 0) {
                List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters);
                httpost.setEntity(formEntity);
            }

            HttpResponse response = httpClient.execute(httpost);
            return getJsonStrFromHttpResponse(response);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            httpost.releaseConnection();
            httpost.abort();
        }

    }

    /**
     * 将httpResponse解析成json字符串
     *
     * @param response
     * @return
     */
    public static String getJsonStrFromHttpResponse(HttpResponse response) throws Exception {

        InputStream in = response.getEntity().getContent();

        StringBuilder resultJson = new StringBuilder();
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = in.read(buf)) != -1) {
            resultJson.append(new String(buf, 0, len));
        }
        return resultJson.toString();

    }

    public static String sendPost(String url, String authorization, String host, String transStartJson) throws Exception {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URLConnection conn =  getUrlConnection(url,authorization,host);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(transStartJson);
            System.out.print("transStartJson:" + transStartJson);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
            throw e;
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    private static URLConnection getUrlConnection(String url, String authorization, String host) throws IOException {
		URL realUrl = new URL(url);
		URLConnection conn = realUrl.openConnection();
		// 设置通用的请求属性
		conn.setRequestProperty("Authorization", authorization);
		conn.setRequestProperty("Host", host);
		conn.setRequestProperty("Accept", "*/*");
		conn.setRequestProperty("Content-Type", "application/json");
		return conn;
	}

}
