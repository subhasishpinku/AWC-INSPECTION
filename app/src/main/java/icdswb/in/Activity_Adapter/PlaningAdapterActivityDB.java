package icdswb.in.Activity_Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import icdswb.in.ActivitySetGet.Dbprocess;
import icdswb.in.R;


public class PlaningAdapterActivityDB  extends RecyclerView.Adapter<PlaningAdapterActivityDB.ViewHolder> {
    private Context mCtx;
    private List<Dbprocess> planinglistdb;

    public PlaningAdapterActivityDB(Context mCtx, List<Dbprocess> planinglistdb){
        this.mCtx = mCtx;
        this.planinglistdb = planinglistdb;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.planingrecyclerviewdb, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder,
                                 int position) {
        Dbprocess PlaningListobj = planinglistdb.get(position);
        holder.dateId.setText(PlaningListobj.getCurrentdate());
       // holder.tableID.setText(PlaningListobj.getIdprocess());
        holder.districId.setText(PlaningListobj.getDistricnamedb());
        holder.projectId.setText(PlaningListobj.getProjectnamedb());
        holder.sectorId.setText(PlaningListobj.getSectorrnamedb());
        holder.centerId.setText(PlaningListobj.getCenternamedb());
        holder.awcsID.setText(PlaningListobj.getAwcsid());
        int totalPrice = 0;
        for (int i = 0; i<planinglistdb.size(); i++)
        {
            holder.tableID.setText(Integer.toString(position+1));

        }

    }


    @Override
    public int getItemCount() {
        return planinglistdb.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dateId,dayID,districId,projectId,sectorId,centerId,tableID,awcsID;
        public ViewHolder(View itemView) {
            super(itemView);
            dateId = itemView.findViewById(R.id.dateId);
            dayID = itemView.findViewById(R.id.dayID);
            districId = itemView.findViewById(R.id.districId);
            projectId = itemView.findViewById(R.id.projectId);
            sectorId = itemView.findViewById(R.id.sectorId);
            centerId = itemView.findViewById(R.id.centerId);
            tableID = itemView.findViewById(R.id.tableID);
            awcsID = itemView.findViewById(R.id.awcsID);
        }
    }

}
