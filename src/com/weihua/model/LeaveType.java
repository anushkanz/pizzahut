package com.weihua.model;

import java.io.Serializable;
import java.util.Set;

public class LeaveType implements Serializable {

    private int id;
    private String leaveName;
    private String leaveDesc;
    private Set<Leave> leaves;
    //new property added in 2010.6.4
    private int noOfDays;

    public int getNoOfDays() {
        return noOfDays;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public String getLeaveDesc() {
        return leaveDesc;
    }

    public void setLeaveDesc(String leaveDesc) {
        this.leaveDesc = leaveDesc;
    }

    public Set<Leave> getLeaves() {
        return leaves;
    }

    public void setLeaves(Set<Leave> leaves) {
        this.leaves = leaves;
    }

    public LeaveType(String leaveName, String leaveDesc, int noOfDays) {
        super();
        this.leaveName = leaveName;
        this.leaveDesc = leaveDesc;
        this.noOfDays = noOfDays;
    }

    public LeaveType() {

    }

}
