package com.karnaphuli.www.kportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pt on 9/26/16.
 */
public class Month {

    @SerializedName("MonthId")
    @Expose
    private Integer monthId;
    @SerializedName("MonthName")
    @Expose
    private String monthName;

    /**
     *
     * @return
     * The monthId
     */
    public Integer getMonthId() {
        return monthId;
    }

    /**
     *
     * @param monthId
     * The MonthId
     */
    public void setMonthId(Integer monthId) {
        this.monthId = monthId;
    }

    /**
     *
     * @return
     * The monthName
     */
    public String getMonthName() {
        return monthName;
    }

    /**
     *
     * @param monthName
     * The MonthName
     */
    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }
}
