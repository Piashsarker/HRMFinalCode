package com.karnaphuli.www.kportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pt on 9/25/16.
 */
public class PendingLeaveApplicationView {


    @SerializedName("ApplicationId")
    @Expose
    private Integer applicationId;
    @SerializedName("ApplicantId")
    @Expose
    private Integer applicantId;
    @SerializedName("ApplicantCode")
    @Expose
    private String applicantCode;
    @SerializedName("ApplicantName")
    @Expose
    private String applicantName;
    @SerializedName("DesignationName")
    @Expose
    private String designationName;
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
    @SerializedName("ApprovedBy")
    @Expose
    private Integer approvedBy;
    @SerializedName("LeaveStatus")
    @Expose
    private String leaveStatus;
    @SerializedName("ApplicantMobileNo")
    @Expose
    private String applicantMobileNo;
    @SerializedName("AppMobileNo")
    @Expose
    private String appMobileNo;

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
     * The applicantId
     */
    public Integer getApplicantId() {
        return applicantId;
    }

    /**
     *
     * @param applicantId
     * The ApplicantId
     */
    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
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
     * The designationName
     */
    public String getDesignationName() {
        return designationName;
    }

    /**
     *
     * @param designationName
     * The DesignationName
     */
    public void setDesignationName(String designationName) {
        this.designationName = designationName;
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

    /**
     *
     * @return
     * The approvedBy
     */
    public Integer getApprovedBy() {
        return approvedBy;
    }

    /**
     *
     * @param approvedBy
     * The ApprovedBy
     */
    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
    }

    /**
     *
     * @return
     * The leaveStatus
     */
    public String getLeaveStatus() {
        return leaveStatus;
    }

    /**
     *
     * @param leaveStatus
     * The LeaveStatus
     */
    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    /**
     *
     * @return
     * The applicantMobileNo
     */
    public String getApplicantMobileNo() {
        return applicantMobileNo;
    }

    /**
     *
     * @param applicantMobileNo
     * The ApplicantMobileNo
     */
    public void setApplicantMobileNo(String applicantMobileNo) {
        this.applicantMobileNo = applicantMobileNo;
    }

    /**
     *
     * @return
     * The appMobileNo
     */
    public String getAppMobileNo() {
        return appMobileNo;
    }

    /**
     *
     * @param appMobileNo
     * The AppMobileNo
     */
    public void setAppMobileNo(String appMobileNo) {
        this.appMobileNo = appMobileNo;
    }

}
