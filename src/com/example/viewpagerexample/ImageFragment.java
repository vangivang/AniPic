package com.example.viewpagerexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.utils.AnimalSounds;

public class ImageFragment extends Fragment  {

	private ImageView animalPicIV;
	public static final int[] animalPic = { 
		R.drawable.p1,
		R.drawable.p2,
		R.drawable.p3,
		R.drawable.p4,
		R.drawable.p5,
		R.drawable.p6,
		R.drawable.p7,
		R.drawable.p8,
		R.drawable.p9,
		R.drawable.p10,
		R.drawable.p11,
		R.drawable.p12,
		R.drawable.p13,
		R.drawable.p14,
		R.drawable.p15,
		R.drawable.p16,
		R.drawable.p17,
		R.drawable.p18,
		R.drawable.p19,
		R.drawable.p20, 
		R.drawable.p21,
		R.drawable.p22,
		R.drawable.p23,
		R.drawable.p24,
		R.drawable.p25,
		};

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// When a new image is attached to View, sound must stop
		AnimalSounds.stopSound();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public static Fragment newInstance(int position) {
		// A new instance of this Fragment is created using int position
		// The position is inserted into the Fragment using a Bundle to be used
		// in onCreateView() method.
		// It is then returned to MainActivity to be displayed using the Adapter
		ImageFragment f = new ImageFragment();
		Bundle args = new Bundle();
		args.putInt("position", position);
		f.setArguments(args);
		return f;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.image_details, container, false);
		animalPicIV = (ImageView) view.findViewById(R.id.animalPicIV);
		animalPicIV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AnimalSounds sounds = new AnimalSounds(getActivity());
				sounds.playAnimalPicSound(v.getId());
			}
		});

		animalPicIV.setImageResource(animalPic[getArguments().getInt("position")]);
		animalPicIV.setId(animalPic[getArguments().getInt("position")]);
		return view;
	}
	
	//Returns the currently displayed image resource id
	public static int getAnimalPicId(int position){
		return animalPic[position];
	}
}