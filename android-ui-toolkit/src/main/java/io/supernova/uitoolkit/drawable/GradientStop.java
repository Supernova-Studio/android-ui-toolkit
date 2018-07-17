package io.supernova.uitoolkit.drawable;

import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;


public class GradientStop implements Comparable<GradientStop> {

	public final float position;
	@ColorInt
	public final int color;


	public GradientStop(@FloatRange(from = 0, to = 1) float position, int color) {

		if (position < 0 || position > 1) {
			throw new IllegalArgumentException("GradientStop position must be in inclusive range from 0 to 1");
		}

		this.position = position;
		this.color = color;
	}


	@Override
	public int compareTo(@NonNull GradientStop o) {
		return Float.compare(this.position, o.position);
	}
}
