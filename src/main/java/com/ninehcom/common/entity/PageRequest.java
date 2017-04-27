/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ninehcom.common.entity;
import org.springframework.data.domain.Sort.Direction;

/**
 * 分页查询对象
 *
 * @author shenjizhe
 */
public class PageRequest implements IPageRequest {

    public final int MIN_SIZE = 1;
    public final int DEFAULT_SIZE = 20;

    public final int MIN_NUMBER = 1;
    public final int DEFAULT_NUMBER = 1;

    public final boolean DEFAULT_NEED_PAGE = true;
    public final Direction DEFAULT_DIRECTION = Direction.DESC;
    public final int DEFAULT_INDEX = 0;

    private int pageSize;
    private int pageNo;
    private int index;
    private boolean needPage;
    private Direction direction;

    public PageRequest() {
        pageSize = DEFAULT_SIZE;
        pageNo = DEFAULT_NUMBER;
        index = DEFAULT_INDEX;
        needPage = DEFAULT_NEED_PAGE;
        direction = DEFAULT_DIRECTION;
    }

    public PageRequest(int pageSize, int pageNo, int index, boolean needPage, Direction direction) {
        setPageSize(pageSize);
        setPageNo(pageNo);
        setIndex(index);
        this.needPage = needPage;
        this.direction = direction;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        if (pageSize < MIN_SIZE) {
            pageSize = MIN_SIZE;
        }
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        if (pageSize < MIN_SIZE) {
            this.pageSize = MIN_SIZE;
        }
        this.pageSize = pageSize;
    }

    /**
     * @return the pageNo
     */
    public int getPageNo() {
        if (pageNo < MIN_NUMBER) {
            pageNo = MIN_NUMBER;
        }
        return pageNo;
    }

    /**
     * @param pageNo the pageNo to set
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        if (pageNo < MIN_NUMBER) {
            this.pageNo = MIN_NUMBER;
        }
        this.pageNo = pageNo;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
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
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * @return the offset
     */
    public int getOffset() {
        return pageSize * (pageNo - 1);
    }
}
