package com.whs.cloud.elasticsearch.usage.index.definition;

import java.util.StringJoiner;

public class IndexDefinition {

    private String type;

    private String analysis;

    public String getType() {
        return type;
    }

    public IndexDefinition setType(String type) {
        this.type = type;
        return this;
    }

    public String getAnalysis() {
        return analysis;
    }

    public IndexDefinition setAnalysis(String analysis) {
        this.analysis = analysis;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", IndexDefinition.class.getSimpleName() + "[", "]")
                .add("type='" + type + "'")
                .add("analysis='" + analysis + "'")
                .toString();
    }
}
