package org.yassineabou.playground.feature.chat.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.yassineabou.playground.app.core.theme.colorSchemeCustom
import org.yassineabou.playground.feature.chat.model.aiProvidersMap
import org.yassineabou.playground.feature.chat.ui.ChatViewModel

@Composable
fun AIProvidersFilterMenu(
    chatViewModel: ChatViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedAiProviders by chatViewModel.selectedAIProviders.collectAsState()

    Column {
        FilterButton(expanded) { expanded = !expanded }
        FilterDropdown(expanded, selectedAiProviders, chatViewModel) { expanded = false }
    }
}

@Composable
private fun FilterButton(
    expanded: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.LightGray
        )
    ) {
        Text(
            text = "Filter",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
            contentDescription = null
        )
    }
}

@Composable
private fun FilterDropdown(
    expanded: Boolean,
    selectedAiProviders: Map<String, Boolean>,
    chatViewModel: ChatViewModel,
    onDismiss: () -> Unit
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismiss,
        modifier = Modifier.background(Color.DarkGray)
    ) {
        DropdownHeader()
        selectedAiProviders.forEach { (model, isSelected) ->
            AIProviderItem(
                name = model,
                iconRes = getIconForProvider(model),
                isSelected = isSelected
            ) {
                chatViewModel.toggleAIProvider(model)
            }
        }
    }
}

@Composable
private fun DropdownHeader() {
    Text(
        "AI PROVIDERS",
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = Color.LightGray,
        modifier = Modifier.padding(8.dp)
    )
}

@Composable
private fun AIProviderItem(
    name: String,
    iconRes: DrawableResource,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .toggleable(
                value = isSelected,
                onValueChange = { onSelect() }
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(resource = iconRes),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = name,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = if (isSelected) Icons.Filled.CheckCircle else Icons.Outlined.RadioButtonUnchecked,
            contentDescription = null,
            tint = if (isSelected) MaterialTheme.colorSchemeCustom.alwaysBlue else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
    }
}

fun getIconForProvider(providerName: String): DrawableResource {
    return aiProvidersMap[providerName] ?: throw IllegalArgumentException("Unknown provider name")
}

