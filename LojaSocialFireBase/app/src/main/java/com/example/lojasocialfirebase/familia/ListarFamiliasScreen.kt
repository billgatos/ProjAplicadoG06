package com.example.lojasocialfirebase.familia

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lojasocialfirebase.extrafun.CountryData


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListarFamiliasScreen(
    familiaViewModel: FamiliaViewModel,
    topBarTitle: String = "Lista de Famílias",
    compactBars: Boolean = false
) {
    var familias by remember { mutableStateOf<List<Familia>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    // Buscar as famílias do Firebase
    LaunchedEffect(Unit) {
        familias = familiaViewModel.getFamilias()
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(topBarTitle, color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF56C596)),
                modifier = if (compactBars) Modifier.height(48.dp) else Modifier
            )
        },
        containerColor = Color(0xFFF1F8E9)
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color(0xFF56C596))
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(familias) { familia ->
                        FamiliaCard(familia)
                    }
                }
            }
        }
    }
}

@Composable
fun FamiliaCard(familia: Familia) {
    // Busca os detalhes do país com base no código
    val country = CountryData.getCountryByCode(familia.paisCodigo)

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Nome: ${familia.nome}",
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "ID: ${familia.idFamilia}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Exibir bandeira e nome do país se o código for válido
            country?.let {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = it.flag),
                        contentDescription = "Bandeira de ${it.name}",
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }
            } ?: Text(
                text = "País desconhecido",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListarFamiliasScreen() {
    // Lista fictícia de famílias simulada
    val familiasMock = listOf(
        Familia(nome = "Família Silva", idFamilia = "1", paisCodigo = "BR"),
        Familia(nome = "Família Haddad", idFamilia = "2", paisCodigo = "LB"),
        Familia(nome = "Família Ivanov", idFamilia = "3", paisCodigo = "RS"),
        Familia(nome = "Família Costa", idFamilia = "4", paisCodigo = "PT")
    )

    // Criação de um estado simulado para a prévia
    val familias = remember { familiasMock }

    // Exibição da tela com os dados simulados
    ListarFamiliasScreenPreviewContent(familias)
}

@Composable
fun ListarFamiliasScreenPreviewContent(familias: List<Familia>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(familias) { familia ->
            FamiliaCard(familia)
        }
    }
}
