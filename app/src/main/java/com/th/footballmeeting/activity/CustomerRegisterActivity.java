package com.th.footballmeeting.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.th.footballmeeting.R;
import com.th.footballmeeting.services.ValidationService;
import com.th.footballmeeting.services.models.UserService;

public class CustomerRegisterActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    ValidationService validator;
    EditText username;
    EditText password;
    EditText rePassword;
    EditText name;
    EditText email;
    EditText phone;

    private GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

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

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


        Button register = (Button) findViewById(R.id.confirm);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkGPSPermission();
            }
        });
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    /* Permission */
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void checkGPSPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                checkGPSPermission();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        10001);
            }
        } else {
            registering();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 10001: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    registering();
                } else {
                    validator.alertValidation("Permission Denied");
                }
                return;
            }
        }
    }

    public void registering() {
        String username = CustomerRegisterActivity.this.username.getText().toString();
        String password = CustomerRegisterActivity.this.password.getText().toString();
        String rePassword = CustomerRegisterActivity.this.rePassword.getText().toString();
        String name = CustomerRegisterActivity.this.name.getText().toString();
        String email = CustomerRegisterActivity.this.email.getText().toString();
        String phone = CustomerRegisterActivity.this.phone.getText().toString();

        final UserService service = new UserService(new UserService.Callback() {
            @Override
            public void callback(boolean status, Object obj) {
                if (status) {
                    validator.successValidation("Register Successful, Please Login.");
                    Intent intent = new Intent(CustomerRegisterActivity.this, CustomerLoginActivity.class);
                    CustomerRegisterActivity.this.startActivity(intent);
                } else {
                    validator.alertValidation("Username or password is incorrect");
                }
            }
        });

        double longitude = this.mLastLocation != null ? this.mLastLocation.getLongitude() : 98.95334243774414;
        double latitude = this.mLastLocation != null ? this.mLastLocation.getLatitude() : 18.7963867351551;
        if (allFilled(username, password, rePassword, name, email, phone)
                && validateUsername(username)
                && validatePassword(password)
                && validateRePassword(rePassword, password)
                && validateName(name)
                && validateEmail(email)
                && validatePhone(phone))
            service.register(username, password, name, email, phone, Double.toString(latitude), Double.toString(longitude));
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

    /* Location Service */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        try {

            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (mLastLocation != null) {
                finish();
            }
        } catch (SecurityException e) {

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
