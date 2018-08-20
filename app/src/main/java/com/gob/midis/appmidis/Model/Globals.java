package com.gob.midis.appmidis.Model;

public class Globals {
    private static Globals instance;
    private String TokenKey;
    private String UsuarioGlobal;


    private Globals(){}

    public void setTokenKey(String d){
        this.TokenKey=d;
    }
    public String getTokenKey(){
        return this.TokenKey;
    }
    public void setUsuarioGlobal(String d){
        this.UsuarioGlobal=d;
    }
    public String getUsuarioGlobal(){
        return this.UsuarioGlobal;
    }


    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }

}
