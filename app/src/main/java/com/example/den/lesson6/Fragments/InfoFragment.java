package com.example.den.lesson6.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.den.lesson6.Interfaces.PhotoItem;
import com.example.den.lesson6.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    public interface InfoFragmentListener {
        void onInfoClosePress();
    }

    public PhotoItem photoItem;
    private InfoFragment.InfoFragmentListener listener;


    @BindView(R.id.textViewAuthor) TextView textViewAuthor;
    @BindView(R.id.buttonInfoClose) Button buttonInfoClose;

    public InfoFragment() {}


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InfoFragment.InfoFragmentListener) {
            listener = (InfoFragment.InfoFragmentListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement InfoFragmentListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);
        textViewAuthor.setText(photoItem.getAuthorName());
        buttonInfoClose.setOnClickListener(button -> {
            listener.onInfoClosePress();
        });
        return view;
    }

}
