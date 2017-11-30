package com.example.codetride.personalassistantapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by CodeTride on 2017/08/14.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final Context context;

    private List<Contact> contacts;

    public RecyclerAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        //allows us to take on of textviews
        final Contact contact;
        contact = contacts.get(position);

        holder.message.setText(contact.getMessage());
        holder.date.setText(contact.getDate());
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Deleting");
                builder.setMessage("Delete this text?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SQLHelper sqlHelper = new SQLHelper(context);

                        sqlHelper.Delete(contact.getId());

                        final Intent intent = new Intent(context, Assistant.class);
                        context.startActivity(intent);
                    }
                });
                builder.setNegativeButton("All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SQLHelper sqlHelper = new SQLHelper(context);

                        sqlHelper.DeleteAll();

                        final Intent intent = new Intent(context, Assistant.class);
                        context.startActivity(intent);
                    }
                });
                builder.create();
                builder.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //global declaration
        TextView message, date;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            //casting
            message = itemView.findViewById(R.id.message);
            date = itemView.findViewById(R.id.date);
            cardView = itemView.findViewById(R.id.card);
        }
    }
}
