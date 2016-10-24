package com.karnaphuli.www.kportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pt on 9/13/16.
 */
public class LeaveReport {

    @SerializedName("FromDate")
    @Expose
    private String fromDate;
    @SerializedName("ToDate")
    @Expose
    private String toDate;
    @SerializedName("TotalLeaveDays")
    @Expose
    private Integer totalLeaveDays;
    @SerializedName("LeaveTypeName")
    @Expose
    private String leaveTypeName;
    @SerializedName("LeaveStatus")
    @Expose
    private String leaveStatus;

    /**
     *
     * @return
     * The fromDate
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     *
     * @param fromDate
     * The FromDate
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     *
     * @return
     * The toDate
     */
    public String getToDate() {
        return toDate;
    }

    /**
     *
     * @param toDate
     * The ToDate
     */
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    /**
     *
     * @return
     * The totalLeaveDays
     */
    public Integer getTotalLeaveDays() {
        return totalLeaveDays;
    }

    /**
     *
     * @param totalLeaveDays
     * The TotalLeaveDays
     */
    public void setTotalLeaveDays(Integer totalLeaveDays) {
        this.totalLeaveDays = totalLeaveDays;
    }

    /**
     *
     * @return
     * The leaveTypeName
     */
    public String getLeaveTypeName() {
        return leaveTypeName;
    }

    /**
     *
     * @param leaveTypeName
     * The LeaveTypeName
     */
    public void setLeaveTypeName(String leaveTypeName) {
        this.leaveTypeName = leaveTypeName;
    }

    /**
     *
     * @return
     * The leaveStatus
     */
    public String getLeaveStatus() {
        return leaveStatus;
    }

    /**
     *
     * @param leaveStatus
     * The LeaveStatus
     */
    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }
}
