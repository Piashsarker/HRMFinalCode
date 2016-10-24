package com.karnaphuli.www.kportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pt on 9/26/16.
 */
public class Year {
    @SerializedName("YearId")
    @Expose
    private Integer yearId;
    @SerializedName("YearName")
    @Expose
    private Integer yearName;

    /**
     *
     * @return
     * The yearId
     */
    public Integer getYearId() {
        return yearId;
    }

    /**
     *
     * @param yearId
     * The YearId
     */
    public void setYearId(Integer yearId) {
        this.yearId = yearId;
    }

    /**
     *
     * @return
     * The yearName
     */
    public Integer getYearName() {
        return yearName;
    }

    /**
     *
     * @param yearName
     * The YearName
     */
    public void setYearName(Integer yearName) {
        this.yearName = yearName;
    }

}
