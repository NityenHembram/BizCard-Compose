package com.nityendroid.bizcardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nityendroid.bizcardapp.ui.theme.BizCardAppTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
//this is the mainActivity
//this change is for test
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BizCardAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CreateBizCard()
                }
            }
        }
    }
}

/*This is the small change to test :-

Test 1
Test 2
*/
@Composable
private fun CreateBizCard() {
    val buttonClickState = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .statusBarsPadding()
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(corner = CornerSize(32.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 20.dp, 0.dp, 0.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageProfileWidget(
                    modifier = Modifier
                        .size(120.dp)
                        .padding(5.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.padding(top = 10.dp), color = Color.Cyan
                )
                Column(modifier = Modifier.padding(30.dp)) {
                    Text(
                        "Nityen Hembram", style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold, color = Color.Blue
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = "Android Compose Programmer",
                        style = TextStyle(
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                    )
                    Text(
                        "NDroid", style = TextStyle(
                            fontSize = 15.sp,
                            color = Color.Black
                        )
                    )
                }

                Button(
                    onClick = { buttonClickState.value = !buttonClickState.value },
                    shape = RectangleShape,
                    colors = ButtonColors(
                        contentColor = Color.White,
                        containerColor = Color.Blue, disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White,
                    ),
                ) { Text("Portfolio") }


                AnimatedVisibility(
                    visible = buttonClickState.value,
                ) {
                    Content()
                }


            }
        }

    }
}

val projectList = listOf(
    "Project 1", "Project 2", "Project 3", "Project 4", "Project 5", "Project 6",
    "Project 7", "Project 8", "Project 9", "Project 10", "Project 11", "Project 12"
)

//@Preview(showBackground = true)
@Composable
private fun Content() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            shape = RoundedCornerShape(corner = CornerSize(30.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray),
            color = Color.Cyan,

            ) {
            Portfolio(projectList)
        }
    }
}

@Composable
private fun Portfolio(data: List<String>) {
    val listState = rememberLazyListState()
    val itemsList by remember { mutableStateOf(projectList) }
    LazyColumn(
        state = listState,
        modifier = Modifier.padding(12.dp)
    ) {
        items(items = itemsList, key = { it }) { item ->  // Use key for better performance
            PortfolioItem(item)
        }
    }
}

@Composable
private fun PortfolioItem(item: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth().align(alignment = Alignment.CenterHorizontally
                ), // Remove `fillMaxHeight()`

        ) {
            ImageProfileWidget(modifier = Modifier.size(40.dp))

            Column(
                modifier = Modifier
                    .weight(1f) // Use weight instead of fillMaxHeight()
                    .padding(start = 15.dp)
            ) {
                Text(item, fontWeight = FontWeight.ExtraBold)
                Text("This is my project and I am proud of it.", color = Color.Gray)
            }
        }
    }
}


@Composable
private fun ImageProfileWidget(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        shadowElevation = 4.dp,
        border = BorderStroke(1.0.dp, Color.Blue),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 1.0f)
    ) {

        Image(
            painter = painterResource(
                R.drawable.myimg,
            ), contentDescription = "profilePicture",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(135.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BizCardAppTheme {
        CreateBizCard()
    }
}