package org.example.android.flickrbrowser;

/**
 * Created by darren on 12/01/15.
 */
public class Movie {

    private String mCpId;
    private String mTitle;
    private String mBackdropOriginal;
    private String mTagline;
    private String mPlot;
    private String mImdb;
    private String mPosterStr = null;
    private Integer mYear;
    private String mRuntime;
    private String mStatus;
    // To be implemented below here ..
//    private String mPosterOriginal;
//    private String mQuality;

    public Movie(String cpId, String title, String backdropOriginal, String tagLine, String plot,
                 String imdb, String posterStr, Integer year, String runTime, String status) {

        this.mCpId = cpId;
        this.mTitle = title;
        this.mBackdropOriginal = backdropOriginal;
        this.mTagline = tagLine;
        this.mPlot = plot;
        this.mImdb = imdb;
        this.mPosterStr = posterStr;
        this.mYear = year;
        this.mRuntime = runTime;
        this.mStatus = status;
//        this.mPosterOriginal = posterOriginal;
//        this.mQuality = quality;

    }

//    public String getmQuality() {
//        return mQuality;
//    }

    public String getmCpId() {
        return mCpId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmBackdropOriginal() {
        return mBackdropOriginal;
    }

    public String getmTagline() {
        return mTagline;
    }

    public String getmPlot() {
        return mPlot;
    }

    public String getmImdb() {
        return mImdb;
    }

    public String getmPosterStr() {
        return mPosterStr;
    }

    public Integer getmYear() {
        return mYear;
    }

    public String getmRuntime() {
        return mRuntime;
    }

    public String getmStatus() {
        return mStatus;
    }

//    public String getmPosterOriginal() {
//        return mPosterOriginal;
//    }

    @Override
    public String toString() {
        return "Movie{" +
                "mCpId='" + mCpId + '\'' +
                ", mTitle='" + mTitle + '\'' +
                ", mBackdropOriginal='" + mBackdropOriginal + '\'' +
                ", mTagline='" + mTagline + '\'' +
                ", mPlot='" + mPlot + '\'' +
                ", mImdb='" + mImdb + '\'' +
                ", mPosterStr='" + mPosterStr + '\'' +
                ", mYear=" + mYear +
                ", mRuntime='" + mRuntime + '\'' +
                ", mStatus='" + mStatus + '\'' +
//                ", mPosterOriginal='" + mPosterOriginal + '\'' +
//                ", mQuality='" + mQuality + '\'' +
                '}';
    }
}
