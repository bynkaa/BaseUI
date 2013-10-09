package com.qsoft.BaseUI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity
{

    public static final String USER_NAME = "android";
    public static final String PASSWORD = "123456";
    public static final String INCORRECT_USER_MESSAGE = "incorrect user";
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
