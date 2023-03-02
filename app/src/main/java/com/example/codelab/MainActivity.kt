package com.example.codelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.CardDefaults


import com.example.codelab.ui.theme.CodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = Color.White
                ) {
                    MyApp(Modifier.fillMaxSize())

                }
            }
        }
    }
}
@Composable
fun OpenningUI(
    onContinueClicked: () -> Unit){
   Surface(color = MaterialTheme.colorScheme.primary) {
       Column(modifier = Modifier
           .fillMaxWidth()
           .fillMaxHeight(),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally) {
           Text(text = "Welcome to the Basics Codelab!"
               ,color = MaterialTheme.colorScheme.onErrorContainer, fontSize = 20.sp,)

           ElevatedButton(
               onClick = onContinueClicked
               , modifier = Modifier.padding(vertical = 24.dp),
           ) {
                    Text(text = "continue")
           }
       }
    }



}
@Composable
private fun Greeting(name: String) {

    CardContent(name)

}

@Composable
 private fun CardContent(name: String) {
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }
    val extraPending by animateDpAsState(if(expanded)48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
//    val paddingBottom = if(expanded)48.dp else 0.dp
    Surface( color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ,shape = RoundedCornerShape(8.dp)
    ) {

        Row(modifier = Modifier
            .padding(24.dp)
            .padding(bottom = extraPending.coerceAtLeast(0.dp))) {
            Column(modifier = Modifier
                .weight(1f)) {
                Text("hello", style = MaterialTheme.typography.bodyLarge)
                Text(text = name, style = MaterialTheme.typography.headlineLarge)
                if (expanded) {
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4),
                    )
                }
            }
            IconButton( onClick = { expanded = !expanded },
            ) { Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
            }
        }

    }

}
@Composable
private fun MyApp(modifier: Modifier = Modifier, names:List<String> = List(1000){"$it"}) {
    var IfTounch = rememberSaveable { mutableStateOf(false) }
    if(IfTounch.value){
    Surface(color =  MaterialTheme.colorScheme.onSurface
    ) {
        LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
            items(names) { name ->
                Greeting(name = name)
            }
        }
    }
    }
    else{
        OpenningUI(onContinueClicked = {IfTounch.value = !IfTounch.value })
    }
}
@Preview(showBackground = true,
        uiMode = UI_MODE_NIGHT_YES,
    name = "Dark")
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CodelabTheme {
        MyApp()
    }
}