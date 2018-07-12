package SalonNavigationMenu;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.eodhuno.ebelle_test.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SalonExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mCtx;
    private List<String> listTitle;
    private Map<String, List<String>> listItem;

    public SalonExpandableListAdapter(Context mCtx, List<String> listTitle, Map<String, List<String>> listItem) {
        this.mCtx = mCtx;
        this.listTitle = listTitle;
        this.listItem = listItem;
    }

    @Override
    public int getGroupCount() {
        listTitle = new ArrayList<>();
        return listTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listItem.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listTitle.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listItem.get(listTitle.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String groupTitle = (String) getGroup(groupPosition);

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) this.mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.salon_drawer_group_listitem, null);
        }
        TextView txtGroupTitle = (TextView) convertView.findViewById(R.id.salonGroupListTitle);
        txtGroupTitle.setTypeface(null, Typeface.BOLD);
        txtGroupTitle.setText(groupTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childTitle = (String) getChild(groupPosition, childPosition);

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) this.mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.salon_drawer_expandable_listitem, null);

        }
        TextView txtChildTitle = (TextView) convertView.findViewById(R.id.salonExpandableListItem);
        txtChildTitle.setText(childTitle);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
