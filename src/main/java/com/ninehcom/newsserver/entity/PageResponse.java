/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.newsserver.entity;

import com.ninehcom.common.entity.IPageRequest;

import java.io.Serializable;
import java.util.List;

/**
 * 分页回应对象
 *
 * @author Administrator
 */
public class PageResponse<T> implements IPageResponse , Serializable {

    private boolean needPage;
    private int totalItemNumber;
    private int pageNo;
    private int pageSize;
    private List<T> list;

    public PageResponse() {
    }

    public PageResponse(int totalItemNumber, IPageRequest page, List<T> list) {
        this(page.isNeedPage(), totalItemNumber, page.getPageNo(), page.getPageSize(), list);
    }

    public PageResponse(boolean needPage, int totalItemNumber, int pageNo, int pageSize, List<T> list) {
        this.needPage = needPage;
        this.totalItemNumber = totalItemNumber;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.list = list;
    }

    /**
     * @return the needPage
     */
    public boolean isNeedPage() {
        return needPage;
    }

    /**
     * @param needPage the needPage to set
     */
    public void setNeedPage(boolean needPage) {
        this.needPage = needPage;
    }

    /**
     * @return the totalItemNumber
     */
    public int getTotalItemNumber() {
        return totalItemNumber;
    }

    /**
     * @param totalItemNumber the totalItemNumber to set
     */
    public void setTotalItemNumber(int totalItemNumber) {
        this.totalItemNumber = totalItemNumber;
    }

    /**
     * @return the pageNo
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * @param pageNo the pageNo to set
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public int getTotalPageNumber() {
        int add = (totalItemNumber % pageSize == 0) ? 0 : 1;
        return totalItemNumber / pageSize + add;
    }

    @Override
    public boolean isHasNext() {
        return pageNo < getTotalPageNumber();
    }

    @Override
    public boolean isHasPrev() {
        return pageNo > 1;
    }

    @Override
    public int getPrev() {
        if (isHasPrev()) {
            return pageNo - 1;
        } else {
            return 1;
        }
    }

    @Override
    public int getNext() {
        if (isHasNext()) {
            return pageNo + 1;
        } else {
            return getTotalPageNumber();
        }
    }

}
