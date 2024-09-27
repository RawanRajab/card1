package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Contact( val phone: String ,val address: String, val name: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactListApp()
        }
    }
}

@Composable
fun ContactListApp() {
    val contacts = listOf(
        Contact("123  St A", "123-456-7890", "John Doe"),
        Contact("456  St B", "987-654-3210", "Bob"),
        Contact("789  St E", "555-555-5555", "Alice Cooper"),
        Contact("123  St A", "123-456-7890", "John Doe"),
        Contact("456  St B", "987-654-3210", "Bob"),
        Contact("789  St E", "555-555-5555", "Alice Cooper"),

    )


    var selectedContact by remember { mutableStateOf<Contact?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        contacts.forEach { contact ->
            ContactItem(contact) { selectedContact = contact }
        }

        selectedContact?.let {
            ContactDetailBox(contact = it, onClose = { selectedContact = null })
        }
    }
}

@Composable
fun ContactItem(contact: Contact, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick)
            .padding(1.dp)
            .background(Color.DarkGray, shape = RoundedCornerShape(10.dp))

    )
    {
        Column(

            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = contact.address,
                fontSize = 16.sp,
                color = Color.White
            )
            Text(
                text = contact.phone,
                fontSize = 16.sp,
                color = Color.White
            )
        }


        Text(
            text = contact.name,
            modifier = Modifier.weight(1f).padding(start = 8.dp),
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.End // Align text to the end
        )
    }
}

@Composable
fun ContactDetailBox(contact: Contact, onClose: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Card(
            modifier = Modifier.size(300.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color.Red),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Contact Details", fontSize = 20.sp, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Name: ${contact.name}", fontSize = 16.sp)
                Text(text = "Phone: ${contact.phone}", fontSize = 16.sp)
                Text(text = "Address: ${contact.address}", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onClose) {
                    Text("Close")
                }
            }
        }
    }
}