package com.example.uoftfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.uoftfinalproject.data.local.InputValidation;
import com.example.uoftfinalproject.data.local.MyDatabaseHandler;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.edt_email)
    TextInputEditText edtEmail;
    @BindView(R.id.edt_password)
    TextInputEditText edtPassword;
    @BindView(R.id.til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    MyDatabaseHandler myDatabaseHandler;
    private InputValidation inputValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        myDatabaseHandler = new MyDatabaseHandler(this);
        inputValidation = new InputValidation(this);

    }

    @OnClick(R.id.btn_login)
    public void loginDetails(){
        if (!inputValidation.isInputEditTextFilled(edtEmail, tilEmail, getString(R.string.error_message_email))) {
            Log.i("Email Validation","Enter  Email");
            return;
        }
        if (!inputValidation.isInputEditTextEmail(edtEmail, tilEmail, getString(R.string.error_message_email))) {
            Log.i("Email Validation","Enter  valid Email");
            return;
        }
        if (!inputValidation.isInputEditTextFilled(edtPassword, tilPassword, getString(R.string.error_message_password))) {
            Log.i("Password Validation","Enter Password");
            return;
        }
     sqliteDatabaseCall();

    }

    private void sqliteDatabaseCall() {
        if (myDatabaseHandler.checkUser(edtEmail.getText().toString().trim()
                , edtPassword.getText().toString().trim())) {


            Intent accountsIntent = new Intent(this, ProductListActivity.class);
            Log.i("Login Successful","Login Successful");
            emptyInputEditText();
            startActivity(accountsIntent);


        } else {
            Toast.makeText(getApplicationContext(),getString(R.string.error_valid_email_password),Toast.LENGTH_LONG).show();
            Log.i("Login error","Wrong Email or Password");

        }
    }
    private void emptyInputEditText() {
        edtEmail.setText(null);
        edtPassword.setText(null);
        Log.i("EditText ","all edit text null");
    }

    @OnClick(R.id.txv_link_signup)
    public void signUpDetails(){
        Log.i("Sign up ","Sign Up class called");
        Intent signUpScreenIntent = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(signUpScreenIntent);
        finish();
    }
}



