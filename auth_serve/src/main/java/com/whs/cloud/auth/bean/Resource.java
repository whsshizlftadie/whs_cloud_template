package com.whs.cloud.auth.bean;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

/**
 * @TableName cloud_auth_resource
 */
@TableName(value = "cloud_auth_resource")
public class Resource implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId
    private Long id;
    /**
     *
     */
    private String resourceName;
    /**
     *
     */
    private String description;
    /**
     *
     */
    private Integer isDelete;
    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Resource)) return false;

        Resource resource = (Resource) o;

        return new EqualsBuilder()
                .append(id, resource.id)
                .append(resourceName, resource.resourceName)
                .append(description, resource.description)
                .append(isDelete, resource.isDelete)
                .append(url, resource.url)
                .append(createTime, resource.createTime)
                .append(modifyTime, resource.modifyTime)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(resourceName)
                .append(description)
                .append(isDelete)
                .append(url)
                .append(createTime)
                .append(modifyTime)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Resource.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("resourceName='" + resourceName + "'")
                .add("description='" + description + "'")
                .add("isDelete=" + isDelete)
                .add("url='" + url + "'")
                .add("createTime=" + createTime)
                .add("modifyTime=" + modifyTime)
                .toString();
    }

    public String getUrl() {
        return url;
    }

    public Resource setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     *
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     *
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date modifyTime;

    /**
     *
     */
    public Long getId() {
        return id;
    }

    /**
     *
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     *
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     *
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     *
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

}