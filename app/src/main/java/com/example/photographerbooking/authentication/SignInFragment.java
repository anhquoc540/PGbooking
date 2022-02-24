package com.example.photographerbooking.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.photographerbooking.MainActivity;
import com.example.photographerbooking.PhotographerMainActivity;
import com.example.photographerbooking.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInFragment extends Fragment implements View.OnClickListener {
    private final String PHOTOGRAPHER = "photographer";
    private final String REQUIRE = "Require";
    private View view;
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    public SignInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin) login();
    }

    private boolean checkLogin() {
        if (TextUtils.isEmpty(etUsername.getText().toString())) {
            etUsername.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError(REQUIRE);
            return false;
        }
        return true;
    }

    private void login() {
        if (!checkLogin()) return;
        Log.i("Username", etUsername.getText().toString());
        Intent intent = null;
        if (etUsername.getText().toString().equals(PHOTOGRAPHER)) {
            intent = new Intent(this.getActivity(), PhotographerMainActivity.class);
        } else {
            intent = new Intent(this.getActivity(), MainActivity.class);
        }
        startActivity(intent);
        this.getActivity().finish();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("SIGN IN FRAGMENT", "STOPPED");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("SIGN IN FRAGMENT", "DESTROYED");
    }
}