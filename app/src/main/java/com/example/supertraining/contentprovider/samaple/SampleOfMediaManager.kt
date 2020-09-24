package com.example.mediastore

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

object SampleOfMediaManager {

    /**
     * This is a Sample of getting Uri from MediaStore.
     * First, searching for RELATIVE PATH or DATA from the directory
     * Second, create file and check if it exists
     * Third, Divide by Mime Type and add uri
     * @param Context is needed application Context
     * @param Directory is path want to receive uri without /storage/emulated/0/
     * @param Return data is an ArrayList value list of uri
     */

    /**
     * Esta es una muestra de cómo obtener Uri de MediaStore.
     * Primero, busque la RELATIVE PATH o los DATA del directory
     * En segundo lugar, cree un archivo y verifique si existe
     * En tercer lugar, divida por Mime Type y agregue uri
     * @param Context is needed application Context
     * @param Directory is path want to receive uri without /storage/emulated/0/
     * @param Return data is an ArrayList value list of uri
     */

    /**
     * Đây là Mẫu lấy Uri từ MediaStore.
     * Đầu tiên, tìm kiếm RELATIVE_PATH hoặc DATA từ thư mục
     * Thứ hai, tạo tệp và kiểm tra xem nó có tồn tại không
     * Thứ ba, Chia theo Mime Type và thêm uri
     * @param Context is needed application Context
     * @param Directory is path want to receive uri without /storage/emulated/0/
     * @param Return data is an ArrayList value list of uri
     */

    /**
     * MediaStore에서 Uri를 가져오는 샘플 오브젝트입니다.
     * 첫 번째, Directory 값을 통해 RELATIVE_PATH (안드로이드 Q) 혹은 DATA를 기준으로 잡아 검색합니다.
     * 두 번째, 파일 객체를 생성하고 실제로 파일이 존재하는지 체크합니다.
     * 세 번째, Mime Type으로 각각 분류하고, uri를 추가합니다
     * @param Context 는 Application Context를 필요로 합니다.
     * @param Directory 는 uri를 리턴 받고 싶은 파일들이 있는 디렉토리입니다. (/storage/emulated/0/를 제외한 경로를 적습니다)
     * @param Return 리턴 데이터는 어레이리스트이며, Uri 객체의 리스트를 리턴합니다.
     */

    /**
     * Call ex)
     * val directoryUriList = SampleOfMediaManager.getDataListUpperThanQ(applicationContext, "DCIM/B612/") (API 29 ↑)
     * val directoryUriList = SampleOfMediaManager.getDataListLowerThanP(applicationContext, "DCIM/B612/") (API 28 ↓)
     *
     *
     * Made by Ebichu, 2020-08-19. Use this Sample for FREE
     * Rename pacakage before using
     */

    @RequiresApi(Build.VERSION_CODES.Q)
    suspend fun getDataListUpperThanQ(context : Context, directory : String) = withContext(Dispatchers.IO) {
        val defaultUri = MediaStore.Files.getContentUri("external")
        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.RELATIVE_PATH,
            MediaStore.Files.FileColumns.DISPLAY_NAME,
            MediaStore.Files.FileColumns.MIME_TYPE)
        val selection = MediaStore.Files.FileColumns.RELATIVE_PATH + " like ?"
        val selectionArgs = arrayOf("%$directory%") // without /storage/emulated/0/ ex) /storage/emulated/0/Pictures/MyPic/ -> Pictures/MyPic/
        val result = ArrayList<Uri>()

        context.contentResolver.query(defaultUri, projection, selection, selectionArgs, null)?.use { cursor ->
            if(cursor.count == 0){
                return@use
            }
            while(cursor.moveToNext()){
                val file = File("/storage/emulated/0/" +
                        cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.RELATIVE_PATH)) +
                        cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME)))
                if(file.exists()){
                    val uri = ContentUris.withAppendedId(defaultUri, cursor.getLong(cursor.getColumnIndex(
                        MediaStore.Files.FileColumns._ID))).toString()

                    val mimeType : String? = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.MIME_TYPE))
                    if(mimeType.isNullOrEmpty()) {
                        result.add(Uri.parse(uri))
                    }else{
                        mimeType.let {
                            result.add(when {
                                it.contains("video") -> {
                                    Uri.parse(uri.replace(
                                        "external/file",
                                        "external/video/media"
                                    ))
                                }

                                it.contains("image") -> {
                                    Uri.parse(uri.replace(
                                        "external/file",
                                        "external/images/media"
                                    ))
                                }

                                it.contains("audio") -> {
                                    Uri.parse(uri.replace(
                                        "external/file",
                                        "external/audio/media"
                                    ))
                                }

                                else -> {
                                    Uri.parse(uri)
                                }
                            })
                        }
                    }
                }
            }
        }
        result
    }

    suspend fun getDataLowerThanP(context : Context, directory : String) = withContext(Dispatchers.IO) {
        val defaultUri = MediaStore.Files.getContentUri("external")
        val projection = arrayOf(
            MediaStore.Files.FileColumns._ID,
            MediaStore.Files.FileColumns.DATA,
            MediaStore.Files.FileColumns.MIME_TYPE)
        val selection = MediaStore.Files.FileColumns.DATA + " like ?"
        val selectionArgs = arrayOf("%$directory%") // without /storage/emulated/0/ ex) /storage/emulated/0/Pictures/MyPic/ -> Pictures/MyPic/
        val result = ArrayList<Uri>()

        context.contentResolver.query(defaultUri, projection, selection, selectionArgs, null)?.use { cursor ->
            if(cursor.count == 0){
                return@use
            }
            while(cursor.moveToNext()){
                val file = File(cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA)))
                if(file.exists()){
                    val uri = ContentUris.withAppendedId(defaultUri, cursor.getLong(cursor.getColumnIndex(
                        MediaStore.Files.FileColumns._ID))).toString()

                    val mimeType : String? = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.MIME_TYPE))
                    if(mimeType.isNullOrEmpty()) {
                        result.add(Uri.parse(uri))
                    }else{
                        mimeType.let {
                            result.add(when {
                                it.contains("video") -> {
                                    Uri.parse(uri.replace(
                                        "external/file",
                                        "external/video/media"
                                    ))
                                }

                                it.contains("image") -> {
                                    Uri.parse(uri.replace(
                                        "external/file",
                                        "external/images/media"
                                    ))
                                }

                                it.contains("audio") -> {
                                    Uri.parse(uri.replace(
                                        "external/file",
                                        "external/audio/media"
                                    ))
                                }

                                else -> {
                                    Uri.parse(uri)
                                }
                            })
                        }
                    }
                }
            }
        }
        result
    }
}