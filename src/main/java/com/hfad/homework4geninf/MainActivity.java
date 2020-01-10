package com.hfad.homework4geninf;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
            setSupportActionBar(toolbar);

            //Attach the SectionsPagerAdapter to the ViewPager
            SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
            pager.setAdapter(pagerAdapter);


        pager.setCurrentItem(getIntent().getIntExtra("Section",0));
            //Attach the ViewPager to the TabLayout
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(pager);
            firebaseAuth = FirebaseAuth.getInstance();

        }




    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_main, menu);

            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_new_message:
//Code to run when the Create Order item is clicked
                    Intent intent = new Intent(this, CreateActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }



    private class SectionsPagerAdapter extends FragmentPagerAdapter {
            public SectionsPagerAdapter(FragmentManager fm) {
                super(fm);
            }
            @Override
            public int getCount() {
                return 2;
            }
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:

                        return new TopFragment();
                    case 1:

                        return new BlogFragment();
                }
                return null;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:

                        return getResources().getText(R.string.home_tab);
                    case 1:

                        return getResources().getText(R.string.blog_tab);

                }
                return null;
            }
        }

    }
