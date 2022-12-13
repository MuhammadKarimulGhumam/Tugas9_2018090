package com.example.bab5_2018090;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.example.bab5_2018090.databinding.ActivityMainBinding;
import com.example.bab5_2018090.databinding.ActivityMain2Binding;
import com.example.bab5_2018090.databinding.ActivityMain3Binding;
import com.google.android.material.navigation.NavigationView;
public class MainActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    private ActivityMainBinding binding;
    RecyclerView recylerView;
    String s1[], s2[],s3[];
    int images[] =
            {R.drawable.helm2,R.drawable.helm3,R.drawable.helm4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                binding =
                ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dl = (DrawerLayout)findViewById(R.id.dl);
        abdt = new
                ActionBarDrawerToggle(this,dl,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(abdt);
        abdt.syncState();
//action Bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nav_view =
                (NavigationView)findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new
                                                           NavigationView.OnNavigationItemSelectedListener() {
                                                               @Override
                                                               public boolean onNavigationItemSelected(@NonNull
                                                                                                               MenuItem item) {
                                                                   int id = item.getItemId();
                                                                   if (id == R.id.nav_profile){
                                                                       Intent a = new Intent(MainActivity.this, ActivityMain2.class);
                                                                       startActivity(a);
                                                                   }else if (id == R.id.nav_reviews){
                                                                       Intent a = new
                                                                               Intent(MainActivity.this,
                                                                               MainActivity.class);
                                                                       startActivity(a);
                                                                   }else if (id == R.id.nav_settings){
                                                                       Intent a = new
                                                                               Intent(MainActivity.this, ActivityMain3.class);
                                                                       startActivity(a);
                                                                   }
                                                                   else if (id == R.id.nav_api){
                                                                       Intent a = new
                                                                               Intent(MainActivity.this, MainActivity4.class);
                                                                       startActivity(a);
                                                                   }
                                                                   return true;
                                                               }
                                                           });
//recycle View
        recylerView = findViewById(R.id.recyclerView);
        s1 = getResources().getStringArray(R.array.Helm);
        s2 = getResources().getStringArray(R.array.Star);
        s3 = getResources().getStringArray(R.array.Deskripsi);
         HelmAdapter appAdapter = new HelmAdapter(this,s1,s2,s3,images);
        recylerView.setAdapter(appAdapter);
        LinearLayoutManager layoutManager = new
                LinearLayoutManager(MainActivity.this,LinearLayoutManager
                .HORIZONTAL, false
        );
        recylerView.setLayoutManager(layoutManager);
        recylerView.setItemAnimator(new DefaultItemAnimator());
//work manager
        final OneTimeWorkRequest request = new
                OneTimeWorkRequest.Builder(MyWorker.class).build();
        binding.button.setOnClickListener(new
                                                  View.OnClickListener() {
                                                      @Override
                                                      public void
                                                      onClick(View view) {
                                                          WorkManager.getInstance().enqueueUniqueWork("Notifikasi",
                                                                  ExistingWorkPolicy.REPLACE, request);
                                                      }
                                                  });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        return abdt.onOptionsItemSelected(item) ||
                super.onOptionsItemSelected(item);
    }
}