package com.example.projekatmobilneaplikacije.fragments.registration;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projekatmobilneaplikacije.R;
import com.example.projekatmobilneaplikacije.activities.HomeActivity;
import com.example.projekatmobilneaplikacije.activities.RegistrationActivity;
import com.example.projekatmobilneaplikacije.databinding.FragmentRegistrationBinding;
import com.example.projekatmobilneaplikacije.model.EventOrganizer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class RegistrationFragment extends Fragment {
    EditText username, password_reg, password_check, name, surname, address, phone;
    private FragmentRegistrationBinding binding;

    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;
    CollectionReference usersRef = db.collection("eventOrganizers");
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);

        }
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        username = root.findViewById(R.id.username);
        password_reg = root.findViewById(R.id.password_reg);
        password_check = root.findViewById(R.id.password2);
        name = root.findViewById(R.id.name);
        surname = root.findViewById(R.id.surname);
        address = root.findViewById(R.id.home_address);
        phone = root.findViewById(R.id.phone);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        binding.registerPUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment registerFragment = new RegisterFragment();

                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_registration_container, registerFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        binding.eventOrganizer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password, namet, surnamet, addresst, phonet, password2;
                email = String.valueOf(username.getText());
                password = String.valueOf(password_reg.getText());
                password2 = String.valueOf(password_check.getText());
                namet = String.valueOf(name.getText());
                surnamet = String.valueOf(surname.getText());
                addresst = String.valueOf(address.getText());
                phonet = String.valueOf(phone.getText());

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password2)) {
                    Toast.makeText(v.getContext(), "Enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!password.equals(password2)){
                    Toast.makeText(v.getContext(), "Enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                    if (firebaseUser != null) {
                                        String userId =firebaseUser.getUid();
                                        EventOrganizer eventOrganizer = new EventOrganizer(userId, email, namet, surnamet, addresst, phonet);
                                        addUserDataToFirestore(eventOrganizer);
                                        Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(v.getContext(), HomeActivity.class);
                                        startActivity(intent);

                                    }
                                }else {
                                    Toast.makeText(v.getContext(), "Error while registering", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });

        return root;
    }

    private void addUserDataToFirestore(EventOrganizer eventOrganizer) {
        // Add your logic to add eventOrganizer to Firestore
        usersRef.add(eventOrganizer)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        // Handle success, if needed
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                        // Handle failure, if needed
                    }
                });
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}