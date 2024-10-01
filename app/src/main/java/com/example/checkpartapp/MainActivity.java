package com.example.checkpartapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checkpartapp.api.ApiService;
import com.example.checkpartapp.model.ApiResponse;
import com.example.checkpartapp.model.PartItem;
import com.example.checkpartapp.model.PartItemAdapter;
import com.example.checkpartapp.model.PartItem_Adapter_Recycler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListView listViewData;
    //    private PartItemAdapter adapter;
    private PartItem_Adapter_Recycler adapter;
    private List<PartItem> partItemList = new ArrayList<>();
    Button btnReset, btnCheck, btnExit;
    EditText edtOldBarcode, edtNewBarcode;
    TextView txtOldPartID, txtNewPartID;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        listViewData = findViewById(R.id.listViewData);

        btnCheck = findViewById(R.id.btnCheck);
        btnExit = findViewById(R.id.btnExit);
        btnReset = findViewById(R.id.btnReset);
        edtNewBarcode = findViewById(R.id.edtNewBarcode);
        edtOldBarcode = findViewById(R.id.edtOldBarcode);
        txtOldPartID = findViewById(R.id.txtOldPartID);
        txtNewPartID = findViewById(R.id.txtNewPartID);

        // Set focus on edtOldBarcode
        edtOldBarcode.requestFocus();

//        adapter = new PartItemAdapter(this, partItemList);
//        listViewData.setAdapter(adapter);
        adapter = new PartItem_Adapter_Recycler(partItemList);
        recyclerView.setAdapter(adapter);
        edtOldBarcode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
//                    Toast.makeText(MainActivity.this, "Enter pressed", Toast.LENGTH_SHORT).show();
                    String upnId = edtOldBarcode.getText().toString();
                    ApiService.apiService.getPartItemList(2, upnId).enqueue(new Callback<ApiResponse>() {
                        @Override
                        public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                            if (response.isSuccessful() && response.body() != null && response.body().isStatus()) {
//                                ApiResponse apiResponse = response.body();
                                if (response.body().getResult() != null && !response.body().getResult().isEmpty()) {
                                    partItemList.clear();
                                    partItemList.addAll(response.body().getResult());
                                    adapter.notifyDataSetChanged();
                                    txtOldPartID.setText(partItemList.get(0).getPART_ID());
//                                    Toast.makeText(MainActivity.this, "Call API Successfully", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "No parts found", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Response error", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ApiResponse> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Call API Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                return false;
            }
        });

        edtNewBarcode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (keyEvent != null && (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    String upnIdNew = edtNewBarcode.getText().toString();
                    boolean flag = false;
                    for (int j = 0; j < partItemList.size(); j++) {
                        if (partItemList.get(j).getUPN_ID().equals(upnIdNew)) {
                            txtNewPartID.setText(partItemList.get(j).getPART_ID());
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) txtNewPartID.setText("");
                    // Automatically trigger the Check button click
                    btnCheck.performClick();

                }
                return false;
            }
        });
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPartID = txtOldPartID.getText().toString();
                String newPartID = txtNewPartID.getText().toString();
                Intent myIntent = new Intent(MainActivity.this, MessageActivity.class);
                if (!oldPartID.isEmpty() && oldPartID.equals(newPartID)) {
//    Toast.makeText(MainActivity.this, "OK" , Toast.LENGTH_SHORT).show();

                    String result = "OK";
                    Bundle myBundle = new Bundle();
                    myBundle.putString("rs", result);

                    myIntent.putExtra("myPackage", myBundle);
                    startActivity(myIntent);
                } else if (oldPartID.isEmpty() && newPartID.isEmpty()) {
//    Toast.makeText(MainActivity.this, "NG" , Toast.LENGTH_SHORT).show();
                    String result = "NG";
                    Bundle myBundle = new Bundle();
                    myBundle.putString("rs", result);

                    myIntent.putExtra("myPackage", myBundle);
                    startActivity(myIntent);
                } else {
//    Toast.makeText(MainActivity.this, "NG" , Toast.LENGTH_SHORT).show();
                    String result = "NG";
                    Bundle myBundle = new Bundle();
                    myBundle.putString("rs", result);

                    myIntent.putExtra("myPackage", myBundle);
                    startActivity(myIntent);
                }

            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtNewBarcode.setText("");
                edtOldBarcode.setText("");
                txtOldPartID.setText("");
                txtNewPartID.setText("");

                partItemList.clear();

                adapter.notifyDataSetChanged();
                // Set focus on edtOldBarcode
                edtOldBarcode.requestFocus();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}