package com.example.miniproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StaffregisterActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;
    StaffDb DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staffregister);


        username = (EditText) findViewById(R.id.susername);
        password = (EditText) findViewById(R.id.spassword);
        repassword = (EditText) findViewById(R.id.srepassword);
        signup = (Button) findViewById(R.id.sbtnsignup);
        signin = (Button) findViewById(R.id.sbtnsignin);
        DB = new StaffDb(this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(StaffregisterActivity.this, "Please Enter all Fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (repass.equals("12345")) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(StaffregisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), StaffActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(StaffregisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(StaffregisterActivity.this, "User Already Exists! Please Sign in..", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(StaffregisterActivity.this, "Check Retyped Password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), StaffActivity.class);
                startActivity(intent);

            }
        });
    }
}
