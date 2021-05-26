package com.weihua.model;

import java.io.Serializable;
import java.util.Date;

public class Leave implements Serializable {

    private int id;
    private Users leaveUser;
    private LeaveType leaveType;
    private String leaveStatus;
    private Date appDate;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String contactDetails;
    private String dissemination;
    private String comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getLeaveUser() {
        return leaveUser;
    }

    public void setLeaveUser(Users leaveUser) {
        this.leaveUser = leaveUser;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getDissemination() {
        return dissemination;
    }

    public void setDissemination(String dissemination) {
        this.dissemination = dissemination;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Leave() {
    }

    public Leave(Users leaveUser, LeaveType leaveType, String status,
            Date appDate, Date startDate, Date endDate, String reason,
            String contactDetails, String dissemination, String comments) {
        super();
        this.leaveUser = leaveUser;
        this.leaveType = leaveType;
        this.leaveStatus = status;
        this.appDate = appDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.contactDetails = contactDetails;
        this.dissemination = dissemination;
        this.comments = comments;
    }

}
