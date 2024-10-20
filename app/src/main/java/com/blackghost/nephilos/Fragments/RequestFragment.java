package com.blackghost.nephilos.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView;

import com.blackghost.nephilos.Interfaces.RequestInterface;
import com.blackghost.nephilos.Managers.RequestManager;
import com.blackghost.nephilos.R;


public class RequestFragment extends Fragment implements RequestInterface {

    private TextView get_info;
    private TextView post_info;
    private TextView put_info;
    private TextView delete_info;
    private TextView patch_info;
    private TextView head_info;
    private TextView options_info;
    private TextView trace_info;
    private TextView connect_info;

    private ProgressBar progressBar;

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

        RequestManager requestManager = new RequestManager(this);

        progressBar = view.findViewById(R.id.progress_bar);

        Spinner requestTypeSpinner = view.findViewById(R.id.request_type_spinner);
        Spinner postContentTypeSpinner = view.findViewById(R.id.POST_content_type);

        LinearLayout getLayout = view.findViewById(R.id.GET_layout);
        LinearLayout postLayout = view.findViewById(R.id.POST_layout);
        LinearLayout putLayout = view.findViewById(R.id.PUT_layout);
        LinearLayout deleteLayout = view.findViewById(R.id.DELETE_layout);
        LinearLayout patchLayout = view.findViewById(R.id.PATCH_layout);
        LinearLayout headLayout = view.findViewById(R.id.HEAD_layout);
        LinearLayout optionsLayout = view.findViewById(R.id.OPTIONS_layout);
        LinearLayout traceLayout = view.findViewById(R.id.TRACE_layout);
        LinearLayout connectLayout = view.findViewById(R.id.CONNECT_layout);


        EditText get_input_url = view.findViewById(R.id.get_input_url);

        get_info = view.findViewById(R.id.get_responce_TextView);
        post_info = view.findViewById(R.id.post_responce_TextView);
        put_info = view.findViewById(R.id.put_responce_TextView);
        delete_info = view.findViewById(R.id.delete_responce_TextView);
        patch_info = view.findViewById(R.id.patch_responce_TextView);
        head_info = view.findViewById(R.id.head_responce_TextView);
        options_info = view.findViewById(R.id.options_responce_TextView);
        trace_info = view.findViewById(R.id.trace_responce_TextView);
        connect_info = view.findViewById(R.id.connect_responce_TextView);

        Button send_GET_btn = view.findViewById(R.id.send_GET_btn);

        requestTypeSpinner.setSelection(0);
        getLayout.setVisibility(View.VISIBLE);
        postLayout.setVisibility(View.GONE);

        Log.d("Content type", postContentTypeSpinner.getSelectedItem().toString());

        requestTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRequest = parent.getItemAtPosition(position).toString();

