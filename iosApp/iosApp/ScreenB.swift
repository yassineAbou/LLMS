//
//  ScreenB.swift
//  iosApp
//
//  Created by Yassine Abou on 15/5/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

import SwiftUI

struct ScreenB: View {
    @Environment(\.presentationMode) var presentationMode

    var body: some View {
        VStack {
            Text("Screen B")
            Button("Navigate Back") {
                self.presentationMode.wrappedValue.dismiss()
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .navigationTitle("Screen B")
        .navigationBarTitleDisplayMode(.inline)
        .centered()
    }
}



#Preview {
    ScreenB()
}
