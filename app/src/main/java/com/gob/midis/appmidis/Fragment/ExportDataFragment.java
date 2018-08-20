package com.gob.midis.appmidis.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gob.midis.appmidis.R;


public class ExportDataFragment extends Fragment {



    public ExportDataFragment() {
        // Required empty public constructor
    }





    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {

        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " should implement DataListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_export_data, container, false);
    }




}
