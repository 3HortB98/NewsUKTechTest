package com.example.newsuktechtest.ui.coinDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsuktechtest.data.models.Coin
import com.example.newsuktechtest.data.models.CoinDetails
import com.example.newsuktechtest.data.models.Whitepaper

@Composable
fun ItemDetailDialog(coin: Coin, onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                coin.name?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(6.dp)
                            .wrapContentSize(Alignment.Center),
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp
                    )
                }
                Divider(thickness = 1.dp, color = Color.Black)
                Row {
                    Column {
                        Row {
                            coin.let {
                                it.coinDetails?.symbol?.let { symbol ->
                                    Text(
                                        text = symbol,
                                        modifier = Modifier
                                            .padding(6.dp)
                                            .wrapContentSize(Alignment.Center),
                                        textAlign = TextAlign.Center,
                                    )
                                }
                                it.type?.let { type ->
                                    Text(
                                        text = "Type: $type",
                                        modifier = Modifier
                                            .padding(6.dp)
                                            .wrapContentSize(Alignment.Center),
                                        textAlign = TextAlign.Center,
                                    )
                                }
                            }
                        }
                        coin.coinDetails?.let {
                            it.description?.let { desc ->
                                Text(
                                    text = desc,
                                    modifier = Modifier
                                        .padding(6.dp)
                                        .wrapContentSize(Alignment.Center),
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }

                    }

                    Column(
                        Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        coin.coinDetails?.logo.let {
                            AsyncImage(
                                it, contentDescription = "logo", modifier = Modifier
                                    .padding(8.dp)
                                    .height(60.dp)
                                    .width(60.dp)
                                    .wrapContentSize(Alignment.Center)
                            )
                        }
                    }
                }
                Column{
                    coin.coinDetails?.let {
                        Text(
                            text = "Active: ${it.isActive}",
                            modifier = Modifier
                                .padding(6.dp)
                                .wrapContentSize(Alignment.Center),
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = "Open Source: ${it.openSource}",
                            modifier = Modifier
                                .padding(6.dp)
                                .wrapContentSize(Alignment.Center),
                            textAlign = TextAlign.Center,
                        )
                        if (!it.developmentStatus.isNullOrEmpty()){
                           Text(
                                text = "Development Status: ${it.developmentStatus}",
                                modifier = Modifier
                                    .padding(6.dp)
                                    .wrapContentSize(Alignment.Center),
                                textAlign = TextAlign.Center,
                            )
                        }
                        if (!it.startedAt.isNullOrEmpty()){
                            Text(
                                text = "Started at: ${it.startedAt}",
                                modifier = Modifier
                                    .padding(6.dp)
                                    .wrapContentSize(Alignment.Center),
                                textAlign = TextAlign.Center,
                            )
                        }


                    }
                }

            }
        }
    }

}

@Preview
@Composable
fun ItemDetailsDialogPreview() {
    ItemDetailDialog(
        coin = Coin(
            name = "Coin",
            type = "token",
            id = "test",
            coinDetails = CoinDetails(
                symbol = "C",
                logo = "https://static.coinpaprika.com/coin/btc-bitcoin/logo.png",
                isActive = true,
                openSource = false,
                description = "some test of some sort. some more words to make it a description",
                developmentStatus = "working product",
                startedAt = "2009-01-03T00:00:00Z",
                whitepaper = Whitepaper(
                    link = "link",
                    thumbnail = "thumbnail"
                )
            )

        )
    ) {

    }
}
//Coin(
//                name = coinDTO.name,
//                id = coinDTO.id,
//                type = coinDTO.type,
//                coinDetails = CoinDetails(
//                    symbol = coinDTO.symbol,
//                    logo = coinDTO.logo,
//                    isActive = coinDTO.isActive,
//                    openSource = coinDTO.openSource,
//                    whitepaper = Whitepaper(
//                        link = coinDTO.whitepaper.link,
//                        thumbnail = coinDTO.whitepaper.thumbnail
//                    )
//                )
//            )
