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
                new list_item("https://lh3.googleusercontent.com/proxy/jS7ZF2UoQONPCULQT5VIHMK382MfmP8OM1RkZpH8fEIPOFp7OBX-qKiCpQ75v5X_qkErJ0xitgqa_2XhdlOSTPF_Wa7GKdyIaK-A-w0j3g6omV7JVHI","닉네임1","제목1",new Date(System.currentTimeMillis()),"내용1"));
        list_itemArrayList.add(
                new list_item("https://lh3.googleusercontent.com/proxy/jS7ZF2UoQONPCULQT5VIHMK382MfmP8OM1RkZpH8fEIPOFp7OBX-qKiCpQ75v5X_qkErJ0xitgqa_2XhdlOSTPF_Wa7GKdyIaK-A-w0j3g6omV7JVHI","닉네임2","제목2",new Date(System.currentTimeMillis()),"내용2"));
        list_itemArrayList.add(
                new list_item("https://lh3.googleusercontent.com/proxy/jS7ZF2UoQONPCULQT5VIHMK382MfmP8OM1RkZpH8fEIPOFp7OBX-qKiCpQ75v5X_qkErJ0xitgqa_2XhdlOSTPF_Wa7GKdyIaK-A-w0j3g6omV7JVHI","닉네임3","제목3",new Date(System.currentTimeMillis()),"내용3"));
        list_itemArrayList.add(
                new list_item("https://lh3.googleusercontent.com/proxy/jS7ZF2UoQONPCULQT5VIHMK382MfmP8OM1RkZpH8fEIPOFp7OBX-qKiCpQ75v5X_qkErJ0xitgqa_2XhdlOSTPF_Wa7GKdyIaK-A-w0j3g6omV7JVHI","닉네임4","제목4",new Date(System.currentTimeMillis()),"내용4"));
        list_itemArrayList.add(
                new list_item("https://lh3.googleusercontent.com/proxy/jS7ZF2UoQONPCULQT5VIHMK382MfmP8OM1RkZpH8fEIPOFp7OBX-qKiCpQ75v5X_qkErJ0xitgqa_2XhdlOSTPF_Wa7GKdyIaK-A-w0j3g6omV7JVHI","닉네임5","제목5",new Date(System.currentTimeMillis()),"내용5"));

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