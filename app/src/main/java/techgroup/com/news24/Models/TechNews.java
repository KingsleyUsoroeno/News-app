package techgroup.com.news24.Models;

import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "tech_news")
public class TechNews extends News implements Parcelable {


    public TechNews(String author, String title, String description, String urlToImage,String url) {
        super(author, title, description, urlToImage,url);
    }

    protected TechNews(Parcel in) {
        super(in);
    }

    public static final Creator<TechNews> CREATOR = new Creator<TechNews>() {
        @Override
        public TechNews createFromParcel(Parcel in) {
            return new TechNews(in);
        }

        @Override
        public TechNews[] newArray(int size) {
            return new TechNews[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(urlToImage);
        dest.writeString(url);
    }
}
