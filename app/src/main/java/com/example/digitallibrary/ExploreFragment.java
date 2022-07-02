package com.example.digitallibrary;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExploreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploreFragment extends Fragment {

    YouTubePlayerView ytPlayer;
    EditText etSearch;

    CardView cardBooks, cardComics, cardMovies, cardTv, cardOthers;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExploreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExploreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExploreFragment newInstance(String param1, String param2) {
        ExploreFragment fragment = new ExploreFragment();
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
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        cardBooks = view.findViewById(R.id.cardBooks);
        cardComics = view.findViewById(R.id.cardComics);
        cardMovies = view.findViewById(R.id.cardMovies);
        cardTv = view.findViewById(R.id.cardTv);
        cardOthers = view.findViewById(R.id.cardOthers);

        ytPlayer = view.findViewById(R.id.ytPlayer);
        getLifecycle().addObserver(ytPlayer);

        etSearch = view.findViewById(R.id.etSearch);
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    String searchQuery = etSearch.getText().toString();

                    Bundle bundle = new Bundle();
                    bundle.putString("search", searchQuery);
                    Fragment SearchFragment = new SearchedCollectionFragment();
                    SearchFragment.setArguments(bundle);
                    getParentFragmentManager().setFragmentResult("search", bundle);

                    getFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, new SearchedCollectionFragment())
                            .commit();

                    return true;
                }
                return false;
            }
        });

        cardBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "Book");
                Fragment SportFragment = new CategoryCollectionFragment();
                SportFragment.setArguments(bundle);
                getParentFragmentManager().setFragmentResult("type", bundle);

                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, new CategoryCollectionFragment())
                        .commit();
            }
        });

        cardComics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "Comic");
                Fragment SportFragment = new CategoryCollectionFragment();
                SportFragment.setArguments(bundle);
                getParentFragmentManager().setFragmentResult("type", bundle);

                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, new CategoryCollectionFragment())
                        .commit();
            }
        });

        cardMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "Movie");
                Fragment SportFragment = new CategoryCollectionFragment();
                SportFragment.setArguments(bundle);
                getParentFragmentManager().setFragmentResult("type", bundle);

                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, new CategoryCollectionFragment())
                        .commit();
            }
        });

        cardTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "TV Show");
                Fragment SportFragment = new CategoryCollectionFragment();
                SportFragment.setArguments(bundle);
                getParentFragmentManager().setFragmentResult("type", bundle);

                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, new CategoryCollectionFragment())
                        .commit();
            }
        });

        cardOthers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("type", "Others");
                Fragment SportFragment = new CategoryCollectionFragment();
                SportFragment.setArguments(bundle);
                getParentFragmentManager().setFragmentResult("type", bundle);

                getFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, new CategoryCollectionFragment())
                        .commit();
            }
        });

        return view;
    }
}