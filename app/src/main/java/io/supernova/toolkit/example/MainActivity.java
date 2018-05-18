package io.supernova.toolkit.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.LineHeightSpan;
import android.widget.TextView;

import io.supernova.supernovauitoolkit.R;
import io.supernova.uitoolkit.text.LineSpacingSpan;


public class MainActivity extends AppCompatActivity {

	private TextView exampleTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.init();
	}


	private void init() {
		this.exampleTextView = findViewById(R.id.exampleTextView);

		SpannableString spannableString = new SpannableString("Example String\nShowcasing line spacing span\nIt's great, trust me");

		LineHeightSpan lineHeightSpan = new LineSpacingSpan();
		spannableString.setSpan(lineHeightSpan, 15, 42, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		this.exampleTextView.setText(spannableString);
	}
}
