package com.example.digitallibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    RecyclerView rc_home;
    DatabaseHelper DB;
    CollectionAdapter collectionAdapter;

    String currentUserId;

    ArrayList<String> userId;
    ArrayList<String> title;
    ArrayList<String> author;
    ArrayList<String> desc;
    ArrayList<String> type;
    ArrayList<Integer> viewed;
    ArrayList<String> datePublished;
    ArrayList<String> rating;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        DB = new DatabaseHelper(getContext());
        rc_home = view.findViewById(R.id.rc_home);

        userId = new ArrayList<>();
        title = new ArrayList<>();
        author = new ArrayList<>();
        desc = new ArrayList<>();
        type = new ArrayList<>();
        viewed = new ArrayList<>();
        datePublished = new ArrayList<>();
        rating = new ArrayList<>();

        storeDataInArray();

        collectionAdapter = new CollectionAdapter(getContext(), userId, title, author, desc, type, viewed, datePublished, rating);
        rc_home.setAdapter(collectionAdapter);
        rc_home.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    void storeDataInArray() {
        SharedPreferences prefs = getContext().getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        String currentUser = prefs.getString("username", "user");

        getCurrentUserId(currentUser);

        Cursor cursor = DB.readUserPosts(currentUserId);

        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                userId.add(cursor.getString(1));
                title.add(cursor.getString(2));
                author.add(cursor.getString(3));
                desc.add(cursor.getString(4));
                type.add(cursor.getString(5));
                viewed.add(cursor.getInt(6));
                datePublished.add(cursor.getString(7));
                rating.add(cursor.getString(8));
            }
        }
    }

    void getCurrentUserId(String username) {
        Cursor cursor = DB.readCurrentUser(username);

        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                currentUserId = cursor.getString(0);
            }
        }
    }
}