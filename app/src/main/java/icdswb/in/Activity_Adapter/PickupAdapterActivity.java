package icdswb.in.Activity_Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import icdswb.in.ActivitySetGet.PickupActivityList;
import icdswb.in.R;

public class PickupAdapterActivity  extends RecyclerView.Adapter<PickupAdapterActivity.ViewHolder> {
    private Context mCtx;
    private List<PickupActivityList> pickupActivityLists;
    public PickupAdapterActivity(Context mCtx, List<PickupActivityList> pickupActivityLists){
        this.mCtx = mCtx;
        this.pickupActivityLists = pickupActivityLists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pickuplistadapter, parent, false);
        return new ViewHolder(view);

    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PickupActivityList pickupListobj = pickupActivityLists.get(position);
        holder.district.setText(pickupListobj.getDistrict());
        holder.project.setText(pickupListobj.getProject());
        holder.sector.setText(pickupListobj.getSector());
        holder.center.setText(pickupListobj.getCenter());


    }
    @Override
    public int getItemCount() {
        return pickupActivityLists.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView district,project,sector,center,sectorId,centerId;
        Button procedIdd,officerIdd;
        public ViewHolder(View itemView) {
            super(itemView);
            district = itemView.findViewById(R.id.district);
            project = itemView.findViewById(R.id.project);
            sector = itemView.findViewById(R.id.sector);
            procedIdd = itemView.findViewById(R.id.procedIdd);
            officerIdd = itemView.findViewById(R.id.officerIdd);
            center = itemView.findViewById(R.id.center);
        }
    }
}