                if (selectedRequest.equals("GET")) {
                    getLayout.setVisibility(View.VISIBLE);
                    postLayout.setVisibility(View.GONE);
                    putLayout.setVisibility(View.GONE);
                    patchLayout.setVisibility(View.GONE);
                    deleteLayout.setVisibility(View.GONE);
                    headLayout.setVisibility(View.GONE);
                    optionsLayout.setVisibility(View.GONE);
                    traceLayout.setVisibility(View.GONE);
                    connectLayout.setVisibility(View.GONE);
                } else if (selectedRequest.equals("POST")) {
                    getLayout.setVisibility(View.GONE);
                    postLayout.setVisibility(View.VISIBLE);
                    putLayout.setVisibility(View.GONE);
                    patchLayout.setVisibility(View.GONE);
                    deleteLayout.setVisibility(View.GONE);
                    headLayout.setVisibility(View.GONE);
                    optionsLayout.setVisibility(View.GONE);
                    traceLayout.setVisibility(View.GONE);
                    connectLayout.setVisibility(View.GONE);
                } else if (selectedRequest.equals("PUT")){
                    getLayout.setVisibility(View.GONE);
                    postLayout.setVisibility(View.GONE);
                    putLayout.setVisibility(View.VISIBLE);
                    patchLayout.setVisibility(View.GONE);
                    deleteLayout.setVisibility(View.GONE);
                    headLayout.setVisibility(View.GONE);
                    optionsLayout.setVisibility(View.GONE);
                    traceLayout.setVisibility(View.GONE);
                    connectLayout.setVisibility(View.GONE);
                } else if (selectedRequest.equals("PATCH")){
                    getLayout.setVisibility(View.GONE);
                    postLayout.setVisibility(View.GONE);
                    putLayout.setVisibility(View.GONE);
                    patchLayout.setVisibility(View.VISIBLE);
                    deleteLayout.setVisibility(View.GONE);
                    headLayout.setVisibility(View.GONE);
                    optionsLayout.setVisibility(View.GONE);
                    traceLayout.setVisibility(View.GONE);
                    connectLayout.setVisibility(View.GONE);
                } else if (selectedRequest.equals("DELETE")){
                    getLayout.setVisibility(View.GONE);
                    postLayout.setVisibility(View.GONE);
                    putLayout.setVisibility(View.GONE);
                    patchLayout.setVisibility(View.GONE);
                    deleteLayout.setVisibility(View.VISIBLE);
                    headLayout.setVisibility(View.GONE);
                    optionsLayout.setVisibility(View.GONE);
                    traceLayout.setVisibility(View.GONE);
                    connectLayout.setVisibility(View.GONE);
                } else if (selectedRequest.equals("HEAD")){
                    getLayout.setVisibility(View.GONE);
                    postLayout.setVisibility(View.GONE);
                    putLayout.setVisibility(View.GONE);
                    patchLayout.setVisibility(View.GONE);
                    deleteLayout.setVisibility(View.GONE);
                    headLayout.setVisibility(View.VISIBLE);
                    optionsLayout.setVisibility(View.GONE);
                    traceLayout.setVisibility(View.GONE);
                    connectLayout.setVisibility(View.GONE);
                } else if (selectedRequest.equals("OPTIONS")){
                    getLayout.setVisibility(View.GONE);
                    postLayout.setVisibility(View.GONE);
                    putLayout.setVisibility(View.GONE);
                    patchLayout.setVisibility(View.GONE);
                    deleteLayout.setVisibility(View.GONE);
                    headLayout.setVisibility(View.GONE);
                    optionsLayout.setVisibility(View.VISIBLE);
                    traceLayout.setVisibility(View.GONE);
                    connectLayout.setVisibility(View.GONE);
                } else if (selectedRequest.equals("TRACE")){
                    getLayout.setVisibility(View.GONE);
                    postLayout.setVisibility(View.GONE);
                    putLayout.setVisibility(View.GONE);
                    patchLayout.setVisibility(View.GONE);
                    deleteLayout.setVisibility(View.GONE);
                    headLayout.setVisibility(View.GONE);
                    optionsLayout.setVisibility(View.GONE);
                    traceLayout.setVisibility(View.VISIBLE);
                    connectLayout.setVisibility(View.GONE);
                } else if (selectedRequest.equals("CONNECT")){
                    getLayout.setVisibility(View.GONE);
                    postLayout.setVisibility(View.GONE);
                    putLayout.setVisibility(View.GONE);
                    patchLayout.setVisibility(View.GONE);
                    deleteLayout.setVisibility(View.GONE);
                    headLayout.setVisibility(View.GONE);
                    optionsLayout.setVisibility(View.GONE);
                    traceLayout.setVisibility(View.GONE);
                    connectLayout.setVisibility(View.VISIBLE);
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
                get_info.setText("");
                progressBar.setVisibility(View.VISIBLE);
                requestManager.send_GET(url);
            }
        });

        return view;
    }

    @Override
    public void GET_request(String response) {
        progressBar.setVisibility(View.GONE);
        get_info.setText(response);
    }

    @Override
    public void POST_request(String response) {
        post_info.setText(response);
    }

    @Override
    public void PUT_request(String response) {
        put_info.setText(response);
    }

    @Override
    public void DELETE_request(String response) {
        delete_info.setText(response);
    }

    @Override
    public void PATCH_request(String response) {
        patch_info.setText(response);
    }

    @Override
    public void HEAD_request(String response) {
        head_info.setText(response);
    }

    @Override
    public void OPTIONS_request(String response) {
        options_info.setText(response);
    }

    @Override
    public void TRACE_request(String response) {
        trace_info.setText(response);
    }

    @Override
    public void CONNECT_request(String response) {
        connect_info.setText(response);
    }
}