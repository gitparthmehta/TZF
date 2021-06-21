package com.tarezameen.foundation.Screens.restApi;


import com.tarezameen.foundation.Screens.restApi.Response.BaseReponseBody;
import com.tarezameen.foundation.Screens.restApi.Response.CommonBaseResponse;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("Role")
    Call<CommonBaseResponse> getRole();

    @POST("signup")
    @FormUrlEncoded
    @Headers("Content-Type:application/x-www-form-urlencoded")
    Call<BaseReponseBody> doRegister(@FieldMap Map<String, String> params);


   @POST("login")
   @FormUrlEncoded
   @Headers("Content-Type:application/x-www-form-urlencoded")
   Call<BaseReponseBody> doLogin(@FieldMap Map<String, String> params);

}
