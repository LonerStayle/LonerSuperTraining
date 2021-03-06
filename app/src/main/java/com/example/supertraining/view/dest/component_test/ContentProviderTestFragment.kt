package com.example.supertraining.view.dest.component_test

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract
import android.provider.Telephony.Mms.Part.CONTENT_ID
import android.util.Log
import android.view.View
import android.widget.SimpleAdapter
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.supertraining.R
import com.example.supertraining.providers.ContentProviderTest
import com.example.supertraining.providers.samaple.Cheese
import com.example.supertraining.databinding.FragmentContentProviderTestBinding
import com.example.supertraining.view.adapter.RecyclerViewCheeseAdapter
import com.example.supertraining.view.base.BaseFragment
import com.example.supertraining.view.utill.tedPermissionCheck
import com.example.supertraining.view.utill.toastShortShow
import java.io.BufferedWriter
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class ContentProviderTestFragment :
    BaseFragment<FragmentContentProviderTestBinding>(R.layout.fragment_content_provider_test) {

    companion object {
        const val TAG = "MainActivity"
        const val LOADER_CHEESES = 1
    }

    private var cheeseAdapter = RecyclerViewCheeseAdapter()

    override fun FragmentContentProviderTestBinding.setDataBind() {
        contentProviderTest = this@ContentProviderTestFragment
        populateInitialDataIfNeeded()
        setLoaderManager()
        setRecyclerViewAdapter()
    }

    override fun FragmentContentProviderTestBinding.setClickListener() {}

    private fun setLoaderManager() {
        LoaderManager.getInstance(requireActivity())
            .initLoader(LOADER_CHEESES, null, loaderCallbacks)


    }

    private fun FragmentContentProviderTestBinding.setRecyclerViewAdapter() {
        list.layoutManager = LinearLayoutManager(list.context)
        list.adapter = cheeseAdapter
    }


    fun setButtonTextFileCreateTestClickLister(v: View) {

        context?.tedPermissionCheck {
            val absolutePath = "/storage/emulated/0/"
//       Original filePath
            Environment.getExternalStorageDirectory().absolutePath

            //        /storage/emulated/0/Android/data/com.example.cameraappexample/files/pictures/
            val testPath = Environment.DIRECTORY_PICTURES

            //        /storage/emulated/0/Android/data/com.example.cameraappexample/files/pictures
            val testPath2 = requireContext().getExternalFilesDir(null)?.absolutePath + "/pictures/"


            val testpath3 = requireContext().getExternalFilesDir("/pictures/")

            val timeStamp = SimpleDateFormat("yyyyMMdd_hhmmss").format(Date())
            val fileName = "email.txt"
            val out = absolutePath + fileName
            val bufferedWriter = BufferedWriter(FileWriter(out))
            bufferedWriter.write("32johnblaster@gmail.com")
            bufferedWriter.close()
            requireContext().toastShortShow("email.text 파일이 생성되었는지 확인해보세요")
        }
    }


    fun setButtonContentResolverStartClickListener(v: View) {
        context?.tedPermissionCheck {
            contentResolverUse()
        }

    }

    //컨텐츠 리졸버
    private fun contentResolverUse() {

        val dataList = ArrayList<Map<String, String>>()

        //커서 생성
        val cursor = context?.contentResolver?.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + "asc"
        )

        while (cursor!!.moveToNext()) {
            val map = HashMap<String, String>()
            // 연락처 id 값
            val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))

            // 연락처 대표 이름
            val name =
                cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY))

            map["name"] = name

            //ID로 전화번호 조회
            val phoneCursor: Cursor? = context?.contentResolver?.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
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
        }
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

    private val loaderCallbacks = object : LoaderManager.LoaderCallbacks<Cursor> {

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

    fun setButtonAddItem(v: View) {

        val values = ContentValues()
        values.put(Cheese.COLUMN_NAME, "New Item")
        val uri =
            requireActivity().contentResolver.insert(ContentProviderTest.URI_CHEESE, values)
        Log.d(TAG, "Added item:$uri")

    }

    fun setButtonUpdateItem(v: View) {

        val uri = queryAndGetOne()
        if (uri != null) {
            val values = ContentValues()
            values.put(Cheese.COLUMN_NAME, "Updated Item")
            requireActivity().contentResolver.update(uri, values, null, null)
        }

    }


    fun setButtonRemoveItem(v: View) {
        val uri = queryAndGetOne()
        if (uri != null) {
            requireActivity().contentResolver.delete(
                uri, null, null
            )
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}