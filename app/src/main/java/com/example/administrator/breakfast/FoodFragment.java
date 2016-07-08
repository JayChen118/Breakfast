package com.example.administrator.breakfast;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 *
 * Created by Jay on 2016/7/6.
 */
public class FoodFragment extends Fragment {

    private List<Food> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Jay", "onCreateView");

        return inflater.inflate(R.layout.list, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.d("Jay", "FoodFragment onCreate");

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Jay", "FoodFragment onActivityCreated");

        list = getArguments().getParcelableArrayList("list");
        View view = getView();

        if (view != null) {

            final ListView listView = (ListView) view.findViewById(R.id.list);

            final BaseAdapter adapter = new BaseAdapter() {
                @Override
                public int getCount() {
                    return list.size();
                }

                @Override
                public Food getItem(int position) {
                    return list.get(position);
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    if (convertView == null) {
                        convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_breakfast, parent, false);
                    }
                    Food food = getItem(position);
                    TextView nameView = (TextView) convertView.findViewById(R.id.name);
                    nameView.setText(food.getName());

                    TextView timesView = (TextView) convertView.findViewById(R.id.times);
                    timesView.setText(food.getTimesText());

                    TextView lastTimeView = (TextView) convertView.findViewById(R.id.last_time);
                    lastTimeView.setText(food.getLastTimeText());
                    return convertView;
                }
            };
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Food food = list.get(position);
                    list.remove(food);
                    food.addTimes();
                    food.setLastTime(TimeUtils.getTime());
                    list.add(list.size(), food);
                    adapter.notifyDataSetChanged();
                    PreferenceUtil.setBreakfastList(list);
                }
            });
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }
}
