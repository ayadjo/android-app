package com.example.projekatmobilneaplikacije.fragments.serviceAndProduct;

import android.os.Bundle;
import androidx.fragment.app.ListFragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projekatmobilneaplikacije.R;
import com.example.projekatmobilneaplikacije.adapters.ServiceAndProductListAdapter;
import com.example.projekatmobilneaplikacije.databinding.FragmentServiceAndProductListBinding;
import com.example.projekatmobilneaplikacije.fragments.registration.RegistrationFragment;
import com.example.projekatmobilneaplikacije.fragments.registration.TimePickerDialogFragment;
import com.example.projekatmobilneaplikacije.model.Category;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ServiceAndProductListFragment extends ListFragment {
    private FragmentServiceAndProductListBinding binding;
    private ServiceAndProductListAdapter adapter;
    private static final String ARG_PARAM = "param";
    private ArrayList<Category> mCategories;
    public static ServiceAndProductListFragment newInstance(ArrayList<Category> categories){
        ServiceAndProductListFragment fragment = new ServiceAndProductListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM, categories);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentServiceAndProductListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategories = getArguments().getParcelableArrayList(ARG_PARAM);
            adapter = new ServiceAndProductListAdapter(getActivity(), mCategories);
            setListAdapter(adapter);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
