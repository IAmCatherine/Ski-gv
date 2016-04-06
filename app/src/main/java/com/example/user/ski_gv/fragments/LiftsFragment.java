package com.example.user.ski_gv.fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.ski_gv.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by User on 30.03.2016.
 */
public class LiftsFragment extends Fragment {
    private static final int LAYOUT = R.layout.lifts_activity;
    private View view;
    ImageView lift1;
    ImageView lift2;
    ImageView lift3;
    ImageView lift4;
    TextView textView;
    Boolean noconnect = false;
    ArrayList<String> lifts;
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT,null);

        lift1 = (ImageView) view.findViewById(R.id.lift1);
        lift2 = (ImageView) view.findViewById(R.id.lift2);
        lift3 = (ImageView) view.findViewById(R.id.lift3);
        lift4 = (ImageView) view.findViewById(R.id.lift4);
        textView = (TextView) view.findViewById(R.id.textView);
        GetInfo getInfo = new GetInfo();
        getInfo.execute();
        return view;
    }

    public  class GetInfo extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            Document doc = null;
            lifts = new ArrayList<>();

            try {
                doc = Jsoup.connect("http://ski-gv.ru").get();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            if (doc!=null) {
                String site = doc.body().html();
               // int i =0;
                int b = site.indexOf("data-class=\"lifts\">");
                int i = site.indexOf("lift-span",b);
               while (b >= 0 && i>=0) {
                   String s = site.substring(b,i);
                   lifts.add(s);
                   b = site.indexOf("data-class=\"lifts\">", i);
                   i = site.indexOf("lift-span",b);
               }
            }
            else {noconnect=true;}
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (!noconnect){
            if (lifts.get(0).contains("close"))
                lift1.setImageResource(R.drawable.iconlifts1closed);
            else lift1.setImageResource(R.drawable.iconlifts1);
            if (lifts.get(1).contains("close"))
                lift2.setImageResource(R.drawable.iconlifts1closed);
            else lift2.setImageResource(R.drawable.iconlifts1);
            if (lifts.get(2).contains("close"))
                lift3.setImageResource(R.drawable.icon_lifts2_closed);
            else lift3.setImageResource(R.drawable.iconlifts2);
            if (lifts.get(3).contains("close"))
                lift4.setImageResource(R.drawable.icon_lifts2_closed);
            else lift4.setImageResource(R.drawable.iconlifts2);
            }
        else textView.setVisibility(View.VISIBLE);}
    }
}
