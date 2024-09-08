package com.adisastrawan.jetpacksubmission.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adisastrawan.jetpacksubmission.model.Champion
import com.adisastrawan.jetpacksubmission.model.FakeData.champions
import com.adisastrawan.jetpacksubmission.ui.theme.Blue6
import com.adisastrawan.jetpacksubmission.ui.theme.Gold2
import com.adisastrawan.jetpacksubmission.ui.theme.Gold4
import com.adisastrawan.jetpacksubmission.ui.theme.JetpackSubmissionTheme
import com.adisastrawan.jetpacksubmission.ui.theme.customFontFamily
import com.adisastrawan.jetpacksubmission.ui.theme.customHeaderFontFamily

@Composable
fun ChampionProfile(champion: Champion,modifier: Modifier=Modifier,navigateBack : ()->Unit){
    val scrollState = rememberScrollState()
    Column(modifier = modifier
        .background(color = Blue6)
        .verticalScroll(scrollState)
        .padding(bottom = 32.dp, top = 8.dp, end = 8.dp, start = 8.dp)) {
        IconButton(onClick = { navigateBack() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Gold2, modifier = Modifier.size(32.dp))
        }
        Image(painter = painterResource(id =champion.img), contentDescription = "Champion Image", modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .padding(top = 32.dp))
        Text(text = champion.subname, modifier = Modifier
            .padding(top = 16.dp)
            .align(Alignment.CenterHorizontally), style = TextStyle(
            color = Color.White,
            fontSize = 24.sp,
            fontFamily = customHeaderFontFamily,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
        ))
        Text(text = champion.name.uppercase(), modifier = Modifier.align(Alignment.CenterHorizontally),style = TextStyle(
            color = Color.White,
            fontSize = 40.sp,
            fontFamily = customHeaderFontFamily,
            fontWeight = FontWeight.ExtraBold,
            fontStyle = FontStyle.Italic,

        ))
        Row(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally), horizontalArrangement = Arrangement.SpaceEvenly) {
            ChampionDetailSection(top = "DIFFICULTY", bottom = champion.difficulty.uppercase() )
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(80.dp)
                    .background(Gold4)
            )
            ChampionDetailSection(top="ROLE", bottom = champion.role.uppercase())
        }
        Divider(color = Gold4, thickness = 1.dp)
        Text(
            text = champion.description,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            textAlign = TextAlign.Center,
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = customFontFamily,)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ChampionProfilePreview(){
    JetpackSubmissionTheme {
        ChampionProfile(champion = champions[0], navigateBack = {})
    }
}

@Composable
fun ChampionDetailSection(top:String,bottom:String){
    Column(modifier = Modifier.padding(8.dp)){
        Text(text = top, modifier = Modifier
            .padding(top = 16.dp)
            .align(Alignment.CenterHorizontally), style = TextStyle(
            color = Color.White,
            fontSize =16.sp,
            fontFamily = customFontFamily,
                fontWeight = FontWeight.SemiBold
        ))
        Text(text = bottom, modifier = Modifier.align(Alignment.CenterHorizontally),style = TextStyle(
            color = Gold2,
            fontSize = 16.sp,
            fontFamily = customHeaderFontFamily,
            fontWeight = FontWeight.Bold,
            ))
    }
}