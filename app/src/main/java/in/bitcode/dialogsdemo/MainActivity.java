package in.bitcode.dialogsdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Dialog
    //AlertDialog - title, message, +ve -ve neutral , icon
    //DatePickerDialog
    //TimePickerDialog
    //ProgressDialog
    //Create custom dialogs ****

    private Button btnAlertDialog, btnDatePickerDialog, btnTimePickerDialog, btnCustomDialog;

    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private Dialog loginDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Bitcode");

                builder.setCancelable(false);

                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mt("Dialog dismissed");
                    }
                });

                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        mt("Dialog cancelled");
                    }
                });

                DialogInterface.OnClickListener listener =
                        new AlertDialogButtonListener();

                AlertDialog alertDialog =
                        builder.setMessage("Quit?")
                                .setIcon(R.mipmap.ic_launcher)
                                .setPositiveButton("Yes", listener)
                                .setNegativeButton("No", listener)
                                .setNeutralButton("Cancel", listener)
//                                .setPositiveButton("Yes", new YesListener())
//                                .setNegativeButton("No", new NoListener())
//                                .setNeutralButton("Cancel", null)
                                .create();
                alertDialog.show();
            }
        });

        btnDatePickerDialog.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DatePickerDialog datePickerDialog =
                                new DatePickerDialog(
                                        MainActivity.this,
                                        new MyDateSetListener(),
                                        2022,
                                        0,
                                        17
                                );

                        datePickerDialog.show();
                    }
                }
        );

        btnTimePickerDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog =
                        new TimePickerDialog(
                                MainActivity.this,
                                new MyTimeSetListener(),
                                22,
                                56,
                                false
                        );
                timePickerDialog.show();
            }
        });

        btnCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginDialog = new Dialog(MainActivity.this);

                loginDialog.setTitle("BitCode");
                loginDialog.setContentView(R.layout.login_dialog);

                edtUsername = loginDialog.findViewById(R.id.edtUsername);
                edtPassword = loginDialog.findViewById(R.id.edtPassword);
                btnLogin = loginDialog.findViewById(R.id.btnLogin);

                btnLogin.setOnClickListener(new MyOnLoginButtonClickListener());

                loginDialog.show();
            }
        });

    }

    private class MyOnLoginButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(edtUsername.getText().toString().equals("bitcode") && edtPassword.getText().toString().equals("1111")) {
                mt("Login Successful!");
                loginDialog.dismiss();
            }
            else {
                mt("Login Failed!");
            }
        }
    }

    private class MyTimeSetListener implements TimePickerDialog.OnTimeSetListener {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            btnTimePickerDialog.setText(hourOfDay + " : " + minute);
        }
    }

    private class MyDateSetListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            btnDatePickerDialog.setText(year  +" - " + (month+1) + " - " + dayOfMonth);
        }
    }

    private class AlertDialogButtonListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which == DialogInterface.BUTTON_POSITIVE) {
                mt("Yes");
            }
            if(which == DialogInterface.BUTTON_NEGATIVE) {
                mt("No");
            }
            if(which == DialogInterface.BUTTON_NEUTRAL) {
                mt("Cancel");
            }
        }
    }

    private class YesListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("You selected Yes!");
            System.exit(0);
        }
    }

    private class NoListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            mt("You selected No!");
        }
    }

    private void mt(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private void init() {
        btnAlertDialog = findViewById(R.id.btnAlertDialog);
        btnDatePickerDialog = findViewById(R.id.btnDatePickerDialog);
        btnTimePickerDialog = findViewById(R.id.btnTimePickerDialog);
        btnCustomDialog = findViewById(R.id.btnCustomDialog);
    }
}