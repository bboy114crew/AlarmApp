package com.thangnv.fu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListView;

import com.thangnv.fu.listener.OnClickItemListViewListener;
import com.thangnv.fu.model.Alarms;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class AlarmFragment extends Fragment {

    private static final String TAG = "AlarmFragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CustomListAdapter adapter;
    private List<Long> listAlarms;

    private List<Alarms> mListAlarmses;

    public AlarmFragment() {
    }

    public static AlarmFragment newInstance(String param1, String param2) {
        AlarmFragment fragment = new AlarmFragment();
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
        // Inflate the layout for this fragment
        mListAlarmses = getListData();
        adapter = new CustomListAdapter(getActivity(), mListAlarmses, new OnClickItemListViewListener() {
            @Override
            public void OnClickItem(View mView, int position) {
                AlarmDialog alarmDialog = new AlarmDialog(getActivity(), mListAlarmses.get(position).getTimeAlarm(),position);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(alarmDialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                alarmDialog.show();
                alarmDialog.getWindow().setAttributes(lp);
            }
        });
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listAlarm);
        listView.setAdapter(adapter);
        return view;
    }


    private List<Alarms> getListData() {
        List<Alarms> alarmsList = new ArrayList<>();
        Alarms a1 = new Alarms("8:45", true);
        Alarms a2 = new Alarms("12:40", false);
        Alarms a3 = new Alarms("6:00", false);
        alarmsList.add(a1);
        alarmsList.add(a2);
        alarmsList.add(a3);
        return alarmsList;
    }

    private List<Long> getListAlarms() {
        List<Alarms> alarms = getListData();
        long timeCount;
        for (Alarms alarm :
                alarms) {
            timeCount = convertTime(alarm.getTimeAlarm());
            if (alarm.isStateAlarm() == true) {
                listAlarms.add(timeCount);
            }
        }
        return listAlarms;
    }

    public long convertTime(String time) {
        Calendar cal = Calendar.getInstance();
        String[] parts = time.split(":");
        int newHour = Integer.parseInt(parts[0]);
        int newMinutes = Integer.parseInt(parts[1]);
        cal.set(Calendar.HOUR, newHour);
        cal.set(Calendar.MINUTE, newMinutes);
        long difference = cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
        return difference;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }
}