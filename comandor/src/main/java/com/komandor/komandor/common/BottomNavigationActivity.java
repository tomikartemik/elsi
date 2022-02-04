package com.komandor.komandor.common;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.komandor.komandor.R;
import com.komandor.komandor.ui.main.chats.ChatsFragment;
import com.komandor.komandor.ui.main.contacts.ContactsFragment;
import com.komandor.komandor.ui.main.profile.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public abstract class BottomNavigationActivity extends CommonActivity {
  private static final String IDS = "IDS";
  private static final String CURRENT = "CURRENT";
  private static final String TAGS = "TAGS";

  private int startIndex = 0;
  private int currentFragmentIndex = 0;

  private FragmentManager fm = getSupportFragmentManager();

  private ArrayList<Integer> menuItemsIDs = new ArrayList<>();
  private List<Fragment> fragments = new ArrayList<>();
  private ArrayList<String> tags = new ArrayList<>();

  protected BottomNavigationView navigationView;


  
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    currentFragmentIndex = startIndex;
    navigationView = findViewById(getNavBarViewID());
    navigationView.setOnNavigationItemSelectedListener((menuItem) -> {
      int index = menuItemsIDs.indexOf(menuItem.getItemId());
      
      if (index == -1) {
        return false;
      }
      
      return loadFragment(index, menuItem);
    });
  }
  
  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    
    outState.putIntegerArrayList(IDS, menuItemsIDs);
    outState.putInt(CURRENT, currentFragmentIndex);
    outState.putStringArrayList(TAGS, tags);
  }
  
  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
  
    currentFragmentIndex = savedInstanceState.getInt(CURRENT);
    menuItemsIDs = savedInstanceState.getIntegerArrayList(IDS);
    tags = savedInstanceState.getStringArrayList(TAGS);
    
    for (String tag : tags) {
      fragments.add(fm.findFragmentByTag(tag));
    }
    
    navigationView.setSelectedItemId(menuItemsIDs.get(currentFragmentIndex));
  }
  
  private boolean loadFragment(int fragmentIndex, MenuItem item) {
    invalidateOptionsMenu();
    
    boolean isSameFragment = currentFragmentIndex == fragmentIndex;
    
    if(!isSameFragment) {
      fm.beginTransaction()
          .hide(getCurrentFragment())
          .show(fragments.get(fragmentIndex))
          .commit();
  
      currentFragmentIndex = fragmentIndex;
    }
  
    onNavigationItemSelect(item);
    
    return !isSameFragment;
  }
  
  public void addFragmentToNavigation(int menuItemID, Fragment fragment) {
    String tag = fragment
            .getClass()
            .getName();
    menuItemsIDs
            .add(menuItemID);
    fragments
            .add(fragment);
    tags
            .add(tag);
    
    FragmentTransaction fragmentTransaction = fm.beginTransaction();
    fragmentTransaction
            .add(getFragmentsContainerID(), fragment, tag);
    
    int lastIndex = fragments.size() - 1;
    if (lastIndex != startIndex) {
      fragmentTransaction
              .hide(fragment)
              .commit();
    } else {
      fragmentTransaction.commit();
      navigationView.setSelectedItemId(menuItemID);
    }
  }
  
  protected abstract void onNavigationItemSelect(MenuItem item);
  
  @Override
  protected int getLayout() {
    return R.layout.ac_bottom_navigation;
  }
  
  protected int getFragmentsContainerID() {
    return R.id.fl_fragment_container;
  }
  
  protected int getNavBarViewID() {
    return R.id.nav_bar;
  }
  
  protected Fragment getCurrentFragment() {
    return fragments.get(currentFragmentIndex);
  }
  
  protected Fragment getFragmentByTag(String tag) {
    int index = tags.indexOf(tag);
    
    if(index == -1) {
      return null;
    }
    
    return fragments.get(index);
  }
  
  public void setStartFragmentIndex(int index) {
    startIndex = index;
  }
}
