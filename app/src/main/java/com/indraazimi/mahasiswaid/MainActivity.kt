/*
 * Copyright (c) 2021 Indra Azimi. All rights reserved.
 *
 * Dibuat untuk kelas Pemrograman untuk Perangkat Bergerak 2.
 * Dilarang melakukan penggandaan dan atau komersialisasi,
 * sebagian atau seluruh bagian, baik cetak maupun elektronik
 * terhadap project ini tanpa izin pemilik hak cipta.
 */

package com.indraazimi.mahasiswaid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.indraazimi.mahasiswaid.data.Mahasiswa
import com.indraazimi.mahasiswaid.data.MahasiswaDb
import com.indraazimi.mahasiswaid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainDialog.DialogListener {

    private val viewModel: MainViewModel by lazy {
        val dataSource = MahasiswaDb.getInstance(this).dao
        val factory = MainViewModelFactory(dataSource)
        ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            MainDialog().show(supportFragmentManager, "MainDialog")
        }
    }

    override fun processDialog(mahasiswa: Mahasiswa) {
        viewModel.insertData(mahasiswa)
    }
}