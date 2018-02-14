package com.th.footballmeeting.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.th.footballmeeting.MainApplication;
import com.th.footballmeeting.R;
import com.th.footballmeeting.model.Customer;
import com.th.footballmeeting.services.ValidationService;
import com.th.footballmeeting.services.models.UserService;

/**
 * A login screen that offers login via email/password.
 */
public class CustomerLoginActivity extends AppCompatActivity {
    static ValidationService validator;
    static UserService userService;
    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.validator = new ValidationService(this);
        this.username = (EditText) findViewById(R.id.username);
        this.password = (EditText) findViewById(R.id.password);

        Button forgotPassword = (Button) findViewById(R.id.forgot);
        forgotPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerLoginActivity.this, ForgotPasswordActivity.class);
                CustomerLoginActivity.this.startActivity(intent);
            }
        });

        this.userService = new UserService(new UserService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                if(status){
                    MainApplication app = (MainApplication) getApplication();
                    app.user = (Customer) obj;
                    Intent intent = new Intent(CustomerLoginActivity.this, CustomerActivity.class);
                    CustomerLoginActivity.this.startActivity(intent);
                } else {
                    validator.alertValidation("Username or password is incorrect");
                }
            }
        });

        Button login = (Button) findViewById(R.id.email_sign_in_button);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = CustomerLoginActivity.this.username.getText().toString();
                String password = CustomerLoginActivity.this.password.getText().toString();

                if (allFilled(username, password)
                        && validateUsername(username)
                        && validatePassword(password))
                    CustomerLoginActivity.this.userService.login(username, password);
            }
        });

//        Button field = (Button) findViewById(R.id.field_login);
//        field.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CustomerLoginActivity.this, FieldLoginActivity.class);
//                CustomerLoginActivity.this.startActivity(intent);
//            }
//        });

        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerLoginActivity.this, CustomerRegisterActivity.class);
                CustomerLoginActivity.this.startActivity(intent);
            }
        });
    }

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

    public boolean validatePassword(String password) {
        if (!validator.isValidText(password)) {
            validator.alertValidation("Password is incorrect format.\n" +
                    "Please use only a-z, A-Z and 0-9");
            return false;
        }

        if (!validator.isTextShorterThan(password, 4) || !validator.isTextLongerThan(password, 10)) {
            validator.alertValidation("Please input 4-10 characters in the password");
            return false;
        }

        return true;
    }
}

