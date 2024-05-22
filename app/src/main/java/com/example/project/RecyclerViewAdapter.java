package com.example.project;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    // Fields
    private List<Animal> items;
    private LayoutInflater layoutInflater;
    private OnClickListener onClickListener;

    // Constructor
    RecyclerViewAdapter(Context context, List<Animal> items, OnClickListener onClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = items;
        this.onClickListener = onClickListener;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Animal animal = items.get(position);

        // Displays the different values except img and info
        holder.name.setText(animal.getName());
        holder.age.setText(String.valueOf(animal.getCompany() + " år"));
        holder.location.setText(animal.getLocation());
        holder.color.setText(animal.getCategory());
        holder.length.setText(String.valueOf(animal.getSize() + " m"));
        holder.weight.setText(String.valueOf(animal.getCost() + " kg"));

        // Displays the img and info fields values
        if (animal.getAuxdata() != null) {
            Picasso.get().load(animal.getAuxdata().getImg()).into(holder.auxImage);
            Picasso.get().load(animal.getAuxdata().getImg()).placeholder(R.drawable.ic_launcher_background).into(holder.auxImage);
        }
    }

    // Gets the size of the items list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Adds the different Views
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView age;
        TextView location;
        TextView color;
        TextView length;
        TextView weight;
        ImageView auxImage;

        // Sets the value of the data onto the View
        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            location = itemView.findViewById(R.id.location);
            color = itemView.findViewById(R.id.color);
            length = itemView.findViewById(R.id.length);
            weight = itemView.findViewById(R.id.weight);
            auxImage = itemView.findViewById(R.id.auxImage);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(items.get(getAdapterPosition()));
        }
    }

    public interface OnClickListener {
        void onClick(Animal animal);
    }
}