package com.lekrom.whatsapplink

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.lekrom.whatsapplink.ui.theme.WhatsappLinkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsappLinkTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FormScreen(::openLink)
                }
            }
        }
    }

    private fun openLink(ddd: String, cellNumber: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(getString(R.string.url, ddd, cellNumber))
        )
        startActivity(intent)
    }
}
