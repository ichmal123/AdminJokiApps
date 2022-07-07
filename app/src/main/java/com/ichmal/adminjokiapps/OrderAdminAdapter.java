package com.ichmal.adminjokiapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class OrderAdminAdapter extends FirebaseRecyclerAdapter<Orderan, OrderAdminAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public OrderAdminAdapter(@NonNull FirebaseRecyclerOptions<Orderan> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Orderan model) {
        holder.username.setText(model.getUsername());
        holder.nama.setText(model.getNama());
        holder.email.setText(model.getEmail());
        holder.noHp.setText(model.getPhone());
        holder.idAkun.setText(model.getIdAkun());
        holder.passAkun.setText(model.getPassAkun());
        holder.tier.setText(model.getTierAkun());
        holder.tipeOrder.setText(model.getTipeOrder());
        holder.tanggal.setText(model.getTanggal());
        holder.total.setText(String.valueOf(model.getTotal()));
        holder.status.setText(model.getStatus());


        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.username.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true, 2000)
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
                EditText status = vi.findViewById(R.id.editStatus);

                Button btnUpdate = vi.findViewById(R.id.btnUpdate);

                username.setText(model.getUsername());
                name.setText(model.getNama());
                email.setText(model.getEmail());
                phone.setText(model.getPhone());
                idAkunUser.setText(model.getIdAkun());
                passAkunUser.setText(model.getPassAkun());
                tier.setText(model.getTierAkun());
                orderBintang.setText(String.valueOf(model.getOrderBintang()));
                harga.setText(String.valueOf(model.getHarga()));
                role.setText(model.getRole());
                tipeOrder.setText(model.getTipeOrder());
                tanggal.setText(model.getTanggal());
                total.setText(String.valueOf(model.getTotal()));
                status.setText(model.getStatus());

                dialogPlus.show();
                String UserID = model.getUserID();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("username", username.getText().toString());
                        map.put("nama", name.getText().toString());
                        map.put("email", email.getText().toString());
                        map.put("phone", phone.getText().toString());
                        map.put("idAkun", idAkunUser.getText().toString());
                        map.put("passAkun", passAkunUser.getText().toString());
                        map.put("tierAkun", tier.getText().toString());
                        map.put("orderBintang", orderBintang.getText().toString());
                        map.put("harga", harga.getText().toString());
                        map.put("role", role.getText().toString());
                        map.put("tipeOrder", tipeOrder.getText().toString());
                        map.put("tanggal", tanggal.getText().toString());
                        map.put("total", total.getText().toString());
                        map.put("status", status.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Orders")
                                .child(getRef(position).getKey()).setValue(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.nama.getContext(), "Data Update Sukses", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(holder.nama.getContext(), "Error While Updating", Toast.LENGTH_SHORT).show();
                                dialogPlus.dismiss();
                            }
                        });
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        TextView username, nama, email, noHp, idAkun, passAkun, tier, tipeOrder, total, status, tanggal;
        Button btnEdit, btnDelete;

        public myViewHolder(@NonNull View itemView) {
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
