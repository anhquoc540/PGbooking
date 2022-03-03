package com.example.photographerbooking.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.model.Category;

import java.util.List;

public class CategoryItemsAdapter extends BaseAdapter {
    private Context context;
    private List<Category> listCategory;
    private LayoutInflater inflater;

    public CategoryItemsAdapter(Context context, List<Category> listCategory) {
        this.context = context;
        this.listCategory = listCategory;
    }

    @Override
    public int getCount() {
        return this.listCategory.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null) {
            view = inflater.inflate(R.layout.category_item, null);
        }

        ImageView ivCategory = view.findViewById(R.id.ivCategory);
        TextView tvCategoryLabel = view.findViewById(R.id.tvCategoryLabel);
        ImageButton btnMoreDetail = view.findViewById(R.id.btnMoreDetail);

        ivCategory.setImageResource(listCategory.get(i).getImage());
        tvCategoryLabel.setText(listCategory.get(i).getLabel());

        btnMoreDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("FAB", "clicked" + i);
            }
        });

        return view;
    }
}


