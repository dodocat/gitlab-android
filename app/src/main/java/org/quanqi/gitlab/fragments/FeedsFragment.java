package org.quanqi.gitlab.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.quanqi.gitlab.R;
import org.quanqi.gitlab.feeds.ActivityEntry;
import org.quanqi.gitlab.feeds.AndroidSaxFeedParser;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_feeds)
public class FeedsFragment extends BaseFragment {

    @ViewById(R.id.listView)
    protected AbsListView feedListView;
    private FeedAdapter adapter;

    public FeedsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new FeedAdapter();
        loadFeeds();

    }

    /*"https://gitlab.com/dashboard.atom?private_token=Ngz1rqMMSsomp4yttRm3"*/
    public static final String FEED_SOURCE = "http://192.168.16.115/dashboard.atom?private_token=dVRAnZpSmoqdUKSeA89m";
    @Background
    protected void loadFeeds() {
        AndroidSaxFeedParser parser = new AndroidSaxFeedParser(FEED_SOURCE);

        List<ActivityEntry> entries = parser.parse();
        for (ActivityEntry entry : entries) {
            Log.i("AndroidSaxFeedParserTest", "entry: " + entry.toString());
            refreshUi(entries);
        }
    }


    @UiThread
    void refreshUi(List<ActivityEntry> activityEntries) {
        feedListView.setAdapter(adapter);
        adapter.activityEntries = activityEntries;
        adapter.notifyDataSetChanged();
    }


    class FeedAdapter extends BaseAdapter {

        private List<ActivityEntry> activityEntries;

        @Override
        public int getCount() {
            return activityEntries == null ? 0 : activityEntries.size();
        }

        @Override
        public Object getItem(int i) {
            return activityEntries.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View item;
            if (view == null) {
                item = View.inflate(viewGroup.getContext(), R.layout.item_activity, null);
            } else {
                item = view;
            }
            TextView eventTextView = (TextView) item.findViewById(R.id.event);
            TextView timeTextView = (TextView) item.findViewById(R.id.time);
            ImageView avatarImageView = (ImageView) item.findViewById(R.id.imageView);

            Picasso.with(viewGroup.getContext()).setIndicatorsEnabled(true);  //only for debug tests
            Picasso.with(viewGroup.getContext())
                    .load(activityEntries.get(i).getThumbnail())
                    .error(R.drawable.ic_error_loadingsmall)
                    .into((avatarImageView));

            eventTextView.setText(activityEntries.get(i).getTitle());
            timeTextView.setText(SimpleDateFormat.getInstance().format(activityEntries.get(i).getUpdated()));
            return item;
        }
    }

}
