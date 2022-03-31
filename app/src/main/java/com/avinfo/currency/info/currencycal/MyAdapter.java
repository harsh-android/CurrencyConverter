package com.avinfo.currency.info.currencycal;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;




public class MyAdapter extends Adapter<MyAdapter.ViewHolder> implements DownloadTask.Document {

    private  View adView;
    private Context context;
    private List<Country> myList = new ArrayList();
    public static AdView adView1;


    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        private ImageView myFlag;
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;


        public ViewHolder(View itemView) {
            super(itemView);
            this.myFlag = (ImageView) itemView.findViewById(R.id.img);
            this.tv1 = (TextView) itemView.findViewById(R.id.txt1);
            this.tv2 = (TextView) itemView.findViewById(R.id.txt2);
            this.tv3 = (TextView) itemView.findViewById(R.id.txt3);
//            adView = itemView.findViewById(R.id.adView);


        }

        public void bind(Country c) {

            this.myFlag.setImageResource(c.getFlag());
            this.tv1.setText(c.getCurrencyName());
            this.tv2.setText(c.getCountryName());
            this.tv3.setText(String.valueOf(c.getRate()));

        }
    }

    public MyAdapter(Context context) {
        this.context = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new ViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind((Country) this.myList.get(position));
    }

    public int getItemCount() {
        return this.myList.size();
    }

    public void setDocument(List<Double> list) {
        String[] currency_symbols = this.context.getResources().getStringArray(R.array.currency_symbols);
        String[] currency_names = this.context.getResources().getStringArray(R.array.currency_names);
        for (int i = 1; i < list.size(); i++) {
            this.myList.add(new Country(currency_names[i], currency_symbols[i], MyFlags.getFlags()[i], (Double) list.get(i)));
        }
        notifyDataSetChanged();
    }
}
