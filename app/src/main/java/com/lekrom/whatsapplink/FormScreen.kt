package com.lekrom.whatsapplink

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lekrom.whatsapplink.ui.theme.WhatsappLinkTheme

private const val MAX_DDD_CHAR = 2
private const val MIN_CELL_CHAR = 8
private const val MAX_CELL_CHAR = 9

@Composable
fun FormScreen(
    action: (ddd: String, cellNumber: String) -> Unit
) {
    var ddd by rememberSaveable {
        mutableStateOf("")
    }
    var cellNumber by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            EditText(
                hint = stringResource(R.string.ddd),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .width(72.dp),
                maxChar = MAX_DDD_CHAR,
                value = ddd
            ) { ddd = it }
            EditText(
                hint = stringResource(R.string.cell_phone),
                modifier = Modifier
                    .padding(end = 16.dp)
                    .fillMaxWidth(),
                maxChar = MAX_CELL_CHAR,
                value = cellNumber
            ) { cellNumber = it }
        }
        Button(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            onClick = {
                if (ddd.length == MAX_DDD_CHAR && cellNumber.length >= MIN_CELL_CHAR) {
                    action(ddd, cellNumber)
                }
            }
        ) {
            Text(text = stringResource(R.string.open))
        }
    }
}

@Composable
fun EditText(
    hint: String,
    value: String,
    modifier: Modifier,
    maxChar: Int,
    callBack: (text: String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        value = value,
        label = { Text(text = hint) },
        onValueChange = {
            if (it.length <= maxChar) {
                callBack(it)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WhatsappLinkTheme {
        FormScreen { _, _ -> }
    }
}