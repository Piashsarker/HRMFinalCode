package com.karnaphuli.www.kportal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.karnaphuli.www.kportal.R;
import com.karnaphuli.www.kportal.model.Contact;

import java.util.ArrayList;

/**
 * Created by pt on 9/14/16.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Contact> contactList = new ArrayList<>() ;
    private  OnItemClickListener onItemClickListener;
    public ContactAdapter(Context context , ArrayList<Contact> contactList){
        this.context= context;
        this.contactList = contactList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_contact,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
              holder.imgLeftArrow.setImageResource(R.drawable.arrow_right);
              holder.name.setText(contactList.get(position).getEmployeeName());
              holder.designation.setText(contactList.get(position).getDesignationName());

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }
    public void setFilter(ArrayList<Contact> contactListModel){
        contactList = new ArrayList<>();
        contactList.addAll(contactListModel);
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout contactHolder ;
        ImageView imgLeftArrow ;
        TextView name , designation ;
        CheckBox checkBox ;

        public ViewHolder(View itemView) {
            super(itemView);
            contactHolder = (LinearLayout) itemView.findViewById(R.id.contactHolder);
            imgLeftArrow = (ImageView) itemView.findViewById(R.id.img_arrow_left);
            name = (TextView) itemView.findViewById(R.id.contact_name);
            designation = (TextView) itemView.findViewById(R.id.contact_designation);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            contactHolder.setOnClickListener(this);
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
