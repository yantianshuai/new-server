package com.ninehcom.common.untils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2016/11/4.
 */
public class HttpUtil {

    /**
     * 包裹post方法，以String形式返出
     * @param url   请求地址
     * @param content   参数
     * @param charset   编码
     * @return
     * @throws IOException
     */
    public static String postAsString(String url , String content , String charset) throws IOException {
        byte[] bytes = HttpUtil.post(url , content , charset);
        if(null == bytes){
            return null;
        }
        String result = new String(bytes,charset);
        return result;
    }

    /**
     * 包裹get方法，一String形式返回
     * @param url   请求地址
     * @param charset   参数
     * @param cookies
     * @return
     * @throws IOException
     */
    public static String getAsString(String url,String charset,String cookies) throws IOException {
        byte[] bytes = HttpUtil.get(url,charset,cookies);
        if(null == bytes){
            return null;
        }
        String result = new String(bytes,charset);
        return result;
    }

    /**
     * post方式请求服务器(http协议)
     * @param url 请求地址
     * @param content   参数
     * @param charset   编码
     * @return
     */
    public static byte[] post(String url,String content,String charset) throws IOException {
        URL console = new URL(url);
        URLConnection conn = console.openConnection();

        conn.setRequestProperty("accept","*/*");
        conn.setRequestProperty("connection","Keep-Alive");
        conn.setRequestProperty("user-agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.27 Safari/525.13");
        conn.setRequestProperty("Content-Type","application/json");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        if(content!=null){
            out.write(content.getBytes(charset));
        }
        // 刷新、关闭
        out.flush();
        out.close();
        try {
            InputStream is = conn.getInputStream();
            if (is != null) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                is.close();
                return outStream.toByteArray();
            }
        } catch (Exception ex) {
            throw ex;
        }
        return null;
    }

    /**
     * get方式请求服务器（http协议）返回byte[]
     * @param url
     * @param charset
     * @param cookies
     * @return
     * @throws IOException
     */
    public static byte[] get(String url,String charset , String cookies) throws IOException {
        URL console = new URL(url);
        URLConnection conn  = console.openConnection();
        conn.setRequestProperty("Cookie",cookies);
        conn.connect();
        InputStream is = conn.getInputStream();
        if(is != null){
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while((len = is.read(buffer))!=-1){
                outStream.write(buffer,0,len);
            }
            is.close();
            return outStream.toByteArray();
        }
        return null;
    }
}
