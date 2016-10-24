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
import com.karnaphuli.www.kportal.model.Outbox;

import java.util.ArrayList;

/**
 * Created by pt on 9/14/16.
 */
public class OutboxAdapter extends RecyclerView.Adapter<OutboxAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Outbox> outboxArrayList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    public OutboxAdapter(Context context, ArrayList<Outbox> outboxArrayList) {
        this.context = context;
        this.outboxArrayList = outboxArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_memo_inbox, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position % 2 == 0 || position == 0) {
            holder.inboxHolder.setBackgroundColor(Color.parseColor("#c7c4c4"));
        }
        holder.name.setText(outboxArrayList.get(position).getFromEmployeeName().toString().toUpperCase()+" ("+outboxArrayList.get(position).getFromEmployeeCode()+")");
        if (outboxArrayList.get(position).getSubject().length() > 30) {
            holder.subject.setText(outboxArrayList.get(position).getSubject().substring(0, 30) + "...");
        } else {
            holder.subject.setText(outboxArrayList.get(position).getSubject().toString());
        }

        holder.date.setText(outboxArrayList.get(position).getDateNTime().substring(0, 10) + " , " + outboxArrayList.get(position).getDateNTime().substring(11, 16));

    }

    @Override
    public int getItemCount() {
        return outboxArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, subject, date;
        LinearLayout inboxHolder;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.senderId);
            subject = (TextView) itemView.findViewById(R.id.subject);
            date = (TextView) itemView.findViewById(R.id.date);
            inboxHolder = (LinearLayout) itemView.findViewById(R.id.inbox_holder);
            inboxHolder.setOnClickListener(this);

            Typeface typeface = Typeface.createFromAsset(context.getAssets(),"fonts/roboto_light.ttf");
            name.setTypeface(typeface);
            subject.setTypeface(typeface);
            date.setTypeface(typeface);
        }


        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}