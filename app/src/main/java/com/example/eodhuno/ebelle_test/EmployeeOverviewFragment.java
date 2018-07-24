package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ebellecustomcard.CustomWidget;


public class EmployeeOverviewFragment extends Fragment {

    CustomWidget employeeLeaderboard,employeeToday;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EmployeeOverviewFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static EmployeeOverviewFragment newInstance(String param1, String param2) {
        EmployeeOverviewFragment fragment = new EmployeeOverviewFragment();
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

        View view = inflater.inflate(R.layout.fragment_employee_overview, container, false);

        employeeLeaderboard = view.findViewById(R.id.employeeLeaderboardOverview);
        employeeLeaderboard.removeShrinkButton();
        employeeLeaderboard.removeInfoButton();
        employeeLeaderboard.removeEditButton();
        employeeLeaderboard.removeDeleteButton();
        employeeLeaderboard.removeProfilePic();
        employeeLeaderboard.removeTitleDescription();
        employeeLeaderboard.removeTitle();
        employeeLeaderboard.setColumnNumber(3);

        employeeLeaderboard.addColumnSpans(new int[]{0,0,1,3},
                new int[]{1,0,1,1},
                new int[]{2,1,1,2},
                new int[]{3,0,1,3});

        employeeLeaderboard.addViews(1,
               2,0,
                1,0,
                9);

        employeeLeaderboard.setBigText(0,0,"Leaderboard");
        employeeLeaderboard.addAlignement(0,0,"left");
        employeeLeaderboard.setSmallText(1,1,"Emma Watson");
        employeeLeaderboard.addAlignement(1,1,"left");
        employeeLeaderboard.setBigText(2,0,"Services");
        employeeLeaderboard.addAlignement(2,1,"left");
        employeeLeaderboard.setSmallText(2,2,"Acryllic Nails");
        employeeLeaderboard.addAlignement(2,2,"left");

        employeeToday = (CustomWidget) view.findViewById(R.id.employeeTodayOverview);
        employeeToday.removeShrinkButton();
        employeeToday.removeInfoButton();
        employeeToday.removeEditButton();
        employeeToday.removeDeleteButton();
        employeeToday.removeProfilePic();
        employeeToday.removeTitleDescription();
        employeeToday.removeTitle();
        employeeToday.setColumnNumber(3);

        employeeToday.addColumnSpans(new int[]{0,0,1,3},
                new int[]{1,0,1,3},
                new int[]{2,0,1,3},
                new int[]{3,0,1,3},
                new int[]{4,1,1,2},
                new int[]{5,1,1,2});

        employeeToday.addViews(1,
                8,
                8,
                20,
                1,0,
                1,0);

        employeeToday.setBigText(0,0,"Today");
        employeeToday.addAlignement(0,0,"left");
        employeeToday.setVLValue(1,0,"7","Available Stylists");
        employeeToday.addAlignement(1,0,"centre");
        employeeToday.setVLValue(2,0,"20","Appointments");
        employeeToday.addAlignement(2,0,"centre");
        employeeToday.setBigText(4,0,"Receptionist");
        employeeToday.addAlignement(4,0,"left");
        employeeToday.setBigText(4,1,"Esther Kayy");
        employeeToday.addAlignement(4,1,"left");
        employeeToday.setBigText(5,0,"Accounts");
        employeeToday.addAlignement(5,0,"left");
        employeeToday.setBigText(5,1,"Joy Kendi");
        employeeToday.addAlignement(5,1,"left");

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
