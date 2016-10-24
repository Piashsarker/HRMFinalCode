package com.karnaphuli.www.kportal.List;

import com.karnaphuli.www.kportal.model.IndividualAttendance;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by pt on 9/26/16.
 */
public class AttendanceList {

    @SerializedName("IndividualAttendance")
    @Expose
    private ArrayList<IndividualAttendance> individualAttendance = new ArrayList<IndividualAttendance>();

    /**
     *
     * @return
     * The individualAttendance
     */
    public ArrayList<IndividualAttendance> getIndividualAttendance() {
        return individualAttendance;
    }

    /**
     *
     * @param individualAttendance
     * The IndividualAttendance
     */
    public void setIndividualAttendance(ArrayList<IndividualAttendance> individualAttendance) {
        this.individualAttendance = individualAttendance;
    }
}
