package com.karnaphuli.www.kportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pt on 9/26/16.
 */
public class IndividualAttendance {
    @SerializedName("SLNo")
    @Expose
    private Integer sLNo;
    @SerializedName("EmployeeId")
    @Expose
    private Integer employeeId;
    @SerializedName("EmployeeCode")
    @Expose
    private String employeeCode;
    @SerializedName("EmployeeName")
    @Expose
    private String employeeName;
    @SerializedName("DepartmentName")
    @Expose
    private String departmentName;
    @SerializedName("DesignationName")
    @Expose
    private String designationName;
    @SerializedName("ADate")
    @Expose
    private String aDate;
    @SerializedName("ADay")
    @Expose
    private String aDay;
    @SerializedName("AType")
    @Expose
    private String aType;
    @SerializedName("ALogin")
    @Expose
    private String aLogin;
    @SerializedName("ALogout")
    @Expose
    private String aLogout;
    @SerializedName("AStatus")
    @Expose
    private String aStatus;
    @SerializedName("InRemarks")
    @Expose
    private String inRemarks;
    @SerializedName("PresentDate")
    @Expose
    private String presentDate;
    @SerializedName("StartDate")
    @Expose
    private String startDate;
    @SerializedName("EndDate")
    @Expose
    private String endDate;
    @SerializedName("TotalDays")
    @Expose
    private Integer totalDays;
    @SerializedName("GroupOfCompanyNameList")
    @Expose
    private String groupOfCompanyName;
    @SerializedName("CompanyAddress")
    @Expose
    private String companyAddress;

    /**
     *
     * @return
     * The sLNo
     */
    public Integer getSLNo() {
        return sLNo;
    }

    /**
     *
     * @param sLNo
     * The SLNo
     */
    public void setSLNo(Integer sLNo) {
        this.sLNo = sLNo;
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
     * The departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     *
     * @param departmentName
     * The DepartmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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
     * The aDate
     */
    public String getADate() {
        return aDate;
    }

    /**
     *
     * @param aDate
     * The ADate
     */
    public void setADate(String aDate) {
        this.aDate = aDate;
    }

    /**
     *
     * @return
     * The aDay
     */
    public String getADay() {
        return aDay;
    }

    /**
     *
     * @param aDay
     * The ADay
     */
    public void setADay(String aDay) {
        this.aDay = aDay;
    }

    /**
     *
     * @return
     * The aType
     */
    public String getAType() {
        return aType;
    }

    /**
     *
     * @param aType
     * The AType
     */
    public void setAType(String aType) {
        this.aType = aType;
    }

    /**
     *
     * @return
     * The aLogin
     */
    public String getALogin() {
        return aLogin;
    }

    /**
     *
     * @param aLogin
     * The ALogin
     */
    public void setALogin(String aLogin) {
        this.aLogin = aLogin;
    }

    /**
     *
     * @return
     * The aLogout
     */
    public String getALogout() {
        return aLogout;
    }

    /**
     *
     * @param aLogout
     * The ALogout
     */
    public void setALogout(String aLogout) {
        this.aLogout = aLogout;
    }

    /**
     *
     * @return
     * The aStatus
     */
    public String getAStatus() {
        return aStatus;
    }

    /**
     *
     * @param aStatus
     * The AStatus
     */
    public void setAStatus(String aStatus) {
        this.aStatus = aStatus;
    }

    /**
     *
     * @return
     * The inRemarks
     */
    public String getInRemarks() {
        return inRemarks;
    }

    /**
     *
     * @param inRemarks
     * The InRemarks
     */
    public void setInRemarks(String inRemarks) {
        this.inRemarks = inRemarks;
    }

    /**
     *
     * @return
     * The presentDate
     */
    public String getPresentDate() {
        return presentDate;
    }

    /**
     *
     * @param presentDate
     * The PresentDate
     */
    public void setPresentDate(String presentDate) {
        this.presentDate = presentDate;
    }

    /**
     *
     * @return
     * The startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate
     * The StartDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @return
     * The endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     *
     * @param endDate
     * The EndDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     *
     * @return
     * The totalDays
     */
    public Integer getTotalDays() {
        return totalDays;
    }

    /**
     *
     * @param totalDays
     * The TotalDays
     */
    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    /**
     *
     * @return
     * The groupOfCompanyName
     */
    public String getGroupOfCompanyName() {
        return groupOfCompanyName;
    }

    /**
     *
     * @param groupOfCompanyName
     * The GroupOfCompanyNameList
     */
    public void setGroupOfCompanyName(String groupOfCompanyName) {
        this.groupOfCompanyName = groupOfCompanyName;
    }

    /**
     *
     * @return
     * The companyAddress
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     *
     * @param companyAddress
     * The CompanyAddress
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
}
