package com.example.uoftfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uoftfinalproject.data.local.MyDatabaseHandler;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_confirm_password)
    EditText edtConfirmPassword;
    MyDatabaseHandler myDatabaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        myDatabaseHandler = new MyDatabaseHandler(this);
    }

    @OnClick(R.id.btn_signup)
    public void signupDetails(){
        if(edtUsername.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter Username", Toast.LENGTH_SHORT).show();
            Log.i("username","Enter Usernmae");
        }else if(edtEmail.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
            Log.i("emial","Enter Email");
        }else if(edtPassword.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
            Log.i("password","Enter Password");
        }else if(edtConfirmPassword.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), "Enter Confirm Password", Toast.LENGTH_SHORT).show();
            Log.i("password","Enter Confrim Password");
        }else if(!edtConfirmPassword.getText().toString().equals(edtPassword.getText().toString())){
            Toast.makeText(getApplicationContext(), "Password not matched", Toast.LENGTH_SHORT).show();
            Log.i("match condition","Password not matched");
        }else{
            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
            sqliteSignUpCall();
        }

    }

    private void sqliteSignUpCall() {
         myDatabaseHandler.addUser(edtUsername.getText().toString(),edtEmail.getText().toString(),edtUsername.getText().toString());

            Toast.makeText(getApplicationContext(), "Registration Successfully", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);

    }


}
