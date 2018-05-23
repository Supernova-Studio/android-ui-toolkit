package io.supernova.uitoolkit.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class LinearGradientDrawable extends Drawable {


	private final PointF start;
	private final PointF end;

	private final List<GradientStop> stops = new ArrayList<>();

	private final Paint gradientPaint = new Paint();


	public LinearGradientDrawable(PointF start, PointF end, Collection<GradientStop> stops) {
		this.start = start;
		this.end = end;

		this.stops.addAll(stops);
	}

	public LinearGradientDrawable(PointF start, PointF end, GradientStop... stops) {
		this.start = start;
		this.end = end;

		this.stops.addAll(Arrays.asList(stops));
	}



	@Override
	protected void onBoundsChange(Rect bounds) {
		super.onBoundsChange(bounds);
		this.invalidateShader();
	}


	@Override
	public void draw(@NonNull Canvas canvas) {
		canvas.drawPaint(this.gradientPaint);
	}


	@Override
	public void setAlpha(int alpha) {
		this.gradientPaint.setAlpha(alpha);
	}


	@Override
	public void setColorFilter(@Nullable ColorFilter colorFilter) {

	}


	@Override
	public int getOpacity() {
		return PixelFormat.OPAQUE;
	}

	public void addStop(GradientStop stop) {
		this.stops.add(stop);
		this.invalidateShader();
	}


	private void invalidateShader() {
		Rect rect = getBounds();

		float width = rect.width();
		float height = rect.height();

		float gradientStartX = width * start.x;
		float gradientStartY = height * start.y;

		float gradientEndX = width * end.x;
		float gradientEndY = height * end.y;

		float[] fractions = new float[this.stops.size()];
		int[] colors = new int[this.stops.size()];

		for(int i = 0; i < stops.size(); i++) {
			GradientStop stop = stops.get(i);
			fractions[i] = stop.fraction;
			colors[i] = stop.color;
		}

		LinearGradient gradient = new LinearGradient(gradientStartX, gradientStartY,
				gradientEndX, gradientEndY,
				colors, fractions, Shader.TileMode.CLAMP);

		gradientPaint.setShader(gradient);
	}
}
