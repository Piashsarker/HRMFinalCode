package com.karnaphuli.www.kportal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.model.LeaveReport;

import java.util.ArrayList;

/**
 * Created by pt on 9/16/16.
 */
public class LeaveAndTourAdapter extends RecyclerView.Adapter<LeaveAndTourAdapter.ViewHolder> {

    private ArrayList<LeaveReport> leaveAndTourList = new ArrayList<>();
    private Context context ;

    public LeaveAndTourAdapter(Context context , ArrayList<LeaveReport> leaveAndTourList){
         this.context = context ;
        this.leaveAndTourList = leaveAndTourList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_leave_and_tour,parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.from.setText(leaveAndTourList.get(position).getFromDate().substring(0,10).concat(" ").concat("("+leaveAndTourList.get(position).getTotalLeaveDays().toString())+")");
        holder.type.setText(leaveAndTourList.get(position).getLeaveTypeName());

        String leaveStatus = leaveAndTourList.get(position).getLeaveStatus();
        if(leaveStatus.equals("") || leaveStatus.equalsIgnoreCase("0")){
            holder.hr.setText("Pending");
        }
        else  if(leaveStatus.equals("1")){
            holder.hr.setText("Rejected");
        }
        else if(leaveStatus.equals("2")){
            holder.hr.setText("Reporting A");
        }
        else if(leaveStatus.equals("3")){
            holder.hr.setText("Rejected");
        }
        else  if(leaveStatus.equals("4")){
            holder.hr.setText("Final Approved");
        }
        else {
            holder.hr.setText(leaveAndTourList.get(position).getLeaveStatus());
        }


    }

    @Override
    public int getItemCount() {
        return leaveAndTourList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView from , type , hr ;

        public ViewHolder(View itemView) {
            super(itemView);

            from = (TextView) itemView.findViewById(R.id.txtFromDays);
            type = (TextView) itemView.findViewById(R.id.txtType);
            hr = (TextView) itemView.findViewById(R.id.txtHr);
        }
    }
}
