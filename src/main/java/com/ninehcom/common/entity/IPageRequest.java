/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.common.entity;

import org.springframework.data.domain.Sort.Direction;

/**
 * 分页查询接口
 * @author Administrator
 */
public interface IPageRequest {
    int getPageNo();
    int getPageSize();
    Direction getDirection();
    boolean isNeedPage();
}
