package com.blackghost.nephilos.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView;

import com.blackghost.nephilos.Managers.RequestManager;
import com.blackghost.nephilos.R;


public class RequestFragment extends Fragment {

    public RequestFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request, container, false);

        RequestManager requestManager = new RequestManager();


        Spinner requestTypeSpinner = view.findViewById(R.id.request_type_spinner);
        LinearLayout getLayout = view.findViewById(R.id.get_layout);
        LinearLayout postLayout = view.findViewById(R.id.post_layout);

        EditText get_input_url = view.findViewById(R.id.get_input_url);
        TextView get_info = view.findViewById(R.id.get_responce_TextView);
        Button send_GET_btn = view.findViewById(R.id.send_GET_btn);

        requestTypeSpinner.setSelection(0);
        getLayout.setVisibility(View.VISIBLE);
        postLayout.setVisibility(View.GONE);

        requestTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRequest = parent.getItemAtPosition(position).toString();

                if (selectedRequest.equals("GET")) {
                    getLayout.setVisibility(View.VISIBLE);
                    postLayout.setVisibility(View.GONE);
                } else if (selectedRequest.equals("POST")) {
                    getLayout.setVisibility(View.GONE);
                    postLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        send_GET_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = String.valueOf(get_input_url.getText());
                requestManager.send_GET(url);
                //get_info.setText(requestManager.send_GET(url));
            }
        });

        return view;
    }
}