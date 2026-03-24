package enzoluchini.com.github.screennavigation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PedidosScreen(modifier: Modifier = Modifier, navController: NavController, cliente: String?) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta)
            .padding(32.dp)
    ) {
        Text(
            text = "tela de pedido", fontSize = 20.sp, fontWeight = FontWeight.Thin
        )
        Button(
            onClick = { navController.navigate("menu") },
            colors = ButtonDefaults.buttonColors(Color.DarkGray),
            modifier = modifier.align(Alignment.Center)
        ) {
            Text(text = "Pedido - $cliente", fontSize = 20.sp)
        }
    }
}