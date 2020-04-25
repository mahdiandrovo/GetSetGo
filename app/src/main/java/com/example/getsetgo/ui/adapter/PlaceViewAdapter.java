package com.example.getsetgo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.getsetgo.R;
import com.example.getsetgo.data.network.responses.Place;

import java.util.List;

public class PlaceViewAdapter extends RecyclerView.Adapter<PlaceViewAdapter.PlaceHolder> {

    private List<Place> places;
    private Context context;

    private OnItemClickListener onItemClickListener;

    public PlaceViewAdapter(List<Place> places, Context context) {
        this.places = places;
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_place, parent, false);
        return new PlaceHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceHolder holder, int position) {
        Place place = places.get(position);
        holder.textView_placename.setText(place.getName());
        holder.textView_placelocation.setText(place.getLocation());
        holder.textView_placedescription.setText(place.getDescription());
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public class PlaceHolder extends RecyclerView.ViewHolder {
        private TextView textView_placename;
        private TextView textView_placelocation;
        private TextView textView_placedescription;

        public PlaceHolder(@NonNull View itemView) {
            super(itemView);
            textView_placename = itemView.findViewById(R.id.place_name);
            textView_placelocation = itemView.findViewById(R.id.place_location);
            textView_placedescription = itemView.findViewById(R.id.place_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (onItemClickListener != null && position != RecyclerView.NO_POSITION)
                        onItemClickListener.onItemClick(places.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Place place);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
