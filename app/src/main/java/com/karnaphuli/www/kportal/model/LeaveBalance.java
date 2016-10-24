package com.karnaphuli.www.kportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pt on 10/8/16.
 */
public class LeaveBalance {
    @SerializedName("LeaveBalance")
    @Expose
    private Integer leaveBalance;
    @SerializedName("LeaveDuration")
    @Expose
    private Integer leaveDuration;

    /**
     *
     * @return
     * The leaveBalance
     */
    public Integer getLeaveBalance() {
        return leaveBalance;
    }

    /**
     *
     * @param leaveBalance
     * The LeaveBalance
     */
    public void setLeaveBalance(Integer leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    /**
     *
     * @return
     * The leaveDuration
     */
    public Integer getLeaveDuration() {
        return leaveDuration;
    }

    /**
     *
     * @param leaveDuration
     * The LeaveDuration
     */
    public void setLeaveDuration(Integer leaveDuration) {
        this.leaveDuration = leaveDuration;
    }

}
