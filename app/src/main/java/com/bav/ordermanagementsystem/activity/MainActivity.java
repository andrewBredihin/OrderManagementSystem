package com.bav.ordermanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.entity.Client;
import com.bav.ordermanagementsystem.entity.Employee;
import com.bav.ordermanagementsystem.entity.UserRole;
import com.bav.ordermanagementsystem.service.UserService;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.bav.ordermanagementsystem.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userService = UserService.getInstance(getApplicationContext());

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        //Проверка на тип пользователя(настроить)
        if (userService.getUserDetails().getClass().equals(Client.class)) {
            navigationView.inflateMenu(R.menu.activity_main_drawer_client);
            navController.setGraph(R.navigation.mobile_navigation_client);
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_my_orders, R.id.nav_create_order, R.id.nav_slideshow, R.id.nav_order_info_client)
                    .setOpenableLayout(drawer)
                    .build();
        } else if (userService.getUserDetails().getClass().equals(Employee.class)){
            if (userService.getUserDetails().getRole().equals(UserRole.EMPLOYEE)){
                navigationView.inflateMenu(R.menu.activity_main_drawer_employee);
                navController.setGraph(R.navigation.mobile_navigation_employee);
                mAppBarConfiguration = new AppBarConfiguration.Builder(
                        R.id.nav_active_orders, R.id.nav_pending_orders, R.id.nav_slideshow, R.id.nav_order_info_employee)
                        .setOpenableLayout(drawer)
                        .build();
            }
            else if(userService.getUserDetails().getRole().equals(UserRole.MANAGER)){
                //дописать отображение для менеджера

            }
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.action_logout).setOnMenuItemClickListener(item -> {
            userService.setUserDetails(null);
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            return true;
        });
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}