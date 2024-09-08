package com.adisastrawan.jetpacksubmission.ui.screen.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adisastrawan.jetpacksubmission.di.Injection
import com.adisastrawan.jetpacksubmission.model.Champion
import com.adisastrawan.jetpacksubmission.ui.common.UIState
import com.adisastrawan.jetpacksubmission.ui.components.ItemChampionLayout
import com.adisastrawan.jetpacksubmission.ui.components.TopBar
import com.adisastrawan.jetpacksubmission.ui.screen.ViewModelFactory
import com.adisastrawan.jetpacksubmission.ui.theme.Gold2
import com.adisastrawan.jetpacksubmission.ui.theme.JetpackSubmissionTheme
import com.adisastrawan.jetpacksubmission.ui.theme.customHeaderFontFamily

@Composable
fun Favorite(
    modifier: Modifier=Modifier,
    navigateToDetail: (String) -> Unit,
    navigateBack: () -> Unit,
    viewModel: FavoriteViewModel = viewModel(factory = ViewModelFactory.getInstance(Injection.provideRepository()))
) {
    val champions = viewModel.champions.collectAsState()

    when (champions.value) {
        is UIState.Error -> {}
        is UIState.Loading -> {
            viewModel.getFavoriteChampions()
        }

        is UIState.Success -> {
            FavoriteContent(
                champions = (champions.value as UIState.Success<List<Champion>>).data,
                navigateToDetail = navigateToDetail,
                navigateBack = navigateBack,
                modifier = modifier
            )
        }
    }
}



@Composable
fun FavoriteContent(
    champions: List<Champion>,
    navigateToDetail: (String) -> Unit,
    modifier: Modifier,
    navigateBack: () -> Unit
) {
    Scaffold(topBar = {
        TopBar(title = "Favorite", navigateBack = navigateBack, action = {})
    }) {
        if (champions.isEmpty()) {
            Box(
                modifier = modifier.fillMaxSize().padding(it),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No Favorite Champion", textAlign = TextAlign.Center, style = TextStyle(
                        color = Gold2,
                        fontSize = 24.sp,
                        fontFamily = customHeaderFontFamily,
                        fontWeight = FontWeight.Bold,

                        )
                )
            }
        } else {
            LazyColumn(modifier = modifier.padding(it)) {
                items(champions, key ={champion->champion.name}) { champion ->
                    ItemChampionLayout(champion = champion, navigateToDetail)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FavoritePreview() {
    JetpackSubmissionTheme {
        Favorite(navigateToDetail = {}, navigateBack = {})
    }
}