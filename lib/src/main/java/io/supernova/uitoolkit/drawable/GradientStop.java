package io.supernova.uitoolkit.drawable;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;


public class GradientStop implements Comparable<GradientStop> {

	public final float fraction;
	@ColorInt
	public final int color;


	public GradientStop(float fraction, int color) {
		this.fraction = fraction;
		this.color = color;
	}


	@Override
	public int compareTo(@NonNull GradientStop o) {
		return Float.compare(this.fraction, o.fraction);
	}
}
