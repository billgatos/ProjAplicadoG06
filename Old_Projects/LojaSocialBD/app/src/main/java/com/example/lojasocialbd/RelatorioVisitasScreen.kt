package com.example.lojasocialbd
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.util.*

@Composable
fun RelatorioVisitasScreen(onVoltarClick: () -> Unit) {
    var dataInicio by remember { mutableStateOf(LocalDate.now()) }
    var dataFim by remember { mutableStateOf(LocalDate.now()) }
    var visitasPorNacionalidade by remember { mutableStateOf(listOf<VisitaPorNacionalidade>()) }
    var showResults by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Relatório de Visitas por Nacionalidade", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Seleção de Data de Início
        Text(text = "Data de Início")
        DatePickerDialogComponent(selectedDate = dataInicio) { selectedDate ->
            dataInicio = selectedDate
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Seleção de Data de Fim
        Text(text = "Data de Fim")
        DatePickerDialogComponent(selectedDate = dataFim) { selectedDate ->
            dataFim = selectedDate
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para gerar o relatório
        Button(onClick = {
            // Aqui você chamaria uma função para buscar os dados do banco de dados
            visitasPorNacionalidade = gerarRelatorioVisitasPorNacionalidade(dataInicio, dataFim)
            showResults = true
        }) {
            Text("Gerar Relatório")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Exibir resultados após a consulta
        if (showResults) {
            Text("Visitas por Nacionalidade", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(visitasPorNacionalidade) { item ->
                    Text("${item.nacionalidade}: ${item.quantidade} visitas")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para voltar ao menu
        Button(onClick = { onVoltarClick() }) {
            Text("Voltar")
        }
    }
}

@Composable
fun DatePickerDialogComponent(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // Inicialize o DatePickerDialog com a data atual
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            onDateSelected(LocalDate.of(year, month + 1, dayOfMonth)) // Atualiza a data escolhida
        },
        selectedDate.year,
        selectedDate.monthValue - 1,
        selectedDate.dayOfMonth
    )

    // Botão para abrir o DatePickerDialog
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = selectedDate.toString(), modifier = Modifier.padding(8.dp))
        Button(onClick = { datePickerDialog.show() }) {
            Text("Selecionar Data")
        }
    }
}

// Simulação de função que gera o relatório (substitua por uma chamada real ao seu banco de dados)
fun gerarRelatorioVisitasPorNacionalidade(dataInicio: LocalDate, dataFim: LocalDate): List<VisitaPorNacionalidade> {
    // Aqui você filtraria os dados reais com base nas datas fornecidas e retornaria as visitas agrupadas por nacionalidade
    return listOf(
        VisitaPorNacionalidade("Portugal", 5),
        VisitaPorNacionalidade("Brasil", 3),
        VisitaPorNacionalidade("Angola", 7)
    )
}

// Simulação de um modelo de dado para visitas por nacionalidade
data class VisitaPorNacionalidade(val nacionalidade: String, val quantidade: Int)
