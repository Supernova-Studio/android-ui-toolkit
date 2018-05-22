package io.supernova.uitoolkit.text;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class LetterSpacingSpan extends MetricAffectingSpan {

	private float letterSpacing;


	public LetterSpacingSpan(float letterSpacing) {
		this.letterSpacing = letterSpacing;
	}



	@Override
	public void updateMeasureState(TextPaint p) {
		p.setLetterSpacing(this.letterSpacing);
	}


	@Override
	public void updateDrawState(TextPaint tp) {
		tp.setLetterSpacing(this.letterSpacing);
	}
}
