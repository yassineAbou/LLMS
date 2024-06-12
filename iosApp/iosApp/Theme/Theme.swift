//
//  Color.swift
//  iosApp
//
//  Created by Yassine Abou on 11/6/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI




private struct AppTypography  {
    
    let displayLarge: (font: Font, lineHeight: CGFloat) = (.custom("Roboto", size: 57), 64)
    let displayMedium: (font: Font, lineHeight: CGFloat) = (.custom("Roboto", size: 45), 52)
    let displaySmall: (font: Font, lineHeight: CGFloat) = (.custom("Roboto", size: 36), 44)
    let headlineLarge: (font: Font, lineHeight: CGFloat) = (.custom("Roboto", size: 32), 40)
    let headlineMedium: (font: Font, lineHeight: CGFloat) = (.custom("Roboto", size: 28), 36)
    let headlineSmall: (font: Font, lineHeight: CGFloat) = (.custom("Roboto", size: 24), 32)
    let titleLarge: (font: Font, lineHeight: CGFloat) = (.custom("Roboto-Medium", size: 22), 28)
    let titleMedium: (font: Font, lineHeight: CGFloat) = (.custom("Roboto-Medium", size: 16), 24)
    let titleSmall: (font: Font, lineHeight: CGFloat) = (.custom("Roboto-Medium", size: 14), 20)
    let bodyLarge: (font: Font, lineHeight: CGFloat) = (.custom("Roboto", size: 16), 24)
    let bodyMedium: (font: Font, lineHeight: CGFloat) = (.custom("Roboto", size: 14), 20)
    let bodySmall: (font: Font, lineHeight: CGFloat) = (.custom("Roboto", size: 12), 16)
    let labelLarge: (font: Font, lineHeight: CGFloat) = (.custom("Roboto-Medium", size: 14), 20)
    let labelMedium: (font: Font, lineHeight: CGFloat) = (.custom("Roboto-Medium", size: 12), 16)
    let labelSmall: (font: Font, lineHeight: CGFloat) = (.custom("Roboto-Medium", size: 11), 16)
}


private let appTypography = AppTypography()


extension View {
    func textStyle(_ style: (font: Font, lineHeight: CGFloat)) -> some View {
        self.font(style.font)
         .lineSpacing(style.lineHeight)
    }
}


enum MaterialTheme {
    
    enum ColorScheme {
        
        static let primary = Color("primary")
        static let onPrimary = Color("onPrimary")
        static let primaryContainer = Color("primaryContainer")
        static let onPrimaryContainer = Color("onPrimaryContainer")
        static let secondary = Color("secondary")
        static let onSecondary = Color("onSecondary")
        static let secondaryContainer = Color("secondaryContainer")
        static let onSecondaryContainer = Color("onSecondaryContainer")
        static let tertiary = Color("tertiary")
        static let onTertiary = Color("onTertiary")
        static let tertiaryContainer = Color("tertiaryContainer")
        static let onTertiaryContainer = Color("onTertiaryContainer")
        static let error = Color("error")
        static let onError = Color("onError")
        static let errorContainer = Color("errorContainer")
        static let onErrorContainer = Color("onErrorContainer")
        static let background = Color("background")
        static let onBackground = Color("onBackground")
        static let surface = Color("surface")
        static let onSurface = Color("onSurface")
        static let surfaceVariant = Color("surfaceVariant")
        static let onSurfaceVariant = Color("onSurfaceVariant")
        static let outline = Color("outline")
        static let outlineVariant = Color("outlineVariant")
        static let scrim = Color("scrim")
        static let inverseSurface = Color("inverseSurface")
        static let inverseOnSurface = Color("inverseOnSurface")
        static let inversePrimary = Color("inversePrimary")
        static let surfaceDim = Color("surfaceDim")
        static let surfaceBright = Color("surfaceBright")
        static let surfaceContainerLowest = Color("surfaceContainerLowest")
        static let surfaceContainerLow = Color("surfaceContainerLow")
        static let surfaceContainer = Color("surfaceContainer")
        static let surfaceContainerHigh = Color("surfaceContainerHigh")
        static let surfaceContainerHighest = Color("surfaceContainerHighest")
        
    }
    

    enum Typography {
        
            static let displayLarge = appTypography.displayLarge
            static let displayMedium = appTypography.displayMedium
            static let displaySmall = appTypography.displaySmall
            static let headlineLarge = appTypography.headlineLarge
            static let headlineMedium = appTypography.headlineMedium
            static let headlineSmall = appTypography.headlineSmall
            static let titleLarge = appTypography.titleLarge
            static let titleMedium = appTypography.titleMedium
            static let titleSmall = appTypography.titleSmall
            static let bodyLarge = appTypography.bodyLarge
            static let bodyMedium = appTypography.bodyMedium
            static let bodySmall = appTypography.bodySmall
            static let labelLarge = appTypography.labelLarge
            static let labelMedium = appTypography.labelMedium
            static let labelSmall = appTypography.labelSmall
        
        }
}
