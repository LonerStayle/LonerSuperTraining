package com.example.supertraining.providers

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import com.example.supertraining.providers.samaple.Cheese
import com.example.supertraining.providers.samaple.SampleDatabase


class ContentProviderTest : ContentProvider() {

    companion object {
        const val AUTHORITY = "com.example.supertraining.providers.provider"
        val URI_CHEESE = Uri.parse(
            "content://" + AUTHORITY + "/" + Cheese.TABLE_NAME
        )
        const val CODE_CHEESE_DIR = 1
        const val CODE_CHEESE_ITEM = 2
    }

    private var uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        uriMatcher.addURI(AUTHORITY, Cheese.TABLE_NAME, CODE_CHEESE_DIR) //uri 비교 후 1이 반환됨
        uriMatcher.addURI(AUTHORITY, "${Cheese.TABLE_NAME}/#", CODE_CHEESE_ITEM) //2가 반환됨
    }



    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {

        return when (uriMatcher.match(uri)) {
            CODE_CHEESE_DIR, CODE_CHEESE_ITEM -> {
                val queryBuilder = SQLiteQueryBuilder()
                queryBuilder.tables = Cheese.TABLE_NAME

                val database = SampleDatabase.getInstance(context!!)
                val cursor = queryBuilder.query(
                    database, projection, selection, selectionArgs, null, null, sortOrder
                )
                cursor.setNotificationUri(context!!.contentResolver, uri)
                cursor
            }
            else -> throw java.lang.IllegalArgumentException("Unknown URI:$uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return when (uriMatcher.match(uri)) {
            CODE_CHEESE_DIR -> {
                val id = SampleDatabase.getInstance(context!!).insert(Cheese.TABLE_NAME, null, values)
                val insertedUri = ContentUris.withAppendedId(uri, id)
                context!!.contentResolver.notifyChange(insertedUri, null)
                insertedUri
            }
            CODE_CHEESE_ITEM ->
                throw java.lang.IllegalArgumentException("Invalid URI, cannot insert with ID:$uri")

            else -> throw java.lang.IllegalArgumentException("Unknown URI:$uri")
        }
    }


    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return when (uriMatcher.match(uri)) {
            CODE_CHEESE_DIR -> throw java.lang.IllegalArgumentException("Invalid URI,cannot update without ID$uri")
            CODE_CHEESE_ITEM -> {
                val id = ContentUris.parseId(uri)
                val count = SampleDatabase.getInstance(context!!).update(
                    Cheese.TABLE_NAME, values, "${Cheese.COLUMN_ID}=?",
                    arrayOf(id.toString())
                )

                context!!.contentResolver.notifyChange(uri, null)
                count
            }
            else -> throw java.lang.IllegalArgumentException("Unknown URI:$uri")
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return when (uriMatcher.match(uri)) {
            CODE_CHEESE_DIR -> throw java.lang.IllegalArgumentException("Invalid URI, cannot update without ID:$uri")
            CODE_CHEESE_ITEM -> {
                val id = ContentUris.parseId(uri)
                val count = SampleDatabase.getInstance(context!!).delete(
                        Cheese.TABLE_NAME,
                        "${Cheese.COLUMN_ID}=?",
                        arrayOf(id.toString())
                    )
                context!!.contentResolver.notifyChange(uri, null)
                count
            }
            else -> throw java.lang.IllegalArgumentException("Unknown URI:$uri")
        }
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            CODE_CHEESE_DIR -> "vnd.android.cursor.dir/$AUTHORITY.${Cheese.TABLE_NAME}"
            CODE_CHEESE_ITEM -> "vnd.android.cursor.item/$AUTHORITY.${Cheese.TABLE_NAME}"
            else -> throw IllegalAccessException("Unknown URI:$uri")
        }
    }


}
