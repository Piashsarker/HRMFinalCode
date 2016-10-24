package com.karnaphuli.www.kportal.List;

import com.karnaphuli.www.kportal.model.OnDutyView;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by pt on 9/21/16.
 */
public class OnDutyList {
    @SerializedName("OnDutyView")
    @Expose
    private ArrayList<OnDutyView> onDutyView = new ArrayList<OnDutyView>();

    /**
     *
     * @return
     * The onDutyView
     */
    public ArrayList<OnDutyView> getOnDutyView() {
        return onDutyView;
    }

    /**
     *
     * @param onDutyView
     * The OnDutyView
     */
    public void setOnDutyView(ArrayList<OnDutyView> onDutyView) {
        this.onDutyView = onDutyView;
    }
}
