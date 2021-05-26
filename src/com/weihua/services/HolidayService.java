package com.weihua.services;

import java.util.Date;
import java.util.List;
import com.weihua.model.Holiday;

public interface HolidayService {

    /**
     * Basic Service
     */
    /**
     * Get all the Holiday *
     */
    public List<Holiday> browseHoliday();

    /**
     * Load Holiday *
     */
    public Holiday loadHoliday(int holidayid);

    /**
     * Delete Holiday *
     */
    public boolean delHoliday(int holidayid);

    /**
     * Add new Holiday *
     */
    public boolean addHoliday(Holiday holiday);

    /**
     * Update Holiday *
     */
    public boolean updateHoliday(Holiday holiday);

    /**
     * Special Services
     */
    /**
     * Get the no of the holiday of the Date Range *
     */
    public int getNoHolidays(Date dateFrom, Date dateTo);

    /**
     * Check the Given date is a holiday or not *
     */
    public boolean checkHoliday(Date dateCheck);

}
