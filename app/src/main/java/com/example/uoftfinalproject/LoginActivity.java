package com.example.uoftfinalproject;

import android.content.Intent;
import android.os.Bundle;
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
            return;
        }
        if (!inputValidation.isInputEditTextEmail(edtEmail, tilEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(edtPassword, tilPassword, getString(R.string.error_message_email))) {
            return;
        }
     sqliteDatabaseCall();

    }

    private void sqliteDatabaseCall() {
        if (myDatabaseHandler.checkUser(edtEmail.getText().toString().trim()
                , edtPassword.getText().toString().trim())) {


            Intent accountsIntent = new Intent(this, ProductListActivity.class);
            emptyInputEditText();
            startActivity(accountsIntent);


        } else {
            Toast.makeText(getApplicationContext(),getString(R.string.error_valid_email_password),Toast.LENGTH_LONG).show();

        }
    }
    private void emptyInputEditText() {
        edtEmail.setText(null);
        edtPassword.setText(null);
    }

    @OnClick(R.id.txv_link_signup)
    public void signUpDetails(){
        Intent signUpScreenIntent = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(signUpScreenIntent);
        finish();
    }
}



