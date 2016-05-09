package com.liferay.ldxdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.liferay.ldxdemo.BuildConfig;
import com.liferay.ldxdemo.R;
//import com.liferay.ldxdemo.beacon.NotificationUtil;
import com.liferay.ldxdemo.notification.PushService;
import com.liferay.ldxdemo.notification.SnackbarUtil;
import com.liferay.mobile.screens.auth.login.LoginListener;
import com.liferay.mobile.screens.auth.login.LoginScreenlet;
import com.liferay.mobile.screens.context.User;

/**
 * @author Javier Gamarra
 */
public class MainActivity extends AppCompatActivity implements LoginListener, View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		LoginScreenlet loginScreenlet = (LoginScreenlet) findViewById(R.id.login_screenlet);
		loginScreenlet.setListener(this);

		if (BuildConfig.DEBUG) {
			setDefaultValuesForUserAndPassword();
		}

		findViewById(R.id.sign_up).setOnClickListener(this);
	}

	@Override
	public void onLoginSuccess(User user) {
		startActivity(new Intent(this, MenuActivity.class));
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				String title = getString(R.string.app_slogan);
				String description = "Near our store today? Hurry in and use your 25% off our Spring Shoe Sale! Click for details.";
				PushService.createGlobalNotification(title, description, getApplicationContext());
			}
		}, 15000);

	}

	@Override
	public void onLoginFailure(Exception e) {
		SnackbarUtil.showMessage(this, "Login failed!");
	}

	private void setDefaultValuesForUserAndPassword() {
		EditText login = (EditText) findViewById(R.id.liferay_login);
		login.setText(R.string.default_user);

		EditText password = (EditText) findViewById(R.id.liferay_password);
		password.setText(R.string.default_password);
	}

	@Override
	public void onClick(View v) {
		startActivity(new Intent(this, SignUpActivity.class));
	}
}
