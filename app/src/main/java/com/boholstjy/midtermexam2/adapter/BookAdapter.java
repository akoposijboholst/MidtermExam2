package com.boholstjy.midtermexam2.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.boholstjy.midtermexam2.R;
import com.boholstjy.midtermexam2.models.Book;

import java.util.List;

/**
 * Created by Eugene Boholst on 2/23/2016.
 */
public class BookAdapter extends ArrayAdapter<Book> {

    private Context mContext;
    private int mLayoutId;
    private List<Book> books;

    public BookAdapter(Context context, int resource, List<Book> books) {
        super(context, resource, books);
        mContext = context;
        mLayoutId = resource;
        this.books = books;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BookHolder bookHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
            bookHolder = new BookHolder();
            bookHolder.tvTitle = (TextView) convertView.findViewById(R.id.book_item_textview);
            convertView.setTag(bookHolder);
        } else {
            bookHolder = (BookHolder) convertView.getTag();
        }

        Book book = books.get(position);

        if (book != null) {
            if (book.isRead()) {
                bookHolder.tvTitle.setTextColor(Color.parseColor("#B0171F"));
            }
            bookHolder.tvTitle.setText(book.getTitle());
        }

        return convertView;
    }

    public static class BookHolder {
        TextView tvTitle;
    }
}
