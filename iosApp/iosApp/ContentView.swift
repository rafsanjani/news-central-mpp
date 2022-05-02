import SwiftUI
import shared
import Shimmer

struct ContentView: View {
    private var newsEngine = NewsCentral.Companion().create(withLog: true)
    
    @StateObject var viewModel: MainViewModel = MainViewModel()
    

    var body: some View {
        
        VStack(alignment: .leading) {
            
            if (viewModel.result is Result.Loading) {
                Text("Loading News Items. Please wait...")
                        .bold()
                        .font(.headline)
                        .shimmering()

            }
            
            if(viewModel.result is Result.Success){
                let data = viewModel.result as! Result.Success
                
                List(data.data, id: \.headline) { article in
                     NewsCard(article: article)
                }
                .environmentObject(viewModel)
                .listStyle(.grouped)
                .padding(.all, 5)
                .frame(maxWidth:.infinity)
            }
            
            if(viewModel.result is Result.Error){
                Text("Error Loading News")
                        .bold()
                        .font(.headline)
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .edgesIgnoringSafeArea(.all)
        .onAppear {
            viewModel.loadArticles(page: 1)
        }
    }
}

struct NewsCard: View{
    @EnvironmentObject var viewModel: MainViewModel
    
    let article: Article


    var body: some View{
        HStack(spacing: 10){
            if #available(iOS 15.0, *) {
                AsyncImage(
                    url: URL(string: article.imageUrl),
                    content: { image in
                        image.resizable()
                            .aspectRatio(contentMode: .fill)
                        
                    },
                    placeholder: {
                        Rectangle()
                            .fill(Color.gray)
                    }
                ).frame(width: 100,height: 90, alignment: Alignment.leading)
                    .cornerRadius(12)
                    
            } else {
                // Fallback on earlier versions
            }
            
            VStack{
                Text(article.headline)
                    .font( .caption)
                    .bold()
                    .lineLimit(2)
                    
                    
                Spacer()
               
                HStack{
                    Spacer()
                    Text(viewModel.formatDate(date: article.date))
                        .font(.caption)
                } .frame(maxWidth: .infinity)
                    
                    
            }
            .frame(maxWidth: .infinity)
        }
        .frame(maxHeight: 90)
    }
}


struct ContentView_Previews: PreviewProvider {

    static var previews: some View {
        Group{
            ContentView()
            NewsCard(article: Article(headline: "headline headline headline headline headline headline headlein", id: "id", content: "Content", imageUrl: "ImageUrl", category: "Category", date: "Date"))
        }
    }
}

