package io.supernova.uitoolkit.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;


/**
 * Created by me.
 */
public class GravityDrawable extends Drawable {

	private String name;


	public GravityDrawable(String name) {
		this.name = name;
	}


	@Override
	protected void onBoundsChange(Rect bounds) {
		super.onBoundsChange(bounds);



		Log.d("GravityDrawable", name  + " received new bounds: " + bounds.toString());
	}


	@Override
	public void getOutline(@NonNull Outline outline) {
		super.getOutline(outline);
	}


	@Override
	public int getIntrinsicWidth() {
		return 300;
	}


	@Override
	public int getIntrinsicHeight() {
		return 300;
	}


	@Override
	public int getMinimumHeight() {
		return this.getIntrinsicHeight();
	}


	@Override
	public int getMinimumWidth() {
		return this.getIntrinsicWidth();
	}


	@Override
	public void draw(@NonNull Canvas canvas) {
//		Paint paint = new Paint();
//		paint.setColor(Color.BLACK);
//		canvas.drawRect(0, 0, canvas.getWidth() / 2, canvas.getHeight(), paint);

		Log.d("GravityDrawable", name + " is drawing in " + canvas.getWidth() + "x" + canvas.getHeight());
	}


	@Override
	public void setAlpha(int alpha) {

	}


	@Override
	public void setColorFilter(@Nullable ColorFilter colorFilter) {

	}


	@Override
	public int getOpacity() {
		return PixelFormat.OPAQUE;
	}
}
