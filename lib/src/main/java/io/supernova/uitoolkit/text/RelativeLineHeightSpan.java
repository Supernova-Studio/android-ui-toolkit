package io.supernova.uitoolkit.text;

import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.style.LineHeightSpan;


public class RelativeLineHeightSpan implements LineHeightSpan.WithDensity {

	private final float lineHeightMultiplier;


	public RelativeLineHeightSpan(float lineHeightMultiplier) {
		this.lineHeightMultiplier = lineHeightMultiplier;
	}


	@Override
	public void chooseHeight(CharSequence text, int start, int end, int spanstartv, int v, Paint.FontMetricsInt fm) {
		// Should not get called, at least not by StaticLayout.
		this.chooseHeight(text, start, end, spanstartv, v, fm, null);
	}

	@Override
	public void chooseHeight(CharSequence text, int start, int end, int spanstartv, int v, Paint.FontMetricsInt fm, @Nullable TextPaint paint) {

		// Calculate new line height.
		float currentHeight = this.getLineHeightFromMetrics(fm);
		float newHeight = currentHeight * lineHeightMultiplier;

		this.applyLineHeight(((int) newHeight), fm);
	}


	private int getLineHeightFromMetrics(Paint.FontMetricsInt fm) {
		return fm.descent - fm.ascent;
	}

	private void applyLineHeight(int newHeight, Paint.FontMetricsInt fm) {
		fm.top = fm.bottom - newHeight;
		fm.ascent = fm.descent - newHeight;
	}
}
