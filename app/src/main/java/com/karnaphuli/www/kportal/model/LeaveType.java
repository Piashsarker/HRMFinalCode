package com.karnaphuli.www.kportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pt on 9/13/16.
 */

public class LeaveType {
    @SerializedName("LeaveTypeId")
    @Expose
    private Integer leaveTypeId;
    @SerializedName("LeaveTypeName")
    @Expose
    private String leaveTypeName;

    /**
     *
     * @return
     * The leaveTypeId
     */
    public Integer getLeaveTypeId() {
        return leaveTypeId;
    }

    /**
     *
     * @param leaveTypeId
     * The LeaveTypeId
     */
    public void setLeaveTypeId(Integer leaveTypeId) {
        this.leaveTypeId = leaveTypeId;
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


}
