package com.karnaphuli.www.kportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pt on 9/25/16.
 */
public class PendingOnDutyView {
    @SerializedName("TourId")
    @Expose
    private Integer tourId;
    @SerializedName("EmployeeId")
    @Expose
    private Integer employeeId;
    @SerializedName("EmployeeCode")
    @Expose
    private String employeeCode;
    @SerializedName("EmployeeName")
    @Expose
    private String employeeName;
    @SerializedName("SubmissionDate")
    @Expose
    private String submissionDate;
    @SerializedName("TourNatureId")
    @Expose
    private Integer tourNatureId;
    @SerializedName("TourNatureName")
    @Expose
    private String tourNatureName;
    @SerializedName("FromDate")
    @Expose
    private String fromDate;
    @SerializedName("ToDate")
    @Expose
    private String toDate;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("TourStatus")
    @Expose
    private String tourStatus;
    @SerializedName("ReportingTo")
    @Expose
    private Integer reportingTo;
    @SerializedName("ApprovedBy")
    @Expose
    private Integer approvedBy;
    @SerializedName("ApplicantMobileNo")
    @Expose
    private String applicantMobileNo;
    @SerializedName("RptMobileNo")
    @Expose
    private String rptMobileNo;
    @SerializedName("AppMobileNo")
    @Expose
    private String appMobileNo;

    /**
     *
     * @return
     * The tourId
     */
    public Integer getTourId() {
        return tourId;
    }

    /**
     *
     * @param tourId
     * The TourId
     */
    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    /**
     *
     * @return
     * The employeeId
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     *
     * @param employeeId
     * The EmployeeId
     */
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    /**
     *
     * @return
     * The employeeCode
     */
    public String getEmployeeCode() {
        return employeeCode;
    }

    /**
     *
     * @param employeeCode
     * The EmployeeCode
     */
    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    /**
     *
     * @return
     * The employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     *
     * @param employeeName
     * The EmployeeName
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     *
     * @return
     * The submissionDate
     */
    public String getSubmissionDate() {
        return submissionDate;
    }

    /**
     *
     * @param submissionDate
     * The SubmissionDate
     */
    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    /**
     *
     * @return
     * The tourNatureId
     */
    public Integer getTourNatureId() {
        return tourNatureId;
    }

    /**
     *
     * @param tourNatureId
     * The TourNatureId
     */
    public void setTourNatureId(Integer tourNatureId) {
        this.tourNatureId = tourNatureId;
    }

    /**
     *
     * @return
     * The tourNatureName
     */
    public String getTourNatureName() {
        return tourNatureName;
    }

    /**
     *
     * @param tourNatureName
     * The TourNatureName
     */
    public void setTourNatureName(String tourNatureName) {
        this.tourNatureName = tourNatureName;
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
     * The remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     *
     * @param remarks
     * The Remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     *
     * @return
     * The tourStatus
     */
    public String getTourStatus() {
        return tourStatus;
    }

    /**
     *
     * @param tourStatus
     * The TourStatus
     */
    public void setTourStatus(String tourStatus) {
        this.tourStatus = tourStatus;
    }

    /**
     *
     * @return
     * The reportingTo
     */
    public Integer getReportingTo() {
        return reportingTo;
    }

    /**
     *
     * @param reportingTo
     * The ReportingTo
     */
    public void setReportingTo(Integer reportingTo) {
        this.reportingTo = reportingTo;
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
     * The rptMobileNo
     */
    public String getRptMobileNo() {
        return rptMobileNo;
    }

    /**
     *
     * @param rptMobileNo
     * The RptMobileNo
     */
    public void setRptMobileNo(String rptMobileNo) {
        this.rptMobileNo = rptMobileNo;
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
