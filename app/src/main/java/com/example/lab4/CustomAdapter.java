package com.example.lab4;

import static android.widget.Toast.makeText;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList noteId, title, content, time;
    int position;

    CustomAdapter(Context context, ArrayList noteId, ArrayList title, ArrayList content, ArrayList time){
        this.context = context;
        this.noteId = noteId;
        this.title = title;
        this.content = content;
        this.time = time;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.new_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        this.position = position;

        holder.tvNoteID.setText(String.valueOf(noteId.get(position)));
        holder.tvTitle.setText(String.valueOf(title.get(position)));
        long timeLong = Long.parseLong(String.valueOf(time.get(position)));
        String formattedTime = DateFormat.getDateTimeInstance().format(timeLong);
        holder.tvTime.setText(String.valueOf(formattedTime));
        holder.mainLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                PopupMenu menu = new PopupMenu(context,v);
                menu.getMenu().add("Delete Note");
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("Delete Note")){
                            Intent intent = new Intent(context, UpdateNoteActivity.class);
                            intent.putExtra("id",String.valueOf(noteId.get(position)));
                            DatabaseHelper dbHE = new DatabaseHelper(context);
                            String id = intent.getStringExtra("id");
                            dbHE.deleteNote(id);
                            intent = new Intent(context, MainActivity.class);
                            context.startActivity(intent);

                        }
                        return true;
                    }
                });
                menu.show();
                return true;

            }
        });





        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateNoteActivity.class);
                intent.putExtra("id",String.valueOf(noteId.get(position)));
                intent.putExtra("title",String.valueOf(title.get(position)));
                intent.putExtra("content",String.valueOf(content.get(position)));
                intent.putExtra("time",String.valueOf(time.get(position)));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvNoteID, tvTitle, tvTime;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNoteID = itemView.findViewById(R.id.tvNoteID);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvTime = itemView.findViewById(R.id.tvTime);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

}
