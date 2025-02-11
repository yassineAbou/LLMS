@file:OptIn(ExperimentalMaterial3Api::class)

package org.yassineabou.playground.feature.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import llms.composeapp.generated.resources.Res
import llms.composeapp.generated.resources.ic_github
import llms.composeapp.generated.resources.ic_linkedIn
import llms.composeapp.generated.resources.ic_logout
import llms.composeapp.generated.resources.ic_play_store
import llms.composeapp.generated.resources.ic_user
import org.jetbrains.compose.resources.vectorResource
import org.koin.compose.viewmodel.koinViewModel
import org.yassineabou.playground.app.core.navigation.Screen
import org.yassineabou.playground.app.core.theme.colorSchemeCustom
import org.yassineabou.playground.feature.profile.model.UserUiState
import org.yassineabou.playground.feature.profile.ui.view.LoginBottomSheet

@Composable
fun ProfileContent(
    profileViewModel: ProfileViewModel = koinViewModel(),
    navController: NavController
) {
    val userUiState by profileViewModel.userUiState.collectAsState()
    val showBottomSheetState by profileViewModel.showBottomSheet.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            UserProfileHeader(
                userUiState = userUiState,
                modifier = Modifier.statusBarsPadding(),
                onLogin = { profileViewModel.onLogin() },
                onLogout = { profileViewModel.onLogout() }

            )
            MenuList(
                loggedIn = userUiState != null,
                modifier = Modifier.padding(top = 16.dp),
                onGeneratedImages = { navController.navigate(Screen.GeneratedImagesScreen.route) },
                onChatHistory = { navController.navigate(Screen.ChatHistoryScreen.route) },
                onDeleteAccount = { profileViewModel.onLogout() }
            )
        }

        if (showBottomSheetState) {
            LoginBottomSheet(
                onDismissRequest = { profileViewModel.onDismissBottomSheet() },
                onAuthenticated = { profileViewModel.onAuthenticated() }
            )
        }
    }
}

@Composable
private fun UserProfileHeader(
    userUiState: UserUiState?,
    modifier: Modifier = Modifier,
    onLogin: () -> Unit,
    onLogout: () -> Unit,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        UserPageTopAppBar(
            loggedIn = userUiState != null,
            onLogout = onLogout,
        )
        UserDetails(
            userUiState = userUiState,
            modifier = Modifier.padding(vertical = 26.dp),
            onLogin = onLogin
        )
    }
}


@Composable
private fun UserPageTopAppBar(
    loggedIn: Boolean,
    onLogout: () -> Unit,
) {
    TopAppBar(
        title = {},
        actions = {
            if (loggedIn) {
                IconButton(
                    onClick = onLogout
                ) {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorSchemeCustom.alwaysBlue.copy(alpha = 0.5F),
                        contentColor = Color.White,
                    ) {
                        Icon(
                            imageVector = vectorResource(Res.drawable.ic_logout),
                            contentDescription = "logout",
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        ),
    )
}

@Composable
private fun UserDetails(
    userUiState: UserUiState?,
    modifier: Modifier = Modifier,
    onLogin: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        UserThumbnailOrIcon(userUiState = userUiState)

        if (userUiState != null) {
            UserInfo(
                name = userUiState.name,
            )
        } else {
            LoginButton(onLogin = onLogin)
        }
    }
}

@Composable
private fun UserThumbnailOrIcon(userUiState: UserUiState?) {
    if (userUiState?.thumbnailUrl != null) {
        AsyncImage(
            model = userUiState.thumbnailUrl,
            contentDescription = "user thumbnail",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
        )

    } else {
        Icon(
            imageVector = vectorResource(Res.drawable.ic_user),
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.background,
            contentDescription = "user icon",
        )
    }
}

@Composable
private fun LoginButton(onLogin: () -> Unit) {
    Button(
        modifier = Modifier.padding(top = 24.dp),
        onClick = onLogin,
        contentPadding = PaddingValues(horizontal = 32.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorSchemeCustom.alwaysWhite,
            containerColor = MaterialTheme.colorSchemeCustom.alwaysBlue
        ),
    ) {
        Text(
            text = "Login",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
private fun UserInfo(
    name: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello, $name",
            color = MaterialTheme.colorScheme.background,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
private fun MenuList(
    loggedIn: Boolean,
    modifier: Modifier = Modifier,
    onGeneratedImages: () -> Unit,
    onChatHistory: () -> Unit,
    onDeleteAccount: () -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                MenuItem(
                    title = "Generated Images",
                    description = "A collection of all your generated images",
                    icon = Icons.Filled.GridView,
                    onClick = onGeneratedImages
                )
                MenuItem(
                    title = "Chat History",
                    description = "A collection of all your chat history",
                    icon = Icons.Filled.History,
                    onClick = onChatHistory
                )
                HorizontalDivider(
                    thickness = 1.dp
                )

                MenuItem(
                    title = "GitHub",
                    description = "Check out all of my open source projects",
                    uri = "https://github.com/yassineAbou",
                    icon = vectorResource(Res.drawable.ic_github)
                )
                MenuItem(
                    title = "LinkedIn",
                    description = "Connect with me",
                    uri = "https://www.linkedin.com/in/yassineabou/",
                    icon = vectorResource(Res.drawable.ic_linkedIn)
                )
                MenuItem(
                    title = "Other Apps",
                    description = "Check out all of my other apps",
                    uri = "https://play.google.com/store/apps/dev?id=8439679079332539766",
                    icon = vectorResource(Res.drawable.ic_play_store)
                )

                HorizontalDivider(
                    thickness = 1.dp
                )


                MenuItem(
                    title = "About",
                    description = "Find out more about this app",
                    uri = "https://github.com/yassineAbou/LLMS",
                    icon = Icons.Filled.Info,
                )

                if (loggedIn) {
                    HorizontalDivider(
                        thickness = 1.dp
                    )
                    MenuItem(
                        title = "Delete Account",
                        description = "Warning! this can not be undone",
                        icon = Icons.Filled.Person,
                        iconTint = MaterialTheme.colorSchemeCustom.alwaysPink.copy(alpha = 0.5F),
                        onClick = onDeleteAccount
                    )
                }
            }
        }
    }
}

@Composable
private fun MenuItem(
    title: String,
    description: String,
    uri: String,
    icon: ImageVector,
    iconTint: Color = MaterialTheme.colorSchemeCustom.alwaysWhite,
) {
    val uriHandler = LocalUriHandler.current
    MenuItem(
        title = title,
        description = description,
        icon = icon,
        iconTint = iconTint,
        onClick = { uriHandler.openUri(uri) }
    )
}

@Composable
private fun MenuItem(
    title: String,
    description: String,
    icon: ImageVector,
    iconTint: Color = MaterialTheme.colorSchemeCustom.alwaysWhite,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.clickable(
            onClick = onClick
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = MaterialTheme.colorSchemeCustom.alwaysBlue.copy(alpha = 0.5F)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "icon description",
                modifier = Modifier.padding(6.dp).size(36.dp),
                tint = iconTint,
            )
        }
        TitleDescriptionText(title = title, description = description)
    }
}

@Composable
private fun TitleDescriptionText(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
        )
        Text(
            text = description,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}