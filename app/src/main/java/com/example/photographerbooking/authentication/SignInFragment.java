package com.example.photographerbooking.authentication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.photographerbooking.MainActivity;
import com.example.photographerbooking.R;
import com.example.photographerbooking.helper.SoftKeyBoardHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SignInFragment extends Fragment implements View.OnClickListener {

    private final String REQUIRE = "Require";
    private final String INVALID = "Wrong username or password";
    private View view;
    private EditText etUsername;
    private EditText etPassword;
    private TextView tvError;
    private FloatingActionButton btnLogin;

    public SignInFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        etUsername = view.findViewById(R.id.etUsername);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvError = view.findViewById(R.id.tvError);
        tvError.setText("");

        btnLogin.setOnClickListener(this);

        etPassword.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    SoftKeyBoardHelper.hideSoftKeyboard(getActivity());
                    btnLogin.requestFocus();
                    return true;
                }
                return false;
            }
        });

        view.findViewById(R.id.sign_in_frame).setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SoftKeyBoardHelper.hideSoftKeyboard(requireActivity());
                return true;
            }
        });

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
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            etUsername.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError(REQUIRE);
            return false;
        }
        if (!TextUtils.equals(username, "admin") || !TextUtils.equals(password, "admin")) {
            tvError.setText(INVALID);
            return false;
        }
        return true;
    }

    private void login() {
        if (!checkLogin()) return;

        Intent intent = new Intent(this.getActivity(), MainActivity.class);
        intent.putExtra("username",etUsername.getText().toString());
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

    @Override
    public void onResume() {
        super.onResume();
        Log.d("SIGN IN FRAGMENT", "RESUMED");
    }
}