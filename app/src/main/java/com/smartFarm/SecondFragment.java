package com.smartFarm;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SecondFragment extends Fragment {

    //--라인차트 선언
    private LineChart lineChart1;
    private LineChart lineChart2;

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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExploreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
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
        View v = inflater.inflate(R.layout.fragment_second, container, false);

        lineChart1 = (LineChart) v.findViewById(R.id.chart_tem);
        lineChart2 = (LineChart) v.findViewById(R.id.chart_hum);

        // 데이터 설정
        List<Entry> entries1 = new ArrayList<>();
        entries1.add(new Entry(1, 17));
        entries1.add(new Entry(2, 18));
        entries1.add(new Entry(3, 20));
        entries1.add(new Entry(4, 19));
        entries1.add(new Entry(5, 18));

        List<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(1, 50));
        entries2.add(new Entry(2, 45));
        entries2.add(new Entry(3, 70));
        entries2.add(new Entry(4, 60));
        entries2.add(new Entry(5, 65));

        /*
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("6/22");
        labels.add("6/23");
        labels.add("6/24");
        labels.add("6/25");
        labels.add("6/26");
        */

        // 온도 그래프 세팅
        LineDataSet lineDataSet1 = new LineDataSet(entries1, "온도(°C)");
        lineDataSet1.setLineWidth(2);
        lineDataSet1.setCircleRadius(6);
        lineDataSet1.setCircleColor(Color.parseColor("#FF0000"));
        //lineDataSet.setCircleColorHole(Color.BLUE);
        lineDataSet1.setColor(Color.parseColor("#FF0000"));
        lineDataSet1.setDrawCircleHole(true);
        lineDataSet1.setDrawCircles(true);
        lineDataSet1.setDrawHorizontalHighlightIndicator(false);
        lineDataSet1.setDrawHighlightIndicators(false);
        lineDataSet1.setDrawValues(false);



        // 습도 그래프 세팅
        LineDataSet lineDataSet2 = new LineDataSet(entries2, "습도(%)");
        lineDataSet2.setLineWidth(2);
        lineDataSet2.setCircleRadius(6);
        lineDataSet2.setCircleColor(Color.parseColor("#0000FF"));
        //lineDataSet.setCircleColorHole(Color.BLUE);
        lineDataSet2.setColor(Color.parseColor("#0000FF"));
        lineDataSet2.setDrawCircleHole(true);
        lineDataSet2.setDrawCircles(true);
        lineDataSet2.setDrawHorizontalHighlightIndicator(false);
        lineDataSet2.setDrawHighlightIndicators(false);
        lineDataSet2.setDrawValues(false);

        LineData lineData1 = new LineData(lineDataSet1);
        LineData lineData2 = new LineData(lineDataSet2);
        lineChart1.setData(lineData1);
        lineChart2.setData(lineData2);


        // x축 속성 설정
        XAxis xAxis1 = lineChart1.getXAxis();
        XAxis xAxis2 = lineChart2.getXAxis();


        xAxis1.setValueFormatter(new DateFormatter());
        xAxis1.setLabelCount(5, true);
        xAxis1.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis1.setTextColor(Color.BLACK);
        xAxis1.enableGridDashedLine(8, 24, 0);


        xAxis2.setValueFormatter(new DateFormatter());
        xAxis2.setLabelCount(5, true);
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis2.setTextColor(Color.BLACK);
        xAxis2.enableGridDashedLine(8, 24, 0);

        // y축 속성 설정
        YAxis yLAxis1 = lineChart1.getAxisLeft();
        YAxis yLAxis2 = lineChart2.getAxisLeft();

        yLAxis1.setAxisMaximum(30);
        yLAxis1.setAxisMinimum(10);
        yLAxis1.setTextColor(Color.BLACK);

        yLAxis2.setAxisMaximum(100);
        yLAxis2.setAxisMinimum(0);
        yLAxis2.setTextColor(Color.BLACK);


        YAxis yRAxis1 = lineChart1.getAxisRight();
        YAxis yRAxis2 = lineChart2.getAxisRight();

        yRAxis1.setDrawLabels(false);
        yRAxis1.setDrawAxisLine(false);
        yRAxis1.setDrawGridLines(false);

        yRAxis2.setDrawLabels(false);
        yRAxis2.setDrawAxisLine(false);
        yRAxis2.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");


        // 차트 그래프 효과 옵션
        lineChart1.setDoubleTapToZoomEnabled(false);
        lineChart1.setDrawGridBackground(false);
        lineChart1.setDescription(description);
        //lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
        lineChart1.invalidate();

        lineChart2.setDoubleTapToZoomEnabled(false);
        lineChart2.setDrawGridBackground(false);
        lineChart2.setDescription(description);
        //lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
        lineChart2.invalidate();

        return v;
    }

    class DateFormatter extends ValueFormatter implements IAxisValueFormatter {

        Calendar mCalender;
        Date mDate;

        {
            mCalender = Calendar.getInstance();
            mDate = new Date();
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            String strDate;

            mDate.setTime((long) value * (1000 * 60 * 60 * 24));
            mCalender.setTime(mDate);

            strDate = "" + mCalender.get(Calendar.YEAR) + "-" + (mCalender.get(Calendar.MONTH) + 1) + "-" + mCalender.get(Calendar.DATE);

            return strDate;
        }
    }


}

