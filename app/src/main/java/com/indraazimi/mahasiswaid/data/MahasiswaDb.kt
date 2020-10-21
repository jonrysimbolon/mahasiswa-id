/*
 * Copyright (c) 2020 Indra Azimi. All rights reserved.
 *
 *  Dibuat untuk kelas Pemrograman untuk Perangkat Bergerak 2.
 *  Dilarang melakukan penggandaan dan atau komersialisasi,
 *  sebagian atau seluruh bagian, baik cetak maupun elektronik
 *  terhadap project ini tanpa izin pemilik hak cipta.
 */

package com.indraazimi.mahasiswaid.data

import androidx.lifecycle.LiveData
import com.google.firebase.database.FirebaseDatabase

class MahasiswaDb private constructor(): MahasiswaDao {

    companion object {
        private const val PATH = "mahasiswa"

        @Volatile
        private var INSTANCE: MahasiswaDb? = null

        fun getInstance(): MahasiswaDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = MahasiswaDb()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    // Tidak seperti Room yang otomatis membuatkan implementasi Dao,
    // di Firebase kita harus membuat implementasi Dao sendiri.

    private val database = FirebaseDatabase.getInstance().getReference(PATH)

    override fun insertData(mahasiswa: Mahasiswa) {
        database.push().setValue(mahasiswa)
    }

    // Berkat kelas MahasiswaLiveData, perubahan via Firebase Console
    // sekarang secara otomatis dapat terlihat di RecyclerView.
    override fun getData(): LiveData<List<Mahasiswa>> {
        return MahasiswaLiveData(database)
    }

    override fun deleteData(ids: List<String>) {
        for (id in ids) {
            database.child(id).removeValue()
        }
    }
}
