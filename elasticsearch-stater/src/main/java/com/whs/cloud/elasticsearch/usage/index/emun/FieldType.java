package com.whs.cloud.elasticsearch.usage.index.emun;

public enum FieldType {
    TEXT("text"),KEYWORD("keyword"),INTEGER("integer"),DATE("date")
    ,FLOAT("float"),DOUBLE("double");

    private String value;

    FieldType(String value) {
        this.value = value;
    }
}
