@file:OptIn(ExperimentalMaterial3Api::class)

package org.yassineabou.playground.feature.profile.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.yassineabou.playground.app.ui.view.BottomSheetButton
import org.yassineabou.playground.app.ui.view.BottomSheetContent
import org.yassineabou.playground.app.ui.view.BottomSheetTitle
import org.yassineabou.playground.app.ui.view.PrivacyInfoSection
import org.yassineabou.playground.app.ui.view.TermsOfServiceAndPrivacyPolicy

@Composable
fun LoginBottomSheet(
    onDismissRequest: () -> Unit,
    onAuthenticated: () -> Unit
) {
    val sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showWhyLoginContent by remember { mutableStateOf(false) }
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        AnimatedVisibility(
            visible = !showWhyLoginContent
        ) {
            BottomSheetContent(
                title = {
                    BottomSheetTitle(
                        """
                    Please login
                    to continue
                     """.trimIndent()
                    )
                },
                actionContent = {
                    BottomSheetButton(
                        text = "Login with Google",
                        modifier = Modifier.fillMaxWidth()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        onAuthenticated = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onDismissRequest()
                                    onAuthenticated()
                                }
                            }
                        }
                    )
                    BottomSheetButton(
                        text = "Login with Apple",
                        modifier = Modifier.fillMaxWidth()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
                        contentColor = MaterialTheme.colorScheme.background,
                        containerColor = MaterialTheme.colorScheme.onBackground,
                        onAuthenticated = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onDismissRequest()
                                    onAuthenticated()
                                }
                            }
                        }
                    )
                },
                footerContent = {
                    TermsOfServiceAndPrivacyPolicy(
                        modifier = Modifier.systemBarsPadding().padding(vertical = 16.dp),
                        leadingItemTitle = "Why login?",
                        onLeadingItemClick = { showWhyLoginContent = true }
                    )
                }
            )
        }
        AnimatedVisibility(
            visible = showWhyLoginContent
        ) {
            WhyLogin(onBackClick = { showWhyLoginContent = false })
        }
    }
}

@Composable
private fun WhyLogin(
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.CenterStart)
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIosNew,
                        contentDescription = "Back"
                    )
                }
            }
            Text(
                text = "Why login",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(end = 24.dp),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
        }
        PrivacyInfoSection()
    }
}