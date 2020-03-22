package com.therealsanjeev.nsutbookexchange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.therealsanjeev.nsutbookexchange.buy.buy;
import com.therealsanjeev.nsutbookexchange.login.LoginActivity;
import com.therealsanjeev.nsutbookexchange.login.RegisterActivity;
import com.therealsanjeev.nsutbookexchange.sell.sell;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FirebaseAuth auth;
    FirebaseUser authUser;

    Button btnBuy;
    Button btnSell;

    private boolean backAlreadyPressed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBuy = findViewById(R.id.btnBuy);
        btnSell = findViewById(R.id.btnSell);

        auth=FirebaseAuth.getInstance();
        authUser=FirebaseAuth.getInstance().getCurrentUser();

        if(authUser==null){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }

        //bck button menu:

        //navigation Bar:
        toolbar=findViewById(R.id.toolBar);
        navigationView=findViewById(R.id.navigationView);
        drawerLayout =findViewById(R.id.drawerMenu);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        final Intent sellIntent = new Intent(MainActivity.this, buy.class);
        final Intent buyIntent=new Intent(MainActivity.this, sell.class);

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(sellIntent);
            }
        });
        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(buyIntent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.buy_menu:
                Intent buyIntent=new Intent(MainActivity.this, buy.class);
                startActivity(buyIntent);
                break;
            case R.id.sell_menu:
                final Intent sellIntent = new Intent(MainActivity.this, sell.class);
                startActivity(sellIntent);
                break;
            case R.id.aboutUs:
                Intent aboutIntent=new Intent(MainActivity.this,aboutUs.class);
                startActivity(aboutIntent);
                break;
            case R.id.exit:
                finish();
                System.exit(0);

            case android.R.id.home:
                this.finish();
            case R.id.logOut:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();


            default:
                break;
        }
        return true;
    }

    public void onBackPressed() {

        if (backAlreadyPressed) {
            // close the application
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            super.onBackPressed();
            return;
        }
        // first back press should set the variable to true & show a Toast to press again to close application
        this.backAlreadyPressed = true;
        Toast.makeText(this,"Press once more to exit", Toast.LENGTH_SHORT).show();
        // set the variable to false if it takes more than 2sec for another back press
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                backAlreadyPressed = false;
            }
        }, 2000);
    }
}
