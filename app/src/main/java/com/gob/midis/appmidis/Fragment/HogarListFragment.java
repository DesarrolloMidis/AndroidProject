package com.gob.midis.appmidis.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gob.midis.appmidis.R;

public class HogarListFragment extends Fragment {
    private FloatingActionButton floatingActionButton;

    public HogarListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hogar_list, container, false);


        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab_NuevoHogar);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

/*                FragmentManager fm = getFragmentManager();
                HogarNewFragment dialogFragment = new HogarNewFragment();
                dialogFragment.show(fm, "Registrar Nuevo Hogar");*/
                Fragment fragment = new HogarNewFragment();;
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .commit();

            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {

        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " should implement DataListener");
        }
    }

}
