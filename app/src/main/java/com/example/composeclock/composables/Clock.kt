package com.example.composeclock.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeclock.data.models.Time

@Composable
fun Number(value: Int, active: Boolean, modifier: Modifier = Modifier) {
    val backgroundColor by animateColorAsState(
        if (active) MaterialTheme.colors.primary else MaterialTheme.colors.primaryVariant
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.background(backgroundColor)
    ) {
        Text(
            text = value.toString(),
            fontSize = 20.sp,
            color = Color.White
        )
    }
}

@Composable
fun NumberColumn(
    range: IntRange,
    current: Int,
    modifier: Modifier = Modifier
) {
    val size = 40.dp
    val mid = (range.last - range.first) / 2f
    val reset = current == range.first
    val offset by animateDpAsState(
        targetValue = size * (mid - current),
        animationSpec = if (reset) {
            spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )
        } else {
            tween(
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            )
        }
    )
    Column(
        Modifier
            .offset(y = offset)
            .clip(RoundedCornerShape(percent = 25))
    ) {
        range.forEach { num ->
            Number(value = num, active = num == current, modifier.size(size))
        }
    }
}

@Composable
fun Clock(time: Time) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val padding = Modifier.padding(horizontal = 4.dp)
        NumberColumn(range = 0..2, current = time.hours / 10, padding)
        NumberColumn(range = 0..9, current = time.hours % 10, padding)
        Spacer(Modifier.size(16.dp))
        NumberColumn(range = 0..5, current = time.minutes / 10, padding)
        NumberColumn(range = 0..9, current = time.minutes % 10, padding)
        Spacer(Modifier.size(16.dp))
        NumberColumn(range = 0..5, current = time.seconds / 10, padding)
        NumberColumn(range = 0..9, current = time.seconds % 10, padding)
    }
}