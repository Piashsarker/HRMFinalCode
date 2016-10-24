package com.karnaphuli.www.kportal.retrofit;

import com.karnaphuli.www.kportal.List.AttendanceList;
import com.karnaphuli.www.kportal.List.ChargeHandOverList;
import com.karnaphuli.www.kportal.List.ContactList;
import com.karnaphuli.www.kportal.List.DistrictList;
import com.karnaphuli.www.kportal.List.GroupOfCompanyList;
import com.karnaphuli.www.kportal.List.InboxList;
import com.karnaphuli.www.kportal.List.LeaveDaysRequiredList;
import com.karnaphuli.www.kportal.List.LeaveReportList;
import com.karnaphuli.www.kportal.List.LeaveTypeList;
import com.karnaphuli.www.kportal.List.LoginList;
import com.karnaphuli.www.kportal.List.MonthList;
import com.karnaphuli.www.kportal.List.OnDutyList;
import com.karnaphuli.www.kportal.List.OnTourList;
import com.karnaphuli.www.kportal.List.OutboxList;
import com.karnaphuli.www.kportal.List.PendingLeaveList;
import com.karnaphuli.www.kportal.List.PendingOnDutyList;
import com.karnaphuli.www.kportal.List.PendingTourViewList;
import com.karnaphuli.www.kportal.List.ThanaList;
import com.karnaphuli.www.kportal.List.YearList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 08-Sep-16.
 */
public interface ApiService {

    @GET("login.aspx")
    Call<LoginList> getLoginDetails(
            @Query("key") String key,
            @Query("user") String user,
            @Query("pass") String pass);

    @GET("EmailServices.aspx")
    Call<InboxList> getInboxList(@Query("key") String key,
                                 @Query("type") String type,
                                 @Query("emp") Integer empId);

    @GET("EmailServices.aspx")
    Call<OutboxList> getOutboxList(@Query("key") String key,
                                   @Query("type") String type,
                                   @Query("emp") Integer empId);

    @GET("contacts.aspx")
    Call<ContactList> getContactList(@Query("key") String key);

    @GET("leave.aspx")
    Call<LeaveReportList> getLeaveReportList(@Query("key") String key,
                                             @Query("actionType") String actionType,
                                             @Query("empId") Integer empId);

    @GET("leave.aspx")
    Call<LeaveTypeList> getLeaveTypeList(@Query("key") String key,
                                         @Query("actionType") String actionType,
                                         @Query("empId") String empId);

    @GET("leave.aspx")
    Call<OnDutyList> getOnDutyList(@Query("key") String key,
                                   @Query("actionType") String actionType,
                                   @Query("empId") String empId);

    @GET("leave.aspx")
    Call<ChargeHandOverList> getChargeOver(@Query("key") String key,
                                           @Query("actionType") String actionType,
                                           @Query("empId") Integer empId);

    @GET("leave.aspx")
    Call<PendingLeaveList> getPendingLeave(@Query("key") String key,
                                           @Query("actionType") String actionType,
                                           @Query("empId") Integer empId);

    @GET("leave.aspx")
    Call<PendingOnDutyList> getPendingOnDuty(@Query("key") String key,
                                             @Query("actionType") String actionType,
                                             @Query("empId") Integer empId);

    @GET("leave.aspx")
    Call<PendingTourViewList> getPendingTour(@Query("key") String key,
                                             @Query("actionType") String actionType,
                                             @Query("empId") Integer empId);

    @GET("leave.aspx")
    Call<OnTourList> getTourReportList(@Query("key") String key,
                                       @Query("actionType") String actionType,
                                       @Query("empId") String empId);

    @GET("leave.aspx")
    Call<MonthList> getMonth(@Query("key") String key,
                             @Query("actionType") String actionType,
                             @Query("empId") String empId);

    @GET("leave.aspx")
    Call<ResponseBody> postPendingLeaveApproved(@Query("key") String key,
                                                @Query("actionType") String actionType,
                                                @Query("empId") String empId, @Query("applicationId") String applicationId);

    @GET("leave.aspx")
    Call<ResponseBody> postPendingLeaveReject(@Query("key") String key,
                                              @Query("actionType") String actionType,
                                              @Query("empId") String empId, @Query("applicationId") String applicationId);

