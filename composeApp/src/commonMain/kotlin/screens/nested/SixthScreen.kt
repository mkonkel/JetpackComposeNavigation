package screens.nested

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import navigation.ROUTE
import navigation.Screen

@Composable
fun SixthScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Nested")
        Text("Sixth screen")
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate(Screen.FourthScreen.withNameAndSurname("John", "Doe")) {
                popUpTo(ROUTE.NESTED)
            }
        }) {
            Text("John Doe Screen")
        }

        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}