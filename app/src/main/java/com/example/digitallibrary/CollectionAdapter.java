package com.example.digitallibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.MyViewHolder> {

    Context context;
    ArrayList userId;
    ArrayList title;
    ArrayList author;
    ArrayList desc;
    ArrayList type;
    ArrayList viewed;
    ArrayList datePublished;
    CardView cardCollection;

    CollectionAdapter(Context context, ArrayList userId, ArrayList title, ArrayList author, ArrayList desc, ArrayList type, ArrayList viewed, ArrayList datePublished) {
        this.context = context;
        this.userId = userId;
        this.title = title;
        this.author = author;
        this.desc = desc;
        this.type = type;
        this.viewed = viewed;
        this.datePublished = datePublished;
    }

    @NonNull
    @Override
    public CollectionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.collection_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtType.setText(String.valueOf(type.get(position)));
        holder.txtTitle.setText(String.valueOf(title.get(position)));
        holder.txtAuthor.setText(String.valueOf(author.get(position)));

        cardCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), CollectionDetailActivity.class);
                intent.putExtra("title", String.valueOf(title.get(position)));
                intent.putExtra("author", String.valueOf(author.get(position)));
                intent.putExtra("desc", String.valueOf(desc.get(position)));
                intent.putExtra("type", String.valueOf(type.get(position)));
                intent.putExtra("viewed", String.valueOf(viewed.get(position)));
                intent.putExtra("datePublished", String.valueOf(datePublished.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtType, txtTitle, txtAuthor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtType = itemView.findViewById(R.id.txtType);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            cardCollection = itemView.findViewById(R.id.cardCollection);

        }
    }
}
