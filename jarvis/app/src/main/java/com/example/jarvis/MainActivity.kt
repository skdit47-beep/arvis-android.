package com.example.jarvis

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    private val permissions = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val needed = mutableListOf(Manifest.permission.RECORD_AUDIO)
        if (Build.VERSION.SDK_INT >= 33) needed += Manifest.permission.POST_NOTIFICATIONS
        if (needed.any { ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED }) {
            permissions.launch(needed.toTypedArray())
        }

        setContent {
            var status by remember { mutableStateOf("STANDBY") }
            val bg = Brush.verticalGradient(listOf(Color(0xFF061326), Color(0xFF020711)))
            MaterialTheme {
                Column(
                    Modifier.fillMaxSize().background(bg).padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("J.A.R.V.I.S", color = Color(0xFF65D9FF), style = MaterialTheme.typography.headlineLarge)
                    Spacer(Modifier.height(8.dp))
                    Text("PERSONAL ASSISTANT // ANDROID", color = Color(0xFF8AAFC8))
                    Spacer(Modifier.height(36.dp))
                    Text(status, color = Color(0xFF65D9FF), style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(22.dp))
                    Button(
                        onClick = {
                            val intent = Intent(this@MainActivity, WakeWordService::class.java)
                            ContextCompat.startForegroundService(this@MainActivity, intent)
                            status = "WAKE-WORD SERVICE REQUESTED"
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF075A82)),
                        shape = RoundedCornerShape(16.dp)
                    ) { Text("ENABLE HEY JARVIS") }
                    Spacer(Modifier.height(12.dp))
                    OutlinedButton(
                        onClick = {
                            stopService(Intent(this@MainActivity, WakeWordService::class.java))
                            status = "STANDBY"
                        }
                    ) { Text("STOP LISTENING") }
                    Spacer(Modifier.height(28.dp))
                    Text(
                        "Wake-word engine setup is required before phrase detection works.",
                        color = Color(0xFF9DB2C7)
                    )
                }
            }
        }
    }
}
