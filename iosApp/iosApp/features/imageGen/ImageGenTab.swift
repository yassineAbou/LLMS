//
//  ImageGenTab.swift
//  iosApp
//
//  Created by Yassine Abou on 23/6/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

import SwiftUI

struct ImageGenTab: View {
    @State private var selectedTab: Int = 0

    let tabs: [Tab] = [
       .init(selectedIcon: Image(systemName: "doc.plaintext.fill"), unselectedIcon: Image(systemName: "doc.plaintext"), title: "Txt2Img"),
       .init(selectedIcon: Image(systemName: "photo.badge.plus.fill"), unselectedIcon: Image(systemName: "photo.badge.plus"), title: "Img2Img")
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
                            Txt2ImgScreen()
                               .tag(0)
                            Img2ImgScreen()
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
    ImageGenTab()
}
