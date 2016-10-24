package com.karnaphuli.www.kportal.List;

import com.karnaphuli.www.kportal.model.LeaveBalance;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by pt on 10/8/16.
 */
public class LeaveDaysRequiredList {

    @SerializedName("LeaveBalance")
    @Expose
    private ArrayList<LeaveBalance> leaveBalance = new ArrayList<LeaveBalance>();

    /**
     *
     * @return
     * The leaveBalance
     */
    public ArrayList<LeaveBalance> getLeaveBalance() {
        return leaveBalance;
    }

    /**
     *
     * @param leaveBalance
     * The LeaveBalance
     */
    public void setLeaveBalance(ArrayList<LeaveBalance> leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

}
