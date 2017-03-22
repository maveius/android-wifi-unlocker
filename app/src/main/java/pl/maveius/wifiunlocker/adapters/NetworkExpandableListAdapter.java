package pl.maveius.wifiunlocker.adapters;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import pl.maveius.wifiunlocker.R;
import pl.maveius.wifiunlocker.model.Network;


public class NetworkExpandableListAdapter extends BaseExpandableListAdapter {

    private final SparseArray<Network> networks;
    private LayoutInflater inflater;
    private Activity activity;

    public NetworkExpandableListAdapter(Activity activity, SparseArray<Network> networks) {
        this.networks = networks;
        this.activity = activity;
        this.inflater = activity.getLayoutInflater();
    }

    @Override
    public Object getChild(int networkPosition, int detailPosition) {
        Network network = networks.get(networkPosition);
        return network.details.get(detailPosition);
    }

    @Override
    public long getChildId(int networkPosition, int detailsPosition) {
        return 0;
    }

    @Override
    public View getChildView(
            int networkPosition,
            final int detailsPosition,
            boolean isLastDetails,
            View convertView,
            ViewGroup parent) {

        final String details = (String) getChild(networkPosition, detailsPosition);

        TextView text = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_details, null);
        }

        text = (TextView) convertView.findViewById(R.id.detailsLabel);
        text.setText(details);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, details,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    @Override
    public int getChildrenCount(int networkPosition) {
        return networks.get(networkPosition).details.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return networks.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return networks.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrow_group, null);
        }

        Network network = (Network) getGroup(groupPosition);
        ((CheckedTextView) convertView).setText(network.name);
        ((CheckedTextView) convertView).setChecked(isExpanded);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}