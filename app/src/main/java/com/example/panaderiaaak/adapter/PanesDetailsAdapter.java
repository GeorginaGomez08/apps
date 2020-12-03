package com.example.panaderiaaak.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.panaderiaaak.R;
import com.example.panaderiaaak.Updatepan;
import com.example.panaderiaaak.database.PanDatbaseContract;
import com.example.panaderiaaak.database.PanDatbaseHelper;
import com.example.panaderiaaak.mode.PanDetails;

import java.util.List;

public class PanesDetailsAdapter extends RecyclerView.Adapter<PanesDetailsAdapter.PanViewHolder>  {

    List<PanDetails> panDetailsList;
    Context context;
    PanDatbaseHelper dbHelper;
    SQLiteDatabase db;

    public PanesDetailsAdapter(List<PanDetails> panDetailsList) {
        this.panDetailsList = panDetailsList;
    }

    @Override
    public PanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View iteView = inflater.inflate(R.layout.list_item, parent, false);
        PanViewHolder viewHolder = new PanViewHolder(iteView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final PanViewHolder holder, final int position) {

        PanDetails panDetails = panDetailsList.get(position);
        holder.tvNombre.setText(panDetails.getNombre());
        holder.tvDescripcion.setText(panDetails.getDescripcion());
        holder.tvPrecio.setText(panDetails.getPrecio());
        holder.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PanDetails panDetails = panDetailsList.get(position);
                final int panId = panDetails.getPanId();
                dbHelper = new PanDatbaseHelper(context);
                db = dbHelper.getWritableDatabase();
                PopupMenu menu = new PopupMenu(context, holder.ivMenu);

                menu.inflate(R.menu.popup_menu);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete:
                                db.delete(PanDatbaseContract.PanDatbase.TABLE_PAN, PanDatbaseContract.PanDatbase._ID + " = " + panId,null);
                                notifyItemRangeChanged(position,panDetailsList.size());
                                panDetailsList.remove(position);
                                notifyItemRemoved(position);
                                db.close();
                                break;
                            case R.id.update:
                                Intent intent = new Intent(context, Updatepan.class);
                                intent.putExtra("USERID", panId);
                                context.startActivity(intent);
                                break;
                        }


                        return false;
                    }
                });
                menu.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return panDetailsList.size();
    }


    public class PanViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvDescripcion, tvPrecio;
        ImageView ivMenu;
        public PanViewHolder(View itemView){
            super((itemView));
            tvNombre = (TextView) itemView.findViewById(R.id.tv_nombre);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tv_descripcion);
            tvPrecio = (TextView) itemView.findViewById(R.id.tv_precio);
            ivMenu = (ImageView) itemView.findViewById(R.id.iv_menu);
        }

    }
}
