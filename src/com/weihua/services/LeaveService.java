package com.weihua.services;

import java.util.Date;
import java.util.List;
import com.weihua.model.Leave;

public interface LeaveService {

    /**
     * *****************
     * Basic Service *****************
     */
    /**
     * Get all the Leave *
     */
    public List<Leave> browseLeave();

    /**
     * Load Leave *
     */
    public Leave loadLeave(int leaveid);

    /**
     * Delete Leave *
     */
    public boolean delLeave(int leaveid);

    /**
     * Add new Leave *
     */
    public boolean addLeave(Leave leave);

    /**
     * Update Leave *
     */
    public boolean updateLeave(Leave leave);

    /**
     * ********************
     * Specialized Service ********************
     */
    /**
     * Paging: show Leave records according to the userId -- Current year*
     */
    public List<Leave> browseLeaveByUserId(int userId, int firstResult, int maxResults);
    //Overload

    public List<Leave> browseLeaveByUserId(int userId);

    /**
     * Paging: show Leave records according to the LeaveType *
     */
    public List<Leave> browseLeaveByType(int typeId, int firstResult, int maxResults);

    /**
     * Paging: Get all the Leave *
     */
    public List<Leave> browseLeave(int firstResult, int maxResults);

    /**
     * Get Leaves by Data Zone*
     */
    public List<Leave> browseLeaveByDataZone(Date dateFormer, Date dateLatter);

    /**
     * Paging: View others who leave in current time (update in 2010.6.4)*
     */
    public List<Leave> browseOthersWhoLeave(int userId);

    /**
     * Paging: View others who leave in current time (update in 2010.6.14)*
     */
    public List<Leave> browseOthersWhoLeaveByMonth(int month, int userId);

    /**
     * Get Leaves by Status and user (update in 2010.6.8)*
     */
    public List<Leave> browseLeavesByStatusAndUser(int userId, String status1, String status2);

    /**
     * View Pending Leaves which belongs to designated Manager *
     */
    public List<Leave> browsePendingLeaves(int userId);

    /**
     * Retrieve Leaves for manager to issue (2010.6.11)*
     */
    public List<Leave> retrieveLeaveByManager(int userId);

}
