//
//  TextGenTab.swift
//  iosApp
//
//  Created by Yassine Abou on 23/6/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct TextGenTab: View {
    @State private var selectedTab: Int = 0

    let tabs: [Tab] = [
       .init(selectedIcon: Image(systemName: "ellipsis.message.fill"), unselectedIcon: Image(systemName: "ellipsis.message"), title: "Chat"),
       .init(selectedIcon: Image(systemName: "clock.arrow.circlepath"), unselectedIcon: Image(systemName: "clock.arrow.circlepath"), title: "History")
    ]

    var body: some View {
        GeometryReader { geo in
            VStack(spacing: 0) {
                // Tabs
                Tabs(tabs: tabs, geoWidth: geo.size.width, selectedTab: $selectedTab)
                   .padding(.top, geo.safeAreaInsets.top) // add padding to respect safe area

                // Views
                TabView(selection: $selectedTab,
                        content: {
                            ChatScreen()
                               .tag(0)
                            HistoryScreen()
                               .tag(1)
                        })
                   .tabViewStyle(PageTabViewStyle(indexDisplayMode:.never))
            }
            .background(MaterialTheme.ColorScheme.surface)
           .edgesIgnoringSafeArea(.top)
        }
    }
}

#Preview {
    TextGenTab()
}


