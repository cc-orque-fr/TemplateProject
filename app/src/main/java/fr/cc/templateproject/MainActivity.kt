package fr.cc.templateproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import fr.cc.templateproject.model.StoreItem
import fr.cc.templateproject.viewmodel.StoreItemsUiState
import fr.cc.templateproject.viewmodel.StoreItemsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: StoreItemsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                when (val value = mainViewModel.uiState.collectAsState().value) {
                    is StoreItemsUiState.SuccessUiState -> {
                        MainScreenCompose(innerPadding, value.stores)
                    }

                    is StoreItemsUiState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(48.dp),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    else -> {
                        // need to wrap all the error

                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text("Error : need to wrap all the errors and put the right icon")
                            Icon(
                                painter = painterResource(R.drawable.error),
                                contentDescription = "Erreur",
                                tint = Color.Transparent,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun MainScreenCompose(paddingValues: PaddingValues, storeItems: List<StoreItem>) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn {
                items(storeItems) {
                    StoreItemView(it)
                }

            }
        }
    }

    @Composable
    fun StoreItemView(storeItem: StoreItem) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 4.dp, shape = MaterialTheme.shapes.medium)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(5.dp)
                    .size(100.dp),
                model = storeItem.image,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )


            Text(modifier = Modifier.padding(16.dp), text = storeItem.description)
        }
    }
}