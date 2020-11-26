package com.example.uoftfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uoftfinalproject.data.APIClient;
import com.example.uoftfinalproject.model.UserInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_password)
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_login)
    public void loginDetails(){
        if(edtEmail.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
            Log.i("emial","Enter Email");
        }else if(edtPassword.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
            Log.i("password","Enter Password");
        }else{
            apiLoginCall();
        }

    }

    private void apiLoginCall() {
        Call<UserInfo> userInfoCall = APIClient.getApiInstanceLogin().userLogin(edtEmail.getText().toString(),edtPassword.getText().toString());
        userInfoCall.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if(response.isSuccessful()){
                    Intent homeScreenIntent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(homeScreenIntent);
                    finish();
                    Toast.makeText(getBaseContext(),"Login Successful",Toast.LENGTH_LONG).show();
                    Log.i("login details","Login successful");

                }

            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                t.printStackTrace();
                Toast .makeText(getApplicationContext(),"Email and Password are not matched"+t.getMessage(),Toast.LENGTH_LONG).show();
                Log.i("login details","login failure:"+t.getMessage());
            }
        });
    }
}
