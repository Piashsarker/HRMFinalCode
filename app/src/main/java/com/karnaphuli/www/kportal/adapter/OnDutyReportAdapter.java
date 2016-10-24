package com.karnaphuli.www.kportal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.model.OnDutyView;

import java.util.ArrayList;

/**
 * Created by pt on 9/21/16.
 */
public class OnDutyReportAdapter extends RecyclerView.Adapter<OnDutyReportAdapter.ViewHolder> {

    private Context context ;
    private ArrayList<OnDutyView> onDutyViewArrayList;

    public  OnDutyReportAdapter(Context context , ArrayList<OnDutyView> onDutyViewArrayList){
        this.context = context ;
        this.onDutyViewArrayList = onDutyViewArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_leave_and_tour,parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            holder.from.setText(onDutyViewArrayList.get(position).getFromDate().substring(0,10));
            holder.remark.setText(onDutyViewArrayList.get(position).getRemarks());


        String leaveStatus = onDutyViewArrayList.get(position).getTourStatus();
        if(leaveStatus.equals("") || leaveStatus.equalsIgnoreCase("0")){
            holder.tourStatus.setText("Pending");
        }
        else  if(leaveStatus.equals("1")){
            holder.tourStatus.setText("Rejected");
        }
        else if(leaveStatus.equals("2")){
            holder.tourStatus.setText("Reporting A");
        }
        else if(leaveStatus.equals("3")){
            holder.tourStatus.setText("Rejected");
        }
        else  if(leaveStatus.equals("4")){
            holder.tourStatus.setText("Final Approved");
        }
        else {
            holder.tourStatus.setText(onDutyViewArrayList.get(position).getTourStatus());
        }

    }

    @Override
    public int getItemCount() {
        return onDutyViewArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView from , remark , tourStatus ;
        public ViewHolder(View itemView) {
            super(itemView);

            from = (TextView) itemView.findViewById(R.id.txtFromDays);
            remark = (TextView) itemView.findViewById(R.id.txtType);
            tourStatus = (TextView) itemView.findViewById(R.id.txtHr);
        }
    }
}
