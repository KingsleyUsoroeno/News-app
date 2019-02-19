package techgroup.com.news24.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "general_news", indices = @Index(value = {"title"}, unique = true))
public class GeneralNews extends News implements Parcelable {

    public GeneralNews(String author, String title, String description, String urlToImage,String url) {
        super(author, title, description, urlToImage,url);

    }

    protected GeneralNews(Parcel in) {
        super(in);
    }

    public static final Creator<GeneralNews> CREATOR = new Creator<GeneralNews>() {
        @Override
        public GeneralNews createFromParcel(Parcel in) {
            return new GeneralNews(in);
        }

        @Override
        public GeneralNews[] newArray(int size) {
            return new GeneralNews[size];
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
