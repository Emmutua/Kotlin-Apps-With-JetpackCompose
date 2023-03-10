package com.example.lists_shuffling

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lists_shuffling.ui.theme.Lists_shufflingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lists_shufflingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    App()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun App() {

    val viewModel: DataViewModel = viewModel()
    var shuffledCards by remember {
        mutableStateOf(viewModel.getLists())
    }



    Scaffold(topBar = {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Emoji",
                fontSize = 25.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        FloatingActionButton(
            onClick = { shuffledCards = viewModel.shuffleEmoji()},
            backgroundColor = Color.Blue,
            contentColor = Color.White,
            elevation = FloatingActionButtonDefaults.elevation(),
            content = {
                Icon(Icons.Default.Refresh, contentDescription = "Reshuffle")
            },
            modifier = Modifier
                .size(70.dp)
                .padding(16.dp)
        )

    },
    floatingActionButtonPosition = FabPosition.End) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(10.dp),
            modifier = Modifier.padding(8.dp)
        ) {

            items(shuffledCards) {
                EmojiCard(emoji = it)
            }
        }

    }
}

@Composable
fun EmojiCard(
    emoji: Emoji,
    modifier: Modifier = Modifier.padding(8.dp)
) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = 8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = emoji.theEmoji, fontSize = 90.sp)
            Text(text = emoji.name, fontSize = 30.sp)
        }
    }
}