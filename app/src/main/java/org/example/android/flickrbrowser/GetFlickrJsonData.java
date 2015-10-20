package org.example.android.flickrbrowser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by keanehubertang on 12/5/14.
 */
public class GetFlickrJsonData extends GetRawData {

    private String LOG_TAG = GetFlickrJsonData.class.getSimpleName();
    private List<org.example.android.flickrbrowser.Movie> mPhotos;
    private URL mDestinationUri;

    public GetFlickrJsonData(String apiCall, String statusParameter) {
        super(null);
        createAndUpdateUri(apiCall, statusParameter);
        mPhotos = new ArrayList<org.example.android.flickrbrowser.Movie>();
    }

    public void execute() {
        super.setmRawUrl(mDestinationUri.toString());
        DownloadJsonData downloadJsonData = new DownloadJsonData();
        Log.v(LOG_TAG, "Built URI = " + mDestinationUri.toString());
        downloadJsonData.execute(mDestinationUri.toString());
    }

    public boolean createAndUpdateUri(String apiCall, String statusParameter) {

        String wantedJsonStr = null;
        String apiKey = "4d81f4f4eb211c427e0d5c9d51c7198b";
        String hostAddress = "bintonet.duckdns.org";
        String serverPort = "5000";

        try {
            mDestinationUri = new URL(
                    "http://"+hostAddress+":"+serverPort+"/api/"+apiKey+"/"+apiCall+"?status="+statusParameter);

            Log.v(LOG_TAG, "Built URI " + mDestinationUri.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return mDestinationUri != null;
    }

    public List<org.example.android.flickrbrowser.Movie> getMPhotos() {
        return mPhotos;
    }

    public void processResult() {

        if(getmDownloadStatus() != DownloadStatus.OK) {
            Log.e(LOG_TAG, "Error downloading raw file");
            return;
        }

        final String OWM__ID = "_id";
        final String OWM_MOVIES = "movies";
        final String OWM_TITLE = "title";
        final String OWM_INFO = "info";
        final String OWM_YEAR = "year";
        final String OWM_IMAGES = "images";
        final String OWM_POSTER_ORIGINAL = "poster_original";

        final String OWM_PLOT = "plot";
        final String OWM_TAGLINE = "tagline";
        final String OWM_IMDB = "imdb";
        final String OWM_STATUS = "status";
        final String OWM_RUNTIME = "runtime";
        final String OWM_GENRES = "genres";

        // to be added below here

        final String OWM_QUALITY = "profile_id";
        final String OWM_BACKDROP_ORIGINAL = "backdrop_original";

        try {

            JSONObject wantedJson = new JSONObject(getmData());
            JSONArray wantedMoviesArray = wantedJson.getJSONArray(OWM_MOVIES);

            String[] resultStr = new String[wantedMoviesArray.length()];

            for(int i = 0; i < wantedMoviesArray.length(); i++) {

                String cpId;
                String title;
                String backdropOriginal;
                String tagLine;
                String plot;
                String imdb;
                String posterStr =null;
                Integer year;
                String runTime;
                String status;
                // To be implemented below here ..
                String posterOriginal;
                String quality;

                //Get the JSON object array representing the movie
                JSONObject movieObject = wantedMoviesArray.getJSONObject(i);
                title = movieObject.getString(OWM_TITLE);
                cpId = movieObject.getString(OWM__ID);
                status = movieObject.getString(OWM_STATUS);
                quality = movieObject.getString(OWM_QUALITY);

                //Get the JSON object for movie info
                JSONObject movieInfoObject = movieObject.getJSONObject(OWM_INFO);
                year = movieInfoObject.getInt(OWM_YEAR);
                tagLine = movieInfoObject.getString(OWM_TAGLINE);
                plot = movieInfoObject.getString(OWM_PLOT);
                imdb = movieInfoObject.getString(OWM_IMDB);
                runTime = movieInfoObject.getString(OWM_RUNTIME);


                //Get the JSON object for Images
                JSONObject movieImagesObject = movieInfoObject.getJSONObject(OWM_IMAGES);
                JSONArray posterArray = movieImagesObject.getJSONArray(OWM_POSTER_ORIGINAL);
                for(int n = 0 ; n < posterArray.length(); n++){
                    posterStr = posterArray.getString(n);
                }
                backdropOriginal = movieImagesObject.getString(OWM_BACKDROP_ORIGINAL);

                JSONArray genresArray = movieInfoObject.getJSONArray(OWM_GENRES);
                ArrayList<String> genreStringArray = new ArrayList<String>();
                for(int g = 0, count = genresArray.length(); g< count; g++)
                {
                    try {
                        genreStringArray.add(genresArray.getString(g));
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

//                Photo photoObject = new Photo(title, author, authorId, link, tags, photoUrl);

                org.example.android.flickrbrowser.Movie movieObj = new org.example.android.flickrbrowser.Movie(
                        cpId, title, backdropOriginal, tagLine, plot,
                        imdb, posterStr, year, runTime, status);

                this.mPhotos.add(movieObj);
            }

            for(org.example.android.flickrbrowser.Movie singlePhoto: mPhotos) {
                Log.v(LOG_TAG, singlePhoto.toString());
            }

        } catch(JSONException jsone) {
            jsone.printStackTrace();
            Log.e(LOG_TAG,"Error processing Json data");
        }

    }

    public class DownloadJsonData extends DownloadRawData {

        protected void onPostExecute(String webData) {
            super.onPostExecute(webData);
            processResult();

        }

        protected String doInBackground(String... params) {
            String[] par = { mDestinationUri.toString() };
            return super.doInBackground(par);
        }

    }

}
