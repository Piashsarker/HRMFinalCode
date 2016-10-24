package com.karnaphuli.www.kportal.Session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.karnaphuli.www.kportal.activity.LoginActivity;

import java.util.HashMap;

/**
 * Created by pt on 9/20/16.
 */
public class SessionManager {
    public static final String KEY_EMPLOYEE_ID = "employee_id";
    public static final String KEY_KEY = "key";
    public static final String KEY_EMPLOYEE_NAME = "employee_name";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_EMPLOYEE_PHOTO = "employee_photo";
    public static final String KEY_REPORTING_TO = "reporting_to";
    public static final String KEY_APPROVED_BY = "approved_by";
    public static final String KEY_EMPLOYEE_CODE = "employee_code";
    public static final String KEY_DEPARTMENT = "department";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_LEAVE_REQUEST_MESSAGE = "leave_request_message";
    public static final String KEY_LEAVE_REJECTED_MESSAGE = "leave_rejected_message";
    public static final String KEY_TOUR_APPROVE_MESSAGE = "tour_approve_message";
    public static final String KEY_TOUR_REJECTED_MESSAGE = "tour_rejected_message";
    public static final String KEY_TOUR_REQUEST_MESSAGE = "tour_request_message";
    public static final String KEY_ONDUTY_APPROVE_MESSAGE = "onduty_approve_message";
    public static final String KEY_ONDUTY_REJECTED_MESSAGE = "onduty_rejected_message";
    public static final String KEY_ONDUTY_REQUEST_MESSAGE = "onduty_request_message";
    public static final String KEY_LEAVE_APPROVE_MESSAGE = "leave_approve_message";
    public static final String KEY_MSG_USER_NAME = "msg_username";
    public static final String KEY_MSG_PASSWORD = "msg_password";
    public static final String KEY_MSG_BRAND_NAME = "msg_brand_name";
    public static final String KEY_REPORTING_MOBILE = "reporting_mobile";
    private static final String PREF_NAME = "RunnerGroupPref";
    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String GROUP_NAME = "group_name";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }


    public void createLoginSession(String key, String employeeId, String employeeName, String employeePhoto, String approvedBy, String reportingBy, String departmentName, String employeeCode, String mobileNo, String reportingMobileNo, String leaveReqMsg, String userId, String msgUserName, String msgPassword, String msgBrandName, String leaveRejected, String leaveApprove, String tourRequest, String tourApprove, String tourRejected, String onDutyRequest, String onDutyApprove, String onDutyRejected) {

        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_KEY, key);
        editor.putString(KEY_EMPLOYEE_ID, employeeId);
        editor.putString(KEY_EMPLOYEE_NAME, employeeName);
        editor.putString(KEY_EMPLOYEE_PHOTO, employeePhoto);
        editor.putString(KEY_APPROVED_BY, approvedBy);
        editor.putString(KEY_REPORTING_TO, reportingBy);
        editor.putString(KEY_EMPLOYEE_CODE, employeeCode);
        editor.putString(KEY_DEPARTMENT, departmentName);
        editor.putString(KEY_MOBILE, mobileNo);
        editor.putString(KEY_REPORTING_MOBILE, reportingMobileNo);
        editor.putString(KEY_LEAVE_REQUEST_MESSAGE, leaveReqMsg);
        editor.putString(KEY_USER_ID, userId);
        editor.putString(KEY_MSG_USER_NAME, msgUserName);
        editor.putString(KEY_MSG_PASSWORD, msgPassword);
        editor.putString(KEY_MSG_BRAND_NAME, msgBrandName);
        editor.putString(KEY_LEAVE_REJECTED_MESSAGE, leaveRejected);
        editor.putString(KEY_LEAVE_APPROVE_MESSAGE, leaveApprove);
        editor.putString(KEY_TOUR_REQUEST_MESSAGE, tourRequest);
        editor.putString(KEY_TOUR_APPROVE_MESSAGE, tourApprove);
        editor.putString(KEY_TOUR_REJECTED_MESSAGE, tourRejected);
        editor.putString(KEY_ONDUTY_REQUEST_MESSAGE , onDutyRequest);
        editor.putString(KEY_ONDUTY_APPROVE_MESSAGE , onDutyApprove);
        editor.putString(KEY_ONDUTY_REJECTED_MESSAGE, onDutyRejected);

        editor.commit();
    }

    public void addGroupOfCompany(String groupName){
        editor.putString(GROUP_NAME, groupName );
        editor.commit();
    }

    public String getGroupOfCompany(){

        return sharedPreferences.getString(GROUP_NAME,null);

    }


    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            context.startActivity(i);
        }
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_KEY, sharedPreferences.getString(KEY_KEY, null));
        user.put(KEY_EMPLOYEE_ID, sharedPreferences.getString(KEY_EMPLOYEE_ID, null));
        user.put(KEY_EMPLOYEE_NAME, sharedPreferences.getString(KEY_EMPLOYEE_NAME, null));
        user.put(KEY_EMPLOYEE_PHOTO, sharedPreferences.getString(KEY_EMPLOYEE_PHOTO, null));
        user.put(KEY_APPROVED_BY, sharedPreferences.getString(KEY_APPROVED_BY, null));
        user.put(KEY_REPORTING_TO, sharedPreferences.getString(KEY_REPORTING_TO, null));
        user.put(KEY_EMPLOYEE_CODE, sharedPreferences.getString(KEY_EMPLOYEE_CODE, null));
        user.put(KEY_DEPARTMENT, sharedPreferences.getString(KEY_DEPARTMENT, null));
        user.put(KEY_MOBILE, sharedPreferences.getString(KEY_MOBILE, null));
        user.put(KEY_REPORTING_MOBILE, sharedPreferences.getString(KEY_REPORTING_MOBILE, null));
        user.put(KEY_LEAVE_REQUEST_MESSAGE, sharedPreferences.getString(KEY_LEAVE_REQUEST_MESSAGE, null));
        user.put(KEY_USER_ID, sharedPreferences.getString(KEY_USER_ID, null));
        user.put(KEY_MSG_USER_NAME, sharedPreferences.getString(KEY_MSG_USER_NAME, null));
        user.put(KEY_MSG_PASSWORD, sharedPreferences.getString(KEY_MSG_PASSWORD, null));
        user.put(KEY_MSG_BRAND_NAME, sharedPreferences.getString(KEY_MSG_BRAND_NAME, null));
        user.put(KEY_LEAVE_REJECTED_MESSAGE, sharedPreferences.getString(KEY_LEAVE_REJECTED_MESSAGE, null));
        user.put(KEY_LEAVE_APPROVE_MESSAGE, sharedPreferences.getString(KEY_LEAVE_APPROVE_MESSAGE, null));
        user.put(KEY_TOUR_REJECTED_MESSAGE, sharedPreferences.getString(KEY_TOUR_REJECTED_MESSAGE,null));
        user.put(KEY_TOUR_REQUEST_MESSAGE , sharedPreferences.getString(KEY_TOUR_REQUEST_MESSAGE,null));
        user.put(KEY_TOUR_APPROVE_MESSAGE , sharedPreferences.getString(KEY_TOUR_APPROVE_MESSAGE,null));
        user.put(KEY_ONDUTY_APPROVE_MESSAGE , sharedPreferences.getString(KEY_ONDUTY_APPROVE_MESSAGE,null));
        user.put(KEY_ONDUTY_REJECTED_MESSAGE , sharedPreferences.getString(KEY_ONDUTY_REJECTED_MESSAGE,null));
        user.put(KEY_ONDUTY_REQUEST_MESSAGE, sharedPreferences.getString(KEY_ONDUTY_REQUEST_MESSAGE,null));
        return user;
    }

    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        // After logout redirect user to Loing Activity
        Intent i = new Intent(context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        context.startActivity(i);
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }
}
