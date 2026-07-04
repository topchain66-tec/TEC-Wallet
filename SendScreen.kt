package com.tecwallet.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.math.BigDecimal

@Composable
fun SendTECScreen(onTransactionSent: (String) -> Unit) {
    var recipient by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var isSending by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Send TEC", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = recipient,
            onValueChange = { recipient = it },
            label = { Text("Recipient Address (TEC...)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount (TEC)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                isSending = true
                // Simulate network delay
                kotlinx.coroutines.MainScope().launch {
                    kotlinx.coroutines.delay(1500)
                    val txHash = "0x" + (1..64).map { "0123456789abcdef".random() }.joinToString("")
                    onTransactionSent(txHash)
                    isSending = false
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = recipient.startsWith("TEC") && amount.isNotEmpty() && !isSending
        ) {
            if (isSending) {
                CircularProgressIndicator(modifier = Modifier.size(20.dp))
            } else {
                Text("Send TEC")
            }
        }
    }
}
