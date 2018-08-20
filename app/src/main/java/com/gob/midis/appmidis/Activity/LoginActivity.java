package com.gob.midis.appmidis.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gob.midis.appmidis.Model.Globals;
import com.gob.midis.appmidis.Model.MyErrorMessage;
import com.gob.midis.appmidis.Model.RequestToken;
import com.gob.midis.appmidis.Model.TokenResponse;
import com.gob.midis.appmidis.Model.mUsuario;
import com.gob.midis.appmidis.R;
import com.gob.midis.appmidis.SWebClient.APIClient;
import com.gob.midis.appmidis.SWebClient.iTokenConnect;
import com.gob.midis.appmidis.SWebClient.iUsuario;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {
    private EditText editUsuario;
    private EditText editContrasena;
    private Button buttonIngresar;
    private SharedPreferences preferences;
    ProgressDialog pgLoading;

    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editUsuario = (EditText) findViewById(R.id.editUsuario);
        editContrasena = (EditText) findViewById(R.id.editContrasena);
        buttonIngresar = (Button) findViewById(R.id.buttonIngresar);
        preferences = getSharedPreferences("Credentials", Context.MODE_PRIVATE);
        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IniciarSession();
            }
        });
    }

    private void IniciarSession() {

        pgLoading = new ProgressDialog(LoginActivity.this);
        pgLoading.setMessage("Espere por favor...");
        pgLoading.setCancelable(false);
        pgLoading.show();

        if (validarDatos()) {
            try {
                String Username = editUsuario.getText().toString().toUpperCase();
                String Password = editContrasena.getText().toString().toUpperCase();
                i = new Intent(LoginActivity.this, MainActivity.class);
                i.setFlags(i.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);

                //ValidarUsuarioSP(Username, Password);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Ingrese correctamente el usuario o la contraseña ", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

/*    private boolean ValidarSP() {
        boolean response = true;
        String spUsuario = preferences.getString("usuario", "");
        String spPassword = preferences.getString("clave", "");
        Toast.makeText(getApplicationContext(), "Usuario Preference: " + spUsuario, Toast.LENGTH_SHORT).show();
        if (!TextUtils.isEmpty(spUsuario)) {
            response = false;
        }
        Toast.makeText(getApplicationContext(), "Existe Preference: " + response, Toast.LENGTH_SHORT).show();
        return response;
    }*/

    private boolean validarDatos() {
        boolean response = true;
        if (editUsuario.getText().toString().isEmpty()) {
            editUsuario.setError("Ingrese Usuario");
            editUsuario.requestFocus();
            return false;
        }
        if (editContrasena.getText().toString().isEmpty()) {
            editContrasena.setError("Ingrese Contraseña");
            editContrasena.requestFocus();
            return false;
        }
        return response;
    }

    public void saveOnPreferences(String usuario, String contrasenna) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("usuario", usuario);
        editor.putString("clave", contrasenna);
        editor.commit();
        editor.apply();
    }

    public void ValidarUsuarioSP(String Usuario, String Password) {
        String spUsuario = preferences.getString("usuario", "");
        String  spPassword= preferences.getString("clave", "");
        if (Usuario.equals(spUsuario)) {
            if (Password.equals(spPassword)) {
                if (pgLoading != null && pgLoading.isShowing()) {
                    pgLoading.dismiss();
                }
                Toast.makeText(getApplicationContext(), "Acceso por SharedPreference", Toast.LENGTH_LONG).show();
                i = new Intent(LoginActivity.this, MainActivity.class);
                i.setFlags(i.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);
            } else {
                ValidarUsuarioWS(Usuario, Password);
            }
        } else {
            ValidarUsuarioWS(Usuario, Password);
        }
    }

    public void ValidarUsuarioWS(String Usuario, String Password) {
        final String lUsuario = Usuario;
        final String lPassword = Password;
 /*       Map<String, String> map = new HashMap();
        map.put("client_id","SPA-APPLICATION-WSDW1S-SDW5S-W6S6S-WD88");
        map.put("client_secret", "1268-asd5-5sdw5-5sdw5s-s4s44-w2w3s");
        map.put("code", "https://localhost:44354/");
        map.put("grant_type", "client_credentials");
        map.put("scope", "API");*/

        Map<String, String> map = new HashMap();
        map.put("client_id", "Intrepido");
        map.put("client_secret", "Dalowos");
        map.put("grant_type", "password");
        map.put("username", "123");
        map.put("password", "123");
        //RequestToken requestToken = new RequestToken("SPA-APPLICATION-WSDW1S-SDW5S-W6S6S-WD88","https://localhost:44354/","client_credentials","client_credentials", "API" );

        try {
            //---------------------Solicitar Token mediante post retrofit---------------------------
            iTokenConnect apiService1 = APIClient.getClient().create(iTokenConnect.class);
            Call<TokenResponse> call2 = apiService1.getToken(map);
            call2.enqueue(new Callback<TokenResponse>() {
                @Override
                public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                    System.out.println("Codigo Request:: " + response.code());
                    //----------------------Si existe Usuario-----------------------
                    if (!response.isSuccessful()) {
                        Gson gson = new Gson();
                        MyErrorMessage message = gson.fromJson(response.errorBody().charStream(), MyErrorMessage.class);
                        //Toast.makeText(getApplicationContext(), "Advertencia: Credenciales de aplicación no válidas - " + message.geterror_description(), Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Advertencia: Credenciales de aplicación no válidas - ", Toast.LENGTH_LONG).show();
                        if (pgLoading != null && pgLoading.isShowing()) {
                            pgLoading.dismiss();
                        }
                    } else {
                        //-----Asignación del Token a la clase TokenResponse.java
                        TokenResponse tokenResponse = response.body();

                        //------------------------------------------Log ----------------------------------------
                        System.out.println("Código de respuesta del API para la solicitud de Token: " + response.body());
                        //------------------------------------------Log ----------------------------------------

                        //-----Asignación del Token a la clase Globals.Token
                        Globals VariablesGlobales = Globals.getInstance();
                        VariablesGlobales.setTokenKey("bearer " + tokenResponse.getaccess_token());
                        String lToken = VariablesGlobales.getTokenKey();//Variable Local
                        //-------------------------------------------------------------------------
                        //------------------------------------------Log ----------------------------------------
                        System.out.println("Token Instaciado LoginActivity: " + response.body());
                        //-----------------------------------------+-Log ----------------------------------------

                        //----------------------------------------------------------------------Validar Usuario------------------------------------------------------------------------
                        Map<String, String> map_usuario = new HashMap();
                        map_usuario.put("vNombreUsuario", lUsuario);
                        map_usuario.put("vPassword", lPassword);
                        Toast.makeText(getApplicationContext(), "lUsuario: " + lUsuario, Toast.LENGTH_SHORT).show();
                        try {
                            System.out.println("Token Instaciado LoginActivity: " + lToken);
                            //------------------------------------------Log ----------------------------------------

                            //---------------Solicitar Nombre de Usuario---------------
                            final iUsuario apiService = APIClient.getClient().create(iUsuario.class);
                            Call<mUsuario> callService = apiService.ValidarUsuario(lToken, map_usuario);
                            callService.enqueue(new Callback<mUsuario>() {
                                @Override
                                public void onResponse(Call<mUsuario> call, Response<mUsuario> response) {
                                    // -----------Log -----------------------------------
                                    System.out.println("Response status code LoginActivity: " + response.code());
                                    //----------------------------Log -----------------------------------

                                    //----------------------Si existe Usuario--------------------------------------------------------------------------------------------
                                    mUsuario decodedResponse = response.body();
                                    if (decodedResponse == null) {
                                        Toast.makeText(getApplicationContext(), "Usuario u/o contraseña no válidas", Toast.LENGTH_SHORT).show();

                                    } else {
                                        //----------------------------Log -----------------------------------
                                        System.out.println("Usuario:" + response.body().getvNombreUsuario());
                                        //-------------------------------------------------------------------

                                        //----------------------------------Actualizar SharedPreference-----------------------------------
                                        saveOnPreferences(lUsuario, lPassword);
                                        //------------------------------------------------------------------------------------------------

                                        Globals VariablesGlobales = Globals.getInstance();
                                        VariablesGlobales.setUsuarioGlobal(response.body().getvNombreUsuario());

                                        if (pgLoading != null && pgLoading.isShowing()) {
                                            pgLoading.dismiss();
                                        }
                                        i = new Intent(LoginActivity.this, MainActivity.class);
                                        i.setFlags(i.FLAG_ACTIVITY_NEW_TASK | i.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(i);
                                        overridePendingTransition(R.anim.zoom_forward_in, R.anim.zoom_forward_out);

                                        Toast.makeText(getApplicationContext(), "BIENVENIDO USUARIO: " + response.body().getvNombreUsuario(), Toast.LENGTH_SHORT).show();
                                    }
                                    if (pgLoading != null && pgLoading.isShowing()) {
                                        pgLoading.dismiss();
                                    }
                                    //-----------------------------------------------------------------------------------------------------------------------------------------
                                }

                                @Override
                                public void onFailure(Call<mUsuario> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "No se puede establecer conexion con el servidor", Toast.LENGTH_SHORT).show();
                                    if (pgLoading != null && pgLoading.isShowing()) {
                                        pgLoading.dismiss();
                                    }
                                }
                            });
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Advertencia: Inconsitencia de Datos", Toast.LENGTH_LONG).show();
                            e.printStackTrace();

                        }
                        //-------------------------------------------------------------------------------------------------------------------------------------------------------------

                        if (pgLoading != null && pgLoading.isShowing()) {
                            pgLoading.dismiss();
                        }

                    }
                }

                @Override
                public void onFailure(Call<TokenResponse> call, Throwable t) {
                    // Log error here since request failed
                    Toast.makeText(getApplicationContext(), "No se puede establecer conexion con el servidor", Toast.LENGTH_SHORT).show();
                    System.out.println("Error de token: " + t.toString());
                    Log.e("Error Response", t.toString());
                    if (pgLoading != null && pgLoading.isShowing()) {
                        pgLoading.dismiss();
                    }
                }
            });

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Advertencia: Inconsitencia de Datos", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }
}
