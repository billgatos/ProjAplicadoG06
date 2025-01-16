package com.example.lojasocialfirebase.visita


import android.content.Context
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.unit.dp
import com.example.lojasocialfirebase.extrafun.CountryData
import com.example.lojasocialfirebase.ui.theme.CustomTextField
import com.example.lojasocialfirebase.ui.theme.backgroundColor
import com.example.lojasocialfirebase.ui.theme.buttonColor
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document

import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

// Função para gerar o PDF com datas no filtro
fun generatePdfReport(data: Map<String, Int>, context: Context, startDate: String?, endDate: String?) {

    // Define o caminho para a pasta pública Downloads
    val downloadsDir =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    val filePath = File(downloadsDir, "RelatorioVisitas.pdf")

    // Certifica de que o diretório de downloads existe
    if (!downloadsDir.exists()) {
        downloadsDir.mkdirs()
    }

    try {
        // Criação do PDF
        val pdfWriter = PdfWriter(filePath)
        val pdfDocument = PdfDocument(pdfWriter)
        val document = Document(pdfDocument)

        // Adiciona título e datas do filtro ao PDF
        document.add(
            com.itextpdf.layout.element.Paragraph("Relatório de Visitas")
                .setBold()
                .setFontSize(20f)
        )
        document.add(com.itextpdf.layout.element.Paragraph("\n"))

        // Adiciona as datas do filtro, se disponíveis
        val filtroTexto = buildString {
            append("Período:")
            append(" ")
            append(startDate ?: "Não especificada")
            append(" a ")
            append(endDate ?: "Não especificada")
        }
        document.add(
            com.itextpdf.layout.element.Paragraph(filtroTexto)
                .setItalic()
                .setFontSize(14f)
        )
        document.add(com.itextpdf.layout.element.Paragraph("\n"))

        // Adiciona conteúdo do relatório
        data.forEach { (code, count) ->
            val countryName = CountryData.countries.find { it.code == code }?.name ?: code
            document.add(com.itextpdf.layout.element.Paragraph("$countryName: $count visitas"))
        }

        // Fecha o documento
        document.close()

        // Notifica o usuário
        Toast.makeText(context, "Relatório salvo em: ${filePath.absolutePath}", Toast.LENGTH_LONG)
            .show()
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Erro ao exportar o relatório", Toast.LENGTH_LONG).show()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RelatorioVisitasScreen(
    visitaViewModel: VisitaViewModel,
    topBarTitle: String = "Relatório de Visitas",
    compactBars: Boolean = false
) {
    var visitasGrouped by remember { mutableStateOf<Map<String, Int>>(emptyMap()) }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        visitaViewModel.getVisitasGroupedByNationality { grouped ->
            visitasGrouped = grouped
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(topBarTitle, color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = buttonColor),
                modifier = if (compactBars) Modifier.height(48.dp) else Modifier
            )
        },
        containerColor = backgroundColor
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF56C596))
                }
            } else {
                // Campos de seleção de data
                Column {
                    CustomTextField(
                        value = startDate,
                        onValueChange = { startDate = it },
                        label = "Data Inicial (yyyy-MM-dd)"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomTextField(
                        value = endDate,
                        onValueChange = { endDate = it },
                        label = "Data Final (yyyy-MM-dd)"
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Botões "Filtrar" e "Limpar"
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {
                                val start = startDate.toStartOfDay()
                                val end = endDate.toEndOfDay()
                                isLoading = true
                                visitaViewModel.getVisitasByDateRange(start, end) { grouped ->
                                    visitasGrouped = grouped
                                    isLoading = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF56C596))
                        ) {
                            Text("Filtrar")
                        }

                        Button(
                            onClick = {
                                startDate = ""
                                endDate = ""
                                isLoading = true
                                visitaViewModel.getVisitasGroupedByNationality { grouped ->
                                    visitasGrouped = grouped
                                    isLoading = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
                        ) {
                            Text("Limpar", color = Color.White)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Lista de visitas filtradas
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(bottom = paddingValues.calculateBottomPadding() + 64.dp)
                ) {
                    items(visitasGrouped.entries.toList()) { entry ->
                        RelatorioCard(nationalityCode = entry.key, count = entry.value)
                    }
                }

                // Botão para exportar PDF
                Button(
                    onClick = {
                        generatePdfReport(visitasGrouped, context, startDate, endDate) // Gera PDF com datas do filtro
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF56C596)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = paddingValues.calculateBottomPadding() + 32.dp)
                ) {
                    Text("Exportar Relatório em PDF", color = Color.White)
                }
            }
        }
    }
}


fun String.toStartOfDay(): Date? {
    return try {
        val calendar = Calendar.getInstance().apply {
            time = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this@toStartOfDay)!!
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 1)
            set(Calendar.MILLISECOND, 0)
        }
        calendar.time
    } catch (e: Exception) {
        null
    }
}

fun String.toEndOfDay(): Date? {
    return try {
        val calendar = Calendar.getInstance().apply {
            time = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this@toEndOfDay)!!
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECOND, 999)
        }
        calendar.time
    } catch (e: Exception) {
        null
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


