package edu.project.TouristTicketOrder.HomePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

    public class LightMode implements ThemeState {
        @Override
        public void apply(AppCompatActivity activity) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
