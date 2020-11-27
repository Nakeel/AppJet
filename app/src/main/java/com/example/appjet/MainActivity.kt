package com.example.appjet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.ripple.RippleIndication
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.navigate
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import com.example.appjet.ui.AppJetTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState()

            AppJetTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(

                        scaffoldState = scaffoldState,
                        topBar = { TopAppBar(title = "Compose App", scaffoldState) },
                        bodyContent = { ScreenNavigation() },
                        drawerElevation = 12.dp,
                        drawerShape = CutCornerShape(topRight = 48.dp),
                        drawerScrimColor = MaterialTheme.colors.primary.copy(alpha = 0.3f),
                        drawerContent = { NavDrawer(scaffoldState = scaffoldState) }

                    )
                }
            }
        }
    }
}

@Composable
fun NavDrawer(scaffoldState: ScaffoldState) {
    Column {
        Surface(
            modifier = Modifier.preferredHeight(100.dp).fillMaxWidth(1f),
            shape = RoundedCornerShape(bottomLeft =30.dp),
            color = MaterialTheme.colors.primary
        ) {
            Column(modifier = Modifier.padding(horizontal = 0.dp,vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    text = "Header Title",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "Header Desc",
                    style = TextStyle(fontSize = 16.sp)
                )
            }
        }
        NavOption(title = "Home",icon = Icons.Default.Home, scaffoldState = scaffoldState)
        NavOption(title = "Cart",icon = Icons.Default.ShoppingCart, scaffoldState = scaffoldState)
        NavOption(title = "Like",icon = Icons.Default.Favorite, scaffoldState = scaffoldState)
        NavOption(title = "Featured",icon = Icons.Default.List, scaffoldState = scaffoldState)
    }
}

@Composable
fun UserCard() {
    val image = imageResource(id = R.drawable.header)
    val imageModifier = Modifier.preferredHeight(180.dp).fillMaxWidth().clip(RoundedCornerShape(5))
    Card(
        shape = RoundedCornerShape(6.dp)

    ) {
        Column(
            modifier = Modifier.clickable(onClick = {}).fillMaxWidth().padding(6.dp)
        ) {

            Row(
                modifier = Modifier.padding(horizontal = 10.dp,vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Surface(
                    modifier = Modifier.preferredSize(50.dp),
                    shape = CircleShape,

                    ) {
                    Image(asset = image,contentScale = ContentScale.Crop)
                }
                Spacer(modifier = Modifier.preferredWidth(20.dp))
                Column {
                    Text(text = "Kamilu Kompose", style = TextStyle( fontWeight = FontWeight.Bold, fontSize = 18.sp))

                    Text(text = "18 mins ago", style = TextStyle(color = Color.Gray,  fontSize = 15.sp))
                }

            }
            Image(asset = image, modifier = imageModifier, contentScale = ContentScale.Crop)
        }
    }




}

@Composable
fun NavOption(title: String,icon: VectorAsset, scaffoldState: ScaffoldState) {
    Row(
        modifier = Modifier.padding(horizontal = 20.dp,vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Icon(asset = icon)

        Text(
            text = title,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold,),
            modifier = Modifier.clickable(
                onClick = {
                    scaffoldState.drawerState.close()
                }, indication = RippleIndication(color = Color.Gray )
            ).padding(16.dp).fillMaxWidth()
        )
    }

}

@Composable
fun TopAppBar(title: String, scaffoldState: ScaffoldState) {
    TopAppBar(title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { scaffoldState.drawerState.open() }) {
                Icon(asset = Icons.Default.Menu)
            }
        }
    )
}

@Composable
fun NewsColumn(navController: NavHostController) {
//    fun NewsColumn() {

    val image = imageResource(id = R.drawable.header)
    val randFeed = listOf<String>("4","5","6","7")
    MaterialTheme() {
        val topography = MaterialTheme.typography

        Surface(Modifier.fillMaxSize().padding(horizontal = 16.dp,vertical = 4.dp)) {
            LazyColumnFor(randFeed) { item ->
                UserCard()
            }}


//    Column(
//        modifier = Modifier.padding(all = 16.dp)
//    ) {
//        val imageModifier = Modifier.preferredHeight(180.dp).fillMaxWidth().clip(RoundedCornerShape(5))
//
//        Image(asset = image, modifier = imageModifier, contentScale = ContentScale.Crop)
//
//        Spacer(modifier = Modifier.preferredHeight(20.dp))
//
//        Text(text = "First Text", style = topography.h6)
//        Text(text = "Second Text", style = topography.body2)
//        Text(text = "Third Text", style = topography.body2)
//
//        Spacer(modifier = Modifier.preferredHeight(10.dp))
//
//
//
//        Spacer(modifier = Modifier.preferredHeight(20.dp))
//
//        Button(onClick = {
//            navController.navigate("newscolumn2")
//        }) {
//            Text(text = "Navigate next")
//        }
//    }
}
}


@Composable
fun NewsColumn2(navController: NavHostController) {
    val image = imageResource(id = R.drawable.header)
    MaterialTheme() {
        val topography = MaterialTheme.typography

        Column(
            modifier = Modifier.padding(all = 16.dp)
        ) {
            val imageModifier = Modifier.preferredHeight(180.dp).fillMaxWidth().clip(RoundedCornerShape(5))

            Image(asset = image, modifier = imageModifier, contentScale = ContentScale.Crop)

            Spacer(modifier = Modifier.preferredHeight(20.dp))

            Text(
                "First Text3",
               style =  topography.h6
            )

            Text(text = "Second Text3", style = topography.body2)
            Text(text = "Third Text3", style = topography.body2)

            Spacer(modifier = Modifier.preferredHeight(10.dp))

            Button(onClick = { navController.navigate("newscolumn2") }) {
                Text(text = "Navigate next")
            }
        }
    }
}

@Composable
fun ScreenNavigation(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = "newscolumn1") {
        composable("newscolumn1") { NewsColumn(navController = navController) }
        composable("newscolumn2") { NewsColumn2(navController = navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppJetTheme {
//        Greeting("Android")
//        NewsColumn()
        ScreenNavigation()
    }
}