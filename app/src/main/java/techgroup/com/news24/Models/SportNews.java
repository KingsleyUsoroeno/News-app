package techgroup.com.news24.Models;

import android.arch.persistence.room.Entity;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "sport_news")
public class SportNews  extends News implements Parcelable {

    public SportNews(String author, String title, String description, String urlToImage,String url) {
        super(author,title,description,urlToImage,url);


    }

    protected SportNews(Parcel in) {
        super(in);
    }

    public static final Creator<SportNews> CREATOR = new Creator<SportNews>() {
        @Override
        public SportNews createFromParcel(Parcel in) {
            return new SportNews(in);
        }

        @Override
        public SportNews[] newArray(int size) {
            return new SportNews[size];
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
