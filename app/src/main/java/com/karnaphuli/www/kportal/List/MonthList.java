package com.karnaphuli.www.kportal.List;

import com.karnaphuli.www.kportal.model.Month;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by pt on 9/26/16.
 */
public class MonthList {
    @SerializedName("Month")
    @Expose
    private ArrayList<Month> month = new ArrayList<Month>();

    /**
     *
     * @return
     * The month
     */
    public ArrayList<Month> getMonth() {
        return month;
    }

    /**
     *
     * @param month
     * The Month
     */
    public void setMonth(ArrayList<Month> month) {
        this.month = month;
    }
}
