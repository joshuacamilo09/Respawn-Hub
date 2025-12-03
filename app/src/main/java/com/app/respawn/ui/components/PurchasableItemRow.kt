package com.app.respawn.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.respawn.R
import com.app.respawn.model.PurchasableItem
import com.app.respawn.repository.GameRepo
import com.app.respawn.ui.theme.RespawnTheme

@Composable
fun PurchasableItemRow(item: PurchasableItem, onClick: () -> Unit){

    val context = LocalContext.current
    val iconId = context.resources.getIdentifier(item.iconResName, "drawable", context.packageName)


    val painter = if (iconId != 0){
        painterResource(id = iconId)
    }
    else {
        painterResource(id = R.drawable.icon_error)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {onClick()},
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Card (
            shape = MaterialTheme.shapes.extraSmall,
            colors = CardDefaults.cardColors(containerColor = Color.LightGray),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            Image (
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(65.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
        }
        Column(modifier = Modifier.weight(1f))
        {
            Text(text = item.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(text = item.description, style = MaterialTheme.typography.bodyMedium, maxLines = 2)

            Spacer(modifier = Modifier.height(10.dp)) //espaçamento

            Text(text = "$${String.format("%.2f", item.price)}", style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, modifier = Modifier.align (
                Alignment.End))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PurchasableItemRowPreview() {
    RespawnTheme {
        PurchasableItemRow(
            item = GameRepo.games[0].purchasableItem[0],
            onClick = {}
        )
    }
}
