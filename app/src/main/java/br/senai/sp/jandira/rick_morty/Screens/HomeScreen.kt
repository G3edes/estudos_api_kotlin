package br.senai.sp.jandira.rick_morty.Screens

import br.senai.sp.jandira.rick_morty.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.rick_morty.model.Character
import br.senai.sp.jandira.rick_morty.model.Result
import br.senai.sp.jandira.rick_morty.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HomeSreen(modifier:Modifier=Modifier){

    //variavel que guarada a lista de personagens
    //devolvidas pela API
    var characterList = remember {
        mutableStateOf(listOf<Character>())
    }

//Obeter um Retrofit Factory
    var callCharaters = RetrofitFactory()
        .getCharacterService()
        .listAll()

    //Enviar a requisicao
    callCharaters.enqueue(object : Callback<Result>{

        override fun onResponse(p0: Call<Result>, response: Response<Result>) {
            characterList.value=response.body()!!.results

        }

        override fun onFailure(p0: Call<Result>, p1: Throwable) {
            TODO("Not yet implemented")
        }
    })

    Box(
        modifier=Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter= painterResource(R.drawable.rickmortybackgrounf),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Box(
            modifier=Modifier
                .fillMaxSize()
                .background(Color(0x88000000))
        )
        Column(
            modifier=Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
                Text(
                    text = "Rick And Morty",
                    color = Color.White,
                    fontSize = (50.sp),
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.fillMaxWidth(),
                )
            Spacer(
                modifier=Modifier
                    .height(16.dp)
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            )
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = "Character List",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = (30.sp)
            )
            Spacer(
                modifier=Modifier
                    .height(16.dp)
            )
            LazyColumn {
                items(characterList.value){
                    CharacterCard(
                        name = it.name,
                        status= it.status,
                        specie= it.species,
                        image = it.image
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomePreview(){
    HomeSreen()
}