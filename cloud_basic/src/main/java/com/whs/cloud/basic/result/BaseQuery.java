package com.whs.cloud.basic.result;



import java.io.Serializable;

public abstract class BaseQuery implements Serializable {

    @QueryField
    private String id;

    @QueryField(operator = OperatorEnum.GE, fieldName = "create_time")
    private String createTimeL;

    @QueryField(operator = OperatorEnum.LE, fieldName = "create_time")
    private String createTimeR;

    private long size = 10;

    private long current = 1;

    private String orders = "create_time:desc";

    public String getId() {
        return id;
    }

    public BaseQuery setId(String id) {
        this.id = id;
        return this;
    }

    public String getCreateTimeL() {
        return createTimeL;
    }

    public BaseQuery setCreateTimeL(String createTimeL) {
        this.createTimeL = createTimeL;
        return this;
    }

    public String getCreateTimeR() {
        return createTimeR;
    }

    public BaseQuery setCreateTimeR(String createTimeR) {
        this.createTimeR = createTimeR;
        return this;
    }

    public long getSize() {
        return size;
    }

    public BaseQuery setSize(long size) {
        this.size = size;
        return this;
    }

    public long getCurrent() {
        return current;
    }

    public BaseQuery setCurrent(long current) {
        this.current = current;
        return this;
    }

    public String getOrders() {
        return orders;
    }

    public BaseQuery setOrders(String orders) {
        this.orders = orders;
        return this;
    }
}