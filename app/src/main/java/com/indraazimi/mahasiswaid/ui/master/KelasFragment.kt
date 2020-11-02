/*
 * Copyright (c) 2020 Indra Azimi. All rights reserved.
 *
 *  Dibuat untuk kelas Pemrograman untuk Perangkat Bergerak 2.
 *  Dilarang melakukan penggandaan dan atau komersialisasi,
 *  sebagian atau seluruh bagian, baik cetak maupun elektronik
 *  terhadap project ini tanpa izin pemilik hak cipta.
 */

package com.indraazimi.mahasiswaid.ui.master

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.indraazimi.mahasiswaid.R
import kotlinx.android.synthetic.main.fragment_kelas.*

class KelasFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kelas, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val kelas = resources.getStringArray(R.array.kelas)
        listView.adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_1, android.R.id.text1, kelas)
        listView.setOnItemClickListener { _, _, position, _ ->
            Log.d("KELAS", kelas[position])
        }
    }
}
