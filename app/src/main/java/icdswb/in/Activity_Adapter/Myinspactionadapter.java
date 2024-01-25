package icdswb.in.Activity_Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import icdswb.in.ActivitySetGet.Myinspaction;
import icdswb.in.MyInspactionView;
import icdswb.in.R;

public class Myinspactionadapter extends RecyclerView.Adapter<Myinspactionadapter.ViewHolder> {
    private Context mCtx;
    private List<Myinspaction> myinspactions;
    public Myinspactionadapter(Context mCtx, List<Myinspaction> myinspactions){
        this.mCtx = mCtx;
        this.myinspactions = myinspactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myinspactionadapter, parent, false);
      return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final Myinspactionadapter.ViewHolder holder, int position) {
        Myinspaction myinspaction = myinspactions.get(position);
        holder.dateId.setText(myinspaction.getInspctndate());
        holder.districId.setText(myinspaction.getDistname());
        holder.projectId.setText(myinspaction.getProjname());
        holder.sectorId.setText(myinspaction.getSectorname());
        holder.centerId.setText(myinspaction.getCentername());
        holder.awcsID.setText(myinspaction.getInspctnid());
        holder.viewID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String insid = holder.awcsID.getText().toString();
                Intent intent = new Intent(mCtx, MyInspactionView.class);
                Bundle bundle_edit  =   new Bundle();
                bundle_edit.putString("insid",insid);
                intent.putExtras(bundle_edit);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myinspactions.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dateId,districId,projectId,sectorId,centerId,awcsID;
        Button viewID;
        public ViewHolder(View itemView) {
            super(itemView);
            dateId = itemView.findViewById(R.id.dateId);
            districId = itemView.findViewById(R.id.districId);
            projectId = itemView.findViewById(R.id.projectId);
            sectorId = itemView.findViewById(R.id.sectorId);
            centerId = itemView.findViewById(R.id.centerId);
            awcsID = itemView.findViewById(R.id.awcsID);
            viewID = itemView.findViewById(R.id.viewID);
        }
    }
}
