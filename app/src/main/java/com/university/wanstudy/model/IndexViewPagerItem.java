package com.university.wanstudy.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dkk on 2016/4/20.
 */
public class IndexViewPagerItem implements Parcelable {
    private int id;
    private String logoUrl;
    
    protected IndexViewPagerItem(Parcel in) {
        logoUrl = in.readString();
    }

    public static final Creator<IndexViewPagerItem> CREATOR = new Creator<IndexViewPagerItem>() {
        @Override
        public IndexViewPagerItem createFromParcel(Parcel in) {
            return new IndexViewPagerItem(in);
        }

        @Override
        public IndexViewPagerItem[] newArray(int size) {
            return new IndexViewPagerItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(logoUrl);
    }
}
