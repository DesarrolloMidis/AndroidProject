package com.gob.midis.appmidis.Model;

import com.google.gson.annotations.SerializedName;

public class RequestToken {
    @SerializedName("client_id")
    private String client_id;
    @SerializedName("client_secret")
    private String client_secret;
    @SerializedName("code")
    private String code;
    @SerializedName("grant_type")
    private String grant_type;
    @SerializedName("scope")
    private String scope;


    public RequestToken(String client_id,String client_secret, String code, String grant_type, String scope){
        this.setclient_id(client_id);
        this.setclient_secret(client_secret);
        this.setcode(code);
        this.setgrant_type(grant_type);
        this.setscope(scope);

    }

    public String getclient_id() {
        return client_id;
    }

    public void setclient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getclient_secret() {
        return client_secret;
    }

    public void setclient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getcode() {
        return code;
    }

    public void setcode(String code) {
        code = code;
    }

    public String getgrant_type() {
        return grant_type;
    }

    public void setgrant_type(String grant_type) {
        grant_type = grant_type;
    }

    public String getscope() {
        return scope;
    }

    public void setscope(String scope) {
        scope = scope;
    }

}
