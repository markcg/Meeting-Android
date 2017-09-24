package com.th.footballmeeting.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.th.footballmeeting.R;

/**
 * A login screen that offers login via email/password.
 */
public class CustomerLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button forgotPassword = (Button) findViewById(R.id.forgot);
        forgotPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerLoginActivity.this, ForgotPasswordActivity.class);
                CustomerLoginActivity.this.startActivity(intent);
            }
        });

        Button login = (Button) findViewById(R.id.email_sign_in_button);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerLoginActivity.this, CustomerActivity.class);
                CustomerLoginActivity.this.startActivity(intent);
            }
        });

        Button field = (Button) findViewById(R.id.field_login);
        field.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerLoginActivity.this, FieldLoginActivity.class);
                CustomerLoginActivity.this.startActivity(intent);
            }
        });

        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerLoginActivity.this, RegisterActivity.class);
                CustomerLoginActivity.this.startActivity(intent);
            }
        });
    }
}

