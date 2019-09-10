package com.moha.instaforexapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.moha.instaforexapp.Modal.Item;
import com.moha.instaforexapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item> itemList;
    private Context context;

    public ItemAdapter(Context context,List<Item> itemList ) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_model,viewGroup,
                false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {

        Item item = itemList.get(position);
            itemViewHolder.txt_time.setText(String.format(Locale.getDefault(),
                    "TimeActual: %d", item.getActualTime()));
            itemViewHolder.txt_comment.setText(String.format("Comment: %s", item.getComment()));
            itemViewHolder.txt_pair.setText(String.format("Pair: %s", item.getPair()));
            itemViewHolder.txt_period.setText(String.format("Period: %s", item.getPeriod()));
            itemViewHolder.txt_price.setText(String.format("Price: %s", item.getPrice()));
            itemViewHolder.txt_Sl.setText(String.format("Sl: %s", item.getSl()));
            itemViewHolder.txt_Tp.setText(String.format("Tp: %s", item.getTp()));


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_time,txt_comment,txt_pair,
                txt_period,txt_price,txt_Sl,txt_Tp;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_time = itemView.findViewById(R.id.txt_time);
            txt_comment =itemView.findViewById(R.id.txt_comment);
            txt_pair = itemView.findViewById(R.id.txt_pair);
            txt_period=itemView.findViewById(R.id.txt_period);
            txt_price=itemView.findViewById(R.id.txt_price);
            txt_Sl=itemView.findViewById(R.id.txt_Sl);
            txt_Tp=itemView.findViewById(R.id.txt_Tp);
        }
    }
}
