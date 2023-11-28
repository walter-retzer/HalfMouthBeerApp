package app.halfmouth.android.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry


object NavAnimations {

    val slideUpEnterAnimation: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) =
        {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Up,
                animationSpec = tween(500)
            )
        }

    val slideDownExitAnimation: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) =
        {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Down,
                animationSpec = tween(500)
            )
        }

    val popEnterDownAnimation: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) =
        {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Down,
                animationSpec = tween(500)
            )
        }

    val popExitDownAnimation: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) =
        {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Down,
                animationSpec = tween(500)
            )
        }


    val slideLeftEnterAnimation: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) =
        {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(500)
            )
        }

    val slideRightExitAnimation: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) =
        {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(500)
            )
        }

    val popEnterRightAnimation: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?) =
        {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(500)
            )
        }

    val popExitRightAnimation: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?) =
        {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(500)
            )
        }

}
