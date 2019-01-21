package io.supernova.toolkit.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.supernova.supernovauitoolkit.R;
import io.supernova.uitoolkit.recycler.GridSpacingItemDecoration;


public class GridSpacingDecorationTestActivity extends AppCompatActivity {

	private RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_spacing_decoration_test);

		this.recyclerView = findViewById(R.id.recycler_view);
		this.initRecyclerView();
	}

	private void initRecyclerView() {
		GridLayoutManager manager = new GridLayoutManager(this, 5, GridLayoutManager.HORIZONTAL, false);
		manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {
				if (position == 8 || position == 14) {
					return 2;
				} else {
					return 1;
				}
			}
		});
		this.recyclerView.setLayoutManager(manager);
		this.recyclerView.setAdapter(new GridSpacingDecorationTestAdapter());
		this.recyclerView.addItemDecoration(new GridSpacingItemDecoration((int) this.getResources().getDimension(R.dimen.test_recycler_view_spacing)));
	}
}
