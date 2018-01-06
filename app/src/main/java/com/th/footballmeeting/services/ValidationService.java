package com.th.footballmeeting.services;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;

import java.util.regex.Pattern;

/**
 * Created by macbookpro on 10/12/2017 AD.
 */

public class ValidationService {
    public Activity activity;

    public ValidationService(Activity activity) {
        this.activity = activity;
    }

    public void alertValidation(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this.activity);
        alert.setTitle("Alert");
        alert.setMessage(message);
        alert.setPositiveButton("Close", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
    public void successValidation(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this.activity);
        alert.setTitle("Success");
        alert.setMessage(message);
        alert.setPositiveButton("Close", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }
    public boolean compareText(String original, String comparer){
        if (original.equals(comparer)) {
            return true;
        }
        return false;
    }
    public boolean isValidText(String name) {
        Pattern p = Pattern.compile("[A-Za-z0-9]");
        if (p.matcher(name).find()) {
            return true;
        }
        return false;
    }

    public boolean isValidEmail(String email) {
        Pattern p = Pattern.compile("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
        if (p.matcher(email).find()) {
            return true;
        }
        return false;
    }

    public boolean isValidNumber(String number) {
        Pattern p = Pattern.compile("[0-9]");
        if (p.matcher(number).find()) {
            return true;
        }
        return false;
    }

    public boolean isValidTime(String name) {
        Pattern p = Pattern.compile("[0-9][0-9]\\:[0-9]?[0-9]");
        if (p.matcher(name).find()) {
            return true;
        }
        return false;
    }

    public boolean isTextShorterThan(String text, int length) {
        return text.length() >= length;
    }

    public boolean isTextLongerThan(String text, int length) {
        return text.length() <= length;
    }

    public boolean isEmapty(String text) {
        return text.equals("");
    }
}
