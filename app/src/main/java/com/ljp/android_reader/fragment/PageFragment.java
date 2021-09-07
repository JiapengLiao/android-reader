package com.ljp.android_reader.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ljp.android_reader.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PageFragment() {
        // Required empty public constructor
    }

    String strSection, strContent;
    public PageFragment(String strSection){
        this.strSection = strSection;
    }
    public PageFragment(String strSection, String strContent){
        this.strContent = strContent;
        this.strSection = strSection;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PageFragment newInstance(String param1, String param2) {
        PageFragment fragment = new PageFragment();
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

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        this.view = view;

        TextView tvSection = view.findViewById(R.id.section);
        TextView tvContent = view.findViewById(R.id.content);
        tvSection.setText(tvSection.getText() + strSection);
//        Layout layout = tvSection.getLayout();

//        int count = 0;
////        for (int i = 0; i < 10000; i++) {
//            count = layout.getLineForVertical(10);
////        }
//        Log.e("onCreateView: ", Integer.toString(layout.getLineEnd(2)));

        tvContent.setText(strContent);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//        TextView tvContent = view.findViewById(R.id.content);
//        Layout layout = tvContent.getLayout();
//        Log.e("onCreateView: ", Integer.toString(layout.getLineEnd(2)));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        TextView tvContent = view.findViewById(R.id.content);
//        Layout layout = tvContent.getLayout();
//        Log.e("onCreateView: ", Integer.toString(layout.getLineEnd(2)));
    }
}