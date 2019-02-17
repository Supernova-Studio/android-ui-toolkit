package io.supernova.toolkit.example;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import io.supernova.supernovauitoolkit.R;
import io.supernova.uitoolkit.drawable.LinearGradientDrawable;
import io.supernova.uitoolkit.text.FontSpan;
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

		FontSpan span = new FontSpan(ResourcesCompat.getFont(this, R.font.font_lato_light));
		spannableString.setSpan(span, 5, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		// Pass spannable text to the view
		this.exampleTextView2.setText(spannableString);
	}


	private void setupGradient() {

		// Create gradient and pass it as the background to the view
		//
		// LinearGradientDrawable.Builder requires context to convert resource references and DP dimensions to pixels and
		// start and end points that define the gradient orientation.
		this.gradientView.setBackground(new LinearGradientDrawable.Builder(this, new PointF(0, 0), new PointF(1, 1))
				// Stops
				.addStop(0, Color.BLUE)
				.addStop(0.3f, Color.BLACK)
				.addStop(0.6f, Color.WHITE)
				.addStopWithResource(1, R.color.colorPrimaryDark)

				// Corner radius resource
				.cornerRadius(R.dimen.test_gradient_corner_radius)

				// Stroke width converted from DPs
				.strokeWidthDp(2)

				// Stroke color resource
				.strokeColorRes(R.color.colorPrimary)

				.build());
	}
}
