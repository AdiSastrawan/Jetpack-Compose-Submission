package com.adisastrawan.jetpacksubmission.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adisastrawan.jetpacksubmission.R
import com.adisastrawan.jetpacksubmission.ui.components.TopBar
import com.adisastrawan.jetpacksubmission.ui.theme.Gold1
import com.adisastrawan.jetpacksubmission.ui.theme.Grey1
import com.adisastrawan.jetpacksubmission.ui.theme.JetpackSubmissionTheme
import com.adisastrawan.jetpacksubmission.ui.theme.customFontFamily
import com.adisastrawan.jetpacksubmission.ui.theme.customHeaderFontFamily

@Composable
fun About(navigateBack: () -> Unit){
    val scrollState = rememberScrollState()
    Scaffold( topBar = {
        TopBar(navigateBack = navigateBack, title = "About", action = {})
    }) {
        AboutContent(modifier = Modifier.padding(it), scrollState = scrollState)
    }
}
@Composable
fun AboutContent(modifier: Modifier,scrollState: ScrollState){
    Column(modifier = modifier
        .padding(top = 16.dp)
        .verticalScroll(scrollState)
        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.adi), contentDescription ="image", modifier = Modifier
            .size(300.dp)
            .clip(
                CircleShape
            )
            .align(Alignment.CenterHorizontally), contentScale = ContentScale.Crop )
        Text(
            text = "Putu Adi Sastrawan", style = TextStyle(
                fontSize = 32.sp,
                fontFamily = customHeaderFontFamily,
                fontWeight = FontWeight.Bold,
                color = Gold1
            )
        )
        Text(
            text = "adisastrawan110703@gmail.com", style = TextStyle(
                fontSize = 24.sp,
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Bold,
                color = Grey1
            )
        )
    }
}
@Composable
@Preview(showBackground = true)
fun AboutPreview(){
    JetpackSubmissionTheme {
        About(navigateBack={})
    }
}