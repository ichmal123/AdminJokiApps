package com.ichmal.adminjokiapps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderanJokiAdapter extends RecyclerView.Adapter<OrderanJokiAdapter.MyViewHolder> {

    ArrayList<Orderan> mList;
    Context context;

    public OrderanJokiAdapter(Context context, ArrayList<Orderan> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_list, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Orderan order = mList.get(position);
        holder.username.setText(order.getUsername());
        holder.nama.setText(order.getNama());
        holder.email.setText(order.getEmail());
        holder.noHp.setText(order.getPhone());
        holder.idAkun.setText(order.getIdAkun());
        holder.passAkun.setText(order.getPassAkun());
        holder.tier.setText(order.getTierAkun());
        holder.tipeOrder.setText(order.getTipeOrder());
        holder.total.setText(String.valueOf(order.getTotal()));
        holder.tanggal.setText(order.getTanggal());
        holder.status.setText(order.getStatus());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.username.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true, 1200)
                        .create();


                View vi = dialogPlus.getHolderView();

                EditText username = vi.findViewById(R.id.editUsername);
                EditText name = vi.findViewById(R.id.editName);
                EditText email = vi.findViewById(R.id.editEmail);
                EditText phone = vi.findViewById(R.id.editPhone);
                EditText idAkunUser = vi.findViewById(R.id.editAkun);
                EditText passAkunUser = vi.findViewById(R.id.editPass);
                EditText tier = vi.findViewById(R.id.editTier);
                EditText orderBintang = vi.findViewById(R.id.editOrderBintang);
                EditText harga = vi.findViewById(R.id.editHarga);
                EditText role = vi.findViewById(R.id.editRole);
                EditText tipeOrder = vi.findViewById(R.id.editTipe);
                EditText tanggal = vi.findViewById(R.id.editTanggal);
                EditText total = vi.findViewById(R.id.editTotal);
                EditText status1 = vi.findViewById(R.id.editStatus);

                Button btnUpdate = vi.findViewById(R.id.btnUpdate);

                username.setText(order.getUsername());
                name.setText(order.getNama());
                email.setText(order.getEmail());
                phone.setText(order.getPhone());
                idAkunUser.setText(order.getIdAkun());
                passAkunUser.setText(order.getPassAkun());
                tier.setText(order.getTierAkun());
                orderBintang.setText(String.valueOf(order.getOrderBintang()));
                harga.setText(String.valueOf(order.getHarga()));
                role.setText(order.getRole());
                tipeOrder.setText(order.getTipeOrder());
                tanggal.setText(order.getTanggal());
                total.setText(String.valueOf(order.getTotal()));
                status1.setText(order.getStatus());

                dialogPlus.show();

//                btnUpdate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Map<String, Object> map = new HashMap<>();
//                        map.put("username", username.getText().toString());
//                        map.put("nama", name.getText().toString());
//                        map.put("email", email.getText().toString());
//                        map.put("phone", phone.getText().toString());
//                        map.put("idAkun", idAkunUser.getText().toString());
//                        map.put("passAkun", passAkunUser.getText().toString());
//                        map.put("tierAkun", tier.getText().toString());
//                        map.put("orderBintang", orderBintang.getText().toString());
//                        map.put("harga", harga.getText().toString());
//                        map.put("role", role.getText().toString());
//                        map.put("tipeOrder", tipeOrder.getText().toString());
//                        map.put("tanggal", tanggal.getText().toString());
//                        map.put("total", total.getText().toString());
//                        map.put("status", status1.getText().toString());
//
//                        String uniq = FirebaseDatabase.getInstance().getReference().child("Order_for_admin").push().getKey();
//                        FirebaseDatabase.getInstance().getReference().child("Order_for_admin")
//                                .child("").updateChildren(map)
//                                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                    @Override
//                                    public void onSuccess(Void unused) {
//                                        Toast.makeText(holder.nama.getContext(), "Data Update Sukses", Toast.LENGTH_SHORT).show();
//                                        dialogPlus.dismiss();
//                                    }
//                                })
//                                .addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(holder.nama.getContext(), "Error While Updating", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                    }
//                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView username, nama, email, noHp, idAkun, passAkun, tier, tipeOrder, total, status, tanggal;
        Button btnEdit, btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username_text);
            nama = itemView.findViewById(R.id.name_text);
            email = itemView.findViewById(R.id.email_text);
            noHp = itemView.findViewById(R.id.phone_text);
            idAkun = itemView.findViewById(R.id.idAkun_text);
            passAkun = itemView.findViewById(R.id.passAkun_text);
            tier = itemView.findViewById(R.id.tier_text);
            tipeOrder = itemView.findViewById(R.id.order_text);
            total = itemView.findViewById(R.id.total_text);
            tanggal = itemView.findViewById(R.id.tanggal_text);
            status = itemView.findViewById(R.id.status_text);

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
