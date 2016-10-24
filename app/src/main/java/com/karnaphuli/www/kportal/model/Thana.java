package com.karnaphuli.www.kportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pt on 10/8/16.
 */
public class Thana {

    @SerializedName("AreaID")
    @Expose
    private Integer areaID;
    @SerializedName("AreaName")
    @Expose
    private String areaName;

    /**
     *
     * @return
     * The areaID
     */
    public Integer getAreaID() {
        return areaID;
    }

    /**
     *
     * @param areaID
     * The AreaID
     */
    public void setAreaID(Integer areaID) {
        this.areaID = areaID;
    }

    /**
     *
     * @return
     * The areaName
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     *
     * @param areaName
     * The AreaName
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
