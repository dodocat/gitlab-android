package org.quanqi.gitlab.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.quanqi.gitlab.R;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardThumbnail;

/**
 * Created by cindy on 9/7/14.
 */
public class FeedCard extends Card {

    protected String mTitle;
    protected String mSecondaryTitle;
    protected int count;

    private String thumbnail;

    public FeedCard(Context context) {
        this(context, R.layout.carddemo_extra_picasso_inner_content);
    }

    public FeedCard(Context context, int innerLayout) {
        super(context, innerLayout);
        init();
    }

    private void init() {

        //Add thumbnail
        PicassoCardThumbnail cardThumbnail = new PicassoCardThumbnail(mContext);
        //It must be set to use a external library!
        cardThumbnail.setExternalUsage(true);
        addCardThumbnail(cardThumbnail);

        //Add ClickListener
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                String cardTitle = mId != null ? mId : mTitle;
                Toast.makeText(getContext(), "Click Listener card=" + cardTitle, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {


        //Retrieve elements
        TextView title = (TextView) parent.findViewById(R.id.carddemo_extra_picasso_main_inner_title);
        TextView secondaryTitle = (TextView) parent.findViewById(R.id.carddemo_extra_picasso_main_inner_secondaryTitle);

        if (title != null)
            title.setText(mTitle);

        if (secondaryTitle != null)
            secondaryTitle.setText(mSecondaryTitle);

    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * CardThumbnail which uses Picasso Library.
     * If you use an external library you have to provide your login inside #setupInnerViewElements.
     * <p/>
     * This method is called before built-in method.
     * If {@link it.gmariotti.cardslib.library.internal.CardThumbnail#isExternalUsage()} is false it uses the built-in method.
     */
    class PicassoCardThumbnail extends CardThumbnail {

        public PicassoCardThumbnail(Context context) {
            super(context);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View viewImage) {

            Picasso.with(getContext()).setIndicatorsEnabled(true);  //only for debug tests
            Picasso.with(getContext())
                    .load(thumbnail)
                    .error(R.drawable.ic_error_loadingsmall)
                    .into((ImageView) viewImage);

        }
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSecondaryTitle() {
        return mSecondaryTitle;
    }

    public void setSecondaryTitle(String secondaryTitle) {
        mSecondaryTitle = secondaryTitle;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
