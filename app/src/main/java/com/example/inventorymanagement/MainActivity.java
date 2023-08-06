package com.example.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.os.Build;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.widget.PopupMenu;

import java.util.ArrayList;
import java.util.List;
import com.example.inventorymanagement.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
        LinearLayout imgIndicators;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

//            listView=findViewById(R.id.listview);
//            ListViewAdapter listViewAdapter=new ListViewAdapter(getApplicationContext(),bookList,bookImage);
//            listView.setAdapter(listViewAdapter);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

//            // Customize the action bar (if needed)
            //getSupportActionBar().setTitle("Inventory Management System");


            ViewPager2 viewPager = findViewById(R.id.view_pager);
            ImageAdapter adapter = new ImageAdapter(setupImageItems());
            viewPager.setAdapter(adapter);

            RatingBar ratingBar = findViewById(R.id.ratingBar);
            ratingBar.setIsIndicator(false);
            ratingBar.setStepSize(1);
            ratingBar.setNumStars(5);
            ImageView arrowIcon = findViewById(R.id.arrowIcon);
            arrowIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Increase the rating by 1 when the arrow icon is clicked
                    float currentRating = ratingBar.getRating();
                    ratingBar.setRating(currentRating - 1);
                }
            });
//            ImageView arrowIcons=findViewById(R.id.arrowIcons);
//            arrowIcons.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Increase the rating by 1 when the arrow icon is clicked
//                    float currentRating = ratingBar.getRating();
//                    ratingBar.setRating(currentRating + 1);
//                }
//            });

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.statusBarColor));
            }


            BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);

            bottomNavigationView.setBackgroundResource(R.drawable.bg_bottom_navigation);

            imgIndicators =findViewById(R.id.dots_indicator);
            setupImageIndicators(adapter);
            setCurrentIndicator(adapter, 0);

            viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    setCurrentIndicator(adapter, position);
                }
            });
        }




    private List<Integer> setupImageItems()
        {
            List<Integer> itemList = new ArrayList<>();
            itemList.add(R.drawable.book1);
            itemList.add(R.drawable.book2);
            itemList.add(R.drawable.books);


            return itemList;
        }

    private void setupImageIndicators(ImageAdapter adapter) {
        ImageView[] indicators = new ImageView[adapter.getItemCount()];

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(0, 0, 0, 0);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            imgIndicators.addView(indicators[i]);

            // Add click listener to each indicator
            final int index = i;
            indicators[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewPager2 viewPager = findViewById(R.id.view_pager);
                    viewPager.setCurrentItem(index, true);
                }
            });
        }
    }

    private void setCurrentIndicator(ImageAdapter adapter, int index) {
        int childCount = imgIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imgView = (ImageView) imgIndicators.getChildAt(i);
            if (i == index) {
                imgView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.indicator_active));
            } else {
                imgView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.indicator_inactive));
            }
        }
    }


    class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder>
        {

            List<Integer> mList;

            public ImageAdapter(List<Integer> mList)
            {
                this.mList = mList;
            }

            @NonNull
            @Override
            public ImageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewer_page, parent, false);
                return new MyViewHolder(view);
            }

            @Override
            public void onBindViewHolder(@NonNull ImageAdapter.MyViewHolder holder, int position) {
                holder.img.setImageResource(mList.get(position));
            }

            @Override
            public int getItemCount() {
                return mList.size();
            }

            class MyViewHolder extends RecyclerView.ViewHolder
            {
                ImageView img;

                public MyViewHolder(@NonNull View itemView) {
                    super(itemView);

                    img = itemView.findViewById(R.id.view_pager_img);
                }
            }
        }

        @Override
        public  boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.main_menu,menu);
            return true;
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_more_vert) {
            // Create a PopupMenu instance
            PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.action_more_vert));

            // Inflate the popup menu layout
            popupMenu.getMenuInflater().inflate(R.menu.menu_pop, popupMenu.getMenu());

            // Set an OnMenuItemClickListener to handle click events
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    int itemId = menuItem.getItemId();
                    if (itemId == R.id.action_list_view) {
                        Intent intent=new Intent(MainActivity.this,ListViewActivity.class);
                        startActivity(intent);
                        return true;
                    } else if (itemId == R.id.action_recycler_view) {
                        Intent intent=new Intent(MainActivity.this,RecyclerViewActivity.class);
                        startActivity(intent);
                        return true;
                    }
                    return false;
                }
            });

            // Show the popup menu
            popupMenu.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    }


