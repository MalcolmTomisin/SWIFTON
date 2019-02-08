package com.swifton.swifton.Database;

public final class SwiftUserContractClass {
    private SwiftUserContractClass() {
    }

    public static final class SwiftUserDataEntry{
        public static final String TABLE_NAME = "user_info";
        public static final String COLUMN_NAME = "user_name";
        public static final String COLUMN_MAIL = "user_mail";
        public static final String COLUMN_STATE = "user_state";
        public static final String COLUMN_FULL_NAME = "user_full_name";
        public static final String COLUMN_PHONE = "user_phone";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_ID = "_id";


        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE" + TABLE_NAME + "(" +  COLUMN_NAME + "TEXT NOT NULL," + COLUMN_MAIL
                + "TEXT UNIQUE NOT NULL," + COLUMN_STATE + "TEXT ," + COLUMN_FULL_NAME + " TEXT ," +
                        COLUMN_PHONE + "NUMERIC ," + COLUMN_USER_ID + "TEXT UNIQUE NOT NULL,"
                        + COLUMN_ID + "INTEGER PRIMARY KEY)";
    }
}
