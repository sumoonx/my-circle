/*
 * Copyright 2016 Team Wheat, CNV-2313
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

package com.seu.mycircle.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.seu.mycircle.R;
import com.seu.mycircle.model.entities.DrawerLabel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter for drawer label items.
 *
 * @author JeremyXu on 2016/1/13 via jeremy_xm@163.com
 * @version v0.0
 */
public class LabelItemListAdapter extends RecyclerView.Adapter<LabelItemListAdapter.LabelItemViewHolder> {
    private Context context;
    private ItemClickListener itemClickListener;
    private List<DrawerLabel> drawerLabels;

    public LabelItemListAdapter(Context context, ItemClickListener listener) {
        this.context = context;
        itemClickListener = listener;
        drawerLabels = new ArrayList<DrawerLabel>();
    }

    public void setDrawerLabels(List<DrawerLabel> drawerLabels) {
        this.drawerLabels = drawerLabels;
    }


    @Override
    public LabelItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(
                R.layout.list_drawer_item, parent, false
        );
        return new LabelItemViewHolder(rootView, itemClickListener);
    }

    @Override
    public void onBindViewHolder(LabelItemViewHolder holder, int position) {
        holder.bindLabelItem(drawerLabels.get(position));
    }

    @Override public int getItemCount() {
        return drawerLabels == null ? 0 : drawerLabels.size();
    }

    public interface ItemClickListener {
        void onLabelItemClick(int position);
    }

    public class LabelItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.drawer_label_icon) ImageView labelIcon;
        @Bind(R.id.drawer_header_name) TextView labelName;
        @Bind(R.id.drawer_label_unreadCnt) TextView labelUnread;
        //@Bind(R.color.white) int backgroundColor;

        public LabelItemViewHolder(View itemView, final ItemClickListener clickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            bindListener(itemView, clickListener);
        }

        public void bindLabelItem(DrawerLabel item) {
            labelIcon.setImageBitmap(item.getIcon());
            labelName.setText(item.getName());
            int unread = item.getUnreadCount();
            if(item.getName() == DrawerLabel.NAME_MY_RELEASE
                    || item.getName() == DrawerLabel.NAME_MY_MESSAGE) {
                labelUnread.setText(String.valueOf(unread));
                return;
            }
            if(unread != 0) {
                labelUnread.setText(String.valueOf(unread));
            }else {
                labelUnread.setVisibility(View.GONE);
            }

        }

        private void bindListener(View itemView, final ItemClickListener itemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onLabelItemClick(getPosition());
                }
            });
        }
    }
}
