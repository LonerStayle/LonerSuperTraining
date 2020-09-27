package com.example.supertraining.view.dest

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supertraining.R
import com.example.supertraining.component.providers.ContentProviderTest
import com.example.supertraining.component.providers.samaple.Cheese
import com.example.supertraining.databinding.FragmentContentProviderTestBinding
import com.example.supertraining.utill.tedPermissionCheck
import com.example.supertraining.view.adapter.CheeseAdapter
import com.example.supertraining.view.base.BaseFragment


class ContentProviderTestFragment :
    BaseFragment<FragmentContentProviderTestBinding>(R.layout.fragment_content_provider_test) {

    companion object {
        const val TAG = "MainActivity"
        const val LOADER_CHEESES = 1
    }

    private var cheeseAdapter = CheeseAdapter()

    override fun FragmentContentProviderTestBinding.setDataBind() {

        populateInitialDataIfNeeded()
        setLoaderManager()
        setRecyclerViewAdapter()

    }

    override fun FragmentContentProviderTestBinding.setClickListener() {
        setButtonAddItem()
        setButtonUpdateItem()
        setButtonRemoveItem()
        setButtonContentResolverStartClickListener()
    }

        private fun setLoaderManager() {
        LoaderManager.getInstance(requireActivity())
            .initLoader(LOADER_CHEESES, null, loaderCallbacks)


    }

    private fun FragmentContentProviderTestBinding.setRecyclerViewAdapter() {
        list.layoutManager = LinearLayoutManager(list.context)
        list.adapter = cheeseAdapter
    }


    private fun FragmentContentProviderTestBinding.setButtonContentResolverStartClickListener() {
        buttonContentResolverStart.setOnClickListener {
            tedPermissionCheck(requireContext(), "연락처 접근 권한을 허용해주세요") {
                contentResolverUse()
            }
        }
    }

    //컨텐츠 리졸버
    private fun contentResolverUse() {

        val dataList = ArrayList<Map<String, String>>()

        val cursor = requireActivity().contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " asc"
        )

        while (cursor!!.moveToNext()) {
            val map = HashMap<String, String>()
            // 연락처 id 값
            val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
            // 연락처 대표 이름
            val name =
                cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))
            map["name"] = name

            // ID로 전화 정보 조회
            val phoneCursor: Cursor? = requireActivity().contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                null, null
            )
            if (phoneCursor!!.moveToFirst()) {
                val number = phoneCursor.getString(
                    phoneCursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                )
                map["phone"] = number
            }
            phoneCursor.close()
            dataList.add(map)
        } // end while
        cursor.close()
        val adapter = SimpleAdapter(
            requireContext(),
            dataList,
            android.R.layout.simple_list_item_2,
            arrayOf("name", "phone"),
            intArrayOf(android.R.id.text1, android.R.id.text2)
        )
        binding.listview.adapter = adapter

    }
     private val loaderCallbacks =  object : LoaderManager.LoaderCallbacks<Cursor> {

         override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
             return CursorLoader(
                 requireContext(),
                 ContentProviderTest.URI_CHEESE,
                 arrayOf(Cheese.COLUMN_NAME),
                 null,
                 null,
                 Cheese.COLUMN_NAME
             )
         }

         override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
             cheeseAdapter.setCheeses(data)
         }

         override fun onLoaderReset(loader: Loader<Cursor>) {
             cheeseAdapter.setCheeses(null)
         }

     }

    @SuppressLint("Recycle")
    private fun populateInitialDataIfNeeded() {
        val cursor = requireActivity().contentResolver.query(
            ContentProviderTest.URI_CHEESE,
            null, null, null, null
        )
        if (cursor != null && cursor.count == 0) {
            Log.d(TAG, "Add initial data")
            for (i in Cheese.CHEESES) {
                val values = ContentValues()
                values.put(Cheese.COLUMN_NAME, i)
                requireActivity().contentResolver.insert(ContentProviderTest.URI_CHEESE, values)
            }
        }
    }

    private fun setButtonAddItem() {
        binding.buttonAdd.setOnClickListener {
            val values = ContentValues()
            values.put(Cheese.COLUMN_NAME, "New Item")
            val uri =
                requireActivity().contentResolver.insert(ContentProviderTest.URI_CHEESE, values)
            Log.d(TAG, "Added item:$uri")
        }
    }

    private fun setButtonUpdateItem() {
        binding.buttonUpdate.setOnClickListener {
            val uri = queryAndGetOne()
            if (uri != null) {
                val values = ContentValues()
                values.put(Cheese.COLUMN_NAME, "Updated Item")
                requireActivity().contentResolver.update(uri, values, null, null)
            }
        }
    }


    private fun setButtonRemoveItem() {
        binding.buttonRemove.setOnClickListener {
            val uri = queryAndGetOne()
            if (uri != null) {
                requireActivity().contentResolver.delete(
                    uri, null, null
                )
            }
        }
    }


    private fun queryAndGetOne(): Uri? {
        val cursor = requireActivity().contentResolver.query(
            ContentProviderTest.URI_CHEESE,
            null, null, null, Cheese.COLUMN_NAME
        )
        return if (cursor != null && cursor.count != 0) {
            cursor.moveToFirst()
            val id = cursor.getString(cursor.getColumnIndex(Cheese.COLUMN_ID))
            val uri = ContentUris.withAppendedId(ContentProviderTest.URI_CHEESE, id.toLong())
            uri
        } else
            null

    }

}