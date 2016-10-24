package com.karnaphuli.www.kportal.List;

import com.karnaphuli.www.kportal.model.PendingLeaveApplicationView;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by pt on 9/25/16.
 */
public class PendingLeaveList {
    @SerializedName("PendingLeaveApplicationView")
    @Expose
    private ArrayList<PendingLeaveApplicationView> pendingLeaveApplicationView = new ArrayList<PendingLeaveApplicationView>();

    /**
     *
     * @return
     * The pendingLeaveApplicationView
     */
    public ArrayList<PendingLeaveApplicationView> getPendingLeaveApplicationView() {
        return pendingLeaveApplicationView;
    }

    /**
     *
     * @param pendingLeaveApplicationView
     * The PendingLeaveApplicationView
     */
    public void setPendingLeaveApplicationView(ArrayList<PendingLeaveApplicationView> pendingLeaveApplicationView) {
        this.pendingLeaveApplicationView = pendingLeaveApplicationView;
    }
}
