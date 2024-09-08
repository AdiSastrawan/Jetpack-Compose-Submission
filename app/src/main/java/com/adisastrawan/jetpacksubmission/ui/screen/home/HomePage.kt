package com.adisastrawan.jetpacksubmission.ui.screen.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.adisastrawan.jetpacksubmission.di.Injection
import com.adisastrawan.jetpacksubmission.model.Champion
import com.adisastrawan.jetpacksubmission.ui.common.UIState
import com.adisastrawan.jetpacksubmission.ui.components.ItemChampionLayout
import com.adisastrawan.jetpacksubmission.ui.components.TopBar
import com.adisastrawan.jetpacksubmission.ui.screen.ViewModelFactory
import com.adisastrawan.jetpacksubmission.ui.theme.Gold2
import com.adisastrawan.jetpacksubmission.ui.theme.Grey1
import com.adisastrawan.jetpacksubmission.ui.theme.Grey1_5
import com.adisastrawan.jetpacksubmission.ui.theme.HextechBlack
import com.adisastrawan.jetpacksubmission.ui.theme.JetpackSubmissionTheme

@Composable
fun Home(
    modifier: Modifier = Modifier,
    navigateToDetail : (String)->Unit,
    navigateToFavorite : ()->Unit,
    navigateToAbout : ()->Unit,
    viewModel: HomeViewModel =
        viewModel(factory = ViewModelFactory.getInstance(Injection.provideRepository()))
) {

        viewModel.uiState.collectAsState(initial = UIState.Loading).value.let { result ->
            when (result) {
                is UIState.Loading -> {
                    viewModel.getAllChampions()
                }

                is UIState.Success -> {
                    HomeContent(modifier = modifier,viewModel=viewModel, navigateToDetail = navigateToDetail, navigateToFavorite = navigateToFavorite, champions = result.data,navigateToAbout=navigateToAbout)

                }

                is UIState.Error -> {


                }
            }

        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(viewModel: HomeViewModel,navigateToFavorite: () -> Unit){

        androidx.compose.material3.SearchBar(
            query = viewModel.query.value,
            onQueryChange = viewModel::searchChampion,
            onSearch = {  },
            active = false,
            onActiveChange = {},
            placeholder = {
                Text(text = "Search", color = Grey1_5)},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Grey1_5
                )
            },
            trailingIcon = {
                IconButton(onClick =navigateToFavorite) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Gold2
                    )
                }
            },
            colors = SearchBarDefaults.colors(containerColor = HextechBlack, dividerColor = Gold2, inputFieldColors = TextFieldDefaults.colors(focusedTextColor = Grey1)),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .heightIn(min = 48.dp)
        ) {}

}

@Composable
fun HomeContent(modifier: Modifier,viewModel: HomeViewModel,navigateToAbout: () -> Unit,navigateToDetail: (String)->Unit,navigateToFavorite: ()->Unit,champions: List<Champion>){
    Scaffold(topBar = {
        TopBar( title = "Home",action ={
            IconButton(onClick = navigateToAbout) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    tint = Gold2)
            }
        })
    }) {padding->
        LazyColumn(modifier = modifier.padding(padding)) {
            item {
                SearchBar(viewModel, navigateToFavorite)
            }
            items(items = champions, key = { it.name }) {champion->
                ItemChampionLayout(champion, navigateToDetail)
            }
        }
    }
}
@Composable
@Preview(showBackground = true)
fun HomePreview() {
    JetpackSubmissionTheme {
        Home(navigateToDetail = {}, navigateToFavorite = {}, navigateToAbout = {})
    }
}