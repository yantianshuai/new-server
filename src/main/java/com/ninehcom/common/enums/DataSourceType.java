/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.common.enums;

/**
 * 数据源操作类型 和 对应名字的枚举
 */
public enum DataSourceType {
    shenhuaDB("shenhuaDB", "ShenHuaDB"),
    taidaDB("taidaDB", "TaiDaDB"),
    guoanDB("guoanDB","GuoAnDB");

    private String type;
    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
