package com.example.newsuktechtest.ui.coinDetail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.newsuktechtest.data.models.Coin
import com.example.newsuktechtest.data.models.CoinDetails

@Composable
fun ItemDetailDialog(coin: Coin, onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            coin.name?.let {
                Text(
                    text = it,
                    modifier = Modifier

                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                )
            }
            coin.coinDetails?.let {
                it.logo?.let { logo ->
                    Text(
                        text = logo,
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center),
                        textAlign = TextAlign.Center,
                    )
                }
                it.symbol?.let { symbol ->
                    Text(
                        text = symbol,
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center),
                        textAlign = TextAlign.Center,
                    )
                }
            }

        }
    }

}