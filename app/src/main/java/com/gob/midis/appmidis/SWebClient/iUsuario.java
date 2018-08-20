package com.gob.midis.appmidis.SWebClient;

import com.gob.midis.appmidis.Model.mUsuario;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface iUsuario {

    @FormUrlEncoded
    @POST("api/Usuario/ValidarUsuario")
    Call<mUsuario> ValidarUsuario(@Header("Authorization") String token, @FieldMap Map<String, String> map);
}
