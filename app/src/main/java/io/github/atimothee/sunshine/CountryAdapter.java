package io.github.atimothee.sunshine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Timo on 2/14/15.
 */
public class CountryAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<Country> countries;

    public CountryAdapter(Context mContext, ArrayList<Country> countries){
        this.mContext = mContext;
        this.countries = countries;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.list_item_forecast,null);
        //TextView nameTextView = (TextView)view.findViewById(R.id.list_item_forecast_textview);
        //nameTextView.setText(countries.get(position).name);
        return view;
    }
}
