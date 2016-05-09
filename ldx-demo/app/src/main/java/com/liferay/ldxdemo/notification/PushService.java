package com.liferay.ldxdemo.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.liferay.ldxdemo.R;
import com.liferay.ldxdemo.activities.MenuActivity;
import com.liferay.ldxdemo.fragments.WalletFragment;
import com.liferay.mobile.screens.push.AbstractPushService;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Javier Gamarra
 */
public class PushService extends AbstractPushService {

	public static final int NOTIFICATION_ID = 2;

	@Override
	protected void processJSONNotification(final JSONObject json) throws JSONException {
		String title = getString(R.string.app_slogan);
		String description = "Near our store today? Hurry in and use your 25% off our Spring Shoe Sale! Click for details.";

		createGlobalNotification(title, description, this);
	}

	public static void createGlobalNotification(String title, String description, Context context) {
		Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
				.setContentTitle(title)
				.setContentText(description)
				.setAutoCancel(true)
				.setSound(uri)
				.setVibrate(new long[]{2000, 1000, 2000, 1000})
				.setSmallIcon(R.drawable.glyph);

		builder.setContentIntent(createPendingIntentForNotifications(context));

		Notification notification = builder.build();
		NotificationManager notificationManager =
				(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(NOTIFICATION_ID, notification);
	}

	private static PendingIntent createPendingIntentForNotifications(Context context) {
		Intent resultIntent = new Intent(context, MenuActivity.class);
		resultIntent.putExtra("fragmentId", R.id.wallet);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		stackBuilder.addNextIntent(resultIntent);
		return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
	}
}
