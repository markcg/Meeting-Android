package com.th.footballmeeting.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.th.footballmeeting.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button field = (Button) findViewById(R.id.field);
        field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, FieldRegisterActivity.class);
                RegisterActivity.this.startActivity(intent);
            }
        });

        Button customer = (Button) findViewById(R.id.customer);
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, CustomerRegisterActivity.class);
                RegisterActivity.this.startActivity(intent);
            }
        });
    }
}
