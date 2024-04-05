package com.example.projekatmobilneaplikacije.fragments.employees;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;


import com.example.projekatmobilneaplikacije.R;
import com.example.projekatmobilneaplikacije.activities.employees.EmployeeRegistrationActivity;
import com.example.projekatmobilneaplikacije.databinding.FragmentEmployeesPageBinding;
import com.example.projekatmobilneaplikacije.fragments.FragmentTransition;
import com.example.projekatmobilneaplikacije.model.Employee;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EmployeesPageFragment extends Fragment {

    public static ArrayList<Employee> employees = new ArrayList<Employee>();
    private FragmentEmployeesPageBinding binding;
    public EmployeesPageFragment() {
        // Required empty public constructor
    }

    public static EmployeesPageFragment newInstance() {
        return new EmployeesPageFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEmployeesPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        prepareEmployeesList(employees);



        FragmentTransition.to(EmployeesListFragment.newInstance(employees), getActivity(),
                    false, R.id.scroll_employees_list);

        FloatingActionButton floatingActionButton = root.findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Otvorite novu aktivnost kada se klikne na floating dugme
                Intent intent = new Intent(getActivity(), EmployeeRegistrationActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }

    private void prepareEmployeesList(ArrayList<Employee> employees){
        employees.add(new Employee(1L, "Jana", "Janic", "Jana Janic" ,"jana@gmail.com", "Koste Racina 14, Novi Sad", "+381645057788", R.drawable.ic_face));
        employees.add(new Employee(1L, "Pera", "Peric", "Pera Peric" ,"pera@gmail.com", "Janka Veselinovica 18, Novi Sad", "+381616065127", R.drawable.ic_face));
        employees.add(new Employee(1L, "Zika", "Zikic", "Zika Zikic" ,"zika@gmail.com",  "Rumenacka 5, Novi Sad", "+381615057123", R.drawable.ic_face));
        employees.add(new Employee(1L, "Mara", "Maric", "Mara Maric" , "mara@gmail.com", "Kornelija Stankovic 24, Novi Sad", "+381695082233", R.drawable.ic_face));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}