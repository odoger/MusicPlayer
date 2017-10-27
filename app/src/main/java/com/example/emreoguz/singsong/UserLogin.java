package com.example.emreoguz.singsong;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserLogin extends AppCompatActivity {

    Button btnLogin;
    EditText editUserName,editUserPassword;
    TextView txtResponse;

    //String serverUrl="http://192.168.1.36/Service/GetUser.php",userName,userPassword;
    String remoteServerUrl="http://autocode.xn--emreberkakrmak-fgc.com/GetUser.php",userName,userPassword;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        editUserName = (EditText)findViewById(R.id.editUserName);
        editUserPassword = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        builder = new AlertDialog.Builder(UserLogin.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RequestQueue requestQueue= Volley.newRequestQueue(UserLogin.this);

                userName = editUserName.getText().toString();
                userPassword = editUserPassword.getText().toString();
                if (userName.equals("")|| userPassword.equals("")) {
                    builder.setTitle("Kullanıcı Adı ve Parola yanlış...");
                    displayAlert("Geçerli bilgiler giriniz");
                }
                else{
                    StringRequest stringRequest =new StringRequest(Request.Method.POST, remoteServerUrl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try{
                                JSONObject jsonObject =new JSONObject(response);
                                JSONObject sys =jsonObject.getJSONObject("USER");
                                String userID = sys.getString("ID");
                                userName = sys.getString("userName");
                                userPassword= sys.getString("userPassword");
                                if(userID.equals("-1")){
                                    builder.setTitle("Hatalı Giriş");
                                    displayAlert("Kullanıcı Adı ve Parola yanlış...");
                                }
                                else {
                                    Intent intent =new Intent(UserLogin.this,MainActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("name",userName);
                                    bundle.putString("password",userPassword);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            }
                            catch (Exception ex){
                                Toast.makeText(UserLogin.this,ex.getMessage(),Toast.LENGTH_LONG).show();
                            }

                            requestQueue.stop();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(UserLogin.this,"Hata meydana geldi",Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                            requestQueue.stop();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("userName",userName);
                            params.put("password",userPassword);
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                }



            }
        });
    }

    public void  displayAlert(String message){
        builder.setMessage(message);
        builder.setPositiveButton("Tamam",new DialogInterface.OnClickListener(){
            @Override
            public  void onClick(DialogInterface dialog,int which){
                editUserName.setText("");
                editUserPassword.setText("");

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
