package com.smartFarm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Date;

public class FourthFragment extends Fragment {

    //게시판 관련 선언
    ListView listView;
    MyListAdapter myListAdapter;
    ArrayList<list_item> list_itemArrayList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FourthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExploreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FourthFragment newInstance(String param1, String param2) {
        FourthFragment fragment = new FourthFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    //프래그먼트가 호출될때
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    //호출완료된 프래그먼트가 레이아웃을 호출할때
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fourth, container, false);

        listView = (ListView) v.findViewById(R.id.my_listView);

        list_itemArrayList = new ArrayList<list_item>();


        list_itemArrayList.add(
                new list_item("https://mblogthumb-phinf.pstatic.net/MjAxODA0MDVfMjkx/MDAxNTIyODkzMjgyNjE1.Tmod6kvz5nXsfJ1ZvsJ5MA9jPXW1quxiExIkkN8p2gQg.l48iSIIJJHrvGZj7UCYML6a5H40W_q2rPY7-nmRKx0kg.JPEG.missj5790/2018-04-05-10-50-52.jpg?type=w800","지원","화분에 싹이 났어요",new Date(System.currentTimeMillis())," "));
        list_itemArrayList.add(
                new list_item("https://pbs.twimg.com/media/ECt7cCDUEAEVoIb.jpg","지훈","저희 집 아보카도 입니다~",new Date(System.currentTimeMillis())," "));
        list_itemArrayList.add(
                new list_item("https://t1.daumcdn.net/cfile/tistory/99CC8B455EC5E14E31","유림","밤 싹틔우기까지!",new Date(System.currentTimeMillis())," "));
        list_itemArrayList.add(
                new list_item("https://lh3.googleusercontent.com/proxy/7C7Vr4rk8J5p91OBTlcaJxoS1NdYTQHr6u-sX9_dZiM8k8zgiki6PAZTALW0D6sB2Ih3hAqvSEnvNqeLAms-8Id3twqx3ICLhknyBvo2sHjkGA48ISk2dGWdkIScwrs7rky9wDSYvwORNQDTXIuQ-hKIZgRSK1Xw10eL2w","성보","이거 시들고 있는건가요?ㅠㅠ",new Date(System.currentTimeMillis())," "));
        list_itemArrayList.add(
                new list_item("https://blog.kakaocdn.net/dn/G1k5A/btqEFDSRDhH/b46cmPuLkrKE7LG9BLwWxK/img.jpg","익주","방울토마토 키우기 2편",new Date(System.currentTimeMillis())," "));

        myListAdapter = new MyListAdapter(getContext(),list_itemArrayList);
        listView.setAdapter(myListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext() ,list_itemArrayList.get(position).getNickname(),Toast.LENGTH_LONG).show();
            }
        });

        return v;

    }
}