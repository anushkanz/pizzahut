package com.weihua.model;

import java.io.Serializable;
import java.util.Set;

public class Users implements Serializable {

    private int id;
    private String userName;
    private String loginName;
    private String password;
    private int adminType;
    private Set<Leave> userLeaves;

    private int childBL12;    //no of child below 12 years old
    private String userTitle; //professional or administrative
    private String photoUrl;  //user photo URL

    private Users authorizedBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAdminType() {
        return adminType;
    }

    public void setAdminType(int adminType) {
        this.adminType = adminType;
    }

    public Set<Leave> getUserLeaves() {
        return userLeaves;
    }

    public void setUserLeaves(Set<Leave> userLeaves) {
        this.userLeaves = userLeaves;
    }

    public int getChildBL12() {
        return childBL12;
    }

    public void setChildBL12(int childBL12) {
        this.childBL12 = childBL12;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Users getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(Users authorizedBy) {
        this.authorizedBy = authorizedBy;
    }

    public Users(String userName, String loginName, String password,
            int adminType, int childBL12, String userTitle, String photoUrl,
            Users authorizedBy) {
        super();
        this.userName = userName;
        this.loginName = loginName;
        this.password = password;
        this.adminType = adminType;
        this.childBL12 = childBL12;
        this.userTitle = userTitle;
        this.photoUrl = photoUrl;
        this.authorizedBy = authorizedBy;
    }

    public Users() {

    }

}
