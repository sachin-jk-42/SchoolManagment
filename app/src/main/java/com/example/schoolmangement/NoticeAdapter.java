package com.example.schoolmangement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.MyViewHolder> {
    private Context context;

    public NoticeAdapter(Context context, ArrayList<String> noticeList) {
        this.context = context;
        this.noticeList = noticeList;
    }

    private ArrayList<String> noticeList = new ArrayList<>();
    @NonNull
    @Override
    public NoticeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notice_item,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeAdapter.MyViewHolder holder, int position) {
        holder.message.setText(noticeList.get(position));
        String temp = String.valueOf(position+1)+ ". ";
        holder.sno.setText(temp);
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView sno, message;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sno = itemView.findViewById(R.id.sno);
            message = itemView.findViewById(R.id.message);
        }
    }
}
