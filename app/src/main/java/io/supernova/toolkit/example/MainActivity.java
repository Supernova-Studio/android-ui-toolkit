package io.supernova.toolkit.example;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import io.supernova.supernovauitoolkit.R;
import io.supernova.uitoolkit.drawable.GradientStop;
import io.supernova.uitoolkit.drawable.LinearGradientDrawable;
import io.supernova.uitoolkit.text.LetterSpacingSpan;
import io.supernova.uitoolkit.text.LineHeightSpan;
import io.supernova.uitoolkit.text.RelativeLineHeightSpan;


public class MainActivity extends AppCompatActivity {

	private TextView exampleTextView;
	private TextView exampleTextView2;

	private View gradientView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.init();
	}


	private void init() {
		this.exampleTextView = findViewById(R.id.exampleTextView);
		this.exampleTextView2 = findViewById(R.id.exampleTextView2);
		this.gradientView = findViewById(R.id.gradientView);

		this.setupLineSpacingExample();
		this.setupLetterSpacingExample();
		this.setupGradient();
	}


	private void setupLineSpacingExample() {

		SpannableString spannableString = new SpannableString("Example String\nShowcasing line spacing span\nIt's awesome, trust me");

		// Setup line height span
		LineHeightSpan lineHeightSpan = new LineHeightSpan(40);
		spannableString.setSpan(lineHeightSpan, 15, 42, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// Setup relative line height span
		RelativeLineHeightSpan relativeLineHeightSpan = new RelativeLineHeightSpan(0.7f);
		spannableString.setSpan(relativeLineHeightSpan, 44, spannableString.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// Pass spannable text to the view
		this.exampleTextView.setText(spannableString);
	}


	private void setupLetterSpacingExample() {

		SpannableString spannableString = new SpannableString("Example String\nShowcasing letter spacing span\nIt's awesome, trust me");

		// Setup stretch line height span
		LetterSpacingSpan stretchingSpan = new LetterSpacingSpan(0.2f);
		spannableString.setSpan(stretchingSpan, 0, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// Setup shrink letter spacing span
		LetterSpacingSpan shrinkingSpan = new LetterSpacingSpan(-0.1f);
		spannableString.setSpan(shrinkingSpan, 30, spannableString.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// Pass spannable text to the view
		this.exampleTextView2.setText(spannableString);
	}


	private void setupGradient() {

		// Create GradientStop array defining colors their position
		// GradientStop position must be between 0 and 1
		GradientStop[] stops = {
				new GradientStop(0, Color.BLUE),
				new GradientStop(0.3f, Color.BLACK),
				new GradientStop(0.6f, Color.WHITE),
				new GradientStop(1, Color.RED)
		};

		// Create LinearGradientDrawable with the stops, define gradient angle with 2 points
		LinearGradientDrawable linearGradientDrawable = new LinearGradientDrawable(new PointF(0, 0), new PointF(1, 1), stops);

		// Set gradient corner radius in DPs
		linearGradientDrawable.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 12, this.getResources().getDisplayMetrics()));

		// Pass gradient to the view
		this.gradientView.setBackground(linearGradientDrawable);
	}
}
