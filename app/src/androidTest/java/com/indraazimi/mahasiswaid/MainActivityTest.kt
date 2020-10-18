/*
 * Copyright (c) 2020 Indra Azimi. All rights reserved.
 *
 *  Dibuat untuk kelas Pemrograman untuk Perangkat Bergerak 2.
 *  Dilarang melakukan penggandaan dan atau komersialisasi,
 *  sebagian atau seluruh bagian, baik cetak maupun elektronik
 *  terhadap project ini tanpa izin pemilik hak cipta.
 */

package com.indraazimi.mahasiswaid

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.indraazimi.mahasiswaid.data.Mahasiswa
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    companion object {
        private val MAHASISWA_DUMMY = Mahasiswa(0, "6706180001", "Tika Aulia Utami")
    }

    @Test
    fun testInsert() {
        // Jalankan MainActivity
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Lakukan aksi untuk menambah data baru
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.nimEditText)).perform(typeText(MAHASISWA_DUMMY.nim))
        onView(withId(R.id.namaEditText)).perform(typeText(MAHASISWA_DUMMY.nama))
        onView(withText(R.string.simpan)).perform(click())

        // Cek apakah data tersebut muncul
        onView(withText(MAHASISWA_DUMMY.nim)).check(matches(isDisplayed()))
        onView(withText(MAHASISWA_DUMMY.nama)).check(matches(isDisplayed()))

        // Tes selesai, tutup activity nya
        activityScenario.close()

        // CATATAN: Ketika testInsert dijalankan > 1x, hasil test akan gagal.
        // Ini terjadi karena di database masih ada data MAHASISWA_DUMMY
        // hasil run sebelumnya. Masalah ini akan kita tangani nanti.
    }
}
