package com.avinfo.currency.info.currencycal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class RatesTable extends Fragment {
    MyAdapter adapter;
    DownloadTask myTask = null;
    public  AdView adView;
//    public static AdView adView1;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.table_rates, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //        ADM BANNER ADS
        adView = new AdView(getActivity());
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(getString(R.string.admob_banner_id));

        adView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        RecyclerView rv = (RecyclerView) view.findViewById(R.id.myRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new MyAdapter(getContext());
        rv.setAdapter(this.adapter);
        this.myTask = new DownloadTask(getContext(), this.adapter);
        this.myTask.execute(new String[]{"https://openexchangerates.org/api/latest.json?app_id=3f751bad3cfb41c7b422c5c105c85742"});
    }

    public void onStop() {
        super.onStop();
        this.myTask.cancel(true);
    }


}
