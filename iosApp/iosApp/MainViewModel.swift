//
//  Hello.swift
//  iosApp
//
//  Created by Rafsanjani Aziz on 02/05/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared
@MainActor class MainViewModel: ObservableObject{
    private let formatter : PrettyDateFormatter = PrettyDateFormatter.Companion().ofPattern(pattern: "MMM dd, yyyy")
    private let newsEngine = NewsCentral.Companion().create(withLog: true)
    @Published var result: Result = Result.Loading()
    
    func loadArticles(page: Int32){
        Task.init {
            do {
                let data = try await newsEngine.fetchNews(page: page)
                
                result = data
            } catch{
                print("Error loading news items")
            }
        }
    }
    
    func formatDate(date: String) -> String {
        do {
            return try formatter.format(dateTime: date)
        } catch {
            return "Error"
        }
    }
}
