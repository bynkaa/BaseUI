package com.qsoft.BaseUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity
{
    public static final String EXTRA_NAME = "extra.name";
    private static final String TAG = "Login";
    private static final String USER_NAME = "android";
    private static final String PASSWORD = "123456";
    private static final String INCORRECT_USER_MESSAGE = "incorrect user";

    Button btLogin;
    EditText etUsername;
    EditText etPassword;
    boolean isUser = false;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etUsername = (EditText) findViewById(R.id.login_etUsername);
        etPassword = (EditText) findViewById(R.id.login_etPassword);
        btLogin = (Button) findViewById(R.id.login_btLogin);

        addClickListenerBtLogin();
    }

    private void addClickListenerBtLogin()
    {
        btLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // do something here
                String userName = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                if (!userName.isEmpty() && !password.isEmpty())
                {
                    isUser = validateUser(userName, password);
                }

                if (!isUser)
                {
                    Toast.makeText(Login.this, INCORRECT_USER_MESSAGE, Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d(TAG,"login successful");
                    Intent intent = new Intent(Login.this,Profile.class);
                    Log.d(TAG,"intent created");
                    intent.putExtra(EXTRA_NAME,userName);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateUser(String userName, String password)
    {
        if (userName.equals(USER_NAME) && password.equals(PASSWORD))
            return true;
        return false;  //To change body of created methods use File | Settings | File Templates.
    }
}
