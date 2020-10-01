/*
 * Copyright (c) 2020 Indra Azimi. All rights reserved.
 *
 *  Dibuat untuk kelas Pemrograman untuk Perangkat Bergerak 2.
 *  Dilarang melakukan penggandaan dan atau komersialisasi,
 *  sebagian atau seluruh bagian, baik cetak maupun elektronik
 *  terhadap project ini tanpa izin pemilik hak cipta.
 */

package com.indraazimi.mahasiswaid

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.indraazimi.mahasiswaid.data.Mahasiswa

class MainDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.dialog_main, null, false)

        val builder = AlertDialog.Builder(requireContext())
        with(builder) {
            setTitle(R.string.mahasiswa_baru)
            setView(view)
            setPositiveButton(R.string.simpan) { _, _ ->
                val mahasiswa = getData(view) ?: return@setPositiveButton
                val listener = requireActivity() as DialogListener
                listener.processDialog(mahasiswa)
            }
            setNegativeButton(R.string.batal) { _, _ -> dismiss() }
        }
        return builder.create()
    }

    private fun getData(view: View): Mahasiswa? {
        val nimEditText = view.findViewById<EditText>(R.id.nimEditText)
        val namaEditText = view.findViewById<EditText>(R.id.namaEditText)

        // Lakukan sanity check terlebih dahulu
        if (nimEditText.text.isEmpty()) {
            showMessage(R.string.nim_wajib_diisi)
            return null
        }

        if (nimEditText.text.length != 10) {
            showMessage(R.string.nim_harus_10chars)
            return null
        }

        if (namaEditText.text.isEmpty()) {
            showMessage(R.string.nama_wajib_diisi)
            return null
        }

        return Mahasiswa(
            nim = nimEditText.text.toString(),
            nama = namaEditText.text.toString()
        )
    }

    private fun showMessage(messageResId: Int) {
        val toast = Toast.makeText(requireContext(), messageResId, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    interface DialogListener {
        fun processDialog(mahasiswa: Mahasiswa)
    }
}
