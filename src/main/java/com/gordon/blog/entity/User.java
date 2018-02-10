package com.gordon.blog.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by gordon.zhang on 2018/2/8.
 */
@Entity
public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(unique = true)
    private String username;    //账号

    private String password;

    private String name;    //昵称

    private String email;

    private String avatar;  //头像图片地址

    private String salt;    //加密的盐

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole",joinColumns = {@JoinColumn(name = "uid")},
            inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roleList;    //

    protected User() {
    }

    public User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getCredentialsSalt() {
        return this.username + this.salt;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
