package com.example.okutech.hideviewwhilescroll;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recycleView;
    private ArrayList<String> arrayList = new ArrayList<>();
    private LinearLayout bottomLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycleView = (RecyclerView) findViewById(R.id.recycleView);
        bottomLL = (LinearLayout) findViewById(R.id.bottomLL);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        for (int i = 0; i < 50; i++) {
            arrayList.add(i, "TEXT VIEW " + i);
        }

        MyAdaptor adaptor = new MyAdaptor(this, arrayList);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(adaptor);
        adaptor.notifyDataSetChanged();

        RecyclerOnScroll onScroll = new RecyclerOnScroll(layoutManager, new RecyclerOnScroll.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

            }
        });
        onScroll.setSearchBottomLL(bottomLL);

        recycleView.addOnScrollListener(onScroll);
    }
}
