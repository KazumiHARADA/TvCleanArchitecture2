package com.example.excadmin.tvcleanarchitecture.presentation.ui.viewpresenter;

import android.content.res.Resources;
import android.support.v17.leanback.widget.DetailsOverviewLogoPresenter;
import android.support.v17.leanback.widget.DetailsOverviewRow;
import android.support.v17.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import android.support.v17.leanback.widget.Presenter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.excadmin.tvcleanarchitecture.R;

/**
 * Created by excadmin on 2017/07/20.
 */

public class MovieDetailsOverviewLogoPresenter extends DetailsOverviewLogoPresenter {

    static class ViewHolder extends DetailsOverviewLogoPresenter.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }

        public FullWidthDetailsOverviewRowPresenter getParentPresenter() {
            return mParentPresenter;
        }

        public FullWidthDetailsOverviewRowPresenter.ViewHolder getParentViewHolder() {
            return mParentViewHolder;
        }
    }

    @Override
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        ImageView imageView = (ImageView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lb_fullwidth_details_overview_logo, parent, false);

        Resources res = parent.getResources();
        int width = res.getDimensionPixelSize(R.dimen.detail_thumb_width);
        int height = res.getDimensionPixelSize(R.dimen.detail_thumb_height);
        imageView.setLayoutParams(new ViewGroup.MarginLayoutParams(width, height));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return new ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        DetailsOverviewRow row = (DetailsOverviewRow) item;
        ImageView imageView = ((ImageView) viewHolder.view);
        imageView.setImageDrawable(row.getImageDrawable());
        if (isBoundToImage((ViewHolder) viewHolder, row)) {
            ViewHolder vh = (ViewHolder) viewHolder;
            vh.getParentPresenter().notifyOnBindLogo(vh.getParentViewHolder());
        }
    }
}