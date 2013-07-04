package com.example.viewpagerexample;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.utils.ImageCycler;

public class OpeningActivity extends Activity implements OnClickListener{

	private ImageView button1;
	private ImageView button2;
	private ImageView button3;
	private ImageView button4;

	private ImageCycler picCycler1;
	private ImageCycler picCycler2;
	private ImageCycler picCycler3;
	private ImageCycler picCycler4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_opening);

		AudioManager mgr = (AudioManager) getSystemService(OpeningActivity.AUDIO_SERVICE);
		mgr.setStreamMute(AudioManager.STREAM_SYSTEM, true);

		button1 = (ImageView) findViewById(R.id.openning_iv1);
		button2 = (ImageView) findViewById(R.id.openning_iv2);
		button3 = (ImageView) findViewById(R.id.openning_iv3);
		button4 = (ImageView) findViewById(R.id.openning_iv4);
		
		// Uncomment this if round buttons return to active duty
		// button1.setOnClickListener(this);
		// button2.setOnClickListener(this);
		// button3.setOnClickListener(this);
		// button4.setOnClickListener(this);

		picCycler1 = new ImageCycler(xmlArrayToDrawableArray(R.array.openning_screen_small_1));
		picCycler2 = new ImageCycler(xmlArrayToDrawableArray(R.array.openning_screen_small_2));
		picCycler3 = new ImageCycler(xmlArrayToDrawableArray(R.array.openning_screen_big_array));
		picCycler4 = new ImageCycler(xmlArrayToDrawableArray(R.array.openning_screen_small_3));

		button1.setImageDrawable(picCycler1);
		button2.setImageDrawable(picCycler2);
		button3.setImageDrawable(picCycler3);
		button4.setImageDrawable(picCycler4);

		findViewById(R.id.openingScreenLayout).setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Intent intent = new Intent(OpeningActivity.this,
								MainActivity.class);
						startActivity(intent);
						overridePendingTransition(R.anim.splashfadein,
								R.anim.splashfadeout);

					}
				});
	}
	
	private Drawable[] xmlArrayToDrawableArray(int arrayName){
		Resources res = getResources();
		TypedArray icons = res.obtainTypedArray(arrayName);
		Drawable[] drawables = new Drawable[icons.length()];
		for (int i = 0; i < icons.length(); i++) {
			drawables[i] = icons.getDrawable(i);
			
		}
		icons.recycle();
		return drawables;
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		picCycler1.startTransition(1000, 1500);
		picCycler2.startTransition(1000, 1500);
		picCycler3.startTransition(1000, 1500);
		picCycler4.startTransition(1000, 1500);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		AudioManager mgr = (AudioManager) getSystemService(OpeningActivity.AUDIO_SERVICE);
		mgr.setStreamMute(AudioManager.STREAM_SYSTEM, false);
	}

	@Override
	public void onClick(View v) {
		int index;
		switch (v.getId()) {
		case R.id.openning_iv1:
			index = picCycler1.getCurrentDrawableIndex();
			Log.d("YO", "resource1 index is:::" + String.valueOf(index));
			startActivityFromRoundPic(index, 1);
			break;
		case R.id.openning_iv2:
			index = picCycler2.getCurrentDrawableIndex();
			Log.d("YO", "resource2 index is:::" + String.valueOf(index));
			startActivityFromRoundPic(index, 2);
			break;
		case R.id.openning_iv3:
			index = picCycler3.getCurrentDrawableIndex();
			Log.d("YO", "resource3 index is:::" + String.valueOf(index));
			startActivityFromRoundPic(index, 3);
			break;
		case R.id.openning_iv4:
			index = picCycler4.getCurrentDrawableIndex();
			Log.d("YO", "resource4 index is:::" + String.valueOf(index));
			startActivityFromRoundPic(index, 4);
			break;
		case R.id.openning_btn_info:
			//TODO show info screen with scrolling credits
			break;
		default:
			break;
		}
		
	}

	private void startActivityFromRoundPic(int index, int cyclerNum) {
		Intent intent = new Intent(OpeningActivity.this, MainActivity.class);
		intent.putExtra("animalPic", index);
		intent.putExtra("cyclerNum", cyclerNum);
		startActivity(intent);
	}
}
