package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.util.Locale


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme{
                UnitConverter()
            }
        }
    }
}

@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("")}
    var outputValue by remember { mutableStateOf("")}
    var inputUnit by remember { mutableStateOf("Meter")}
    var outputUnit by remember { mutableStateOf("Meter")}
    var inputExpanded by remember { mutableStateOf(false)}
    var outputExpanded by remember { mutableStateOf(false)}
    val conversionFactor = remember { mutableStateOf(1.0)}
    val oConversionFactor = remember { mutableStateOf(1.0)}


    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        outputValue = String.format(Locale.US, "%.6f", inputValueDouble * conversionFactor.value / oConversionFactor.value).trimEnd('0').trimEnd('.')
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {

        Text("Unit Converter", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)

        Spacer(modifier=Modifier.height(32.dp))

        OutlinedTextField(
            value = inputValue,
            label = { Text("Enter a value") } ,
            onValueChange = {
                inputValue = it
                convertUnits()
            })

        Spacer(modifier=Modifier.height(16.dp))

        Row {
            //Input Box
            Box {
                //Input Button
                Button(onClick = {inputExpanded = true}) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
                DropdownMenu(expanded = inputExpanded, onDismissRequest = {inputExpanded = false}) {
                    DropdownMenuItem(text = { Text("Kilometer") },
                        onClick = {
                            inputUnit = "Kilometer"
                            inputExpanded = false
                            conversionFactor.value = 1000.0
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Meter") },
                        onClick = {
                            inputUnit = "Meter"
                            inputExpanded = false
                            conversionFactor.value = 1.0
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Centimeter") },
                        onClick = {
                            inputUnit = "Centimeter"
                            inputExpanded = false
                            conversionFactor.value = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Millimeter") },
                        onClick = {
                            inputUnit = "Millimeter"
                            inputExpanded = false
                            conversionFactor.value = 0.001
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Mile") },
                        onClick = {
                            inputUnit = "Mile"
                            inputExpanded = false
                            conversionFactor.value = 1609.344
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Foot") },
                        onClick = {
                            inputUnit = "Foot"
                            inputExpanded = false
                            conversionFactor.value = 0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Inch") },
                        onClick = {
                            inputUnit = "Inch"
                            inputExpanded = false
                            conversionFactor.value = 0.0254
                            convertUnits()
                        })
                }
            }

            Spacer(modifier=Modifier.width(16.dp))

            //Output Box
            Box {
                //Output Button
                Button(onClick = {outputExpanded = true} ) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)

                }
                DropdownMenu(expanded = outputExpanded, onDismissRequest = {outputExpanded = false}) {
                    DropdownMenuItem(text = { Text("Kilometer") },
                        onClick = {
                            outputUnit = "Kilometer"
                            outputExpanded = false
                            oConversionFactor.value = 1000.0
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Meter") },
                        onClick = {
                            outputUnit = "Meter"
                            outputExpanded = false
                            oConversionFactor.value = 1.0
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Centimeter") },
                        onClick = {
                            outputUnit = "Centimeter"
                            outputExpanded = false
                            oConversionFactor.value = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Millimeter") },
                        onClick = {
                            outputUnit = "Millimeter"
                            outputExpanded = false
                            oConversionFactor.value = 0.001
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Mile") },
                        onClick = {
                            outputUnit = "Mile"
                            outputExpanded = false
                            oConversionFactor.value = 1609.344
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Foot") },
                        onClick = {
                            outputUnit = "Foot"
                            outputExpanded = false
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        })
                    DropdownMenuItem(text = { Text("Inch") },
                        onClick = {
                            outputUnit = "Inch"
                            outputExpanded = false
                            oConversionFactor.value = 0.0254
                            convertUnits()
                        })
                }
            }
        }
        Spacer(modifier=Modifier.height(32.dp))

        if (outputValue.isNotEmpty()) {
            Text(
                "Result: $outputValue $outputUnit",
                fontSize = 22.sp,
            )
        }
    }
}

@Preview(showBackground = true)     //showBackground = true is used to show the background
@Composable                         //of the app in the preview
fun UnitConverterPreview() {
    UnitConverter()
}