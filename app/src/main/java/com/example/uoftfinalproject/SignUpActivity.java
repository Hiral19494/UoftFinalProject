package com.example.uoftfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.uoftfinalproject.data.local.InputValidation;
import com.example.uoftfinalproject.data.local.MyDatabaseHandler;
import com.example.uoftfinalproject.model.UserInfo;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.edt_email)
    TextInputEditText edtEmail;
    @BindView(R.id.edt_password)
    TextInputEditText edtPassword;
    @BindView(R.id.edt_username)
    TextInputEditText edtUsername;
    @BindView(R.id.edt_confirm_password)
    TextInputEditText edtConfirmPassword;
    @BindView(R.id.til_username)
    TextInputLayout tilUsername;
    @BindView(R.id.til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.til_confirm_password)
    TextInputLayout tilConfirmPassword;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    MyDatabaseHandler myDatabaseHandler;
    private InputValidation inputValidation;
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        myDatabaseHandler = new MyDatabaseHandler(this);
        inputValidation = new InputValidation(this);
        userInfo = new UserInfo();
    }

    @OnClick(R.id.btn_signup)
    public void signupDetails(){
        if (!inputValidation.isInputEditTextFilled(edtUsername, tilUsername, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(edtEmail, tilEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(edtEmail, tilEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(edtPassword, tilPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(edtPassword, edtConfirmPassword,
                tilConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!myDatabaseHandler.checkUser(edtEmail.getText().toString().trim())) {

            userInfo.setUsername(edtUsername.getText().toString().trim());
            userInfo.setEmail(edtEmail.getText().toString().trim());
            userInfo.setPassword(edtPassword.getText().toString().trim());

            myDatabaseHandler.addUser(userInfo);

            Toast.makeText(getApplicationContext(), getString(R.string.success_message), Toast.LENGTH_LONG).show();
            Intent accountsIntent = new Intent(this, LoginActivity.class);
            emptyInputEditText();
            startActivity(accountsIntent);

        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.error_email_exists), Toast.LENGTH_LONG).show();

        }

    }
    private void emptyInputEditText() {
        edtUsername.setText(null);
        edtEmail.setText(null);
        edtPassword.setText(null);
        edtConfirmPassword.setText(null);
    }

}


