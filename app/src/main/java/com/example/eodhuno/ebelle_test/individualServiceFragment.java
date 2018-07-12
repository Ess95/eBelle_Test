package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ebellecustomcard.CustomWidget;


public class individualServiceFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    CustomWidget customWidget;

    private OnFragmentInteractionListener mListener;

    public individualServiceFragment() {
        // Required empty public constructor
    }


    public static individualServiceFragment newInstance(String param1, String param2) {
        individualServiceFragment fragment = new individualServiceFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.individual_service_fragment, container, false);
        customWidget = view.findViewById(R.id.individualServiceListCustomItem);
        customWidget.removeTitle();
        customWidget.removeProfilePic();
        customWidget.removeTitleDescription();
        customWidget.addColumnSpans(new int[]{0,0,1,3},
                new int[]{1,0,1,3},
                new int[]{2,0,1,3},
                new int[]{3,0,1,3},
                new int[]{4,0,1,3},
                new int[]{5,0,1,3},
                new int[]{6,0,1,3},
                new int[]{7,0,1,2});

        customWidget.addViews(1,
                2,
                1,
                0,
                5,
                1,
                0,
                0,13,
                13,13);

        customWidget.setBigText(0,0,"Braiding");
        customWidget.setBigText(2,0,"Description");
        customWidget.setSmallText(3,0,"grhgcdyj beyujag fjurbcygd hrtgdus hirbd"+
                "grhgcdyj beyujag fjurbcygd hrtgdus hirbd");
        customWidget.setCurrency(4,0,1500,"Price");
        customWidget.setBigText(5,0,"Products");
        customWidget.setSmallText(6,0,"Abuja");
        customWidget.setSmallText(7,0,"Add to cart");
        customWidget.addAlignement(7,0, "right");
        customWidget.setButtonText(7,2,"Cart");
        customWidget.setButtonText(8,0,"Back");
        customWidget.setButtonText(8,1,"OK");

        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
