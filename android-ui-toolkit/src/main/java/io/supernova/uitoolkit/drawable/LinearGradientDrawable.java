package io.supernova.uitoolkit.drawable;

import android.content.Context;
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
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.Dimension;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class LinearGradientDrawable extends Drawable {

	@NonNull
	private final PointF start;
	@NonNull
	private final PointF end;

	private final List<GradientStop> stops = new ArrayList<>();
	private float cornerRadius = 0f;

	private final RectF reusableRect = new RectF(0, 0, 0, 0);

	private final Paint gradientPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private final Paint strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);


	public LinearGradientDrawable(@NonNull PointF start, @NonNull PointF end, Collection<GradientStop> stops) {
		this.start = start;
		this.end = end;

		this.stops.addAll(stops);

		this.init();
	}


	public LinearGradientDrawable(@NonNull PointF start, @NonNull PointF end, GradientStop... stops) {
		this.start = start;
		this.end = end;

		this.stops.addAll(Arrays.asList(stops));

		this.init();
	}


	@Override
	protected void onBoundsChange(Rect bounds) {
		super.onBoundsChange(bounds);
		this.invalidateShader();
	}


	@Override
	public void draw(@NonNull Canvas canvas) {

		RectF floatBounds = this.getFloatReusableBounds();
		canvas.drawRoundRect(floatBounds, this.cornerRadius, this.cornerRadius, this.gradientPaint);

		if (this.hasStroke()) {
			canvas.drawRoundRect(floatBounds, this.cornerRadius, this.cornerRadius, this.strokePaint);
		}
	}


	@Override
	public void setAlpha(int alpha) {
		this.gradientPaint.setAlpha(alpha);
		this.strokePaint.setAlpha(alpha);
	}


	@Override
	public void setColorFilter(@Nullable ColorFilter colorFilter) {
		this.gradientPaint.setColorFilter(colorFilter);
		this.strokePaint.setColorFilter(colorFilter);
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
		outline.setAlpha(this.getAlpha() / 255f);
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
		invalidateSelf();
	}


	public float getStrokeWidth() {
		return this.strokePaint.getStrokeWidth();
	}


	public void setStrokeWidth(float width) {
		this.strokePaint.setStrokeWidth(width);
		invalidateSelf();
	}


	@ColorInt
	public int getStrokeColor() {
		return this.strokePaint.getColor();
	}


	public void setStrokeColor(@ColorInt int color) {
		this.strokePaint.setColor(color);
		invalidateSelf();
	}


	private void init() {
		this.strokePaint.setStyle(Paint.Style.STROKE);
	}


	private void invalidateShader() {
		if(this.stops.isEmpty()) {
			this.setDefaultColorShader();
		} else if(this.stops.size() == 1) {
			this.setSolidColorPaint(this.stops.get(0).color);
		} else {
			this.setLinearGradientShaderPaint();
		}

		invalidateSelf();
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


	private boolean hasStroke() {
		return this.strokePaint.getStrokeWidth() != 0;
	}


	public static class Builder {

		@NonNull
		private Context context;

		private final PointF start;
		private final PointF end;

		private final List<GradientStop> stops = new ArrayList<>();

		private float cornerRadius = 0f;
		private float strokeWidth = 0f;
		@ColorInt
		private int strokeColor = Color.TRANSPARENT;


		public Builder(@NonNull Context context, @NonNull PointF start, @NonNull PointF end) {
			this.context = context;
			this.start = start;
			this.end = end;
		}


		/*  STOPS  */

		public Builder addStop(GradientStop stop) {
			this.stops.add(stop);
			return this;
		}

		public Builder addStop(float position, @ColorInt int color) {
			return this.addStop(new GradientStop(position, color));
		}

		public Builder addStopWithResource(float position, @ColorRes int color) {
			return this.addStop(new GradientStop(position, ContextCompat.getColor(context, color)));
		}


		/*  RAW VALUES  */

		public Builder cornerRadiusPx(@Dimension float cornerRadius) {
			this.cornerRadius = cornerRadius;
			return this;
		}

		public Builder strokeWidthPx(@Dimension float width) {
			this.strokeWidth = width;
			return this;
		}

		public Builder cornerRadiusDp(@Dimension float radius) {
			this.cornerRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, radius, this.context.getResources().getDisplayMetrics());
			return this;
		}

		public Builder strokeWidthDp(@Dimension float width) {
			this.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, this.context.getResources().getDisplayMetrics());
			return this;
		}

		public Builder strokeColor(@ColorInt int color) {
			this.strokeColor = color;
			return this;
		}


		/*  RESOURCE VALUES  */

		public Builder cornerRadius(@DimenRes int cornerRadiusRes) {
			this.cornerRadius = this.context.getResources().getDimension(cornerRadiusRes);
			return this;
		}

		public Builder strokeWidth(@DimenRes int strokeWidthRes) {
			this.strokeWidth = this.context.getResources().getDimension(strokeWidthRes);
			return this;
		}

		public Builder strokeColorRes(@ColorRes int strokeColorRes) {
			this.strokeColor = ContextCompat.getColor(this.context, strokeColorRes);
			return this;
		}


		/*  BUILD  */

		public LinearGradientDrawable build() {
			LinearGradientDrawable gradient = new LinearGradientDrawable(this.start, this.end, this.stops);

			gradient.setStrokeColor(this.strokeColor);
			gradient.setStrokeWidth(this.strokeWidth);
			gradient.setCornerRadius(this.cornerRadius);

			return gradient;
		}
	}
}
