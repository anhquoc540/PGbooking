package com.example.photographerbooking.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.model.Category;


import java.util.List;

public class CategoryItemsAdapter extends RecyclerView.Adapter<CategoryItemsAdapter.ItemViewHolder>{
    private Context context;
    private List<Category> listCate;

    public CategoryItemsAdapter(Context context, List<Category> listCate) {
        this.context = context;
        this.listCate = listCate;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Category item = listCate.get(position);
        holder.category = item ;
        holder.txtlabel.setText(item.getLabel().toString());
        holder.contentImg.setImageResource(item.getImage());
        holder.waveImg.setImageResource(item.getColor());

    }
    @Override
    public int getItemViewType(int position) {
        if(listCate.size() == 1)
            return 0;
        else{
            if (listCate.size() % 2 == 0){
                return 1;
            }else{
                return (position > 1 && position == listCate.size()-1)?0:1;
            }
        }

    }

    @Override
    public int getItemCount() {
        return listCate.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView txtlabel ;
        ImageView contentImg;
        ImageView waveImg;
        ImageButton btnView;
        Category category;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            txtlabel = itemView.findViewById(R.id.txtLabel);
           contentImg = itemView.findViewById(R.id.category_image);
            waveImg = itemView.findViewById(R.id.wave_image);
            btnView = itemView.findViewById(R.id.btnView);


            btnView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("demo", "onClick: item clicked " + category.getLabel());
                }
            });
        }
    }
}


