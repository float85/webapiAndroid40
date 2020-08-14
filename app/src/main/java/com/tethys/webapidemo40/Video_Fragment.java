package com.tethys.webapidemo40;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.tethys.webapidemo40.databinding.VideoRagmentBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Video_Fragment extends Fragment {
    VideoRagmentBinding binding;
    String urlApi = "https://demo1913415.mockable.io/demoSinhvien";
    List<VideoProduct> videoProducts;


    public static Video_Fragment newInstance() {

        Bundle args = new Bundle();

        Video_Fragment fragment = new Video_Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.video_ragment, container, false);

        videoProducts =new ArrayList<>();

        new DoGetData().execute();

        return binding.getRoot();
    }

    class DoGetData extends AsyncTask<Void, Void, String> {
        String result="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.progressBar.setVisibility(View.VISIBLE);
        }

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(Void... voids) {

            try {
                URL url = new URL(urlApi);
                URLConnection connection = url.openConnection();
                InputStream is = connection.getInputStream();
                int byteCharacter;

                while ((byteCharacter = is.read()) != -1) {
                    result += (char) byteCharacter;
                }
                // có chuỗi json Array add vào list
                Log.d("", "doInBackground: " + result);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            getJsonArray(result);

            binding.tvName.setText(videoProducts.get(0).getTitle());

            binding.progressBar.setVisibility(View.GONE);
        }
    }

    private void getJsonArray(String jsonArray) {
        try {
            JSONArray jArray = new JSONArray(jsonArray);

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jsonObject = jArray.getJSONObject(i);

                int id = jsonObject.getInt("id");
                int publisher_id = jsonObject.getInt("publisher_id");
                int content_type = jsonObject.getInt("content_type");
                int tab = jsonObject.getInt("tab");
                String title = jsonObject.getString("title");
                String avatar = jsonObject.getString("avatar");

                videoProducts.add(new VideoProduct(id, publisher_id, content_type, tab, title, avatar));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
