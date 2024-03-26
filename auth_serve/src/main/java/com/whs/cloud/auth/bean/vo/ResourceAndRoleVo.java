package com.whs.cloud.auth.bean.vo;

import java.util.List;
import java.util.StringJoiner;

public class ResourceAndRoleVo {

    private List<String> roleName;

    private String url;

    public List<String> getRoleName() {
        return roleName;
    }

    public ResourceAndRoleVo setRoleName(List<String> roleName) {
        this.roleName = roleName;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ResourceAndRoleVo setUrl(String url) {
        this.url = url;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ResourceAndRoleVo.class.getSimpleName() + "[", "]")
                .add("roleName='" + roleName + "'")
                .add("url='" + url + "'")
                .toString();
    }
}
