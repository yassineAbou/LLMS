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
        
        VStack(alignment:.center, spacing: 16) {
            
            Text("Screen A")
               .textStyle(MaterialTheme.Typography.headlineLarge)
            NavigationLink("Navigate to Screen B", destination: ScreenB())
               .foregroundColor(MaterialTheme.ColorScheme.primary)
               .textStyle(MaterialTheme.Typography.bodyLarge)
        }
       .frame(maxWidth:.infinity, maxHeight:.infinity)
       .background(MaterialTheme.ColorScheme.surface)
        
    }
    
}

#Preview {
    ScreenA()
}
