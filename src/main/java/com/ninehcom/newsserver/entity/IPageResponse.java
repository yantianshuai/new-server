/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.newsserver.entity;

/**
 *
 * @author Administrator
 */
public interface IPageResponse {

    boolean isNeedPage();

    int getTotalItemNumber();

    int getTotalPageNumber();

    boolean isHasNext();

    boolean isHasPrev();

    int getPageNo();

    int getPrev();

    int getNext();

    int getPageSize();
}
