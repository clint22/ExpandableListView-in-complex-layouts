package org.bestdoc.jitha.tvseriesnandmovies;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jitha on 29/7/17.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<String> mListDataHeader;
    private HashMap<String, List<MovieDetails>> mListDataChild;
    private LayoutInflater mLayoutInflater;
    private int mMovieSize;
    private int mSeriesSize;



    public ExpandableListViewAdapter(Context context, List<String> listDataHeader, HashMap<String, List<MovieDetails>> listDataChild, int movie_size, int series_size) {


        this.mContext = context;
        this.mListDataHeader = listDataHeader;
        this.mListDataChild = listDataChild;
        this.mMovieSize = movie_size;
        this.mSeriesSize = series_size;

        if (context != null) {
            mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        Log.e("child", new Gson().toJson(listDataChild));


    }

    @Override
    public int getGroupCount() {
        return mListDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.mListDataChild.get(this.mListDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mListDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.mListDataChild.get(this.mListDataHeader.get(groupPosition)).get(childPosititon);
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
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.parent_layout, parent, false);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.txt_speciality_header);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        if (groupPosition == 0) {
            lblListHeader.setText(headerTitle + " (" + mMovieSize + ")");
        } else {
            lblListHeader.setText(headerTitle + " (" + mSeriesSize + ")");
        }
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView,
                             ViewGroup parent) {

        final MovieDetails movieDetails = (MovieDetails) getChild(groupPosition, childPosition);

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.child_layout, parent, false);
        }

        TextView name = (TextView)convertView.findViewById(R.id.txt_movie_name);
        TextView id = (TextView)convertView.findViewById(R.id.txt_id);
       /* TextView name = ButterKnife.findById(convertView,R.id.txt_movie_name);
        TextView id = ButterKnife.findById(convertView,R.id.txt_id);*/

        name.setText(movieDetails.getName());
        id.setText(String.valueOf(movieDetails.getId()));



        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
