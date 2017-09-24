package com.th.footballmeeting.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.th.footballmeeting.R;

public class FieldLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_login);

        Button login = (Button) findViewById(R.id.email_sign_in_button);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FieldLoginActivity.this, FieldActivity.class);
                FieldLoginActivity.this.startActivity(intent);
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
}
