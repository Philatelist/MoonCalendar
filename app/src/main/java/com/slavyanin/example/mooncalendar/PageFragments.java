package com.slavyanin.example.mooncalendar;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PageFragments extends Fragment {

    private int pageNumber;

    public static PageFragments newInstance(int page) {
        PageFragments fragment = new PageFragments();
        Bundle args=new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    public PageFragments() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_page, container, false);
        TextView pageHeader=(TextView)result.findViewById(R.id.displayText);
        pageHeader.setText(String.valueOf(pageNumber+1) + "-й Лунный день." );
        return result;
    }
}