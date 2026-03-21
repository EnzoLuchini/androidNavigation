package enzoluchini.com.github.screennavigation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MenuScreen(modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .padding(20.dp)
    ) {
        Text(text = "tela de menu", fontSize = 20.sp)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Button(
                onClick = { navController.navigate("pedido") })
            {
                Text(text = "Ir para a pagina de pedido")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate("perfil") })
            {
                Text(text = "Ir para a pagina de perfil")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { navController.navigate("login") })
            {
                Text(text = "Ir para a pagina de login")
            }
        }
    }
}