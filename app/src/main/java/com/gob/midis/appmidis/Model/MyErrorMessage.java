package com.gob.midis.appmidis.Model;

import com.google.gson.annotations.SerializedName;

public class MyErrorMessage {
    @SerializedName("error")
    private String error;

    @SerializedName("error_description")
    private String error_description;


    public String geterror() {
        return error;
    }

    public void seterror(String error) {
        this.error = error;
    }

    public String geterror_description() {
        return error_description;
    }

    public void seterror_description(String error_description) {
        this.error_description = error_description;
    }

    public MyErrorMessage(String error,String error_description){

        this.seterror(error);
        this.seterror_description(error_description);
    }

}
