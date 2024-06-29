//
//  SwiftUIView.swift
//  iosApp
//
//  Created by Yassine Abou on 26/6/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct Tab {
    var selectedIcon: Image?
    var unselectedIcon: Image?
    var title: String
}
struct Tabs: View {
    var fixed = true
    var tabs: [Tab]
    var geoWidth: CGFloat
    @Binding var selectedTab: Int
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            ScrollViewReader { proxy in
                VStack(spacing: 0) {
                    HStack(spacing: 0) {
                        ForEach(0 ..< tabs.count, id: \.self) { row in
                            Button(action: {
                                withAnimation {
                                    selectedTab = row
                                }
                            }, label: {
                                VStack(spacing: 0) {
                                    HStack(alignment: .center) {
                                        // Image
                                        AnyView(selectedTab == row ? tabs[row].selectedIcon : tabs[row].unselectedIcon)
                                            .foregroundColor(selectedTab == row ? MaterialTheme.ColorScheme.primary : MaterialTheme.ColorScheme.onSurfaceVariant)
                                            .padding(EdgeInsets(top: 0, leading: 15, bottom: 0, trailing: 0))
                                        // Text
                                        Text(tabs[row].title)
                                            .textStyle(MaterialTheme.Typography.bodyLarge)                                            .foregroundColor(selectedTab == row ? MaterialTheme.ColorScheme.primary : MaterialTheme.ColorScheme.onSurfaceVariant)
                                            .padding(EdgeInsets(top: 10, leading: 3, bottom: 10, trailing: 15))
                                            
                                    }
                                    .frame(width: fixed ? (geoWidth / CGFloat(tabs.count)) : .none, height: 52)
                                    // Bar Indicator
                                    Rectangle().fill(selectedTab == row ? MaterialTheme.ColorScheme.primary : Color.clear)
                                        .frame(height: 3)
                                }.fixedSize()
                            })
                                .accentColor(Color.white)
                                .buttonStyle(PlainButtonStyle())
                        }
                    }
                    .onChange(of: selectedTab) { target in
                        withAnimation {
                            proxy.scrollTo(target)
                        }
                    }
                }
            }
        }
        .frame(height: 55)
        .onAppear(perform: {
            UIScrollView.appearance().backgroundColor = UIColor(MaterialTheme.ColorScheme.surface)
            UIScrollView.appearance().bounces = fixed ? false : true
        })
        .onDisappear(perform: {
            UIScrollView.appearance().bounces = true
        })
    }
}

struct Tabs_Previews: PreviewProvider {
    static var previews: some View {
        Tabs(fixed: true,
             tabs: [
                .init(selectedIcon: Image(systemName: "star.fill"), unselectedIcon: Image(systemName: "star"),title: "Tab 1"),
                .init(selectedIcon: Image(systemName: "star.fill"), unselectedIcon: Image(systemName: "star"),title: "Tab 2"),
                .init(selectedIcon: Image(systemName: "star.fill"), unselectedIcon: Image(systemName: "star"),title: "Tab 3")],
             geoWidth: 375,
             selectedTab: .constant(0))
    }
}
