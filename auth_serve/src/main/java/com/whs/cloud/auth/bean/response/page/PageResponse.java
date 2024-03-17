package com.whs.cloud.auth.bean.response.page;

import java.util.List;
import java.util.StringJoiner;

public class  PageResponse<T> {

    private List<T> recorders;

    private Long current;

    private Long total;

    private Long size;

    public List<T> getRecorders() {
        return recorders;
    }

    public PageResponse<T> setRecorders(List<T> recorders) {
        this.recorders = recorders;
        return this;
    }

    public Long getCurrent() {
        return current;
    }

    public PageResponse<T> setCurrent(Long current) {
        this.current = current;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public PageResponse<T> setTotal(Long total) {
        this.total = total;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public PageResponse<T> setSize(Long size) {
        this.size = size;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PageResponse.class.getSimpleName() + "[", "]")
                .add("recorders=" + recorders)
                .add("current=" + current)
                .add("total=" + total)
                .add("size=" + size)
                .toString();
    }
}
