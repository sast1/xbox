package com.example.version1.adaptadores;

import android.content.Context;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.version1.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {


    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;
    final ListAdapter.OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(ListElement item);
    }


    public ListAdapter(List<ListElement> itemList, Context context, ListAdapter.OnItemClickListener listener) {

        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));


    }


    public void setItems(List<ListElement> items) {
        mData = items;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView portada;
        TextView nombre, empresa, presio, j, nombreusu, gmailcom, cedulacom, telefonocom, costo, limite;


        ViewHolder(View itemView) {
            super(itemView);
            portada = itemView.findViewById(R.id.portada);
            nombre = itemView.findViewById(R.id.nombre_del_juego);
            empresa = itemView.findViewById(R.id.empresa_del_juego);
            presio = itemView.findViewById(R.id.presio);
            j = itemView.findViewById(R.id.j);

            /*datos para la base de datos del comprador*/
            nombreusu = itemView.findViewById(R.id.nombreusu);
            gmailcom = itemView.findViewById(R.id.gmailcom);
            cedulacom = itemView.findViewById(R.id.cedulacom);
            telefonocom = itemView.findViewById(R.id.telefonocom);
            costo = itemView.findViewById(R.id.costo);
            limite = itemView.findViewById(R.id.limites);
            /*datos para la base de datos del comprador*/
        }


        void bindData(final ListElement item) {


            portada.setImageResource(item.getPortada());
            nombre.setText(item.getNombre());
            empresa.setText(item.getEmpresa());
            presio.setText(item.getPresio());
            j.setText(item.getJ());/*descripcion*/

            /*datos para la base de datos del comprador*/
            nombreusu.setText(item.getNombreusu());
            gmailcom.setText(item.getGmailcom());
            cedulacom.setText(item.getCedulacom());
            telefonocom.setText(item.getTelefonocom());
            costo.setText(item.getCosto());
            limite.setText(item.getEl_limite());
            /*datos para la base de datos del comprador*/

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);

                }
            });

        }
    }
}



