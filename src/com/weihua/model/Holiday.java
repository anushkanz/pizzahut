package com.weihua.model;

import java.io.Serializable;
import java.util.Date;

public class Holiday implements Serializable {

    /**
     * Holiday won't be Saturday or Sunday
     */
    private int id;
    private Date holidayDay;
    private String holidayName;
    private String holidayDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getHolidayDay() {
        return holidayDay;
    }

    public void setHolidayDay(Date holidayDay) {
        this.holidayDay = holidayDay;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public String getHolidayDesc() {
        return holidayDesc;
    }

    public void setHolidayDesc(String holidayDesc) {
        this.holidayDesc = holidayDesc;
    }

    public Holiday() {

    }

    public Holiday(Date holidayDay, String holidayName, String holidayDesc) {
        super();
        this.holidayDay = holidayDay;
        this.holidayName = holidayName;
        this.holidayDesc = holidayDesc;
    }

}
