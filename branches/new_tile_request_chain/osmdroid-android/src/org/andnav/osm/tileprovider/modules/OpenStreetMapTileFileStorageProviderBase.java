package org.andnav.osm.tileprovider.modules;

import org.andnav.osm.tileprovider.IRegisterReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;

public abstract class OpenStreetMapTileFileStorageProviderBase extends
		OpenStreetMapTileModuleProviderBase {

	private static final Logger logger = LoggerFactory.getLogger(OpenStreetMapTileFileStorageProviderBase.class);

	/** whether the sdcard is mounted read/write */
	private boolean mSdCardAvailable = true;

	private final IRegisterReceiver mRegisterReceiver;
	private MyBroadcastReceiver mBroadcastReceiver;

	public OpenStreetMapTileFileStorageProviderBase(
			final int aThreadPoolSize,
			final int aPendingQueueSize,
			final IRegisterReceiver aRegisterReceiver) {
		super(aThreadPoolSize, aPendingQueueSize);

		checkSdCard();

		mRegisterReceiver = aRegisterReceiver;
		mBroadcastReceiver = new MyBroadcastReceiver();

		final IntentFilter mediaFilter = new IntentFilter();
		mediaFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
		mediaFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
		mediaFilter.addDataScheme("file");
		aRegisterReceiver.registerReceiver(mBroadcastReceiver, mediaFilter);
	}

	private void checkSdCard() {
		final String state = Environment.getExternalStorageState();
		logger.info("sdcard state: " + state);
		mSdCardAvailable = Environment.MEDIA_MOUNTED.equals(state);
	}

	protected boolean getSdCardAvailable() {
		return mSdCardAvailable;
	}

	@Override
	public void detach() {
		if (mBroadcastReceiver != null) {
			mRegisterReceiver.unregisterReceiver(mBroadcastReceiver);
			mBroadcastReceiver = null;
		}
		super.detach();
	}

	protected void onMediaMounted() {
		// Do nothing by default. Override to handle.
	}

	protected void onMediaUnmounted() {
		// Do nothing by default. Override to handle.
	}

	/**
	 * This broadcast receiver will recheck the sd card when the mount/unmount
	 * messages happen
	 *
	 */
	private class MyBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(final Context aContext, final Intent aIntent) {

			final String action = aIntent.getAction();

			checkSdCard();

			if (Intent.ACTION_MEDIA_MOUNTED.equals(action))
				onMediaMounted();
			else if (Intent.ACTION_MEDIA_UNMOUNTED.equals(action))
				onMediaUnmounted();
		}
	}
}
