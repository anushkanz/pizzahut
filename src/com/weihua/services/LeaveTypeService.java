package com.weihua.services;

import java.util.List;

import com.weihua.model.LeaveType;

public interface LeaveTypeService {

    /**
     * Get all the LeaveType
     */
    public List<LeaveType> browseLeaveType();

    /**
     * Load LeaveType
     */
    public LeaveType loadLeaveType(int leaveTypeid);

    /**
     * Delete LeaveType
     */
    public boolean delLeaveType(int leaveTypeid);

    /**
     * Add new LeaveType
     */
    public boolean addLeaveType(LeaveType leaveType);

    /**
     * Update LeaveType
     */
    public boolean updateLeaveType(LeaveType leaveType);
}
