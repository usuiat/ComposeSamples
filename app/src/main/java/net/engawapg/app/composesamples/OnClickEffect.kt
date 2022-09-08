package net.engawapg.app.composesamples

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun NoRippleEffectSample() {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.fillMaxSize().padding(20.dp)
    ) {
        var text1 by remember { mutableStateOf("Click me") }
        var text2 by remember { mutableStateOf("Click me") }
        ClickMe(
            title = text1,
            subTitle = "(Ripple effect)",
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.clickable { text1 += "!" }
        )
        ClickMe(
            title = text2,
            subTitle = "(No ripple effect)",
            color = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier.clickableNoRipple { text2 += "!" }
        )
    }
}

fun Modifier.clickableNoRipple(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
): Modifier = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick
    )
}

@Composable
fun ClickMe(
    title: String,
    subTitle: String,
    color: Color,
    modifier: Modifier,
) {
    Surface(
        color = color,
        shape = RoundedCornerShape(20.dp),
        modifier = modifier.height(250.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        )  {
            Text(
                text = title,
                style = MaterialTheme.typography.displayMedium,
            )
            Text(
                text = subTitle,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}