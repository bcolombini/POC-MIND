package com.example.brunocolombini.poc_mind;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.empty_state)
    ConstraintLayout emptyState;

    @BindView(R.id.recycleView)
    RecycleViewEmpty recyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private RecycleViewAdapter recycleViewAdapter;
    private List<String> stringList = new ArrayList<>();
    private Faker faker = new Faker();
    private MenuItem counterItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpToolbar();
        mockStrings();
        setUpRecycleView();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        counterItem = menu.findItem(R.id.counter);
        counterItem.setTitle(recycleViewAdapter.getItemCount() + "");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.all:
                recycleViewAdapter.selectAll();
                break;
            case R.id.delete:
                recycleViewAdapter.deleteSelected();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        counterItem.setTitle(recycleViewAdapter.getItemCount() + "");
        return true;
    }

    public void setUpToolbar() {
        toolbar.setTitle("Cadastro em Grupo");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void mockStrings() {
        for (int i = 0; i < 100; i++) {
            stringList.add(faker.name().fullName());
        }
    }

    private void setUpRecycleView() {
        recycleViewAdapter = new RecycleViewAdapter(stringList);
        recyclerView.setEmptyStateView(emptyState);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recycleViewAdapter);
    }
}
