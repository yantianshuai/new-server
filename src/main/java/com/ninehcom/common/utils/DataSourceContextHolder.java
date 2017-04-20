/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.common.utils;


import java.util.logging.Logger;

public class DataSourceContextHolder {

    //声明当前线程局部变量
    private static final ThreadLocal<String> local = new ThreadLocal<>();

    public static final Logger log = Logger.getLogger(DataSourceContextHolder.class.getName());

    //返回当前线程的局部变量
    public static ThreadLocal<String> getLocal(){
        return local;
    }

    public static void setDataSource(Object obj){
        local.set(String.valueOf(obj));
        log.info("---------正在配置数据源----------"+String.valueOf(obj));
    }

//    public static void shenhua(){
//        //设置当前线程局部变量的值
//        local.set(DataSourceType.shenhua.getType());
//    }
//
//    public static void taida(){
//        //设置当前线程局部变量的值
//        local.set(DataSourceType.taida.getType());
//    }
//
//    public static void guoan(){
//        //设置当前线程局部变量的值
//        local.set(DataSourceType.guoan.getType());
//    }

    public static String getJdbcType(){
        //返回当前线程数据源的名字
        return local.get();
    }
}
