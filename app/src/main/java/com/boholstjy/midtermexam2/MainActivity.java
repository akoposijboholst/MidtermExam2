package com.boholstjy.midtermexam2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ListView;

import com.boholstjy.midtermexam2.adapter.BookAdapter;
import com.boholstjy.midtermexam2.models.Book;
import com.boholstjy.midtermexam2.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String API_URL = "http://joseniandroid.herokuapp.com/api/books";
    public static final String OB_ID = "_id";
    public static final String OB_TITLE = "title";
    public static final String OB_GENRE = "genre";
    public static final String OB_AUTHOR = "author";
    public static final String OB_ISREAD = "isRead";

    private ArrayList<Book> books;
    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        books = new ArrayList<>();
        mListView = (ListView) findViewById(R.id.listView);
        new GetBooks().execute();
    }

    private class GetBooks extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            String json = HttpUtils.getResponse(API_URL, "GET");
            if (TextUtils.isEmpty(json)) {
                return null;
            }
            try {
                JSONArray jsonArray = new JSONArray(json);

                for (int x = 0; x < jsonArray.length(); x++) {
                    JSONObject b = jsonArray.getJSONObject(x);
                    String id = b.getString(OB_ID);
                    String title = b.getString(OB_TITLE);
                    String genre = b.getString(OB_GENRE);
                    String author = b.getString(OB_AUTHOR);
                    Boolean isRead = b.getBoolean(OB_ISREAD);

                    books.add(new Book(id, title, genre, author, isRead));
                }

            } catch (JSONException ex) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            BookAdapter adapter = new BookAdapter(getApplicationContext(), R.layout.book_item, books);
            mListView.setAdapter(adapter);
        }
    }
}
