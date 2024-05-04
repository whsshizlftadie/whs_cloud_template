package com.whs.cloud.auth.bean.request.resource;

import com.whs.cloud.auth.bean.RoleResource;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;
import java.util.StringJoiner;

public class ResourceBindRoleRequest {

    List<RoleResource> roleResources;

    public List<RoleResource> getRoleResources() {
        return roleResources;
    }

    public ResourceBindRoleRequest setRoleResources(List<RoleResource> roleResources) {
        this.roleResources = roleResources;
        return this;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", ResourceBindRoleRequest.class.getSimpleName() + "[", "]")
                .add("roleResources=" + roleResources)
                .toString();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof ResourceBindRoleRequest)) return false;

        ResourceBindRoleRequest that = (ResourceBindRoleRequest) o;

        return new EqualsBuilder()
                .append(roleResources, that.roleResources)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(roleResources)
                .toHashCode();
    }
}
