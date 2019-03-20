/*
 * Copyright 2019 AJ Sherrell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ajsherrell.android.bakingapp.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ajsherrell.android.bakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

// used resource: https://jakewharton.github.io/butterknife/

public class BakeryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.bakery_name_tv)
    public TextView mBakeryNameTv;

    @BindView(R.id.servings_tv)
    public TextView mServingsTv;

    @BindView(R.id.bakery_image)
    public ImageView bakeryImage;

    public BakeryViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
