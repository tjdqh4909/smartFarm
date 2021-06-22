package com.smartFarm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;


public class SecondFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }


    private static String TAG = "phptest_MainActivity";

    private static final String TAG_JSON="temp";
    private static final String TAG_Timedata = "Timedata";
    private static final String TAG_Temperature = "Temperature";
    private static final String TAG_Humidity = "Humidity";
    private static final String TAG_soilDry_count = "soilDry_count";
    private static final String TAG_waterEmpty_count = "waterEmpty_count";

    private TextView mTextViewResult;
    ArrayList<HashMap<String, String>> mArrayList;
    ListView mlistView;
    String mJsonString;
    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second, container, false);

        mTextViewResult = (TextView)v.findViewById(R.id.textView_main_result);
        mlistView = (ListView) v.findViewById(R.id.listView_main_list);
        mArrayList = new ArrayList<>();
        btn = (Button)v.findViewById(R.id.btn);

        GetData task = new GetData();
        task.execute("http://192.168.219.103/getjson.php");

        btn.setOnClickListener(this);

        //Toast.makeText(this.getContext(), "메세지!!", Toast.LENGTH_SHORT).show();

        return v;
    }

    public void onClick(View view) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();

    }

    private class GetData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(getActivity(),
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "response  - " + result);

            if (result == null){

                mTextViewResult.setText(errorString);
            }
            else {
                mJsonString = result;
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }


    private void showResult(){

        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);

                String Timedata = item.getString(TAG_Timedata);
                String Temperature = item.getString(TAG_Temperature);
                String Humidity = item.getString(TAG_Humidity);
                String soilDry_count = item.getString(TAG_soilDry_count);
                String waterEmpty_count = item.getString(TAG_waterEmpty_count);

                HashMap<String,String> hashMap = new HashMap<>();

                hashMap.put(TAG_Timedata, Timedata);
                hashMap.put(TAG_Temperature, Temperature);
                hashMap.put(TAG_Humidity, Humidity);
                hashMap.put(TAG_soilDry_count, soilDry_count);
                hashMap.put(TAG_waterEmpty_count, waterEmpty_count);

                mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    getActivity(), mArrayList, R.layout.item_list,
                    new String[]{TAG_Timedata, TAG_Temperature, TAG_Humidity, TAG_soilDry_count, TAG_waterEmpty_count},
                    new int[]{R.id.Timedata, R.id.Temperature, R.id.Humidity, R.id.soilDry_count, R.id.waterEmpty_count}
            );

            mlistView.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG, "showResult : ", e);
        }





    }



}
