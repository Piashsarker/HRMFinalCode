package com.karnaphuli.www.kportal.List;

import com.karnaphuli.www.kportal.model.Year;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by pt on 9/26/16.
 */
public class YearList {

    @SerializedName("Year")
    @Expose
    private ArrayList<Year> year = new ArrayList<Year>();

    /**
     *
     * @return
     * The year
     */
    public ArrayList<Year> getYear() {
        return year;
    }

    /**
     *
     * @param year
     * The Year
     */
    public void setYear(ArrayList<Year> year) {
        this.year = year;
    }
}
