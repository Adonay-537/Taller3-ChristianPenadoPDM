package com.example.com.pdm0126.parcial2room

import android.app.Application

class Parcial2Application : Application() {
    val appProvider by lazy { AppProvider(this) }
}