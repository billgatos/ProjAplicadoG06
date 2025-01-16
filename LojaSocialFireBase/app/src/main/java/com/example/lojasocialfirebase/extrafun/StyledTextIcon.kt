package com.example.lojasocialfirebase.extrafun

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.lojasocialfirebase.R
import com.example.lojasocialfirebase.ui.theme.steelBlue

@Composable
fun StyledTextIcon(
    text: String,
    color: Color = steelBlue, // Cor padrão
    fontSize: Int = 18, // Tamanho padrão
    fontWeight: FontWeight = FontWeight.Bold, // Peso padrão
    modifier: Modifier = Modifier // Modificador opcional
) {
    val spectralFont = FontFamily(
        Font(R.font.spectralextrabold)
    )
    Text(
        text = text,
        color = color,
        fontSize = fontSize.sp,
        fontWeight = fontWeight,
        fontFamily = spectralFont,
        modifier = modifier
    )
}
