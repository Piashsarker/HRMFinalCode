package com.karnaphuli.www.kportal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.model.IndividualAttendance;

import java.util.ArrayList;

/**
 * Created by pt on 9/15/16.
 */
public class AttendanceReportAdapter extends RecyclerView.Adapter<AttendanceReportAdapter.ViewHolder> {
    private Context context;
    private ArrayList<IndividualAttendance> attendanceList = new ArrayList<>();

    public AttendanceReportAdapter(Context context, ArrayList<IndividualAttendance> attendanceList) {
        this.context = context;
        this.attendanceList = attendanceList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_attendance_report, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.date.setText(attendanceList.get(position).getADate().substring(0, 10));
        holder.status.setText(attendanceList.get(position).getAStatus().toString());
        if(!attendanceList.get(position).getALogin().isEmpty()){
            holder.in.setText(attendanceList.get(position).getALogin().substring(0,5));
        }
        else {
            holder.in.setText("     ");
        }
        if( !attendanceList.get(position).getALogout().isEmpty()){
            holder.out.setText(attendanceList.get(position).getALogout().substring(0,5));
        }
        else {

            holder.out.setText("    ");
        }

    }

    @Override
    public int getItemCount() {
        return attendanceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date , in , out , status ;
        public ViewHolder(View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.txt_date);
            in = (TextView) itemView.findViewById(R.id.txt_in);
            out = (TextView) itemView.findViewById(R.id.txt_out);
            status = (TextView) itemView.findViewById(R.id.txt_remark);
        }

    }
}
