package com.soltrux.app.demo.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.soltrux.app.demo.entidades.clsDrawerItem;
import com.soltrux.app.demo.fragment.FragmentCodigo;
import com.soltrux.app.demo.fragment.FragmentListaContactos;
import com.soltrux.app.demo.fragment.FragmentListaPersonas;
import com.soltrux.app.demo.fragment.FragmentMapa;
import com.soltrux.app.demo.fragment.FragmentOne;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
        private FragmentManager frgManager;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
        private int posicion=0;
	CustomDrawerAdapter adapter;

	List<clsDrawerItem> dataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
                
                frgManager = getSupportFragmentManager();
		// Initializing
		dataList = new ArrayList<clsDrawerItem>();
		mTitle = mDrawerTitle = getTitle();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		// Add Drawer Item to dataList
                
                
		dataList.add(new clsDrawerItem("Message", R.drawable.ic_action_email));
		dataList.add(new clsDrawerItem(getString(R.string.str_mapa), R.drawable.ic_action_good));
		dataList.add(new clsDrawerItem(getString(R.string.str_crud_sqlite), R.drawable.ic_action_gamepad));
		dataList.add(new clsDrawerItem(getString(R.string.str_gernerar_codigo), R.drawable.ic_action_labels));                
		dataList.add(new clsDrawerItem(getString(R.string.str_contactos), R.drawable.ic_action_cloud));
                dataList.add(new clsDrawerItem(getString(R.string.str_agregar_pregunta), R.drawable.ic_action_search));                
		
		dataList.add(new clsDrawerItem(getString(R.string.str_servicio), R.drawable.ic_action_camera));
		dataList.add(new clsDrawerItem(getString(R.string.str_salir), R.drawable.ic_action_video));
		
		adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
				dataList);

		mDrawerList.setAdapter(adapter);

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);
                

		if (savedInstanceState == null) {
			SelectItem();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
         @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_settings:  
                Toast.makeText(MainActivity.this, "Seleccione Otro", Toast.LENGTH_SHORT).show();
            return true;
   
        default:
            return mDrawerToggle.onOptionsItemSelected(item);
        }
    }
	public void SelectItem() {

		Fragment fragment = null;
		Bundle args = new Bundle();
		switch (posicion) {
		case 0:
                        posicion=0;
			fragment = new FragmentOne();
			args.putString(FragmentOne.ITEM_NAME, dataList.get(posicion)
					.getItemName());
			args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(posicion)
					.getImgResID());
			break;
		case 1:
                       fragment = new FragmentMapa();
			break;
		case 2:
                    
			fragment = new FragmentListaPersonas();
			break;
		case 3:
			fragment = new FragmentCodigo();
			break;
		case 4:
			fragment = new FragmentListaContactos();
			break;
		case 5:
			fragment = new FragmentOne();
			args.putString(FragmentOne.ITEM_NAME, dataList.get(posicion)
					.getItemName());
			args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(posicion)
					.getImgResID());
			break;
		case 6:
			fragment = new FragmentOne();
			args.putString(FragmentOne.ITEM_NAME, dataList.get(posicion)
					.getItemName());
			args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(posicion)
					.getImgResID());
			break;
	
	
	



		default:
			break;
		}                
                
                fragment.setArguments(args);		
                FragmentTransaction fragmentTransaction = frgManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, fragment);
                fragmentTransaction.commit();        

                mDrawerList.setItemChecked(posicion, true);
                setTitle(dataList.get(posicion).getItemName());
                mDrawerLayout.closeDrawer(mDrawerList);

	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                    if(posicion!=position)
                    {
                        posicion=position;
			SelectItem();
                    }
                    else
                        Toast.makeText(MainActivity.this, "Seleccione Otro", Toast.LENGTH_SHORT).show();

		}
	}

        public class CustomDrawerAdapter extends ArrayAdapter<clsDrawerItem> {

	Context context;
	List<clsDrawerItem> drawerItemList;
	int layoutResID;

	public CustomDrawerAdapter(Context context, int layoutResourceID,
			List<clsDrawerItem> listItems) {
		super(context, layoutResourceID, listItems);
		this.context = context;
		this.drawerItemList = listItems;
		this.layoutResID = layoutResourceID;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		DrawerItemHolder drawerHolder;
		View view = convertView;

		if (view == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			drawerHolder = new DrawerItemHolder();

			view = inflater.inflate(layoutResID, parent, false);
			drawerHolder.ItemName = (TextView) view
					.findViewById(R.id.drawer_itemName);
			drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);

			view.setTag(drawerHolder);

		} else {
			drawerHolder = (DrawerItemHolder) view.getTag();

		}

		clsDrawerItem dItem = (clsDrawerItem) this.drawerItemList.get(position);

		drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(
				dItem.getImgResID()));
		drawerHolder.ItemName.setText(dItem.getItemName());

		return view;
	}

	private class DrawerItemHolder {
		TextView ItemName;
		ImageView icon;
	}
    }
}
