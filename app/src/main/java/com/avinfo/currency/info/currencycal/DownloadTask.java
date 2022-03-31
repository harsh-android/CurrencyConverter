package com.avinfo.currency.info.currencycal;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class DownloadTask extends AsyncTask<String, Void, List<Double>> {
    public static List<Double> list;
    private Document _document;
    private Context context;
    private ProgressDialog dialog;
    private List<Double> myList;

    interface Document {
        void setDocument(List<Double> list);
    }

    public DownloadTask(Context context, Document document) {
        this._document = document;
        this.context = context;
        list = new ArrayList();
    }

    protected void onPreExecute() {
        super.onPreExecute();
        this.dialog = new ProgressDialog(this.context);
        this.dialog.setMessage("Please Wait...");
        this.dialog.show();
    }

    protected List<Double> doInBackground(String... params) {
        String rates = request(params[0]);
        this.myList = new ArrayList();
        getRatesFromJson(this.myList, rates);
        return this.myList;
    }

    protected void onPostExecute(List<Double> doubles) {
        super.onPostExecute(doubles);
        list.addAll(doubles);
        if (list.size() != 0) {
            this._document.setDocument(doubles);
            this.dialog.dismiss();
            return;
        }
        this.dialog.dismiss();
        Toast toast = Toast.makeText(this.context, "Please check your internet connection", Toast.LENGTH_SHORT);
        toast.setGravity(17, 0, 0);
        toast.show();
    }

    private String request(String urlString) {
        StringBuilder str = new StringBuilder("");
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
            InputStream stream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String str2 = "";
            while (true) {
                str2 = br.readLine();
                if (str2 == null) {
                    break;
                }
                str.append(str2 + "\n");
            }
            br.close();
            stream.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return str.toString().trim();
    }

    private void getRatesFromJson(List<Double> myList, String str) {
        try {
            JSONObject object2 = new JSONObject(str).getJSONObject("rates");
            String[] currency_symbols = this.context.getResources().getStringArray(R.array.currency_symbols);
            for (String str2 : currency_symbols) {
                double x;
                String currency = String.valueOf(object2.get(str2));
                if (currency.length() < 5) {
                    x = Double.valueOf(currency).doubleValue();
                } else {
                    x = Double.valueOf((currency + "0.00000").substring(0, 5)).doubleValue();
                }
                myList.add(Double.valueOf(x));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
