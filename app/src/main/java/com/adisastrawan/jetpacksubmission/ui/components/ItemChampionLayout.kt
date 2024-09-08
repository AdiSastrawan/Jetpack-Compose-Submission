package com.adisastrawan.jetpacksubmission.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adisastrawan.jetpacksubmission.model.Champion
import com.adisastrawan.jetpacksubmission.model.FakeData.champions
import com.adisastrawan.jetpacksubmission.ui.theme.Blue6
import com.adisastrawan.jetpacksubmission.ui.theme.Gold1
import com.adisastrawan.jetpacksubmission.ui.theme.Gold3
import com.adisastrawan.jetpacksubmission.ui.theme.Grey1
import com.adisastrawan.jetpacksubmission.ui.theme.JetpackSubmissionTheme
import com.adisastrawan.jetpacksubmission.ui.theme.customFontFamily
import com.adisastrawan.jetpacksubmission.ui.theme.customHeaderFontFamily

@Composable
fun ItemChampionLayout(champion: Champion, navigateToDetail: (String) -> Unit,modifier: Modifier=Modifier) {
    Column(modifier = Modifier
        .background(color = Blue6)
        .clickable { navigateToDetail(champion.name) }) {
        ChampionItem(champion = champion)
    }

}

@Composable
@Preview(showBackground = true)
fun ItemChampionPreview() {
    JetpackSubmissionTheme {
        ItemChampionLayout(champion = champions[0], navigateToDetail = {})
    }
}

@Composable
fun ChampionItem(champion: Champion, modifier: Modifier = Modifier) {
    Row {
        Image(
            painter = painterResource(id = champion.img),
            contentDescription = "Champion Image",
            modifier = modifier
                .size(100.dp, 120.dp)
                .padding(start = 4.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        ChampionItemDetail(champion = champion)
    }
    Divider(
        color = Gold3,
        thickness = 1.dp,
        modifier = modifier
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .fillMaxWidth()
    )
}

@Composable
fun ChampionItemDetail(champion: Champion){
    Column {
        Text(
            text = champion.name,
            style = TextStyle(
                fontFamily = customHeaderFontFamily,
                color = Gold1,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = champion.description,
            style = TextStyle(
                fontFamily = customFontFamily,
                color = Grey1,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            ),
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}