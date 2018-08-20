package com.gob.midis.appmidis.Model;

import com.google.gson.annotations.SerializedName;

public class TokenResponse {
    @SerializedName("access_token")
    private String access_token;

    @SerializedName("expires_in")
    private String expires_in;

    @SerializedName("token_type")
    private String token_type;


    @SerializedName("error")
    private String error;

    @SerializedName("error_description")
    private String error_description;

    public TokenResponse(String access_token,String token_type, String expires_in,String error,String error_description){
        System.out.println("Token__________________________________Hocal " + access_token + token_type + expires_in+ error+ error_description);

        this.setaccess_token(access_token);
        this.settoken_type(token_type);
        this.setexpires_in(expires_in);
        this.seterror(error);
        this.seterror_description(error_description);
    }


    public String getaccess_token() {
        return access_token;
    }
    public void setaccess_token(String access_token) { this.access_token = access_token;}

    public String gettoken_type() {
        return token_type;
    }
    public void settoken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getexpires_in() {
        return expires_in;
    }
    public void setexpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String geterror() {
        return error;
    }
    public void seterror(String error) { this.error = error; }

    public String geterror_description() {
        return error_description;
    }
    public void seterror_description(String error_description) { this.error_description = error_description; }
}
