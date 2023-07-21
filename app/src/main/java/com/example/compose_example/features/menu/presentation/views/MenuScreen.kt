package com.example.compose_example.features.menu.presentation.views

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose_example.R
import com.example.compose_example.core.navigation.graphs.MenuNavGraph
import com.example.compose_example.core.utils.noRippleClickable
import com.example.compose_example.core.utils.subTitleTextStyle
import com.example.compose_example.features.menu.utils.BottomBarItem

@Composable
fun MenuScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomNavNoAnimation(navController = navController) }

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.light_pink).copy(alpha = 0.1f)),
        ) {
            MenuNavGraph(navController = navController)
        }
    }
}

@Composable
fun BottomNavNoAnimation(
    navController: NavHostController
) {
    val screens = listOf(
        BottomBarItem.Home,
        BottomBarItem.Favorite,
        BottomBarItem.Account,
        BottomBarItem.Settings,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        BottomNavigation(backgroundColor = Color.White) {
            screens.forEach { screen ->
                val isSelected =
                    currentDestination?.hierarchy?.any { it.route == screen.route } == true
                val animatedWeight by animateFloatAsState(targetValue = if (isSelected) 1.5f else 1f)
                Box(
                    modifier = Modifier
                        .weight(animatedWeight)
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center
                ) {
                    BottomNavItem(
                        modifier = Modifier.noRippleClickable(
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            },
                        ),
                        screen = screen,
                        isSelected = isSelected
                    )
                }
            }
        }

    }
}

@Composable
private fun BottomNavItem(
    modifier: Modifier = Modifier,
    screen: BottomBarItem,
    isSelected: Boolean,
) {
    val animatedAlpha by animateFloatAsState(targetValue = if (isSelected) 1f else .5f)
    val animatedIconSize by animateDpAsState(
        targetValue = 26.dp,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )
    ConstraintLayout(
        modifier = modifier
            .height(if (isSelected) 40.dp else 30.dp)
            .shadow(
                elevation = if (isSelected) 15.dp else 0.dp,
                shape = RoundedCornerShape(15.dp),
            )
            .background(
                color = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(horizontal = 5.dp),
    ) {
        val (icon, text) = createRefs()
        FlipIcon(
            modifier = Modifier
                .constrainAs(icon) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxHeight()
                .alpha(animatedAlpha)
                .size(animatedIconSize),
            isActive = isSelected,
            activeIcon = screen.activeIcon,
            inactiveIcon = screen.inactiveIcon,
        )
        if (isSelected) {
            Text(
                text = screen.title,
                style = subTitleTextStyle.copy(
                    color = if (isSelected) colorResource(id = R.color.dark_red) else Color.Black,
                    fontSize = 15.sp,
                ),
                maxLines = 1,
                modifier = Modifier
                    .constrainAs(text) {
                        top.linkTo(parent.top)
                        start.linkTo(icon.end)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                    .padding(horizontal = 5.dp)
            )
        }

    }
}

@Composable
fun FlipIcon(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    activeIcon: Int,
    inactiveIcon: Int,
) {
    val animationRotation by animateFloatAsState(
        targetValue = if (isActive) 180f else 0f,
        animationSpec = spring(
            stiffness = Spring.StiffnessVeryLow,
            dampingRatio = Spring.DampingRatioMediumBouncy
        )
    )
    val icon = if (animationRotation > 90f) {
        rememberVectorPainter(image = ImageVector.vectorResource(activeIcon))
    } else {
        rememberVectorPainter(image = ImageVector.vectorResource(inactiveIcon))
    }

    Box(
        modifier = modifier
            .graphicsLayer { rotationY = animationRotation },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = icon,
            tint = if (isActive) colorResource(id = R.color.dark_red) else Color.Black,
            contentDescription = null,
        )
    }
}

