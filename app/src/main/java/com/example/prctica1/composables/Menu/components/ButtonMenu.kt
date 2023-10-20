package com.example.prctica1.composables.Menu.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.prctica1.R

@Composable
fun MenuButton(onCreaturesButtonClick: () -> Unit, text: String){
    Button(
        onClick = { onCreaturesButtonClick ()},
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.height(40.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}