package io.supernova.toolkit.example;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.supernova.supernovauitoolkit.R;


public class GridSpacingDecorationTestAdapter extends RecyclerView.Adapter<GridSpacingDecorationTestAdapter.TestViewHolder> {

	@NonNull
	@Override
	public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new TestViewHolder(parent);
	}


	@Override
	public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {

	}


	@Override
	public int getItemCount() {
		return 30;
	}


	static class TestViewHolder extends RecyclerView.ViewHolder {

		TestViewHolder(ViewGroup parent) {
			super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_spacing_decoration_test, parent, false));
		}
	}
}
