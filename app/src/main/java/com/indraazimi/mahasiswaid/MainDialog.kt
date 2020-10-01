/*
 * Copyright (c) 2021 Indra Azimi. All rights reserved.
 *
 * Dibuat untuk kelas Pemrograman untuk Perangkat Bergerak 2.
 * Dilarang melakukan penggandaan dan atau komersialisasi,
 * sebagian atau seluruh bagian, baik cetak maupun elektronik
 * terhadap project ini tanpa izin pemilik hak cipta.
 */

package com.indraazimi.mahasiswaid

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.indraazimi.mahasiswaid.databinding.DialogMainBinding

class MainDialog : DialogFragment() {

    private lateinit var binding: DialogMainBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        binding = DialogMainBinding.inflate(inflater, null, false)

        val builder = AlertDialog.Builder(requireContext()).apply {
            setTitle(R.string.tambah_mahasiswa)
            setView(binding.root)
            setPositiveButton(R.string.simpan) { _, _ ->
                val listener = requireActivity() as DialogListener
                listener.processDialog()
            }
            setNegativeButton(R.string.batal) { _, _ -> dismiss() }
        }
        return builder.create()
    }

    interface DialogListener {
        fun processDialog()
    }
}