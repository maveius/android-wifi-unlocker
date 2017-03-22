package pl.maveius.wifiunlocker;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import pl.maveius.wifiunlocker.adapters.NetworkExpandableListAdapter;
import pl.maveius.wifiunlocker.io.NetworkConfigFileReader;
import pl.maveius.wifiunlocker.model.Network;
import pl.maveius.wifiunlocker.security.PermissionHelper;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView list;
    private NetworkExpandableListAdapter adapter;
    private PermissionHelper permissionHelper = new PermissionHelper();

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        permissionHelper.askPermissions(this);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);

        loadNetworkList();
    }



    private void loadNetworkList() {

        Resources resources = getResources();
        list = (ExpandableListView) findViewById(R.id.networkList);

        NetworkConfigFileReader fileReader = new NetworkConfigFileReader(resources);
        SparseArray<Network> networkData = fileReader.read();

        adapter = new NetworkExpandableListAdapter(this, networkData);

        list.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
