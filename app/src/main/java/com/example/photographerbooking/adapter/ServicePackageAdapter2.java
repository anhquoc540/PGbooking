package com.example.photographerbooking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.data.PhotographerData;
import com.example.photographerbooking.model.Category;
import com.example.photographerbooking.model.PhotoService;
import com.example.photographerbooking.model.Photographer;

import java.util.List;

public class ServicePackageAdapter2 extends BaseAdapter {
    private Context context;
    private List<PhotoService> listService;
    private LayoutInflater inflater;
    final private ListItemClickListener mOnClickListener;

    public ServicePackageAdapter2(Context context, List<PhotoService> listService, ListItemClickListener mOnClickListener) {
        this.context = context;
        this.listService = listService;
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public int getCount() {
        return listService.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public interface ListItemClickListener {
        void onMoreServiceClick(int clickedItemIndex);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null) {
            view = inflater.inflate(R.layout.service_package_item, null);
        }

        ImageView serviceImage = view.findViewById(R.id.serviceImage);
        TextView serviceName = view.findViewById(R.id.serviceName);
        TextView rating = view.findViewById(R.id.rating);
        TextView price = view.findViewById(R.id.price);
        TextView pgName = view.findViewById(R.id.tvPGName);
        ImageView ivAvatar = view.findViewById(R.id.ivAvatar);
        RatingBar ratingBar = view.findViewById(R.id.rbAverageRating);

        PhotoService service = listService.get(i);
        PhotographerData dataPG = new PhotographerData();
        Photographer pg = dataPG.getPG(service.getIdPG());

        serviceImage.setImageResource(service.getRepresentativeImg());
        serviceName.setText(service.getName());
        rating.setText(service.getRating() + "");
        price.setText("$" + String.format("%.0f", service.getPrice()));
        ratingBar.setRating(service.getRating());
        pgName.setText(pg.getName());
        ivAvatar.setImageResource(pg.getAvatar());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnClickListener.onMoreServiceClick(i);
            }
        });

        return view;
    }
}
