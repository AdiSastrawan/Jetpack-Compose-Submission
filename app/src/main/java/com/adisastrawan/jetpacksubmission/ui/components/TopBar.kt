package com.adisastrawan.jetpacksubmission.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adisastrawan.jetpacksubmission.ui.theme.Gold2
import com.adisastrawan.jetpacksubmission.ui.theme.HextechBlack
import com.adisastrawan.jetpacksubmission.ui.theme.customHeaderFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navigateBack:(()->Unit)?=null,title : String,action : @Composable () -> Unit){
    TopAppBar(
        navigationIcon = {
            if(navigateBack != null){
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Gold2,
                    modifier = Modifier
                        .clickable { navigateBack() }
                        .size(48.dp)
                        .padding(horizontal = 8.dp))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = HextechBlack,
            titleContentColor = Gold2,
        ),
        title = {
            Text(
                title, style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = customHeaderFontFamily,
                    fontWeight = FontWeight.Bold,
                )
            )
        },
        actions = {
            action()
        }
    )
}