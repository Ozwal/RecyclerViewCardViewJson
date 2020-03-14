package tinoco.castro.recyclerviewcardviewjson;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ViewHolder> {

    private List<ListaItem> listaItems;
    private Context context;

    public MiAdaptador(List<ListaItem> listaItems, Context context) {
        this.listaItems = listaItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ListaItem listaItem = listaItems.get(position);

        //asigna el texto a cada textview dependiendo de la posicion de la lista
        holder.textViewHead.setText(listaItem.getHead());
        //holder.textViewDesc.setText(listaItem.getDesc());

        Picasso.with(context).load(listaItem.getImageUri()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listaItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewHead;
        public TextView textViewDesc;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewHead = (TextView)itemView.findViewById(R.id.textViewHead);
            textViewDesc =(TextView)itemView.findViewById(R.id.textViewDesc);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}
