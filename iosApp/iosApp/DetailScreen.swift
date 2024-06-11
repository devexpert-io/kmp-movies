import Foundation
import SwiftUI
import ComposeApp

struct DetailScreen: View {
    @StateObject var viewModelStoreOwner: SharedViewModelStoreOwner<DetailViewModel>
    
    init(id: Int32) {
        self._viewModelStoreOwner = StateObject(
            wrappedValue: SharedViewModelStoreOwner(DetailViewModel(id: id))
        )
    }
    
    var body: some View {
        Observing(viewModelStoreOwner.instance.state) { state in
            VStack {
                if(state.loading) {
                    ProgressView()
                        .progressViewStyle(CircularProgressViewStyle())
                }
                
                if let movieDetail = state.movie {
                    MovieDetail(movie: movieDetail)
                }
            }
        }
    }
}

struct MovieDetail: View {
    var movie: Movie
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading) {
                if let backdrop = movie.backdrop {
                    AsyncImage(url: URL(string: backdrop)) { image in
                        image
                            .resizable()
                            .frame(maxWidth: .infinity)
                    } placeholder: {
                        ProgressView()
                    }
                    .aspectRatio(16 / 9, contentMode: .fill)
                    .frame(maxHeight: 200)
                    .clipped()
                }
                Text(movie.overview)
                    .padding()
                
                VStack(alignment: .leading, spacing: 8) {
                    Text("**Original language**: \(movie.originalLanguage)")
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Text("**Original title**: \(movie.originalTitle)")
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Text("**Release date**: \(movie.releaseDate)")
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Text("**Popularity**: \(movie.popularity)")
                        .frame(maxWidth: .infinity, alignment: .leading)
                    Text("**Vote average**: \(movie.voteAverage)")
                        .frame(maxWidth: .infinity, alignment: .leading)
                }
                .padding()
                .background(Color.secondary.opacity(0.1))
                .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, alignment: .leading)
                .cornerRadius(8)
            }
        }
        .navigationTitle(movie.title)
    }
}
