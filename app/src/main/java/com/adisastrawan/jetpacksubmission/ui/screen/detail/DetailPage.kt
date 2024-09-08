package com.adisastrawan.jetpacksubmission.ui.screen.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adisastrawan.jetpacksubmission.di.Injection
import com.adisastrawan.jetpacksubmission.model.Champion
import com.adisastrawan.jetpacksubmission.ui.common.UIState
import com.adisastrawan.jetpacksubmission.ui.components.ChampionProfile
import com.adisastrawan.jetpacksubmission.ui.screen.ViewModelFactory
import com.adisastrawan.jetpacksubmission.ui.theme.Gold3
import com.adisastrawan.jetpacksubmission.ui.theme.JetpackSubmissionTheme

@Composable
fun Detail(
    modifier: Modifier = Modifier,
    name: String,
    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory.getInstance(Injection.provideRepository())),
    navigateBack: () -> Unit,
) {
    viewModel.checkFavorite(name)
    viewModel.uiState.collectAsState(initial = UIState.Loading).value.let { uiState ->
        when (uiState) {
            is UIState.Loading -> {
                viewModel.getChampionByName(name)
            }
            is UIState.Error -> {
            }
            is UIState.Success -> {
                DetailContent(
                    viewModel = viewModel,
                    champion = uiState.data,
                    name = name,
                    modifier = modifier,
                    navigateBack = navigateBack
                )
            }
        }
    }
}


@Composable
fun DetailContent(
    viewModel: DetailViewModel,
    champion: Champion,
    name: String,
    modifier: Modifier,
    navigateBack: () -> Unit
) {
    Scaffold(modifier = modifier, floatingActionButton = {
        FloatingActionButton(onClick = {
            if (viewModel.isFavorite.value) {
                viewModel.deleteFavorite(champion)
            } else {
                viewModel.addFavorite(champion)
            }
            viewModel.checkFavorite(name)
        }, containerColor = Gold3) {
            Icon(
                imageVector = if (viewModel.isFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Add"
            )
        }
    }) {
        ChampionProfile(
            champion = champion,
            modifier = Modifier.padding(it),
            navigateBack = navigateBack
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DetailPreview() {
    JetpackSubmissionTheme {
        Detail(name = "Aatrox", navigateBack = {})
    }
}