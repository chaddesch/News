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

    private String LOG_TAG = "ArticleAdapter";

    static class ViewHolder {
        public TextView webTitleView;
        public TextView sectionNameView;
        public TextView pubDateView;
    }

    public ArticleAdapter(Context context, List<Article> articles){
        super(context, 0, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article_list_item, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.webTitleView = (TextView) listItemView.findViewById(R.id.web_title);
            viewHolder.sectionNameView = (TextView) listItemView.findViewById(R.id.section_name);
            viewHolder.pubDateView = (TextView) listItemView.findViewById(R.id.pub_date);
            listItemView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) listItemView.getTag();
        Article article = getItem(position);

        holder.webTitleView.setText(article.getWebTitle());
        holder.sectionNameView.setText(article.getSectionName());
        holder.pubDateView.setText(article.getWebPubDate());

        return listItemView;
    }
}
