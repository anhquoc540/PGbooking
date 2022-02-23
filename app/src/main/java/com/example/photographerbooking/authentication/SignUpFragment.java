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

import androidx.fragment.app.Fragment;

import com.example.photographerbooking.MainActivity;
import com.example.photographerbooking.R;
import com.example.photographerbooking.helper.SoftKeyBoardHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SignUpFragment extends Fragment implements View.OnClickListener{

    private View view;
    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private FloatingActionButton btnSignUp;
    private final String REQUIRE = "Require";
    private final String NOT_MATCH = "Confirm password not match";

    public SignUpFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        etUsername = view.findViewById(R.id.etUsername);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        etConfirmPassword = view.findViewById(R.id.etConfirmPassword);
        btnSignUp = view.findViewById(R.id.btnSignUp);

        etConfirmPassword.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    SoftKeyBoardHelper.hideSoftKeyboard(requireActivity());
                    btnSignUp.requestFocus();
                    return true;
                }
                return false;
            }
        });

        view.findViewById(R.id.sign_up_frame).setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SoftKeyBoardHelper.hideSoftKeyboard(requireActivity());
                return true;
            }
        });

        btnSignUp.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private boolean checkSignUp() {
        if (TextUtils.isEmpty(etUsername.getText().toString())) {
            etUsername.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(etConfirmPassword.getText().toString())) {
            etConfirmPassword.setError(REQUIRE);
            return false;
        }
        if (!TextUtils.equals(etPassword.getText().toString(),
                etConfirmPassword.getText().toString())) {
            etConfirmPassword.setError(NOT_MATCH);
            return false;
        }
        return true;
    }

    private void signUp() {
        if (!checkSignUp()) return;

        Intent intent = new Intent(this.getActivity(), MainActivity.class);
        intent.putExtra("username",etUsername.getText().toString());
        startActivity(intent);
        this.getActivity().finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSignUp) signUp();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("SIGN UP FRAGMENT", "STOPPED");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("SIGN UP FRAGMENT", "DESTROYED");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("SIGN UP FRAGMENT", "RESUMED");
    }
}