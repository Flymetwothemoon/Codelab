package com.example.codelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codelab.ui.theme.CodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(

                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()

                }
            }
        }
    }
}
@Composable
fun OpenningUI(
    onContinueClicked: () -> Unit){
   Surface(color = MaterialTheme.colorScheme.primaryContainer) {
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
fun Greeting(name: String) {
    var expanded = remember {
        mutableStateOf(false)
    }
    val paddingBottom = if(expanded.value)48.dp else 0.dp
    Surface( color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ,shape = RoundedCornerShape(8.dp)
            ) {

        Row(modifier = Modifier
            .padding(24.dp)
            .padding(bottom = paddingBottom)) {
            Column(modifier = Modifier
                .weight(1f)) {
                Text("hello")
                Text(text = name)

            }
            ElevatedButton( onClick = { expanded.value = !expanded.value },
            ) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }

    }

}
@Composable
private fun MyApp(modifier: Modifier = Modifier, names:List<String> = listOf("1","2","3","4"
,"5","6","7","8","9")) {
    var IfTounch = remember {
        mutableStateOf(false)
    }
    if(IfTounch.value){
    Surface(
    ) {
        LazyColumn(modifier = modifier.padding(vertical = 8.dp)) {
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
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CodelabTheme {
//        OpenningUI()
    }
}