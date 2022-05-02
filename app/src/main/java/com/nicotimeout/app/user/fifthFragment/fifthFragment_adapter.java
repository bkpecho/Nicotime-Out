package com.nicotimeout.app.user.fifthFragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.nicotimeout.app.R;

import java.util.ArrayList;

public class fifthFragment_adapter extends RecyclerView.Adapter {
    Context context;
    ArrayList arrayList, arrayListName;

    public fifthFragment_adapter(Context context, ArrayList arrayList, ArrayList arrayListName) {
        this.context = context;
        this.arrayList = arrayList;
        this.arrayListName = arrayListName;
    }

    public fifthFragment_adapter() {

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_fifth_adapter, parent, false);
        ViewHolderClass viewHolderClass = new ViewHolderClass(view);
        return viewHolderClass;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass = (ViewHolderClass) holder;
        viewHolderClass.imageView.setImageResource(fifthFragment_data.data_images[position]);
        viewHolderClass.textView.setText(fifthFragment_data.data_title[position]);
        ((ViewHolderClass) holder).mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, fifthFragment_popup.class);
                intent.putExtra("title", fifthFragment_data.data_title[position]);
                intent.putExtra("description", fifthFragment_data.data_description[position]);
                intent.putExtra("images", fifthFragment_data.data_images[position]);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListName.size();
    }

    public class ViewHolderClass extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        ConstraintLayout mainLayout;

        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.fragment_fifth_imageView);
            textView = itemView.findViewById(R.id.fragment_fifth_textView);
            mainLayout = itemView.findViewById(R.id.fragment_fifth_adapter_mainLayout);
        }
    }
}
