package com.example.newlistapplication.ui.main;

import android.database.Cursor;
import android.widget.ListView;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.loader.content.CursorLoader;
import com.example.newlistapplication.MyObject;
import com.example.newlistapplication.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private View view;
    private ListView list;
    private ArrayList<MyObject> data;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if(view==null)
        {
            view=inflater.inflate(R.layout.fragment_main, container,false);
        }
        else
        {
            ViewGroup parent = (ViewGroup) view.getParent();
            parent.removeView(view);
        }

        data=getData();
        CustomListAdapter adapter=new CustomListAdapter(getActivity(),R.layout.list_item_swiped,data);
        list =(ListView)view.findViewById(R.id.listItems);
        list.setAdapter(adapter);
        return view;

        //return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public ArrayList<MyObject> getData()
    {
        ArrayList<MyObject> listdata =new ArrayList<MyObject>();

        listdata.add(new MyObject("Angelina A", "Angelina's Description", "angelina@example.com", "+357 99999999"));
        listdata.add(new MyObject("Angeliki A", "Angeliki's Description", "angeliki@example.com", "+357 99999999"));
        listdata.add(new MyObject("Angel A", "Angel's Description", "angel@example.com", "+357 99999999"));
        listdata.add(new MyObject("Angy A", "Angy's Description", "angely@example.com", "+357 99999999"));
        listdata.add(new MyObject("Angelica A", "Angelica's Description", "angelica@example.com", "+357 99999999"));

        return listdata;
    }

}