    @GET("leave.aspx")
    Call<ResponseBody> postPendingTourReject(@Query("key") String key,
                                              @Query("actionType") String actionType,
                                              @Query("empId") String empId, @Query("tourid") String applicationId);

    @GET("leave.aspx")
    Call<ResponseBody> postPendingTourApproved(@Query("key") String key,
                                             @Query("actionType") String actionType,
                                             @Query("empId") String empId, @Query("tourid") String applicationId);
    @GET("leave.aspx")
    Call<ResponseBody> postPendingOnDutyApproved(@Query("key") String key,
                                               @Query("actionType") String actionType,
                                               @Query("empId") String empId, @Query("tourid") String applicationId);
    @GET("leave.aspx")
    Call<ResponseBody> postPendingOnDutyReject(@Query("key") String key,
                                               @Query("actionType") String actionType,
                                               @Query("empId") String empId, @Query("tourid") String applicationId);
    @GET("leave.aspx")
    Call<YearList> getYear(@Query("key") String key,
                           @Query("actionType") String actionType,
                           @Query("empId") String empId);

    @GET("attendance.aspx")
    Call<AttendanceList> getAttendanceList(@Query("key") String key, @Query("empId") String empId, @Query("YearId") String yearId
            , @Query("MonthId") String monthId);


    @POST("leave.aspx")
    Call<ResponseBody> postLeave(@Query("key") String key,
                                 @Query("actionType") String actionType,
                                 @Query("empId") String empId,
                                 @Query("fromDate")
                                 String fromDate,
                                 @Query("toDate")
                                 String toDate,
                                 @Query("leavePhone")
                                 String leavePhone,
                                 @Query("leaveType") Integer leaveType,
                                 @Query("purpose")
                                 String purpose,
                                 @Query("updatedBy")
                                 String updatedBy,
                                 @Query("handOver")
                                 String handOver);


    @POST("onDuty.aspx")
    Call<ResponseBody> postOnDuty(@Query("key") String key,
                                  @Query("EmployeeId") String EmployeeId,
                                  @Query("SubmissionDate")
                                  String SubmissionDate,
                                  @Query("FromDate") String FromDate,
                                  @Query("ToDate") String ToDate,
                                  @Query("Purpuse") String Purpose,
                                  @Query("UpdateBy") String UpdateBy,
                                  @Query("ApprovedBy")
                                  String ApprovedBy,
                                  @Query("ReportingTo") String ReportingTo);

    @POST("onTour.aspx")
    Call<ResponseBody> postOnTour(@Query("key") String key,
                                  @Query("EmployeeId") String EmployeeId,
                                  @Query("SubmissionDate")
                                  String SubmissionDate,
                                  @Query("FromDate") String FromDate,
                                  @Query("ToDate") String ToDate,
                                  @Query("Purpuse") String Purpose,
                                  @Query("UpdateBy") String UpdateBy,
                                  @Query("ApprovedBy")
                                  String ApprovedBy,
                                  @Query("ReportingTo") String ReportingTo,
                                  @Query("areaId")String areaId);

    @POST("EmailServices.aspx")
    Call<ResponseBody> postMessage(@Query("key") String key, @Query("type") String type, @Query("from") String from, @Query("to") String to, @Query("subject") String subject,
                                   @Query("msg") String msg);

    @GET("leave.aspx")
    Call<DistrictList> getDistrict (@Query("key") String key,
                                    @Query("actionType") String actionType,
                                    @Query("empId") String empId);
    @GET("leave.aspx")
    Call<ThanaList> getThana (@Query("key") String key,
                              @Query("actionType") String actionType,
                              @Query("empId") String empId,
                              @Query("districtId")String districtId);
    @POST("EmailServices.aspx")
    Call<ResponseBody> postReadStatus(@Query("key") String key ,
                                      @Query("type")String type,
                                      @Query("employeeMailId") String employeeMailId);


    @GET("leave.aspx")
    Call<LeaveDaysRequiredList> getLeaveDays (@Query("key") String key,
                                              @Query("actionType") String actionType,
                                              @Query("empId") String empId,
                                              @Query("leavetypeId")Integer leaveTypeId,
                                              @Query("fromdate")String fromdate,
                                              @Query("todate")String todate);

   @GET(" groupofcompany.aspx")
    Call<GroupOfCompanyList> getGroupName(@Query("key") String key);

}
