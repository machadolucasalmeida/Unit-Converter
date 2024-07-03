package com.lucas.unitconverter

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucas.unitconverter.ui.theme.UnitConverterTheme
import com.lucas.unitconverter.ui.theme.backgroundDarkMain
import com.lucas.unitconverter.ui.theme.btColor
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    UnitConverter()
            }
            }
        }
    }
}


@Composable
    fun UnitConverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val iConversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }
    val iButtonLabel = remember { mutableStateOf("Select") }
    val oButtonLabel = remember { mutableStateOf("Select") }

    fun ConverterUnits(){
        val inputValueDoble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDoble * iConversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

        Column (modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Text(text = "Unit Converter")
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = inputValue,
                onValueChange = {
                    inputValue = it
                    ConverterUnits()},
                label = { Text(text = "Enter Value")})
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Box{
                    Button(onClick = {iExpanded = true}, colors = ButtonDefaults.buttonColors(btColor)) {
                        Text("${iButtonLabel.value}", color = Color.White)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                    }
                    DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded = false}) {
                        DropdownMenuItem(
                            text = { Text(text = "Cemtimeters")},
                            onClick = {
                                inputUnit = "Centimeters"
                                iExpanded = false
                                iConversionFactor.value = 0.01
                                iButtonLabel.value = "Cm"
                                ConverterUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Meters")},
                            onClick = {
                                inputUnit = "Meters"
                                iExpanded = false
                                iButtonLabel.value = "Mts"
                                iConversionFactor.value = 1.00
                                ConverterUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Feet")},
                            onClick = {
                                iExpanded = false
                                inputUnit = "Feet"
                                iButtonLabel.value = "Ft"
                                iConversionFactor.value =0.3048
                                ConverterUnits()
                            })
                        DropdownMenuItem(
                            text = { Text(text = "Milimeters")},
                            onClick = {
                                inputUnit = "Milimeters"
                                iExpanded = false
                                iButtonLabel.value = "MM"
                                iConversionFactor.value =0.001
                                ConverterUnits()
                            })
                    }

                }
                Spacer(modifier = Modifier.width(32.dp))
                Box {
                    Button(onClick = {oExpanded = true}, colors = ButtonDefaults.buttonColors(btColor)){
                        Text("${oButtonLabel.value}", color = Color.White)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                    }
                    DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded = false}) {
                        DropdownMenuItem(
                            text = { Text(text = "Cemtimeters")},
                            onClick = {
                                outputUnit = "Centimeters"
                                oExpanded = false
                                oButtonLabel.value = "Cm"
                                iButtonLabel.value = "Cm"
                                oConversionFactor.value = 0.01
                                ConverterUnits()
                            })
                        DropdownMenuItem(
                            text = { Text(text = "Meters")},
                            onClick = {
                                outputUnit = "Meters"
                                oExpanded = false
                                oButtonLabel.value = "Mts"
                                oConversionFactor.value = 1.00
                                ConverterUnits()
                            })
                        DropdownMenuItem(
                            text = { Text(text = "Feet")},
                            onClick = {
                                outputUnit = "Feet"
                                oExpanded = false
                                oButtonLabel.value = "Ft"
                                oConversionFactor.value =0.3048
                                ConverterUnits()
                            })
                        DropdownMenuItem(
                            text = { Text(text = "Milimeters")},
                            onClick = {
                                outputUnit = "Milimeters"
                                oExpanded = false
                                oButtonLabel.value = "MM"
                                oConversionFactor.value =0.001
                                ConverterUnits()
                            })
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Result: ${outputValue}")
        }
    }


@Preview(showBackground =true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}