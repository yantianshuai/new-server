package com.ninehcom.common.enums;



/**
 * Created by Administrator on 2016/11/11.
 */
public enum DataSourceType {
    gaDataSource("gaDataSource", "国安数据库"),
    tdDataSource("tdDataSource", "泰达数据库"),
    shDataSource("shDataSource", "申花数据库");
    private String type;
    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }


    public Object getType() {
        return type;
    }
}
