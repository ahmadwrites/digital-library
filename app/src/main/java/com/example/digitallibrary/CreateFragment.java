package com.example.digitallibrary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateFragment extends Fragment {

    Integer userId;
    EditText etTitle, etAuthor, etDesc, etDate, etRating;
    Spinner spType;
    Switch swViewed;
    Button btnCreate;
    DatabaseHelper DB;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateFragment newInstance(String param1, String param2) {
        CreateFragment fragment = new CreateFragment();
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
        View view = inflater.inflate(R.layout.fragment_create, container, false);

        DB = new DatabaseHelper(getContext());

        etTitle = view.findViewById(R.id.etTitle);
        etAuthor = view.findViewById(R.id.etAuthor);
        etDesc = view.findViewById(R.id.etDesc);
        etDate = view.findViewById(R.id.etDate);
        etRating = view.findViewById(R.id.etRating);
        spType = view.findViewById(R.id.spType);
        swViewed = view.findViewById(R.id.swViewed);
        btnCreate = view.findViewById(R.id.btnCreate);

        etRating.setFilters(new RatingBarFilter[]{new RatingBarFilter(0, 5)});

        swViewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swViewed.isChecked()) {
                    swViewed.setText("Viewed");
                } else {
                    swViewed.setText("Not Viewed");
                }
            }
        });

        // Get current ID of current user
        SharedPreferences prefs = getActivity().getSharedPreferences("PrefsFile", Context.MODE_PRIVATE);
        String currentUser = prefs.getString("username", "user");

        Cursor cursor = DB.readCurrentUser(currentUser);
        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(), "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                userId = cursor.getInt(0);
            }
        }


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String author = etAuthor.getText().toString();
                String desc = etDesc.getText().toString();
                String date = etDate.getText().toString();
                String type = spType.getSelectedItem().toString();
                String rating = etRating.getText().toString();
                Boolean viewed = swViewed.getText().toString() == "Viewed" ? true : false;

                if (author.equals("") || desc.equals("") || date.equals("") || type.equals("Select item type") || rating.equals("")) {
                    Toast.makeText(getContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    boolean insert = DB.insertCollection(userId, title, author, desc, type, viewed, date, rating);
                    if (insert) {
                        Toast.makeText(getContext(), "Successfully created listing.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}