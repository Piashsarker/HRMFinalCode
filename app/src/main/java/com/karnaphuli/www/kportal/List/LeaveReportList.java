package com.karnaphuli.www.kportal.List;

import com.karnaphuli.www.kportal.model.LeaveReport;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by pt on 9/13/16.
 */
public class LeaveReportList {
    @SerializedName("LeaveReport")
    @Expose
    private ArrayList<LeaveReport> leaveReport = new ArrayList<LeaveReport>();

    /**
     *
     * @return
     * The leaveReport
     */
    public ArrayList<LeaveReport> getLeaveReport() {
        return leaveReport;
    }

    /**
     *
     * @param leaveReport
     * The LeaveReport
     */
    public void setLeaveReport(ArrayList<LeaveReport> leaveReport) {
        this.leaveReport = leaveReport;
    }

}
