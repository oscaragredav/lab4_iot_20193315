package com.example.l4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.l4.databinding.ActivityAppBinding;
import com.example.l4.fragment.ClimaFragment;
import com.example.l4.fragment.GeoFragment;
import com.example.l4.service.CiudadService;

public class appActivity extends AppCompatActivity {

    ActivityAppBinding binding;
    CiudadService ciudadService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new GeoFragment());


        binding.bottomNavigationView.setOnItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.geo) {
                replaceFragment(new GeoFragment());
            } else if (menuItem.getItemId() == R.id.clima) {
                replaceFragment(new ClimaFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment);
        fragmentTransaction.commit();
    }
}