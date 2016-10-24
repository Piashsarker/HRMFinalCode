package com.karnaphuli.www.kportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pt on 10/8/16.
 */
public class District {

    @SerializedName("DistrictId")
    @Expose
    private Integer districtId;
    @SerializedName("DistrictName")
    @Expose
    private String districtName;

    /**
     *
     * @return
     * The districtId
     */
    public Integer getDistrictId() {
        return districtId;
    }

    /**
     *
     * @param districtId
     * The DistrictId
     */
    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    /**
     *
     * @return
     * The districtName
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     *
     * @param districtName
     * The DistrictName
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
