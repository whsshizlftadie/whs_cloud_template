package com.whs.cloud.elasticsearch.usage.document.search.page;

import java.util.List;
import java.util.StringJoiner;

public class ESPage<T> {

    private List<T> content;

    private Integer size;

    private Integer current;

    private Boolean isFirstPage;

    private Boolean isEndPage;

    private long total;

    public long getTotal() {
        return total;
    }

    public ESPage<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public List<T> getContent() {
        return content;
    }

    public ESPage<T> setContent(List<T> content) {
        this.content = content;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public ESPage<T> setSize(Integer size) {
        this.size = size;
        return this;
    }

    public Integer getCurrent() {
        return current;
    }

    public ESPage<T> setCurrent(Integer current) {
        this.current = current;
        return this;
    }

    public Boolean getFirstPage() {
        return isFirstPage;
    }

    public ESPage<T> setFirstPage(Boolean firstPage) {
        isFirstPage = firstPage;
        return this;
    }

    public Boolean getEndPage() {
        return isEndPage;
    }

    public ESPage<T> setEndPage(Boolean endPage) {
        isEndPage = endPage;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ESPage.class.getSimpleName() + "[", "]")
                .add("content=" + content)
                .add("size=" + size)
                .add("current=" + current)
                .add("isFirstPage=" + isFirstPage)
                .add("isEndPage=" + isEndPage)
                .add("total=" + total)
                .toString();
    }
}
