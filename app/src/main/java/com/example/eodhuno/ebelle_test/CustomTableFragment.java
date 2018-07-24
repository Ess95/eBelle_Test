package com.example.eodhuno.ebelle_test;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class CustomTableFragment extends Fragment {
    GridLayout gridLayout;
    LinearLayout linearLayout;
    TextView titleTextView;
    String tableTitle;

    ArrayList<String> stringTitles = new ArrayList<String>();
    ArrayList<LinkedHashMap<String, String[]>> sectionsArraylist = new ArrayList<LinkedHashMap<String, String[]>>();
    ArrayList<String> allShrinkTexts = new ArrayList<String>();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CustomTableFragment() {
    }


    public static CustomTableFragment newInstance(String param1, String param2) {
        CustomTableFragment fragment = new CustomTableFragment();
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
        View view = inflater.inflate(R.layout.fragment_custom_table, container, false);
        gridLayout = view.findViewById(R.id.customTableGridLayout);
        linearLayout = view.findViewById(R.id.gridLinearLayout);
        titleTextView = view.findViewById(R.id.titleGridLayout);

        String[] stringTitlesArray = new String[stringTitles.size()];
        stringTitles.toArray(stringTitlesArray);

        addTitles(stringTitlesArray);
        addSections(allShrinkTexts,sectionsArraylist);
        titleTextView.setText(tableTitle);
        return view;
    }

    public void setTitle(String tableTitle){
        if(titleTextView != null){
            titleTextView.setText(tableTitle);
        }
        this.tableTitle = tableTitle;
    }

    public void setColumnHeaders(String... headers){
        stringTitles.clear();
        for(String header:headers){
            stringTitles.add(header);
        }
        if(gridLayout != null){
            addTitles(headers);
        }
    }

    public  void addData(ArrayList<String> shrinkTexts, ArrayList<LinkedHashMap<String, String[]>> sections){
        allShrinkTexts = shrinkTexts;
        sectionsArraylist = sections;
        if(gridLayout != null){
            addSections(shrinkTexts,sections);
        }
    }

    private void addTitles(String... titles){
        gridLayout.setColumnCount(titles.length);
        gridLayout.setRowCount(1);

        for(String title:titles){
            TextView currTextView = (TextView) getLayoutInflater().inflate(R.layout.big_text_view_layout,null);
            currTextView.setText(title);

            GridLayout.LayoutParams currentLayoutParams = new GridLayout.LayoutParams();
            currentLayoutParams.width = GridLayout.LayoutParams.WRAP_CONTENT;
            currentLayoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
            currentLayoutParams.leftMargin = 15;
            currentLayoutParams.rightMargin = 15;
            currentLayoutParams.topMargin = 15;
            currentLayoutParams.bottomMargin = 15;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                currentLayoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED,1f);
            }

            currTextView.setLayoutParams(currentLayoutParams);
            gridLayout.addView(currTextView);
        }
    }

    private void addSections(final ArrayList<String> shrinkTexts, ArrayList<LinkedHashMap<String, String[]>> sections){
        int index = 0;
        for(LinkedHashMap<String, String[]> section:sections){
            final GridLayout currGridSection = new GridLayout(this.getContext());
            LinearLayout.LayoutParams currLinearGridSectionParam =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            currLinearGridSectionParam.bottomMargin = 15;
            currLinearGridSectionParam.topMargin = 15;
            currLinearGridSectionParam.leftMargin = 15;
            currLinearGridSectionParam.rightMargin = 15;
            currGridSection.setLayoutParams(currLinearGridSectionParam);
            currGridSection.setColumnCount(stringTitles.size());
            currGridSection.setRowCount(section.size());

            currGridSection.setBackground(getResources().getDrawable(R.drawable.rounded_button_border));

            final View currShrinkView = getLayoutInflater().inflate(R.layout.shrink_button,null);
            final ImageButton shrinkButton = currShrinkView.findViewById(R.id.shrinkButton);
            final TextView shrinkTextView = currShrinkView.findViewById(R.id.shrinkText);

            LinearLayout.LayoutParams currShrinkLinearGridSectionParam =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT);
            currShrinkLinearGridSectionParam.bottomMargin = 15;
            currShrinkLinearGridSectionParam.topMargin = 40;
            currShrinkLinearGridSectionParam.leftMargin = 15;
            currShrinkLinearGridSectionParam.rightMargin = 15;
            currShrinkView.setLayoutParams(currShrinkLinearGridSectionParam);

            final int currIndex = index;
            shrinkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(currGridSection.getVisibility() == View.VISIBLE){
                        currGridSection.setVisibility(View.GONE);
                        shrinkTextView.setVisibility(View.VISIBLE);
                        shrinkButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_up));
                        shrinkTextView.setText(shrinkTexts.get(currIndex));
                        currShrinkView.setBackground(getResources().getDrawable(R.drawable.rounded_button_border));
                    }else{
                        currGridSection.setVisibility(View.VISIBLE);
                        shrinkTextView.setVisibility(View.GONE);
                        currShrinkView.setBackgroundResource(0);
                        shrinkButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_down));

                    }
                }
            });

            linearLayout.addView(currShrinkView);
            linearLayout.addView(currGridSection);

            for(String key:section.keySet()){
                String[] values = section.get(key);

                TextView leftView = (TextView) getLayoutInflater().inflate(R.layout.small_text_view_layout,null);
                leftView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                leftView.setText(key);

                GridLayout.LayoutParams leftLayoutParams = new GridLayout.LayoutParams();
                leftLayoutParams.width = GridLayout.LayoutParams.WRAP_CONTENT;
                leftLayoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
                leftLayoutParams.leftMargin = 15;
                leftLayoutParams.rightMargin = 15;
                leftLayoutParams.topMargin = 15;
                leftLayoutParams.bottomMargin = 15;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    leftLayoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED,GridLayout.FILL, 1f);
                }
                leftView.setLayoutParams(leftLayoutParams);
                currGridSection.addView(leftView);
                for(String value:values){
                    TextView currTextView = (TextView) getLayoutInflater().inflate(R.layout.small_text_view_layout,null);
                    leftView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                    currTextView.setText(value);

                    GridLayout.LayoutParams currentLayoutParams = new GridLayout.LayoutParams();
                    currentLayoutParams.width = GridLayout.LayoutParams.WRAP_CONTENT;
                    currentLayoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
                    currentLayoutParams.leftMargin = 15;
                    currentLayoutParams.rightMargin = 15;
                    currentLayoutParams.topMargin = 15;
                    currentLayoutParams.bottomMargin = 15;

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        currentLayoutParams.columnSpec = GridLayout.spec(GridLayout.UNDEFINED,GridLayout.FILL, 1f);
                    }
                    currTextView.setLayoutParams(currentLayoutParams);

                    currGridSection.addView(currTextView);
                }
            }
            ++index;
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
