package com.gob.midis.appmidis.Model;

import com.google.gson.annotations.SerializedName;

public class mUsuario {
    @SerializedName("Id_Usuario")
    private String Id_Usuario;
    @SerializedName("Id_UsuarioRol")
    private String Id_Usuario_Rol;
    @SerializedName("vRol")
    private String vRol;
    @SerializedName("vDNI")
    private String vDNI;
    @SerializedName("vNombreUsuario")
    private String vNombreUsuario;
    @SerializedName("vPassword")
    private String vPassword;
    @SerializedName("bEstado")
    private String bEstado;
    @SerializedName("RecordCount")
    private String RecordCount;

    public mUsuario(String Id_Usuario, String Id_Usuario_Rol,String vRol, String vDNI, String vNombreUsuario, String vPassword, String bEstado, String RecordCount){
        this.setId_Usuario(Id_Usuario);
        this.setId_Usuario_Rol(Id_Usuario_Rol);
        this.setvRol(vRol);
        this.setvDNI(vDNI);
        this.setvNombreUsuario(vNombreUsuario);
        this.setvPassword(vPassword);
        this.setbEstado(bEstado);
        this.setRecordCount(RecordCount);
    }

    public String getId_Usuario() {
        return Id_Usuario;
    }
    public void setId_Usuario(String Id_Usuario) {
        this.Id_Usuario = Id_Usuario;
    }

    public String getId_Usuario_Rol() {
        return Id_Usuario_Rol;
    }
    public void setId_Usuario_Rol(String id_Usuario_Rol) {
        Id_Usuario_Rol = id_Usuario_Rol;
    }

    public String getvRol() {
        return vRol;
    }
    public void setvRol(String vRol) {this.vRol = vRol; }

    public String getvDNI() {
        return vDNI;
    }
    public void setvDNI(String vDNI) {
        vDNI = vDNI;
    }

    public String getvNombreUsuario() {
        return vNombreUsuario;
    }
    public void setvNombreUsuario(String vNombreUsuario) {
        vNombreUsuario = vNombreUsuario;
    }

    public String getvPassword() {
        return vPassword;
    }
    public void setvPassword(String vPassword) {
        vPassword = vPassword;
    }

    public String getbEstado() {
        return bEstado;
    }
    public void setbEstado(String bEstado) {
        bEstado = bEstado;
    }

    public String getRecordCount() {
        return RecordCount;
    }
    public void setRecordCount(String RecordCount) {
        RecordCount = RecordCount;
    }

}
