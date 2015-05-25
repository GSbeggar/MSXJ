package com.msxj.android.ui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.kaixin.android.R;
import com.msxj.android.MSXJApplication;

public class NearbyPhotoLayout {
	private MSXJApplication mKXApplication;
	private View mLayout;
	private ImageView mPhoto;

	public NearbyPhotoLayout(Context context, MSXJApplication application) {
		mKXApplication = application;
		mLayout = LayoutInflater.from(context).inflate(
				R.layout.lbs_nearby_photo_display_item, null);
		mPhoto = (ImageView) mLayout
				.findViewById(R.id.lbs_nearby_photo_display_item_photo);
	}

	public View getLayout() {
		return mLayout;
	}

	public void setPhoto(String image) {
		mPhoto.setImageBitmap(mKXApplication.getNearbyPhoto(image));
	}
}
