package com.karnaphuli.www.kportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pt on 9/23/16.
 */
public class PendingCHOApplicationView {

    @SerializedName("ApplicationId")
    @Expose
    private Integer applicationId;
    @SerializedName("ApplicantCode")
    @Expose
    private String applicantCode;
    @SerializedName("ApplicantName")
    @Expose
    private String applicantName;
    @SerializedName("ApplicationDate")
    @Expose
    private String applicationDate;
    @SerializedName("LeaveTypeName")
    @Expose
    private String leaveTypeName;
    @SerializedName("FromDate")
    @Expose
    private String fromDate;
    @SerializedName("ToDate")
    @Expose
    private String toDate;
    @SerializedName("Purpuse")
    @Expose
    private String purpuse;

    /**
     *
     * @return
     * The applicationId
     */
    public Integer getApplicationId() {
        return applicationId;
    }

    /**
     *
     * @param applicationId
     * The ApplicationId
     */
    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    /**
     *
     * @return
     * The applicantCode
     */
    public String getApplicantCode() {
        return applicantCode;
    }

    /**
     *
     * @param applicantCode
     * The ApplicantCode
     */
    public void setApplicantCode(String applicantCode) {
        this.applicantCode = applicantCode;
    }

    /**
     *
     * @return
     * The applicantName
     */
    public String getApplicantName() {
        return applicantName;
    }

    /**
     *
     * @param applicantName
     * The ApplicantName
     */
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    /**
     *
     * @return
     * The applicationDate
     */
    public String getApplicationDate() {
        return applicationDate;
    }

    /**
     *
     * @param applicationDate
     * The ApplicationDate
     */
    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    /**
     *
     * @return
     * The leaveTypeName
     */
    public String getLeaveTypeName() {
        return leaveTypeName;
    }

    /**
     *
     * @param leaveTypeName
     * The LeaveTypeName
     */
    public void setLeaveTypeName(String leaveTypeName) {
        this.leaveTypeName = leaveTypeName;
    }

    /**
     *
     * @return
     * The fromDate
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     *
     * @param fromDate
     * The FromDate
     */
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    /**
     *
     * @return
     * The toDate
     */
    public String getToDate() {
        return toDate;
    }

    /**
     *
     * @param toDate
     * The ToDate
     */
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    /**
     *
     * @return
     * The purpuse
     */
    public String getPurpuse() {
        return purpuse;
    }

    /**
     *
     * @param purpuse
     * The Purpuse
     */
    public void setPurpuse(String purpuse) {
        this.purpuse = purpuse;
    }
}
