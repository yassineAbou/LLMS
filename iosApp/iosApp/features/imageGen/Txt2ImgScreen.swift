//
//  txt2ImgScreen.swift
//  iosApp
//
//  Created by Yassine Abou on 28/6/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct Txt2ImgScreen: View {
    var body: some View {
        ZStack {
            Text("txt2Img")
               .textStyle(MaterialTheme.Typography.headlineLarge)
        }
       .frame(maxWidth:.infinity, maxHeight:.infinity)
       .background(MaterialTheme.ColorScheme.surface)
    }
}

#Preview {
    Txt2ImgScreen()
}
