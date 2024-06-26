package com.rizqanmr.saltytest.ui.screen.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.rizqanmr.saltytest.data.model.UserItem
import com.rizqanmr.saltytest.ui.component.HeightSpacer
import com.rizqanmr.saltytest.ui.component.RemoteImage
import com.rizqanmr.saltytest.ui.component.WidthSpacer
import com.rizqanmr.saltytest.ui.theme.SaltyTestTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SaltyTestTheme {
                val viewModel: MainViewModel by viewModel()
                val mainState by viewModel.mainState.collectAsState()
                val listUsers = viewModel.listUser.collectAsLazyPagingItems()
                val context = LocalContext.current

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (mainState) {
                        is MainState.LoadingState -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        is MainState.Success -> {
                            LazyColumn(content = {
                                items(listUsers.itemCount) {index ->
                                    val userItem = listUsers[index]
                                    userItem?.let { UserItems(item = it) }
                                }
                            })
                        }
                        is MainState.Error -> {
                            val message = (mainState as MainState.Error).errorMessage
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}

@Composable
fun UserItems(item: UserItem) {
    Column {
        Row(modifier = Modifier.padding(8.dp)) {
            RemoteImage(url = item.avatar, modifier = Modifier.requiredSize(48.dp))
            WidthSpacer(value = 10.dp)
            Column {
                Text(
                    text = item.getUserFullName(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                HeightSpacer(value = 4.dp)
                Text(text = item.email)
            }
        }
        Divider(
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SaltyTestTheme {
        UserItems(item = UserItem(firstName = "Budi", email = "budi@mail.com"))
    }
}