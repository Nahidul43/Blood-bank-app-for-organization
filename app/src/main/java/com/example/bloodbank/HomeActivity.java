package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.HashMap;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
  //  TextView aP,aN,bP,bN,oP,oN,abP,abN;

    RecyclerView recyclerView;
    HashMap<String,String>hashMap;
    ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bottomNavigation=findViewById(R.id.bottomNavigation);


        recyclerView=findViewById(R.id.recyclerView);

bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.Home){
            startActivity(new Intent(HomeActivity.this, HomeActivity.class));
        }else{
            startActivity(new Intent(HomeActivity.this, AddData.class));
        }
        return true;
    }
});









        hashMap=new HashMap<>();
        hashMap.put("title","A+");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("title","A-");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("title","B+");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("title","B-");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("title","O+");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("title","O-");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("title","AB+");
        arrayList.add(hashMap);

        hashMap=new HashMap<>();
        hashMap.put("title","AB-");
        arrayList.add(hashMap);

        recyclerView.setAdapter(new XAdapter());
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));




    }

    public class XAdapter extends RecyclerView.Adapter<XAdapter.Holder>{



        public class Holder extends RecyclerView.ViewHolder{

            TextView aP;

            public Holder(@NonNull View itemView) {
                super(itemView);
                aP=itemView.findViewById(R.id.aP);
            }
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=getLayoutInflater();
            View view=inflater.inflate(R.layout.item,parent,false);
            return new Holder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            hashMap=arrayList.get(position);
            String name=hashMap.get("title");
            holder.aP.setText(name);

            holder.aP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(HomeActivity.this, ShowDataActivity.class);
                    intent.putExtra("ID", name);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }


    }





}