package com.example.chad.news;

import java.net.URL;
import java.util.Date;

/**
 * Created by Chad on 1/14/2017.
 */

public class Article {

    private String mWebTitle;

    private String mSectionName;

    private String mWebPubDate;

    private URL mWebUrl;

    public Article(String webTitle, String sectionName, String webPubDate, URL webUrl){
        mWebTitle = webTitle;
        mSectionName = sectionName;
        mWebPubDate = webPubDate;
        mWebUrl = webUrl;
    }

    public String getWebTitle(){
        return mWebTitle;
    }

    public String getSectionName(){
        return mSectionName;
    }

    public String getWebPubDate(){
        return mWebPubDate;
    }

    public URL getWebUrl(){
        return mWebUrl;
    }
}
