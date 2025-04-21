package com.example.test

import android.content.ClipDescription
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.test.ui.theme.Purple40
import com.example.test.ui.theme.TestTheme
import kotlinx.coroutines.internal.OpDescriptor

@Composable
fun GymsScreen (){
    val vm: GymsViewModel= viewModel()

    LazyColumn {
        items(vm.state){ gym->
            GymItem(gym) {
                vm.toggleFavouriteState( it)
            }
        }
    }


}

@Composable
fun GymItem(gym: Gym,onClick: (Int) -> Unit) {
        var isFavouriteState by remember { mutableStateOf(false) }
        var icon= if (gym.isFavourite){
            Icons.Filled.Favorite
        } else {
            Icons.Filled.FavoriteBorder
        }
    Card(modifier =Modifier.padding(8.dp),
            elevation = cardElevation(8.dp) ) {
             Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
                DefaultIcon(Icons.Filled.Place,Modifier.weight(0.15f),"location Icon")
                GymDetails(gym,Modifier.weight(0.70f))
                 DefaultIcon(icon,Modifier.weight(0.15f),"Favourite Icon",{
                     onClick(gym.id)
                 })
            }
        }
    }

@Composable
fun DefaultIcon(icon: ImageVector, modifier: Modifier,contentDescription: String,onClick:()-> Unit={}) {
    Image(
        imageVector = icon,
        contentDescription=contentDescription,
        modifier= Modifier.padding(8.dp).clickable{onClick()}
        , colorFilter = ColorFilter.tint(Color.DarkGray)
    )
}

@Composable
fun GymDetails(gym: Gym,modifier: Modifier) {
    Column (modifier=modifier){
        Text(text=gym.name, color = Purple40 )

        Text(gym.place,color = Color.Black )
    }

}


//@Preview(showBackground = true, showSystemUi = true, device = "id:pixel_9_pro_xl")
@Composable
fun GymsScreenpreview (){

        GymsScreen()

}