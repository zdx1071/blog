package com.gordon.blog.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by gordon.zhang on 2018/2/10.
 */
@Entity
public class SysPermission implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Column(columnDefinition = "enum('menu','button')")
    private String resourceType;    //资源类型

    private String url;

    private String permission;  //权限字符串

    @ManyToMany
    @JoinTable(name = "SysRolePermission",joinColumns = {@JoinColumn(name = "permissionId")},
        inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
