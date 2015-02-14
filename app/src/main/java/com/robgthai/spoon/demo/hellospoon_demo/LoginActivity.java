package com.robgthai.spoon.demo.hellospoon_demo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class LoginActivity extends ActionBarActivity {

    EditText edtEmail, edtPassword;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtResult = (TextView) findViewById(R.id.txtResult);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);

        findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(edtEmail.getText().toString())
                        && !TextUtils.isEmpty(edtPassword.getText().toString())) {

                    if(android.util.Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches()) {
                        txtResult.setText("OK");
                    }else {
                        txtResult.setText("Invalid email");
                    }
                }else {
                    txtResult.setText("Invalid login");
                }
            }
        });

        findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                txtResult.setText("");
                edtEmail.setText("");
                edtPassword.setText("");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
