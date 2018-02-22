package com.th.footballmeeting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.th.footballmeeting.R;
import com.th.footballmeeting.services.ValidationService;
import com.th.footballmeeting.services.models.FieldService;
import com.th.footballmeeting.services.models.UserService;

public class ForgotPasswordActivity extends AppCompatActivity {
    static ValidationService validator;
    static UserService userService;
    static FieldService fieldService;
    EditText username;
    EditText email;
    RadioButton isCustomer;
    RadioButton isField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        this.validator = new ValidationService(this);
        this.username = (EditText) findViewById(R.id.username);
        this.email = (EditText) findViewById(R.id.email);
//        this.isCustomer = (RadioButton) findViewById(R.id.isCustomer);
//        this.isField = (RadioButton) findViewById(R.id.isField);
//
        Button confirm = (Button) findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ForgotPasswordActivity.this.username.getText().toString();
                String email = ForgotPasswordActivity.this.email.getText().toString();
//                boolean selectCustomer = ForgotPasswordActivity.this.isCustomer.isChecked();
//                boolean selectField = ForgotPasswordActivity.this.isField.isChecked();

                userService = new UserService(new UserService.Callback() {
                    @Override
                    public void callback(boolean status, Object obj) {
                        if (status) {
                            ForgotPasswordActivity.this.validator.successValidation("Your new password is sent to your email.");
                            Intent intent = new Intent(ForgotPasswordActivity.this, CustomerLoginActivity.class);
                            ForgotPasswordActivity.this.startActivity(intent);
                        } else {
                            validator.alertValidation("Username or password is incorrect");
                        }
                    }
                });

                fieldService = new FieldService(new UserService.Callback() {
                    @Override
                    public void callback(boolean status, Object obj) {
                        if (status) {
                            validator.successValidation("Your new password is sent to your email.");
                            Intent intent = new Intent(ForgotPasswordActivity.this, FieldLoginActivity.class);
                            ForgotPasswordActivity.this.startActivity(intent);
                        } else {
                            validator.alertValidation("Username or password is incorrect");
                        }
                    }
                });

                if (allFilled(username, email)
                        && validateUsername(username)
                        && validateEmail(email)) {
//                    if (selectCustomer)
                        userService.forgotPassword(username, email);
//                    if (selectField)
//                        fieldService.forgotPassword(username, email);
                }
            }
        });

        Button cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPasswordActivity.this, CustomerLoginActivity.class);
                ForgotPasswordActivity.this.startActivity(intent);
            }
        });
    }

    /* Validator */
    public boolean allFilled(String username, String password) {
        if (validator.isEmapty(username)
                || validator.isEmapty(password)) {
            validator.alertValidation("Please fill in all required text field");
            return false;
        }
        return true;
    }

    public boolean validateUsername(String username) {
        if (!validator.isValidText(username)) {
            validator.alertValidation("Username is incorrect format.\n" +
                    "Please use only a-z, A-Z and 0-9");
            return false;
        }

        if (!validator.isTextShorterThan(username, 4) || !validator.isTextLongerThan(username, 10)) {
            validator.alertValidation("Please input 4-10 characters in the username");
            return false;
        }

        return true;
    }

    public boolean validateEmail(String email) {
        if (!validator.isValidEmail(email)) {
            validator.alertValidation("Email is incorrect format.\n" +
                    "Please use correct email format");
            return false;
        }

        if (!validator.isTextShorterThan(email, 10) || !validator.isTextLongerThan(email, 30)) {
            validator.alertValidation("Please input 10-30 characters in the email");
            return false;
        }

        return true;
    }
}
