//
//  Img2ImgScreen.swift
//  iosApp
//
//  Created by Yassine Abou on 28/6/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct Img2ImgScreen: View {
    var body: some View {
        ZStack {
            Text("Img2Img")
               .textStyle(MaterialTheme.Typography.headlineLarge)
        }
       .frame(maxWidth:.infinity, maxHeight:.infinity)
       .background(MaterialTheme.ColorScheme.surface)
    }
}

#Preview {
    Img2ImgScreen()
}
