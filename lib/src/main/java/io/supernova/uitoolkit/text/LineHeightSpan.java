package io.supernova.uitoolkit.text;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;


public class LineHeightSpan implements android.text.style.LineHeightSpan.WithDensity {

	private final int height;


	public LineHeightSpan(int height) {
		this.height = height;
	}


	@Override
	public void chooseHeight(CharSequence text, int start, int end, int spanstartv, int v, Paint.FontMetricsInt fm) {
		// Should not get called, at least not by StaticLayout.
		this.chooseHeight(text, start, end, spanstartv, v, fm, null);
	}

	@Override
	public void chooseHeight(CharSequence text, int start, int end, int spanstartv, int v, Paint.FontMetricsInt fm, @Nullable TextPaint paint) {
		int size = height;

		// Apply density if provided.
		if (paint != null) {
			size *= paint.density;
		}

		this.applyLineHeight(size, fm);
	}

	private void applyLineHeight(int newHeight, Paint.FontMetricsInt fm) {
		fm.top = fm.bottom - newHeight;
		fm.ascent = fm.descent - newHeight;
	}
}
