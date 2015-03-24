package seanmkelley.callout;

import android.provider.BaseColumns;

/**
 * Created by Austin on 3/24/2015.
 */
//from http://developer.android.com/training/basics/data-storage/databases.html
    //hoping this will work
public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static abstract class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
        //...
    }
}