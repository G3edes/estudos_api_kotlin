package br.senai.sp.jandira.rick_morty.Screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CharacterCard(
    name: String = "Nome do personagem",
    specie: String = "Espécie do personagem",
    status: String = "Status",
    image: String = "Url da imagem"
) {
    Card(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth()
            .height(100.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0x2F31FF19))
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(90.dp),
                shape = CircleShape
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = "Imagem do personagem",
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = name)
                Text(text = specie)
                Text(text = status)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterCardPreview() {
    CharacterCard(
        name = "Rick Sanchez",
        specie = "Humano",
        status = "Vivo",
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
    )
}

