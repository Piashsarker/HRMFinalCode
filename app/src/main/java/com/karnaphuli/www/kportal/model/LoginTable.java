package com.karnaphuli.www.kportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 08-Sep-16.
 */
public class LoginTable {

    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("EmployeeId")
    @Expose
    private Integer employeeId;
    @SerializedName("EmployeeCode")
    @Expose
    private String employeeCode;
    @SerializedName("EmployeeName")
    @Expose
    private String employeeName;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("UserPassword")
    @Expose
    private String userPassword;
    @SerializedName("EmployeePhoto")
    @Expose
    private String employeePhoto;
    @SerializedName("ReportingTo")
    @Expose
    private Integer reportingTo;
    @SerializedName("ApprovedBy")
    @Expose
    private Integer approvedBy;
    @SerializedName("DepartmentName")
    @Expose
    private String departmentName;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("ReportingMobileNo")
    @Expose
    private String reportingMobileNo;
    @SerializedName("LeaveReqMsg")
    @Expose
    private String leaveReqMsg;
    @SerializedName("LeaveRejMsg")
    @Expose
    private String leaveRejMsg;
    @SerializedName("LeaveAprMsg")
    @Expose
    private String leaveAprMsg;
    @SerializedName("TourReqMsg")
    @Expose
    private String tourReqMsg;
    @SerializedName("TourRejMsg")
    @Expose
    private String tourRejMsg;
    @SerializedName("TourAprMsg")
    @Expose
    private String tourAprMsg;
    @SerializedName("ODReqMsg")
    @Expose
    private String oDReqMsg;
    @SerializedName("ODRejMsg")
    @Expose
    private String oDRejMsg;
    @SerializedName("ODAprMsg")
    @Expose
    private String oDAprMsg;
    @SerializedName("MsgUserName")
    @Expose
    private String msgUserName;
    @SerializedName("MsgUserPass")
    @Expose
    private String msgUserPass;
    @SerializedName("MsgBrandName")
    @Expose
    private String msgBrandName;

    /**
     *
     * @return
     * The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * The UserId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * The userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     * The UserName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     * The userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     *
     * @param userPassword
     * The UserPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     *
     * @return
     * The employeePhoto
     */
    public String getEmployeePhoto() {
        return employeePhoto;
    }

    /**
     *
     * @param employeePhoto
     * The EmployeePhoto
     */
    public void setEmployeePhoto(String employeePhoto) {
        this.employeePhoto = employeePhoto;
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
     * The mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     *
     * @param mobileNo
     * The MobileNo
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     *
     * @return
     * The reportingMobileNo
     */
    public String getReportingMobileNo() {
        return reportingMobileNo;
    }

    /**
     *
     * @param reportingMobileNo
     * The ReportingMobileNo
     */
    public void setReportingMobileNo(String reportingMobileNo) {
        this.reportingMobileNo = reportingMobileNo;
    }

    /**
     *
     * @return
     * The leaveReqMsg
     */
    public String getLeaveReqMsg() {
        return leaveReqMsg;
    }

    /**
     *
     * @param leaveReqMsg
     * The LeaveReqMsg
     */
    public void setLeaveReqMsg(String leaveReqMsg) {
        this.leaveReqMsg = leaveReqMsg;
    }

    /**
     *
     * @return
     * The leaveRejMsg
     */
    public String getLeaveRejMsg() {
        return leaveRejMsg;
    }

    /**
     *
     * @param leaveRejMsg
     * The LeaveRejMsg
     */
    public void setLeaveRejMsg(String leaveRejMsg) {
        this.leaveRejMsg = leaveRejMsg;
    }

    /**
     *
     * @return
     * The leaveAprMsg
     */
    public String getLeaveAprMsg() {
        return leaveAprMsg;
    }

    /**
     *
     * @param leaveAprMsg
     * The LeaveAprMsg
     */
    public void setLeaveAprMsg(String leaveAprMsg) {
        this.leaveAprMsg = leaveAprMsg;
    }

    /**
     *
     * @return
     * The tourReqMsg
     */
    public String getTourReqMsg() {
        return tourReqMsg;
    }

    /**
     *
     * @param tourReqMsg
     * The TourReqMsg
     */
    public void setTourReqMsg(String tourReqMsg) {
        this.tourReqMsg = tourReqMsg;
    }

    /**
     *
     * @return
     * The tourRejMsg
     */
    public String getTourRejMsg() {
        return tourRejMsg;
    }

    /**
     *
     * @param tourRejMsg
     * The TourRejMsg
     */
    public void setTourRejMsg(String tourRejMsg) {
        this.tourRejMsg = tourRejMsg;
    }

    /**
     *
     * @return
     * The tourAprMsg
     */
    public String getTourAprMsg() {
        return tourAprMsg;
    }

    /**
     *
     * @param tourAprMsg
     * The TourAprMsg
     */
    public void setTourAprMsg(String tourAprMsg) {
        this.tourAprMsg = tourAprMsg;
    }

    /**
     *
     * @return
     * The oDReqMsg
     */
    public String getODReqMsg() {
        return oDReqMsg;
    }

    /**
     *
     * @param oDReqMsg
     * The ODReqMsg
     */
    public void setODReqMsg(String oDReqMsg) {
        this.oDReqMsg = oDReqMsg;
    }

    /**
     *
     * @return
     * The oDRejMsg
     */
    public String getODRejMsg() {
        return oDRejMsg;
    }

    /**
     *
     * @param oDRejMsg
     * The ODRejMsg
     */
    public void setODRejMsg(String oDRejMsg) {
        this.oDRejMsg = oDRejMsg;
    }

    /**
     *
     * @return
     * The oDAprMsg
     */
    public String getODAprMsg() {
        return oDAprMsg;
    }

    /**
     *
     * @param oDAprMsg
     * The ODAprMsg
     */
    public void setODAprMsg(String oDAprMsg) {
        this.oDAprMsg = oDAprMsg;
    }

    /**
     *
     * @return
     * The msgUserName
     */
    public String getMsgUserName() {
        return msgUserName;
    }

    /**
     *
     * @param msgUserName
     * The MsgUserName
     */
    public void setMsgUserName(String msgUserName) {
        this.msgUserName = msgUserName;
    }

    /**
     *
     * @return
     * The msgUserPass
     */
    public String getMsgUserPass() {
        return msgUserPass;
    }

    /**
     *
     * @param msgUserPass
     * The MsgUserPass
     */
    public void setMsgUserPass(String msgUserPass) {
        this.msgUserPass = msgUserPass;
    }

    /**
     *
     * @return
     * The msgBrandName
     */
    public String getMsgBrandName() {
        return msgBrandName;
    }

    /**
     *
     * @param msgBrandName
     * The MsgBrandName
     */
    public void setMsgBrandName(String msgBrandName) {
        this.msgBrandName = msgBrandName;
    }

}