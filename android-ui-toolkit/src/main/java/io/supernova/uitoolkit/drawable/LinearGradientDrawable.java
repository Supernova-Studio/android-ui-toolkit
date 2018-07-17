package io.supernova.uitoolkit.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class LinearGradientDrawable extends Drawable {

	@NonNull
	private final PointF start;

	@NonNull
	private final PointF end;

	private final List<GradientStop> stops = new ArrayList<>();
	private float cornerRadius = 0f;

	private final RectF reusableRect = new RectF(0, 0, 0, 0);

	private final Paint gradientPaint = new Paint();


	public LinearGradientDrawable(@NonNull PointF start, @NonNull PointF end, Collection<GradientStop> stops) {
		this.start = start;
		this.end = end;

		this.stops.addAll(stops);
	}


	public LinearGradientDrawable(@NonNull PointF start, @NonNull PointF end, GradientStop... stops) {
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
		canvas.drawRoundRect(this.getFloatReusableBounds(), this.cornerRadius, this.cornerRadius, this.gradientPaint);
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


	@Override
	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public void getOutline(@NonNull Outline outline) {
		super.getOutline(outline);

		outline.setRoundRect(this.getBounds(), this.cornerRadius);

		// Outline accepts range 0f - 1f, while drawable alpha is 0 - 255
		outline.setAlpha(this.getAlpha() / 255);
	}


	public void addStop(GradientStop stop) {
		this.stops.add(stop);
		this.invalidateShader();
	}


	public float getCornerRadius() {
		return this.cornerRadius;
	}


	public void setCornerRadius(float cornerRadius) {
		this.cornerRadius = cornerRadius;
	}


	private void invalidateShader() {
		if(this.stops.isEmpty()) {
			this.setDefaultColorShader();
		} else if(this.stops.size() == 1) {
			this.setSolidColorPaint(this.stops.get(0).color);
		} else {
			this.setLinearGradientShaderPaint();
		}
	}


	private void setDefaultColorShader() {
		this.setSolidColorPaint(Color.TRANSPARENT);
	}


	private void setSolidColorPaint(@ColorInt int color) {
		this.gradientPaint.setShader(null);
		this.gradientPaint.setColor(color);
	}


	private void setLinearGradientShaderPaint() {

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
			fractions[i] = stop.position;
			colors[i] = stop.color;
		}

		LinearGradient gradient = new LinearGradient(gradientStartX, gradientStartY,
				gradientEndX, gradientEndY,
				colors, fractions, Shader.TileMode.CLAMP);

		gradientPaint.setShader(gradient);
	}


	private RectF getFloatReusableBounds() {
		Rect bounds = this.getBounds();

		this.reusableRect.bottom = bounds.bottom;
		this.reusableRect.left = bounds.left;
		this.reusableRect.right = bounds.right;
		this.reusableRect.top = bounds.top;

		return this.reusableRect;
	}
}
