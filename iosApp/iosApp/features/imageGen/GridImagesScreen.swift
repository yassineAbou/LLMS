//
//  GridImagesScreen.swift
//  iosApp
//
//  Created by Yassine Abou on 30/6/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

import SwiftUI

struct GridImagesScreen: View {
    var body: some View {
        ZStack {
            Text("Grid Images")
               .textStyle(MaterialTheme.Typography.headlineLarge)
        }
       .frame(maxWidth:.infinity, maxHeight:.infinity)
       .background(MaterialTheme.ColorScheme.surface)
    }
}

#Preview {
    GridImagesScreen()
}
