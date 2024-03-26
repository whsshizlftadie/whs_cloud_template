package com.whs.cloud.auth.bean.vo;

import com.whs.cloud.auth.bean.Resource;
import com.whs.cloud.auth.bean.Role;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;
import java.util.StringJoiner;

public class RoleAndResourceVo extends Role {

    List<Resource> resources;

    public List<Resource> getResources() {
        return resources;
    }

    public RoleAndResourceVo setResources(List<Resource> resources) {
        this.resources = resources;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof RoleAndResourceVo)) return false;

        RoleAndResourceVo that = (RoleAndResourceVo) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(resources, that.resources)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(resources)
                .toHashCode();
    }

    @Override
    public String toString() {
        return super.toString()+new StringJoiner(", ", RoleAndResourceVo.class.getSimpleName() + "[", "]")
                .add("resources=" + resources)
                .toString();
    }
}
