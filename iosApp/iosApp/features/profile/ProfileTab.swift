//
//  ProfileTab.swift
//  iosApp
//
//  Created by Yassine Abou on 23/6/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ProfileTab: View {
    var body: some View {
        ZStack {
            Text("Profile Content")
               .textStyle(MaterialTheme.Typography.headlineLarge)
        }
       .frame(maxWidth:.infinity, maxHeight:.infinity)
       .background(MaterialTheme.ColorScheme.surface)
    }
}

#Preview {
    ProfileTab()
}
