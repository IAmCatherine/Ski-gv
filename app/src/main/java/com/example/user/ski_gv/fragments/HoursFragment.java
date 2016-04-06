package com.example.user.ski_gv.fragments;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.ski_gv.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by User on 28.03.2016.
 */
public class HoursFragment extends Fragment {
    private static final int LAYOUT = R.layout.hours_activity;
    private View view;
    TextView text;


    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, null);
        text =(TextView)view.findViewById(R.id.textView);
        text =(TextView)view.findViewById(R.id.textView);
       Json json = new Json();
        json.execute();
        return view;
    }

    public class Json extends AsyncTask<Void,Void,Void>{
        String stringBuilder;
        @Override
        protected Void doInBackground(Void... params) {
            Document doc = null;
            Element element;
            try {
                doc = Jsoup.connect("http://ski-gv.ru").get();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            if (doc!=null){
                element = doc.select("div.description-schedule").first();
                stringBuilder=element.toString();
                stringBuilder=stringBuilder.replaceAll("&nbsp;"," ");
                stringBuilder = stringBuilder.replaceAll("[a-zA-Z=<>&\"/:;-]","");
            }
            else
            stringBuilder = "Не удалось подключиться к сети Интернет";
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            text.setText(stringBuilder.toUpperCase());

        }
    }
}
