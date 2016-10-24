package com.karnaphuli.www.kportal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pt on 9/13/16.
 */
public class Contact {
    @SerializedName("EmployeeId")
    @Expose
    private Integer employeeId;


    @SerializedName("EmployeeCode")
    @Expose
    private String employeeCode;
    @SerializedName("EmployeeName")
    @Expose
    private String employeeName;
    @SerializedName("DesignationName")
    @Expose
    private String designationName;
    @SerializedName("DepartmentName")
    @Expose
    private String departmentName;
    @SerializedName("MobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("LocationName")
    @Expose
    private String locationName;

    private boolean isSelected;

    /**
     * @return The employeeId
     */
    public Integer getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId The EmployeeId
     */
    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    /**
     * @return The employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName The EmployeeName
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return The designationName
     */
    public String getDesignationName() {
        return designationName;
    }

    /**
     * @param designationName The DesignationName
     */
    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    /**
     * @return The departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName The DepartmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return The mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo The MobileNo
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @return The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email The Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return The locationName
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * @param locationName The LocationName
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
