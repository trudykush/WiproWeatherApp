package com.weathermap.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weathermap.R;

import java.util.List;

public class WeatherLayoutAdapter extends RecyclerView.Adapter<WeatherLayoutAdapter.MyViewHolder>  {

    private Context mContext;
    private List<String> mAllHeading;
    private List<List<String>> mAllValuesList;
    private List<String> mDateList;
    private LayoutInflater mInflater;

    public WeatherLayoutAdapter(Context context, List<String> allHeading, List<List<String>> allValues,
                                List<String> dateList) {
        mContext = context;
        mAllHeading = allHeading;
        mAllValuesList = allValues;
        mDateList = dateList;
    }

    @NonNull
    @Override
    public WeatherLayoutAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = mInflater.inflate(R.layout.weather_rv_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherLayoutAdapter.MyViewHolder holder, int position) {

        holder.dateHeading.setText(mAllHeading.get(0));
        holder.weatherHeading.setText(mAllHeading.get(1));
        holder.tempHeading.setText(mAllHeading.get(2));
        holder.humidityHeading.setText(mAllHeading.get(3));
        holder.windHeading.setText(mAllHeading.get(4));

        holder.dateValue.setText(mDateList.get(position));
        holder.weatherValue.setText(mAllValuesList.get(0).get(position));
        holder.tempValue.setText(mAllValuesList.get(1).get(position));
        holder.humidityValue.setText(mAllValuesList.get(2).get(position));
        holder.windValue.setText(mAllValuesList.get(3).get(position));

    }

    @Override
    public int getItemCount() {
        if (mAllHeading != null && mAllHeading.size() != 0) {
            return mAllHeading.size();
        }
        return mAllHeading != null ? mAllHeading.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView weatherHeading, weatherValue;
        TextView tempHeading, tempValue;
        TextView humidityHeading, humidityValue;
        TextView windHeading, windValue;
        TextView dateHeading, dateValue;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            weatherHeading = itemView.findViewById(R.id.weatherHeading);
            weatherValue = itemView.findViewById(R.id.weatherResult);

            tempHeading = itemView.findViewById(R.id.tempHeading);
            tempValue = itemView.findViewById(R.id.tempResult);

            humidityHeading = itemView.findViewById(R.id.humidityHeading);
            humidityValue = itemView.findViewById(R.id.humidityResult);

            windHeading = itemView.findViewById(R.id.windHeading);
            windValue = itemView.findViewById(R.id.windResult);

            dateHeading = itemView.findViewById(R.id.dateHeading);
            dateValue = itemView.findViewById(R.id.dateResult);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
