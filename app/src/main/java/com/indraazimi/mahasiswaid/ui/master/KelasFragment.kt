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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.indraazimi.mahasiswaid.R
import kotlinx.android.synthetic.main.fragment_kelas.*

class KelasFragment : Fragment() {

    private var isTablet: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kelas, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        isTablet = resources.getBoolean(R.bool.isTablet)

        val kelas = resources.getStringArray(R.array.kelas)
        listView.adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_list_item_activated_1, android.R.id.text1, kelas)
        listView.setOnItemClickListener { _, _, position, _ ->
            if (isTablet) {
                val bundle = bundleOf("kelas" to kelas[position])
                findNavController().navigate(R.id.action_global_mainFragment, bundle)
                return@setOnItemClickListener
            }

            findNavController().navigate(
                KelasFragmentDirections.actionKelasFragmentToMainFragment(kelas[position])
            )
        }

        if (isTablet) {
            // Di tablet, kita tidak mengubah title dari ActionBar,
            // tapi kita memberi selection indicator pada ListView.
            listView.choiceMode = ListView.CHOICE_MODE_SINGLE
            listView.setItemChecked(0, true)
        }
    }
}
