package com.ninehcom.common.untils;

import javax.net.ssl.*;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by zhangbin on 2016/11/4.
 */
public class HttpsUtil {
    private static class TrustAnyTrustManager implements X509TrustManager{

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier{

        @Override
        public boolean verify(String hostname, SSLSession sslSession) {
            return true;
        }
    }

    /**
     * post方式请求服务器（https协议）
     * @param url   请求地址
     * @param content   参数
     * @param charset   编码
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws KeyManagementException
     */
    public static byte[] post(String url,String content , String charset) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());

        URL console = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
        conn.setSSLSocketFactory(sc.getSocketFactory());
        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
        conn.connect();
        DataOutputStream out = new DataOutputStream(conn.getOutputStream());
        out.write(content.getBytes(charset));
        // 刷新、关闭
        out.flush();
        out.close();
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
        return null;
    }

    /**
     * 包裹post 返回String(Https协议)
     * @param url
     * @param charset
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws KeyManagementException
     */
    public static String postAsString(String url,String content,String charset) throws NoSuchAlgorithmException, IOException, KeyManagementException {
        byte[] bytes = HttpsUtil.post(url,content,charset);
        if(null == bytes){
            return null;
        }
        String result = new String(bytes,charset);
        return result;
    }

    /**
     * get方式请求
     * @param url
     * @param charset
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     */
    public static byte[] get(String url, String charset)throws NoSuchAlgorithmException, KeyManagementException, IOException {
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());

        URL console = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
        conn.setSSLSocketFactory(sc.getSocketFactory());
        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
        conn.connect();
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
        return null;
    }

    /**
     * 包裹get，返回String(Https协议)
     * @return
     */
    public static String getAsString(String url, String charset) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        byte[] bytes = HttpsUtil.get(url, charset);
        if (null == bytes) {
            return null;
        }
        String result = new String(bytes, charset);
        return result;
    }
}
