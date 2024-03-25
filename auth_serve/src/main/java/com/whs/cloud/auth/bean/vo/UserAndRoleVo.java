package com.whs.cloud.auth.bean.vo;

import com.whs.cloud.auth.bean.Role;
import com.whs.cloud.auth.bean.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;
import java.util.StringJoiner;

public class UserAndRoleVo extends User {


    private List<Role> roles;

    public List<Role> getRoles() {
        return roles;
    }

    public UserAndRoleVo setRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof UserAndRoleVo)) return false;

        UserAndRoleVo that = (UserAndRoleVo) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(roles, that.roles)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(roles)
                .toHashCode();
    }

    @Override
    public String toString() {
        return super.toString() + new StringJoiner(", ", UserAndRoleVo.class.getSimpleName() + "[", "]")
                .add("roles=" + roles)
                .toString();
    }
}
