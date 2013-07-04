package com.example.viewpagerexample;

import java.util.Timer;
import java.util.TimerTask;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.utils.AnimalSounds;

public class MainActivity extends FragmentActivity {

	// private int animalIndexFromOpeningActivity;
	private MyAdapter mAdapter;
	private ViewPager mPager;
	private Button rightArrow;
	private TextView title;

	private boolean playSound = false;
	private Handler handler;
	private Runnable update;
	private Timer swipeTimer;
	
	private AnimalSounds sounds;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);

		// Uncomment this if round buttons return to active duty
		// animalIndexFromOpeningActivity = getIntent().getIntExtra("animalPic",
		// -1);

		title = (TextView) findViewById(R.id.animalNameTV);
		Typeface font = Typeface.createFromAsset(getAssets(), "arial_bold.ttf");
		title.setTypeface(font);
		title.setText(determineText(0));

		// For some reason I cant set an OnClickListner in onCreate() any
		// more...
		// Weird as hell... so here's a private method. Pathetic. I blame
		// eclipse.
		setClickOnText(title);

		findViewById(R.id.rightBTN).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
				playSound = false;
			}
		});

		mAdapter = new MyAdapter(getSupportFragmentManager());
		mPager = (ViewPager) findViewById(R.id.pager);

		// Converts 4 DIP to pixels to set page margins
		Resources r = getResources();
		float marginPixels = TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, r.getDisplayMetrics());
		mPager.setPageMargin((int) marginPixels);

		mPager.setAdapter(mAdapter);

		// Uncomment this if round buttons return to active duty
		// if(animalIndexFromOpeningActivity > -1){
		// Log.d("YO", "animalIndexFromOpenningActivity is:::" +
		// String.valueOf(animalIndexFromOpeningActivity));
		// int cyclerNum = getIntent().getIntExtra("cyclerNum", -1);
		// if(cyclerNum > -1) {
		// switch (cyclerNum) {
		// case 1:
		// mPager.setCurrentItem(animalIndexFromOpeningActivity);
		// title.setText(determineText(animalIndexFromOpeningActivity));
		// break;
		// case 2:
		// mPager.setCurrentItem(animalIndexFromOpeningActivity+7);
		// title.setText(determineText(animalIndexFromOpeningActivity+7));
		// break;
		// case 3:
		// mPager.setCurrentItem(animalIndexFromOpeningActivity+21);
		// title.setText(determineText(animalIndexFromOpeningActivity+21));
		// break;
		// case 4:
		// mPager.setCurrentItem(animalIndexFromOpeningActivity+13);
		// title.setText(determineText(animalIndexFromOpeningActivity+13));
		// break;
		// default:
		// break;
		// }
		// }
		// }

		mPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {

				// Because the animal image changed, playSound boolean flips to
				// false
				// This insures the animal name/pic sound will stop
				playSound = false;
				rightArrow = (Button) findViewById(R.id.rightBTN);
				if (position == ImageFragment.animalPic.length - 1) {
					rightArrow.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View arg0) {
							mPager.setCurrentItem(mPager.getCurrentItem()
									- ImageFragment.animalPic.length, false);
						}
					});
					rightArrow.setBackgroundResource(R.drawable.back_to_start);
				} else {
					rightArrow.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							mPager.setCurrentItem(mPager.getCurrentItem() + 1,
									true);
						}
					});
					rightArrow
							.setBackgroundResource(R.drawable.custom_pawright_button);
				}
				title.setText(determineText(position));
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		findViewById(R.id.leftBTN).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mPager.setCurrentItem(mPager.getCurrentItem() - 1, true);
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		AnimalSounds.stopSound();
	}

	private void setClickOnText(TextView v) {
		v.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Once animal name is clicked, playSound flips to true
				// In the next declared thread, it will be checked to determine
				// whether to play the next sound or not
				playSound = true;
				sounds = new AnimalSounds(MainActivity.this);
				sounds.playAnimalNameSound(mPager.getCurrentItem());

				// This thread will execute after playing animal name.
				// It will then check the playSound boolean.
				// If it is true, a second call for playAnimalPicSound method
				// will occur.
				// Else, it will not call that function.
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (playSound != false) {
							// Using static method to retrieve the currently
							// displayed image's resource ID
							// the playAnimalPicSound will work
							sounds.playAnimalPicSound(ImageFragment
									.getAnimalPicId(mPager.getCurrentItem()));
						}
					}
				}).start();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.quit_app:
			AudioManager mgr = (AudioManager) getSystemService(OpeningActivity.AUDIO_SERVICE);
			mgr.setStreamMute(AudioManager.STREAM_SYSTEM, false);
			finish();
			break;
		case R.id.return_to_start:
			mPager.setCurrentItem(0, true);
			break;
		case R.id.play:
			loopPictures();
			break;
		case R.id.stop:
			if(swipeTimer != null){
				swipeTimer.cancel();
				swipeTimer.purge();
				break;
			}
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void loopPictures() {
		// This will run the command to swipe pictures automatically using the
		// scheduled timer
		handler = new Handler();
		
		// This is the actual command the handler runs
		update = new Runnable() {
			
			@Override
			public void run() {
				if (mPager.getCurrentItem() == ImageFragment.animalPic.length-1) {
					mPager.setCurrentItem(0, false);
					sounds = new AnimalSounds(MainActivity.this);
					sounds.playAnimalNameSound(mPager.getCurrentItem());
				}else{
					mPager.setCurrentItem(mPager.getCurrentItem()+1, true);
					sounds = new AnimalSounds(MainActivity.this);
					sounds.playAnimalNameSound(mPager.getCurrentItem());
				}
			}
		};

		// This will call the handler every set time
		swipeTimer = new Timer();
		swipeTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				handler.post(update);
			}
		}, 100, 2000);
	}

	// Connects with animal name array.
	// Retrieves animal name from array and returns it for setText();
	private String determineText(int position) {
		Resources res = getResources();
		String[] animalNameList = res.getStringArray(R.array.animal_name);
		return animalNameList[position];
	}

	public class MyAdapter extends FragmentStatePagerAdapter {

		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return ImageFragment.animalPic.length;
		}

		@Override
		public Fragment getItem(int position) {
			if (mPager.getCurrentItem() == ImageFragment.animalPic.length) {
				return ImageFragment.newInstance(0);
			}else{
				return ImageFragment.newInstance(position);
			}
		}
	}
}
