package com.gob.midis.appmidis.SWebClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    //public static final String BASE_URL = "http://192.168.65.178:80/SW_SEG/";//Web
    public static final String BASE_URL = "http://sdv.midis.gob.pe/Sis_ws_seg/";//Web

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
