/*
 * Copyright (c) 2020 Indra Azimi. All rights reserved.
 *
 *  Dibuat untuk kelas Pemrograman untuk Perangkat Bergerak 2.
 *  Dilarang melakukan penggandaan dan atau komersialisasi,
 *  sebagian atau seluruh bagian, baik cetak maupun elektronik
 *  terhadap project ini tanpa izin pemilik hak cipta.
 */

package com.indraazimi.mahasiswaid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.indraazimi.mahasiswaid.data.Mahasiswa
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainDialog.DialogListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener {
            MainDialog().show(supportFragmentManager, "MainDialog")
        }
    }

    override fun processDialog(mahasiswa: Mahasiswa) {
        Log.d("MainActivity", mahasiswa.toString())
    }
}
