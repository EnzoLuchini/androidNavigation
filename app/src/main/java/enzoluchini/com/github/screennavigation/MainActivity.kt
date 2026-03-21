package enzoluchini.com.github.screennavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import enzoluchini.com.github.screennavigation.screens.LoginScreen
import enzoluchini.com.github.screennavigation.screens.MenuScreen
import enzoluchini.com.github.screennavigation.screens.PedidoScreen
import enzoluchini.com.github.screennavigation.screens.PerfilScreen
import enzoluchini.com.github.screennavigation.ui.theme.ScreenNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScreenNavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController();
                    NavHost(navController = navController, startDestination = "login") {
                        composable(route = "login") {
                            LoginScreen(
                                modifier = Modifier.padding(innerPadding),
                                 navController = navController
                            )
                        }
                        composable(route = "perfil") {
                            PerfilScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )

                        }
                        composable(route = "pedido") {
                            PedidoScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                        }
                        composable("menu") {
                            MenuScreen(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}