//
//  ScreenA.swift
//  iosApp
//
//  Created by Yassine Abou on 15/5/2024.
//  Copyright © 2024 orgName. All rights reserved.
//


import SwiftUI

struct ScreenA: View {
    var body: some View {
        NavigationView {
            VStack {
                Text("Screen A")
                NavigationLink(destination: ScreenB()) {
                    Text("Navigate to Screen B")
                }
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            .navigationTitle("Screen A")
            .navigationBarTitleDisplayMode(.inline)
            .centered()
        }
    }
}

#Preview {
    ScreenA()
}
