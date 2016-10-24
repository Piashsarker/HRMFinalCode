package com.karnaphuli.www.kportal.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.model.Inbox;

import java.util.ArrayList;

/**
 * Created by pt on 9/13/16.
 */
public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.ViewHolder> {
    private Context context ;
    private ArrayList<Inbox> inboxList = new ArrayList<>();
    private Typeface typeface;
    private OnItemClickListener onItemClickListener;
    public InboxAdapter(Context context , ArrayList<Inbox> inboxlist){
        this.context = context ;
        this.inboxList = inboxlist;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_memo_inbox, parent,false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        typeface= Typeface.createFromAsset(context.getAssets(),"fonts/roboto_light.ttf");
        if(inboxList.get(position).getReadStatus().equals("N")){
            holder.name.setTypeface(typeface,Typeface.BOLD);
            holder.subject.setTypeface(typeface,Typeface.BOLD);
            holder.date.setTypeface(typeface,Typeface.BOLD);
        }
        else{
            holder.name.setTypeface(typeface);
            holder.subject.setTypeface(typeface);
            holder.date.setTypeface(typeface);
        }
        if(position%2==0 || position==0){
            holder.inboxHolder.setBackgroundColor(Color.parseColor("#c7c4c4"));
        }


        holder.name.setText(inboxList.get(position).getFromEmployeeName().toString().toUpperCase()+" ("+inboxList.get(position).getFromEmployeeCode()+")");
        if(inboxList.get(position).getSubject().length()>30){
            holder.subject.setText(inboxList.get(position).getSubject().substring(0,30)+"...");

        }
        else {
            holder.subject.setText(inboxList.get(position).getSubject().toString());
        }

        holder.date.setText("Date: "+inboxList.get(position).getDateNTime().toString().substring(0,10)+" , "+inboxList.get(position).getDateNTime().substring(12,16));

    }

    @Override
    public int getItemCount() {

        return inboxList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name , subject , date ;
        LinearLayout inboxHolder;
        public ViewHolder(View itemView) {
            super(itemView);
            inboxHolder = (LinearLayout) itemView.findViewById(R.id.inbox_holder);
            name = (TextView) itemView.findViewById(R.id.senderId);
            subject = (TextView) itemView.findViewById(R.id.subject);
            date = (TextView) itemView.findViewById(R.id.date);





            inboxHolder.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface  OnItemClickListener{
        public void onItemClick(View v , int position);
    }
    public void setOnItemClickListener(final OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
