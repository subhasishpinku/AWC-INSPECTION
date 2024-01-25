package icdswb.in.Activity_Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import icdswb.in.ActivitySetGet.PlaningList;
import icdswb.in.R;


public class PlaningAdapterActivity  extends RecyclerView.Adapter<PlaningAdapterActivity.ViewHolder> {
    private Context mCtx;
    private List<PlaningList> planinglist;
    public PlaningAdapterActivity(Context mCtx, List<PlaningList> planinglist){
        this.mCtx = mCtx;
        this.planinglist = planinglist;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.planingrecyclerview, parent, false);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(ViewHolder holder,
                                 int position) {
        PlaningList PlaningListobj = planinglist.get(position);
        holder.dateId.setText(PlaningListobj.getPlanDT());
//        holder.dayID.setText(PlaningListobj.getDayID());
      //  holder.districId.setText(PlaningListobj.getDistricID());
        holder.projectId.setText(PlaningListobj.getProjectName());
        holder.sectorId.setText(PlaningListobj.getSectorName());
        holder.centerId.setText(PlaningListobj.getCentreName());
    }
    @Override
    public int getItemCount() {
        return planinglist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dateId,dayID,districId,projectId,sectorId,centerId;
        public ViewHolder(View itemView) {
            super(itemView);
            dateId = itemView.findViewById(R.id.dateId);
            dayID = itemView.findViewById(R.id.dayID);
         //   districId = itemView.findViewById(R.id.districId);
            projectId = itemView.findViewById(R.id.projectId);
            sectorId = itemView.findViewById(R.id.sectorId);
            centerId = itemView.findViewById(R.id.centerId);
            }
            }
}
