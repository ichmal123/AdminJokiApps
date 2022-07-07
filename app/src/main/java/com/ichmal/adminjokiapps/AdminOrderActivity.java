package com.ichmal.adminjokiapps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminOrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    OrderAdminAdapter orderAdminAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Orderan> options =
                new FirebaseRecyclerOptions.Builder<Orderan>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Orders"), Orderan.class)
                .build();

        orderAdminAdapter = new OrderAdminAdapter(options);
        recyclerView.setAdapter(orderAdminAdapter);

//        list = new ArrayList<>();
//        riwayatAdapter = new OrderanJokiAdapter(this, list);
//
//        recyclerView.setAdapter(riwayatAdapter);
//        root.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Orderan order = dataSnapshot.getValue(Orderan.class);
//                    order.setKey(dataSnapshot.getKey());
//                    list.add(order);
//                }
//                riwayatAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        setupToolbar();
    }
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.tbOrderAdmin);
        toolbar.setTitle("Menu History Order");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        orderAdminAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        orderAdminAdapter.stopListening();
    }
}