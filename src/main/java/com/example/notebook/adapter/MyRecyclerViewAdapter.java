package com.example.notebook.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notebook.NoteActivity;
import com.example.notebook.R;
import com.example.notebook.model.Note;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private static LayoutInflater inflater;
    private static List<Note> list;

    public MyRecyclerViewAdapter(Context context, List<Note> list) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String headline = list.get(i).getHeadline();
        String body = list.get(i).getBody();
        long id = list.get(i).getId();

        viewHolder.nHeadLine.setText(headline);
        viewHolder.nBody.setText(body);
        viewHolder.nId.setText(String.valueOf(list.get(i).getId()));

        //holder.textView.setText(list.get(position)); //bind data to one row in the view

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nHeadLine, nBody, nId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nHeadLine = itemView.findViewById(R.id.nHeadline);
            nBody = itemView.findViewById(R.id.nBody);
            nId = itemView.findViewById(R.id.listId);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), NoteActivity.class);
                    intent.putExtra("id", list.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(intent);
                }
            });
        }

    }
}



