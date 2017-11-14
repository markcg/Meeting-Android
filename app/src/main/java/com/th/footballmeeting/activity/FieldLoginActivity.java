package com.th.footballmeeting.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.th.footballmeeting.R;
import com.th.footballmeeting.services.ValidationService;
import com.th.footballmeeting.services.models.FieldService;
import com.th.footballmeeting.services.models.UserService;

public class FieldLoginActivity extends AppCompatActivity {
    static ValidationService validator;
    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_login);

        this.validator = new ValidationService(this);
        this.username = (EditText) findViewById(R.id.username);
        this.password = (EditText) findViewById(R.id.password);

        Button login = (Button) findViewById(R.id.email_sign_in_button);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FieldLoginActivity.this, FieldActivity.class);
                FieldLoginActivity.this.startActivity(intent);
                String username = FieldLoginActivity.this.username.getText().toString();
                String password = FieldLoginActivity.this.password.getText().toString();

                final FieldService service = new FieldService(new FieldService.Callback() {
                    @Override
                    public void callback(boolean status, Object obj) {
                        if(status){
                            Intent intent = new Intent(FieldLoginActivity.this, CustomerActivity.class);
                            FieldLoginActivity.this.startActivity(intent);
                        } else {
                            validator.alertValidation("Username or password is incorrect");
                        }
                    }
                });

                if (allFilled(username, password)
                        && validateUsername(username)
                        && validatePassword(password))
                    service.login(username, password);
            }
        });

        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FieldLoginActivity.this, RegisterActivity.class);
                FieldLoginActivity.this.startActivity(intent);
            }
        });

        Button forgotPassword = (Button) findViewById(R.id.forgot);
        forgotPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FieldLoginActivity.this, ForgotPasswordActivity.class);
                FieldLoginActivity.this.startActivity(intent);
            }
        });
    }

    /* Validate */
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
            validator.alertValidation("Please input 4-10 characters");
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
            validator.alertValidation("Please input 4-10 characters");
            return false;
        }

        return true;
    }
}
