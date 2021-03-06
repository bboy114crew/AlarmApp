package com.thangnv.fu.view.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.thangnv.fu.R;
import com.thangnv.fu.listener.OnClickOptionAlarmListner;
import com.thangnv.fu.model.AlarmInfo;
import com.thangnv.fu.model.RealmInteger;
import com.thangnv.fu.view.adapters.CustomListDayAdapter;

import java.util.Arrays;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by ll on 5/25/2017.
 */

public class DayDialog extends Dialog {
    private LinearLayout viewBackDay;
    private OnClickOptionAlarmListner mListener;
    private RealmList<RealmInteger> listRepeate;
    private AlarmInfo alarmInfo;
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);

    }

    public static final List<String> listDay = Arrays.asList("Every Monday", "Every Tuesday", "Every Wednesday", "Every Thursday", "Every Friday",
                                "Every Saturday", "Every Sunday");
    public DayDialog(@NonNull Context context, AlarmInfo alarmInfo) {
        super(context);
        listRepeate = new RealmList<>();
        this.alarmInfo = alarmInfo;
        if(context instanceof OnClickOptionAlarmListner) mListener = (OnClickOptionAlarmListner)context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_day_repeate);
        viewBackDay = (LinearLayout) findViewById(R.id.view_back);
        // initiate a ListView
        if(alarmInfo.getDayRepeate() == null)
            alarmInfo.setDayRepeate(new RealmList<RealmInteger>());
        ListView listView = (ListView) findViewById(R.id.listViewDay);
        CustomListDayAdapter customAdapter = new CustomListDayAdapter(getContext(), listDay,  alarmInfo.getDayRepeate() , mListener);

        listView.setAdapter(customAdapter);
        viewBackDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //alarmInfo.setDayRepeate(listRepeate);
                dismiss();

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }



}
