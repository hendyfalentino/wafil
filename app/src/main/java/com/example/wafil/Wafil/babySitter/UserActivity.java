package com.example.wafil.Wafil.babySitter;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wafil.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity implements UserAdapter.OnItemClickListener {
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_IMAGE = "image";
    public static final String EXTRA_PASSWORD = "password";
    public static final String EXTRA_ADDRESS = "address";
    public static final String EXTRA_PHONE_NUMBER = "phone_number";
    public static final String EXTRA_GENDER = "gender";

    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
    private static final String URL_PRODUCTS = "https://babysitterppl.000webhostapp.com/UserApi.php";

    //a list to store all the products
    List<User> userList;
    //the recyclerview
    RecyclerView recyclerView;
    //RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //initializing the productlist
        userList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();
    }

    private void loadProducts() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject user = array.getJSONObject(i);

                                //adding the product to product list
                                userList.add(new User(
                                        user.getString("name"),
                                        user.getString("email"),
                                        user.getString("image"),
                                        user.getString("password"),
                                        user.getString("address"),
                                        user.getString("phone_number"),
                                        user.getString("gender")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            UserAdapter adapter = new UserAdapter(UserActivity.this, userList);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(UserActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this,DetailActivity.class);
        User clickedItem = userList.get(position);

        detailIntent.putExtra(EXTRA_NAME, clickedItem.getName());
        detailIntent.putExtra(EXTRA_EMAIL, clickedItem.getEmail());
        detailIntent.putExtra(EXTRA_IMAGE, clickedItem.getImage());
        detailIntent.putExtra(EXTRA_PASSWORD, clickedItem.getPassword());
        detailIntent.putExtra(EXTRA_ADDRESS, clickedItem.getAddress());
        detailIntent.putExtra(EXTRA_PHONE_NUMBER, clickedItem.getPhone_number());
        detailIntent.putExtra(EXTRA_GENDER, clickedItem.getGender());

        startActivity(detailIntent);
    }
}
