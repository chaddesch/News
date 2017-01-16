package com.example.chad.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import static android.R.id.list;

/**
 * Created by Chad on 1/14/2017.
 */

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(Context context, List<Article> articles){
        super(context, 0, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article_list_item, parent, false);
        }

        Article currentArticle = getItem(position);

        String webTitle = currentArticle.getWebTitle();

        TextView webTitleView = (TextView) listItemView.findViewById(R.id.web_title);

        webTitleView.setText(webTitle);

        String sectionName = currentArticle.getSectionName();

        TextView sectionNameView = (TextView) listItemView.findViewById(R.id.section_name);

        sectionNameView.setText(sectionName);

        String pubDate = currentArticle.getWebPubDate();

        TextView pubDateView = (TextView) listItemView.findViewById(R.id.pub_date);

        pubDateView.setText(pubDate);

        return listItemView;
    }
}
