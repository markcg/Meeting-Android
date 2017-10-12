package com.th.footballmeeting.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.th.footballmeeting.R;
import com.th.footballmeeting.services.ValidationService;

public class CustomerRegisterActivity extends AppCompatActivity {
    ValidationService validator;
    EditText username;
    EditText password;
    EditText rePassword;
    EditText name;
    EditText email;
    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register);
        this.validator = new ValidationService(this);
        this.username = (EditText) findViewById(R.id.username);
        this.password = (EditText) findViewById(R.id.password);
        this.rePassword = (EditText) findViewById(R.id.repassword);
        this.name = (EditText) findViewById(R.id.name);
        this.email = (EditText) findViewById(R.id.email);
        this.phone = (EditText) findViewById(R.id.phone);

        Button register = (Button) findViewById(R.id.confirm);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerRegisterActivity.this, CustomerLoginActivity.class);
                String username = CustomerRegisterActivity.this.username.getText().toString();
                String password = CustomerRegisterActivity.this.password.getText().toString();
                String rePassword = CustomerRegisterActivity.this.rePassword.getText().toString();
                String name = CustomerRegisterActivity.this.name.getText().toString();
                String email = CustomerRegisterActivity.this.email.getText().toString();
                String phone = CustomerRegisterActivity.this.phone.getText().toString();
                if (allFilled(username, password, rePassword, name, email, phone)
                        && validateUsername(username)
                        && validatePassword(password)
                        && validateRePassword(rePassword, password)
                        && validateName(name)
                        && validateEmail(email)
                        && validatePhone(phone))
                    CustomerRegisterActivity.this.startActivity(intent);
            }
        });
    }

    /* Validation */
    public boolean allFilled(String username, String password, String rePassword, String name, String email, String phone) {
        if (validator.isEmapty(username)
                || validator.isEmapty(password)
                || validator.isEmapty(rePassword)
                || validator.isEmapty(name)
                || validator.isEmapty(email)
                || validator.isEmapty(phone)) {
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

    public boolean validateRePassword(String rePassword, String password) {
        if (!validator.compareText(password, rePassword)) {
            validator.alertValidation("Password are not match.");
            return false;
        }

        if (!validator.isValidText(password)) {
            validator.alertValidation("Re-password is incorrect format.\n" +
                    "Please use only a-z, A-Z and 0-9");
            return false;
        }

        if (!validator.isTextShorterThan(password, 4) || !validator.isTextLongerThan(password, 10)) {
            validator.alertValidation("Please input 4-10 characters");
            return false;
        }

        return true;
    }

    public boolean validateName(String name) {
        if (!validator.isValidText(name)) {
            validator.alertValidation("Name is incorrect format.\n" +
                    "Please use only a-z, A-Z and 0-9");
            return false;
        }

        if (!validator.isTextShorterThan(name, 4) || !validator.isTextLongerThan(name, 30)) {
            validator.alertValidation("Please input 4-30 characters");
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
            validator.alertValidation("Please input 10-30 characters");
            return false;
        }

        return true;
    }

    public boolean validatePhone(String phone) {
        if (!validator.isValidNumber(phone)) {
            validator.alertValidation("Phone number is incorrect format.\n" +
                    "Please use only 0-9");
            return false;
        }

        if (!validator.isTextShorterThan(phone, 10) || !validator.isTextLongerThan(phone, 10)) {
            validator.alertValidation("Please input 10 characters");
            return false;
        }

        return true;
    }
}
