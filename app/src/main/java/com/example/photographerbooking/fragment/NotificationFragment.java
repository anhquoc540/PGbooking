package com.example.photographerbooking.fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.RecoverySystem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.photographerbooking.R;
import com.example.photographerbooking.adapter.NotificationAdapter;
import com.example.photographerbooking.interfaces.ItemOnClickListener;
import com.example.photographerbooking.model.Notification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parent = inflater.inflate(R.layout.fragment_notification, container, false);
        RecyclerView recyclerView = parent.findViewById(R.id.rvNotification);
        List<Notification> notificationList = new ArrayList<>();
        int id = 0;
        while (id < 25) {
            id = id + 1;
            if (id < 5) {
                notificationList.add(new Notification(id, "Your book on Maria wedding service has been declined", id + "", LocalDateTime.now().minusHours(2)));
            } else if (id < 10) {
                notificationList.add(new Notification(id, "Your book on Maria wedding service has been declined", id + "", LocalDateTime.now().minusDays(2)));
            } else if (id < 15) {
                notificationList.add(new Notification(id, "Your book on Maria wedding service has been declined", id + "", LocalDateTime.now().minusWeeks(2)));
            }else if(id < 18){
                notificationList.add(new Notification(id, "Your book on Maria wedding service has been declined", id + "", LocalDateTime.now().minusMonths(2)));
            }else if(id < 22){
                notificationList.add(new Notification(id, "Your book on Maria wedding service has been declined", id + "", LocalDateTime.now().minusYears(2)));
            }
        }
        NotificationAdapter adapter = new NotificationAdapter(notificationList, this.getContext(), new ItemOnClickListener<Integer>() {
            @Override
            public void OnItemClicked(Integer id) {

            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return parent;
    }
}