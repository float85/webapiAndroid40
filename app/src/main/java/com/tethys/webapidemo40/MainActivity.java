package com.tethys.webapidemo40;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.tethys.webapidemo40.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    List<VideoProduct> videoProducts;

    String json = "{\"id\":\"144\",\"publisher_id\":\"3\",\"content_type\":\"3\",\"tab\":\"0\",\"title\":\"Chinese Series\",\"avatar\":null}";

    String jsonArray = "[{\"id\":\"144\",\"publisher_id\":\"3\",\"content_type\":\"3\",\"tab\":\"0\",\"title\":\"Chinese Series\",\"avatar\":null},{\"id\":\"111\",\"publisher_id\":\"113\",\"content_type\":\"113\",\"tab\":\"110\",\"title\":\"Series Phim\",\"avatar\":\"----------\"}]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        videoProducts = new ArrayList<>();

//        getJson();

        getJsonArray();
    }

    private void getJsonArray() {
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

            binding.tvJson.setText(videoProducts.get(1).getTitle());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getJson() {

        try {
            JSONObject jsonObject = new JSONObject(json);

            int id = jsonObject.getInt("id");
            int publisher_id = jsonObject.getInt("publisher_id");
            int content_type = jsonObject.getInt("content_type");
            int tab = jsonObject.getInt("tab");
            String title = jsonObject.getString("title");
            String avatar = jsonObject.getString("avatar");

            String textview = "id: " + id +
                    "\npublisher_id: " + publisher_id +
                    "\ntitle: " + title;

            binding.tvJson.setText(textview);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}