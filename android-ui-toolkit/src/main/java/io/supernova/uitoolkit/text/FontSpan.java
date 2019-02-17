package io.supernova.uitoolkit.text;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class FontSpan extends MetricAffectingSpan {

	@Nullable
	private final Typeface typeface;

	public FontSpan(@Nullable final Typeface typeface) {
		this.typeface = typeface;
	}

	@Override
	public void updateMeasureState(@NonNull TextPaint textPaint) {
		update(textPaint);
	}

	@Override
	public void updateDrawState(TextPaint textPaint) {
		update(textPaint);
	}

	private void update(TextPaint textPaint) {
		Typeface old = textPaint.getTypeface();
		int oldStyle = old != null ? old.getStyle() : Typeface.NORMAL;
		Typeface font = Typeface.create(this.typeface, oldStyle);
		textPaint.setTypeface(font);
	}
}