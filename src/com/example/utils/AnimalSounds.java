package com.example.utils;

import android.app.Activity;
import android.media.MediaPlayer;

import com.example.viewpagerexample.R;

public class AnimalSounds {
	
	private int[] ANIMALNAME_SOUND = {
		R.raw.dog_name,
		R.raw.cat_name, 
		R.raw.cow_name,
		R.raw.horse_name,
		R.raw.sheep_name,
		R.raw.goat_name,
		R.raw.pig_name, 
		R.raw.donkey_name, 
		R.raw.peacock_name,
		R.raw.rooster_name,
		R.raw.duck_name,
		R.raw.dove_name, 
		R.raw.parrot_name,
		R.raw.camel_name, 
		R.raw.deer_name,
		R.raw.lion_name,
		R.raw.elephant_name,
		R.raw.rhino_name,
		R.raw.giraffe_name,
		R.raw.bear_name, 
		R.raw.zebra_name, 
		R.raw.hippo_name, 
		R.raw.monkey_name,
		R.raw.dolphine_name, 
		R.raw.whale_name 
		};
	
	private static MediaPlayer mp;
	private Activity myActivity;
	
	public AnimalSounds(Activity activity){
		myActivity = activity;
	}
	
	public void playAnimalNameSound(int action){
		playSound(ANIMALNAME_SOUND[action]);
	}
	
	
	public void playAnimalPicSound(int action){
		switch (action) {
		case R.drawable.p1:
			playSound(R.raw.dogogg);
			return;
		case R.drawable.p2:
			playSound(R.raw.catogg);
			return;
		case R.drawable.p3:
			playSound(R.raw.cowogg);
			return;
		case R.drawable.p4:
			playSound(R.raw.horseogg);
			return;
		case R.drawable.p5:
			playSound(R.raw.sheepogg);
			return;
		case R.drawable.p6:
			playSound(R.raw.goatogg);
			return;
		case R.drawable.p7:
			playSound(R.raw.pigogg);
			return;
		case R.drawable.p8:
			playSound(R.raw.donkeyogg);
			return;
		case R.drawable.p9:
			playSound(R.raw.peacockogg);
			return;
		case R.drawable.p10:
			playSound(R.raw.roosterogg);
			return;
		case R.drawable.p11:
			playSound(R.raw.duckogg);
			return;
		case R.drawable.p12:
			playSound(R.raw.doveogg);
			return;
		case R.drawable.p13:
			playSound(R.raw.parrotogg);
			return;
		case R.drawable.p14:
			playSound(R.raw.camelogg);
			return;
		case R.drawable.p15:
			playSound(R.raw.deerogg);
			return;
		case R.drawable.p16:
			playSound(R.raw.lionogg);
			return;
		case R.drawable.p17:
			playSound(R.raw.elephanogg);
			return;
		case R.drawable.p18:
			playSound(R.raw.rhinoogg);
			return;
		case R.drawable.p19:
			playSound(R.raw.giraffeogg);
			return;
		case R.drawable.p20:
			playSound(R.raw.bearogg);
			return;
		case R.drawable.p21:
			playSound(R.raw.zebraogg);
			return;
		case R.drawable.p22:
			playSound(R.raw.hippoogg);
			return;
		case R.drawable.p23:
			playSound(R.raw.chimpogg);
			return;
		case R.drawable.p24:
			playSound(R.raw.dolphinogg);
			return;
		case R.drawable.p25:
			playSound(R.raw.whaleogg);
			return;
		default:
			return;
		}
	}
	
	private void playSound(int resId) {
		if (mp != null) {
			mp.reset();
		}
		mp = MediaPlayer.create(myActivity, resId);
		mp.start();
	}

	public static void stopSound() {
		if (mp != null && mp.isPlaying()) {
			mp.stop();
			mp = null;
		}
	}

}
