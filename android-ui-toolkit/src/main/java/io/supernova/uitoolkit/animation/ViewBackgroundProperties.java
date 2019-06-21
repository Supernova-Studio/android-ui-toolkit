package io.supernova.uitoolkit.animation;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.util.Property;
import android.view.View;

import io.supernova.uitoolkit.drawable.LinearGradientDrawable;

public interface ViewBackgroundProperties {

    /**
     * Allows animating corner radius on a View with an implementation of ValueAnimator or
     * ObjectAnimator. This will only make effect on a View target that has either {@link LinearGradientDrawable}
     * or {@link GradientDrawable} as its background.
     */
    Property<View, Float> CORNER_RADIUS = new Property<View, Float>(Float.class, "cornerRadius") {

        @Override
        public Float get(View view) {
            Drawable drawable = view.getBackground();

            if (drawable == null) {
                return 0f;
            }

            if (drawable instanceof LinearGradientDrawable) {
                return ((LinearGradientDrawable) drawable).getCornerRadius();
            } else if (drawable instanceof GradientDrawable && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return ((GradientDrawable) drawable).getCornerRadius();
            } else {
                return 0f;
            }
        }

        @Override
        public void set(View view, Float value) {
            Drawable drawable = view.getBackground();

            if (drawable == null) {
                return;
            }

            if (drawable instanceof GradientDrawable) {
                GradientDrawable gradientDrawable = (GradientDrawable) drawable;
                gradientDrawable.setCornerRadius(value);
            } else if (drawable instanceof LinearGradientDrawable) {
                LinearGradientDrawable linearGradientDrawable = (LinearGradientDrawable) drawable;
                linearGradientDrawable.setCornerRadius(value);
            }
        }
    };


    /**
     * Allows animating stroke width on a View with an implementation of ValueAnimator or
     * ObjectAnimator. This will only make effect on a View target that has {@link LinearGradientDrawable}
     * as its background.
     */
    Property<View, Float> STROKE_WIDTH = new Property<View, Float>(Float.class, "strokeWidth") {

        @Override
        public Float get(View view) {

            Drawable drawable = view.getBackground();

            if (!(drawable instanceof LinearGradientDrawable)) {
                return 0f;
            }

            return ((LinearGradientDrawable) drawable).getStrokeWidth();
        }

        @Override
        public void set(View view, Float value) {

            Drawable drawable = view.getBackground();

            if (!(drawable instanceof LinearGradientDrawable)) {
                return;
            }

            ((LinearGradientDrawable) drawable).setStrokeWidth(value);
        }
    };

    /**
     * Allows animating stroke color on a View with an implementation of ValueAnimator or
     * ObjectAnimator. This will only make effect on a View target that has {@link LinearGradientDrawable}
     * as its background.
     *
     * Animating colors requires setting {@link android.animation.ArgbEvaluator} to your instance of
     * {@link android.animation.ValueAnimator}.
     */
    Property<View, Integer> STROKE_COLOR = new Property<View, Integer>(Integer.class, "strokeColor") {

        @Override
        @ColorInt
        public Integer get(View view) {

            Drawable drawable = view.getBackground();

            if (!(drawable instanceof LinearGradientDrawable)) {
                return Color.TRANSPARENT;
            }

            return ((LinearGradientDrawable) drawable).getStrokeColor();
        }

        @Override
        public void set(View view, @ColorInt Integer value) {

            Drawable drawable = view.getBackground();

            if (!(drawable instanceof LinearGradientDrawable)) {
                return;
            }

            ((LinearGradientDrawable) drawable).setStrokeColor(value);
        }
    };


    /**
     * Allows animating background on a View with an implementation of ValueAnimator or
     * ObjectAnimator.
     *
     * Animating colors requires setting {@link android.animation.ArgbEvaluator} to your instance of
     * {@link android.animation.ValueAnimator}.
     */
    Property<View, Integer> BACKGROUND_COLOR = new Property<View, Integer>(Integer.class, "strokeColor") {

        @Override
        @ColorInt
        public Integer get(View view) {
            Drawable drawable = view.getBackground();

            if (drawable instanceof GradientDrawable) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    ColorStateList colorStateList = ((GradientDrawable) drawable).getColor();
                    if (colorStateList != null) {
                        return colorStateList.getDefaultColor();
                    }
                }
            }

            return Color.TRANSPARENT;
        }

        @Override
        public void set(View view, @ColorInt Integer value) {
            Drawable drawable = view.getBackground();

            if (drawable instanceof GradientDrawable) {
                ((GradientDrawable) drawable).setColor(value);
            } else {
                view.setBackgroundColor(value);
            }
        }
    };
}
