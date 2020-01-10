package com.hfad.homework4geninf;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

class BlogCardAdapter extends RecyclerView.Adapter<BlogCardAdapter.ViewHolder>{
    private final ArrayList<BlogMessage> messages;

    private Listener listener;
    private String userId;
    interface Listener {
        void onClick(int position);
    }
    public void setListener(Listener listener){
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }
    public BlogCardAdapter(ArrayList<BlogMessage> messages,String userId){
        this.messages = messages;
        this.userId = userId;

    }
    @Override
    public int getItemCount(){
        return messages.size();
    }
    @Override
    public BlogCardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_card, parent, false);

        return new ViewHolder(cv);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        CardView cardView = holder.cardView;

        TextView titleTextView = (TextView)cardView.findViewById(R.id.card_title);
        TextView contentTextView = (TextView)cardView.findViewById(R.id.card_content);
        TextView headerTextView = (TextView)cardView.findViewById(R.id.card_header);
       // Button editButton = cardView.findViewById(R.id.editButton);
        //Button removeButton = cardView.findViewById(R.id.removeButton);

        BlogMessage message = messages.get(position);
        titleTextView.setText(message.getTitle());
        contentTextView.setText(message.getContent());
        headerTextView.setText(message.getUserLogin()+" On "+message.getDate());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }
}
