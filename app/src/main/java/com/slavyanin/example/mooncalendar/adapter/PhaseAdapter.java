package com.slavyanin.example.mooncalendar.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.slavyanin.example.mooncalendar.R;
import com.slavyanin.example.mooncalendar.activity.MoonPhasesDescriptionActivity;
import com.slavyanin.example.mooncalendar.activity.MoonPhasesViewActivity;
import com.slavyanin.example.mooncalendar.entity.MoonPhase;

import java.util.List;


public class PhaseAdapter extends ArrayAdapter<MoonPhase> {

    private int[] phase = {R.string.newMoon, R.string.theFirstPhase, R.string.FirstQuarter, R.string.theSecondPhase,
            R.string.fullMoon, R.string.theThirdPhase, R.string.thirdQuarter, R.string.theFourthPhase};

    private String[] phase_descr = {"1", "2", "3", "4", "5", "6", "7", "8"};

    private int[] phasePic = {R.drawable.phase01, R.drawable.phase02, R.drawable.phase03, R.drawable.phase04,
            R.drawable.phase05, R.drawable.phase06, R.drawable.phase07, R.drawable.phase08};

    private final Context context;

    public PhaseAdapter(Context context, int resource, List<MoonPhase> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {

            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = mInflater.inflate(R.layout.list_phase, null);

            holder = new ViewHolder();
            holder.txtDesc = (TextView) convertView.findViewById(R.id.text_phase);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.name_phase);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon_phase);

            convertView.setTag(holder);

        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtDesc.setText(phase_descr[position]);
        holder.txtTitle.setText(phase[position]);
        holder.imageView.setImageResource(phasePic[position]);

//        View v = null;
//        assert v != null;
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, MoonPhasesDescriptionActivity.class);
//                context.startActivity(intent);
//            }
//        });


        return convertView;
    }



    private class ViewHolder{
        ImageView imageView;
        TextView txtTitle;
        TextView txtDesc;
    }
}
