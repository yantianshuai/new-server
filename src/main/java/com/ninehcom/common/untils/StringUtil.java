package com.ninehcom.common.untils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

public class StringUtil {

    private final static Log logger = LogFactory.getLog(StringUtil.class);

    /**
     * 检测是否为空 会忽略掉空格
     * @param src
     * @return
     */
    public static boolean isEmpty(String src) {
        if (null == src) {
            return true;
        }
        if (src.trim().equals("")) {
            return true;
        }
        return false;
    }

    /**
     * 生成数据文件
     *
     * @param filePath
     *            写入文件的路径
     * @param content
     *            写入的字符串内容
     * @return
     * @return
     * @throws Exception
     */
    public static  void string2File(String content, String filePath) throws Exception {
        try{
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8");
            out.write(content);
            out.flush();
            out.close();
        }catch(Exception e){
            throw e;
        }
    }

    /**
     * 读取数据文件
     *
     * @param filePath
     *            读取的文件路径
     * @param encoding
     *            读取后的字符串编码集设置
     * @return
     */
    public static String file2String(String filePath, String encoding) {
        StringWriter writer = new StringWriter();
        File file = new File(filePath);
        InputStreamReader reader = null;
        try {
            if (encoding == null || "".equals(encoding.trim())) {
                reader = new InputStreamReader(new FileInputStream(file));
            } else {
                reader = new InputStreamReader(new FileInputStream(file),
                        encoding);
            }
            // 将输入流写入输出流
            char[] buffer = new char[1024];
            int n = 0;
            while (-1 != (n = reader.read(buffer))) {
                writer.write(buffer, 0, n);
            }
        } catch (Exception e) {
            logger.error(e);
            return null;
        } finally {
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error(e);
                }
        }
        // 返回转换结果
        if (writer != null) {
            return writer.toString();
        } else {
            return null;
        }
    }

}
