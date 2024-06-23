//
//  BottomNavigation.swift
//  iosApp
//
//  Created by Yassine Abou on 23/6/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct BottomNavigation: View {
    var body: some View {
        TabView {
            TextGenTab()
                .tabItem {
                    Label("TextGen", systemImage: "doc.text.fill")
                }
            ImageGenTab()
                .tabItem {
                    Label("ImageGen", systemImage: "photo.fill")
                }
            ProfileTab()
                .tabItem {
                    Label("Profile", systemImage: "person.fill")
                }
        }
        .onAppear() {
            UITabBar.appearance().backgroundColor = UIColor(MaterialTheme.ColorScheme.surfaceContainerLow)
            UITabBar.appearance().unselectedItemTintColor =
            UIColor(MaterialTheme.ColorScheme.outlineVariant)
            

        }
        .tint(MaterialTheme.ColorScheme.onSecondaryContainer)
    }
}

#Preview {
    BottomNavigation()
}
