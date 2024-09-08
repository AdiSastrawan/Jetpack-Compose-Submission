package com.adisastrawan.jetpacksubmission

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.adisastrawan.jetpacksubmission.ui.theme.Blue6
import com.adisastrawan.jetpacksubmission.ui.theme.JetpackSubmissionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackSubmissionTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Blue6)
                ) {
                    SubmissionApp()
                }
            }
        }
    }
}


