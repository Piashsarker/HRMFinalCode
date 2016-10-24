package com.karnaphuli.www.kportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pt on 9/13/16.
 */
public class Inbox {
    @SerializedName("EmployeeMailId")
    @Expose
    private Integer employeeMailId;
    @SerializedName("FromEmployeeId")
    @Expose
    private Integer fromEmployeeId;
    @SerializedName("FromEmployeeCode")
    @Expose
    private String fromEmployeeCode;
    @SerializedName("FromEmployeeName")
    @Expose
    private String fromEmployeeName;
    @SerializedName("ToEmployeeId")
    @Expose
    private Integer toEmployeeId;
    @SerializedName("ToEmployeeCode")
    @Expose
    private String toEmployeeCode;
    @SerializedName("ToEmployeeName")
    @Expose
    private String toEmployeeName;
    @SerializedName("Subject")
    @Expose
    private String subject;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("ReadStatus")
    @Expose
    private String readStatus;
    @SerializedName("DateNTime")
    @Expose
    private String dateNTime;

    /**
     *
     * @return
     * The employeeMailId
     */
    public Integer getEmployeeMailId() {
        return employeeMailId;
    }

    /**
     *
     * @param employeeMailId
     * The EmployeeMailId
     */
    public void setEmployeeMailId(Integer employeeMailId) {
        this.employeeMailId = employeeMailId;
    }

    /**
     *
     * @return
     * The fromEmployeeId
     */
    public Integer getFromEmployeeId() {
        return fromEmployeeId;
    }

    /**
     *
     * @param fromEmployeeId
     * The FromEmployeeId
     */
    public void setFromEmployeeId(Integer fromEmployeeId) {
        this.fromEmployeeId = fromEmployeeId;
    }

    /**
     *
     * @return
     * The fromEmployeeCode
     */
    public String getFromEmployeeCode() {
        return fromEmployeeCode;
    }

    /**
     *
     * @param fromEmployeeCode
     * The FromEmployeeCode
     */
    public void setFromEmployeeCode(String fromEmployeeCode) {
        this.fromEmployeeCode = fromEmployeeCode;
    }

    /**
     *
     * @return
     * The fromEmployeeName
     */
    public String getFromEmployeeName() {
        return fromEmployeeName;
    }

    /**
     *
     * @param fromEmployeeName
     * The FromEmployeeName
     */
    public void setFromEmployeeName(String fromEmployeeName) {
        this.fromEmployeeName = fromEmployeeName;
    }

    /**
     *
     * @return
     * The toEmployeeId
     */
    public Integer getToEmployeeId() {
        return toEmployeeId;
    }

    /**
     *
     * @param toEmployeeId
     * The ToEmployeeId
     */
    public void setToEmployeeId(Integer toEmployeeId) {
        this.toEmployeeId = toEmployeeId;
    }

    /**
     *
     * @return
     * The toEmployeeCode
     */
    public String getToEmployeeCode() {
        return toEmployeeCode;
    }

    /**
     *
     * @param toEmployeeCode
     * The ToEmployeeCode
     */
    public void setToEmployeeCode(String toEmployeeCode) {
        this.toEmployeeCode = toEmployeeCode;
    }

    /**
     *
     * @return
     * The toEmployeeName
     */
    public String getToEmployeeName() {
        return toEmployeeName;
    }

    /**
     *
     * @param toEmployeeName
     * The ToEmployeeName
     */
    public void setToEmployeeName(String toEmployeeName) {
        this.toEmployeeName = toEmployeeName;
    }

    /**
     *
     * @return
     * The subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     *
     * @param subject
     * The Subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     *
     * @return
     * The msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     *
     * @param msg
     * The msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     *
     * @return
     * The readStatus
     */
    public String getReadStatus() {
        return readStatus;
    }

    /**
     *
     * @param readStatus
     * The ReadStatus
     */
    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    /**
     *
     * @return
     * The dateNTime
     */
    public String getDateNTime() {
        return dateNTime;
    }

    /**
     *
     * @param dateNTime
     * The DateNTime
     */
    public void setDateNTime(String dateNTime) {
        this.dateNTime = dateNTime;
    }

}


