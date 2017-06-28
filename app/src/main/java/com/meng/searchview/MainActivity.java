package com.meng.searchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private CardView mCardViewSearch;
    private LinearLayout mSearchLayout;
    private ImageView mIvSearchBack;
    private EditText mEtSearch;
    private ImageView mClearSearch;
    private RecyclerView mRecycleview;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initResultItem();
        initToolbar();
        initListener();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mCardViewSearch = (CardView) findViewById(R.id.cardView_search);
        mSearchLayout = (LinearLayout) findViewById(R.id.search_layout);
        mIvSearchBack = (ImageView) findViewById(R.id.iv_search_back);
        mEtSearch = (EditText) findViewById(R.id.et_search);
        mClearSearch = (ImageView) findViewById(R.id.clearSearch);
        mRecycleview = (RecyclerView) findViewById(R.id.recycleview);
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i + 1 + "碗胡辣汤");
        }
    }

    private void initResultItem() {
        RvAdapter adapter = new RvAdapter(list, new RvAdapter.IListener() {
            @Override
            public void normalItemClick(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void clearItemClick() {
                Toast.makeText(MainActivity.this, "清除历史记录", Toast.LENGTH_SHORT).show();
            }
        });
        mRecycleview.setAdapter(adapter);
        mRecycleview.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
    }

    private void initToolbar() {
        mToolbar.inflateMenu(R.menu.menu_main);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItem = item.getItemId();
                switch (menuItem) {
                    case R.id.action_search:
                        SearchViewUtils.handleToolBar(getApplicationContext(), mCardViewSearch, mEtSearch);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void initListener() {
        mIvSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchViewUtils.handleToolBar(getApplicationContext(), mCardViewSearch, mEtSearch);
            }
        });
    }
}
