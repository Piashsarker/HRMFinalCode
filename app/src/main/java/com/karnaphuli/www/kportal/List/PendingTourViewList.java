package com.karnaphuli.www.kportal.List;

import com.karnaphuli.www.kportal.model.PendingTourView;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by pt on 9/25/16.
 */
public class PendingTourViewList {

    @SerializedName("PendingTourView")
    @Expose
    private ArrayList<PendingTourView> pendingTourView = new ArrayList<PendingTourView>();

    /**
     *
     * @return
     * The pendingTourView
     */
    public ArrayList<PendingTourView> getPendingTourView() {
        return pendingTourView;
    }

    /**
     *
     * @param pendingTourView
     * The PendingTourView
     */
    public void setPendingTourView(ArrayList<PendingTourView> pendingTourView) {
        this.pendingTourView = pendingTourView;
    }


}
