package com.nuradityo.crudmvp.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nuradityo.crudmvp.R;
import com.nuradityo.crudmvp.model.user;

import java.util.List;

public class RV_Adapter extends RecyclerView.Adapter<RV_Adapter.MyViewHolder> {

    private List<user> users;
    private OnCallbackListener listener;

    public RV_Adapter(List<user> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public RV_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RV_Adapter.MyViewHolder holder, int position) {
        user user_ = users.get(position);
        holder.tvNama.setText(user_.getNama());
        holder.tvStatus.setText(user_.getStatus());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setOnCallbackListener(OnCallbackListener listener) {
        this.listener = listener;
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvNama, tvStatus;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            tvNama = itemView.findViewById(R.id.TV_Nama);
            tvStatus = itemView.findViewById(R.id.TV_Status);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onClick(users.get(getAdapterPosition()));
            }
        }
    }

    public interface OnCallbackListener {

        void onClick(user user);
    }

}
