package com.example.volunteer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	
	
	 private EditText passwordField,mail;
	 private TextView status;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		mail = (EditText)findViewById(R.id.etEmail);  
        passwordField = (EditText)findViewById(R.id.etPassword);
        
        final Button button = (Button) findViewById(R.id.btSubmit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              login(v);            
            }
        });
		
	}

	protected void login(View v) {
		
		String password = passwordField.getText().toString();
        String email = mail.getText().toString();
        	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
