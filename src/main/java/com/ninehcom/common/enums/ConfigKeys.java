/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.common.enums;

/**
 *
 * @author Administrator
 */
public enum ConfigKeys {

    TeamId("TeamId"),
    HistoryCountLimit("HistoryCountLimit"),
    RollCount("RollCount"),
    HomeNewsCount("HomeNewsCount"),
    ReadTimes("ReadTimes"),
    NewsCommentNotify("NewsCommentNotify"),
    SensitiveWordURL("SensitiveWordURL")
    ;
    private String key;

    private ConfigKeys(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }

}
