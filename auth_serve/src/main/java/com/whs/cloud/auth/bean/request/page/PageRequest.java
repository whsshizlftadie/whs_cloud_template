package com.whs.cloud.auth.bean.request.page;

public class PageRequest {

    private Integer size;

    private Integer current;

    public Integer getSize() {
        return size;
    }

    public PageRequest setSize(Integer size) {
        this.size = size;
        return this;
    }

    public Integer getCurrent() {
        return current;
    }

    public PageRequest setCurrent(Integer current) {
        this.current = current;
        return this;
    }
}
