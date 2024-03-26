package com.whs.cloud.auth.bean.request.role;

import com.whs.cloud.auth.bean.UserRole;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;
import java.util.StringJoiner;

public class RoleBindUserRequest {

    List<UserRole> userRoles;


    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public RoleBindUserRequest setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RoleBindUserRequest.class.getSimpleName() + "[", "]")
                .add("userRoles=" + userRoles)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof RoleBindUserRequest)) return false;

        RoleBindUserRequest that = (RoleBindUserRequest) o;

        return new EqualsBuilder()
                .append(userRoles, that.userRoles)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userRoles)
                .toHashCode();
    }
}
