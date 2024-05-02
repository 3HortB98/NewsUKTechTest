package com.example.newsuktechtest.ui.coins

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.example.newsuktechtest.data.models.Coin

@Composable
fun CoinItem(coin: Coin, onClick:() -> Unit){
    Column(modifier = Modifier
        .padding(6.dp)
        .fillMaxWidth()
        .border(2.dp, SolidColor(Color.Black), shape = RoundedCornerShape(10.dp))
        .clickable { onClick() }){
        Row(modifier = Modifier.padding(6.dp)) {
            coin.name?.let { Text(text = it) }
        }

        }
}
