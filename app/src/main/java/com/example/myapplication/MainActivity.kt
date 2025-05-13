package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var nota by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Contenedor principal centrado en la pantalla
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Card principal con borde azul claro
        Column(
            modifier = Modifier
                .width(280.dp)
                .background(Color(0xFF90CAF9))
                .border(1.dp, Color.Black)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título
            Text(
                text = "Parcial #1",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Nombres de estudiantes
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Angel Hernandez",
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Alexander Alvarado",
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // input para obtener el valor
            Text(
                text = "Ingrese la nota a validar",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo de entrada numerico
            OutlinedTextField(
                value = nota,
                onValueChange = {
                    // se hace validacion para evitar ingresar letras
                    if (it.isEmpty() || it.toDoubleOrNull() != null) {
                        nota = it
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón validar
            Button(
                onClick = {
                    val notaNum = nota.toFloatOrNull()
                    if (notaNum != null) {
                        resultado = when {
                            notaNum in 91.0..100.0 -> "A (Excelente)"
                            notaNum in 81.0..90.0 -> "B (Bueno)"
                            notaNum in 71.0..80.0 -> "C (Regular)"
                            notaNum in 61.0..70.0 -> "D (Más o menos regular)"
                            notaNum < 61.0 -> "No aprobado"
                            else -> "Nota fuera de rango"
                        }
                        Toast.makeText(context, "Resultado: $resultado", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Por favor, ingrese una nota válida",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2141F3))
            ) {
                Text(
                    text = "Validar",
                    fontSize = 16.sp
                )
            }

            // Mostrar el resultado si existe
            if (resultado.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Resultado: $resultado",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = when {
                        resultado.startsWith("A") -> Color.Green
                        resultado.startsWith("B") -> Color(0xFF4CAF50)
                        resultado.startsWith("C") -> Color(0xFFFFA000)
                        resultado.startsWith("D") -> Color(0xFFFF9800)
                        else -> Color.Red
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MyApplicationTheme {
        MainScreen()
    }
}