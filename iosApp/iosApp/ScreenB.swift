//
//  ScreenB.swift
//  iosApp
//
//  Created by Yassine Abou on 15/5/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ScreenB: View {
    @Environment(\.presentationMode) var presentationMode

    var body: some View {
        
        VStack(alignment:.center, spacing: 16) {
            
            Text("Screen B")
               .textStyle(MaterialTheme.Typography.headlineLarge)
            Button("Navigate back") {
                presentationMode.wrappedValue.dismiss()
            }
           .foregroundColor(MaterialTheme.ColorScheme.primary)
           .textStyle(MaterialTheme.Typography.bodyLarge)
            
        }
       .frame(maxWidth:.infinity, maxHeight:.infinity)
       .background(MaterialTheme.ColorScheme.surface)
        
    }
}

#Preview {
    ScreenB()
}
