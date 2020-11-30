package com.example.uoftfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uoftfinalproject.data.local.MyDatabaseHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    MyDatabaseHandler myDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        myDatabaseHandler = new MyDatabaseHandler(this);

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
            sqliteDatabaseCall();
        }

    }

    private void sqliteDatabaseCall() {
        boolean status = (myDatabaseHandler.getLoginData(edtEmail.getText().toString(), edtPassword.getText().toString()));
               Log.d("email",edtEmail.getText().toString());
                Log.d("status", String.valueOf(status));
        if (status) {
            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(getApplicationContext(), "You are not Registerd!", Toast.LENGTH_LONG).show();

        }
    }

    @OnClick(R.id.txv_link_signup)
    public void signUpDetails(){
        Intent signUpScreenIntent = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(signUpScreenIntent);
        finish();
    }



}
