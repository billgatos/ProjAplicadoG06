package com.example.lojasocialfirebase.visita


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.lojasocialfirebase.extrafun.CountryData
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document

fun generatePdfReport(data: Map<String, Int>, filePath: String) {
    val pdfWriter = PdfWriter(filePath)
    val pdfDocument = PdfDocument(pdfWriter)
    val document = Document(pdfDocument)

    document.add(com.itextpdf.layout.element.Paragraph("Relatório de Visitas").setBold().setFontSize(20f))
    document.add(com.itextpdf.layout.element.Paragraph("\n"))

    data.forEach { (code, count) ->
        val countryName = CountryData.countries.find { it.code == code }?.name ?: code
        document.add(com.itextpdf.layout.element.Paragraph("$countryName: $count visitas"))
    }

    document.close()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RelatorioVisitasScreen(
    visitaViewModel: VisitaViewModel
) {
    var visitasGrouped by remember { mutableStateOf<Map<String, Int>>(emptyMap()) }
    var isLoading by remember { mutableStateOf(true) }
    val context = LocalContext.current

    // Buscar os dados do Firebase
    LaunchedEffect(Unit) {
        visitaViewModel.getVisitasGroupedByNationality { grouped ->
            visitasGrouped = grouped
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Relatório de Visitas", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF56C596))
            )
        },
        containerColor = Color(0xFFF1F8E9)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween // Distribui os itens verticalmente
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF56C596))
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f) // Faz a lista ocupar o máximo de espaço possível
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(visitasGrouped.entries.toList()) { entry ->
                        RelatorioCard(nationalityCode = entry.key, count = entry.value)
                    }
                }

                // Botão para exportar PDF
                Button(
                    onClick = {
                        val filePath = "${context.filesDir}/RelatorioVisitas.pdf"
                        generatePdfReport(visitasGrouped, filePath)
                        Toast.makeText(context, "Relatório exportado para: $filePath", Toast.LENGTH_LONG).show()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF56C596)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp) // Espaçamento entre a lista e o botão
                ) {
                    Text("Exportar Relatório em PDF", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun RelatorioCard(nationalityCode: String, count: Int) {
    val country = CountryData.countries.find { it.code == nationalityCode }

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8E9)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "País: ${country?.name ?: nationalityCode}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Quantidade de Visitas: $count",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
