package com.example.mysecondapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mysecondapp.R;

public class LoginActivity extends Activity {

    EditText inputID,inputPassword;
    Button loginButton;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputID = findViewById(R.id.inputID);
        inputPassword = findViewById(R.id.inputPassword);
        loginButton = findViewById(R.id.loginButton);

        sp  = getSharedPreferences("ID&&Password",0);
        final SharedPreferences.Editor editor = sp.edit();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("18888888888","123456");   //固定
                editor.commit();
                String str = inputID.getText().toString();
                startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("inputID",inputID.getText().toString()));
//                if (!sp.getString(inputID.getText().toString(), "").isEmpty())
//                {
//                    if (sp.getString(inputID.getText().toString(), "").equals(inputPassword.getText().toString()))
//                    {
//                        inputID.setText(str);
//                        inputPassword.setText("");
//                        startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("inputID",inputID.getText().toString()));
//                    }
//                    else
//                    {
//                        AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
//                        dialog.setTitle("提示：");
//                        dialog.setMessage("密码为空或密码错误!");
//                        dialog.setPositiveButton("确定", null);
//                        AlertDialog show = dialog.create();
//                        show.show();
//                    }
//                } else {
//                    AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
//                    dialog.setTitle("提示：");
//                    dialog.setMessage("账号为空或账号不存在!");
//                    dialog.setPositiveButton("确定", null);
//                    AlertDialog show = dialog.create();
//                    show.show();
//                }

            }
        });
    }
}
