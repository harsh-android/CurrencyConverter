package com.avinfo.currency.info.currencycal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CurrencyFragment extends Fragment {
    private EditText edit;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private int pos1;
    private int pos2;
    int posi1;
    int posi2;
    int tp1;
    int tp2;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private InterstitialAd mInterstitialAd;
    private Button convertButton;

    class C05221 implements OnItemSelectedListener {
        C05221() {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int position1, long id) {
            String str1 = getResources().getStringArray(R.array.currency_names)[position1];
            img1.setImageResource(MyFlags.getFlags()[position1]);
            tv1.setText(str1);
            pos1 = position1;
            posi1 = position1;
            tp1 = position1;
            if (tv3.getText() != null) {
                tv3.setText("");
            }
            if (edit.getText() != null) {
                edit.setText("");
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }
    
    class C05232 implements OnItemSelectedListener {
        C05232() {
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int position2, long id) {
            String str2 = getResources().getStringArray(R.array.currency_names)[position2];
            img2.setImageResource(MyFlags.getFlags()[position2]);
            tv2.setText(str2);
            pos2 = position2;
            posi2 = position2;
            tp2 = position2;
            if (tv3.getText() != null) {
                tv3.setText("");
            }
            if (edit.getText() != null) {
                edit.setText("");
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* renamed from: com.home210.office406.village4302.v1321.CurrencyFragment$3 */
    class C05243 implements OnClickListener {




        C05243() {


        }

        public void onClick(View v) {

            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            }
            if (DownloadTask.list.size() == 0) {
                Toast toast = Toast.makeText(getContext(), "Please check your internet connection", Toast.LENGTH_SHORT);
                toast.setGravity(17, 0, 0);
                toast.show();
                return;
            }
            String amountString = edit.getText().toString();
            if (amountString.equals("")) {
                Toast toast = Toast.makeText(getContext(), "Invalid input", Toast.LENGTH_SHORT);
                toast.setGravity(17, 0, 0);
                toast.show();
                return;
            }
            tv3.setText(String.valueOf(BigDecimal.valueOf(Double.valueOf(new DecimalFormat("0.00").format(Double.valueOf((Double.valueOf(amountString).doubleValue() / DownloadTask.list.get(pos1).doubleValue()) * DownloadTask.list.get(pos2).doubleValue()))).doubleValue())));
        }
    }

    class C05255 implements OnClickListener {
        C05255() {
        }

        public void onClick(View v) {
            String[] currency_names = getResources().getStringArray(R.array.currency_names);
            int position1 = posi2;
            String str1 = currency_names[position1];
            img1.setImageResource(MyFlags.getFlags()[position1]);
            tv1.setText(str1);
            pos1 = position1;
            if (posi2 == tp2) {
                posi2 = tp1;
            } else {
                posi2 = tp2;
            }
            int position2 = posi1;
            String str2 = currency_names[position2];
            img2.setImageResource(MyFlags.getFlags()[position2]);
            tv2.setText(str2);
            pos2 = position2;
            if (posi1 == tp1) {
                posi1 = tp2;
            } else {
                posi1 = tp1;
            }
            if (DownloadTask.list.size() == 0) {
                Toast toast = Toast.makeText(getContext(), "Please check your internet connection", Toast.LENGTH_SHORT);
                toast.setGravity(17, 0, 0);
                toast.show();
                return;
            }
            String amountString = edit.getText().toString();
            if (amountString.equals("")) {
                Toast toast = Toast.makeText(getContext(), "Invalid input", Toast.LENGTH_SHORT);
                toast.setGravity(17, 0, 0);
                toast.show();
                return;
            }
            tv3.setText(String.valueOf(BigDecimal.valueOf(Double.valueOf(new DecimalFormat("0.00").format(Double.valueOf((Double.valueOf(amountString).doubleValue() / DownloadTask.list.get(pos1).doubleValue()) * DownloadTask.list.get(pos2).doubleValue()))).doubleValue())));
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.currency_fragment, container, false);
    }

    @SuppressLint("ResourceType")
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        ADM BANNER ADS
        AdView adView = new AdView(getActivity());
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(getString(R.string.admob_banner_id));

        adView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


//        ADM INTRT ADS
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.admob_interstitile_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                new C05243();

                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });

        Spinner sp1 = view.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.currency_symbols, 17367048);
        adapter1.setDropDownViewResource(17367049);
        sp1.setAdapter(adapter1);
        Spinner sp2 = view.findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.currency_symbols, 17367048);
        adapter2.setDropDownViewResource(17367049);
        sp2.setAdapter(adapter2);
        this.img1 = view.findViewById(R.id.c_f_flag);
        this.tv1 = view.findViewById(R.id.c_f_text);
        sp1.setOnItemSelectedListener(new C05221());
        this.img2 = view.findViewById(R.id.c_f_flag2);
        this.tv2 = view.findViewById(R.id.c_f_text2);
        sp2.setOnItemSelectedListener(new C05232());
        this.edit = view.findViewById(R.id.edit);
        this.tv3 = view.findViewById(R.id.txt_result);
        view.findViewById(R.id.convertButton).setOnClickListener(

                new C05243()
        );
        convertButton=(Button)view.findViewById(R.id.convertButton);

        this.img3 = view.findViewById(R.id.c_f_symbol);
        this.img3.setOnClickListener(new C05255());

//        convertButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new C05243();
//                if (mInterstitialAd.isLoaded()) {
//                    mInterstitialAd.show();
//                }
//
//            }
//        });
    }
}
