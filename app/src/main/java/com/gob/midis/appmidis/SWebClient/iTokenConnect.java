package com.gob.midis.appmidis.SWebClient;

import com.gob.midis.appmidis.Model.RequestToken;
import com.gob.midis.appmidis.Model.TokenResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface iTokenConnect {

    @FormUrlEncoded
    @POST("token")
    Call<TokenResponse> getToken(@FieldMap Map<String,String> map);

    //@FormUrlEncoded

/*
    @POST("connect/token")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})

    Call<TokenResponse> getToken(@Body RequestToken map);
*/

/*    @POST("/connect/token")
    @FormUrlEncoded
    Call<TokenResponse> getToken(@Body RequestToken post);*/

    //@GET("api/Usuarios/Get/{id}")
    //Call<mUsuarios> getUsuario(@Header("Authorization") String token, @Path("id") String id);

}
