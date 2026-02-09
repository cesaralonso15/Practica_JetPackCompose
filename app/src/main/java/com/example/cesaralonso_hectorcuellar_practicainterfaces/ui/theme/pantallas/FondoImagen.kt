import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.cesaralonso_hectorcuellar_practicainterfaces.R

// Este composable pone una imagen de fondo en toda la pantalla
// y encima una capa oscura para que se lea bien el texto.
@Composable
fun FondoImagen(content: @Composable () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {

        // Imagen a pantalla completa
        Image(
            painter = painterResource(R.drawable.fondo_libros),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Capa oscura para contraste (si no, no se lee bien)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.35f))
        )

        // Contenido de la pantalla por encima
        content()
    }
}