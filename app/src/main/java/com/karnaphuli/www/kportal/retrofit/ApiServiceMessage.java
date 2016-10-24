package com.karnaphuli.www.kportal.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by pt on 9/23/16.
 */
public interface ApiServiceMessage {

    @POST("localbulk.php")
    Call<ResponseBody> postMessage(@Query("user") String user,
                                  @Query("pass") String pass,
                                  @Query("to") String to,
                                  @Query("sender") String sender,
                                  @Query("message") String message,
                                  @Query("unicode") String unicode);
}
