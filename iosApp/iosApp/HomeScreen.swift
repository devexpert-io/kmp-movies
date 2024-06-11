import SwiftUI
import ComposeApp

extension Movie: Identifiable { }

struct HomeScreen: View {
    @StateObject var viewModelStoreOwner = SharedViewModelStoreOwner<HomeViewModel>()
    @StateObject var locationManager = LocationManager()

    var body: some View {
        NavigationView {
            VStack{
                Observing(viewModelStoreOwner.instance.state) { state in
                    if(state.loading){
                        ProgressView()
                            .progressViewStyle(CircularProgressViewStyle())
                    }
                    
                    if(!state.movies.isEmpty){
                        let columns = [
                            GridItem(.adaptive(minimum: 100), spacing: 5)
                        ]
                        
                        ScrollView {
                            LazyVGrid(columns: columns, spacing: 5) {
                                ForEach(state.movies) { movie in
                                    NavigationLink(destination: DetailScreen(id: movie.id)) {
                                        MovieItemView(movie: movie)
                                    }
                                }
                            }
                        }
                        .padding(.horizontal)
                    }
                }
            }
            .navigationBarTitle(Text("KmpMovies"))
        }.onAppear {
            locationManager.requestPermission {
                viewModelStoreOwner.instance.onUiReady()
            }
        }
    }

}
struct MovieItemView: View {
    var movie: Movie

    var body: some View {
        VStack {
            GeometryReader { geometry in
                ZStack(alignment: .topTrailing) {
                    AsyncImage(url: URL(string: movie.poster)) { phase in
                        switch phase {
                        case .empty:
                            ProgressView()
                                .progressViewStyle(CircularProgressViewStyle())
                                .frame(width: geometry.size.width, height: geometry.size.height)
                        case .success(let image):
                            image
                                .resizable()
                                .aspectRatio(2 / 3, contentMode: .fill)
                                .frame(width: geometry.size.width, height: geometry.size.height)
                                .clipped()
                                .cornerRadius(8)
                        case .failure:
                            Image(systemName: "photo")
                                .resizable()
                                .aspectRatio(2 / 3, contentMode: .fill)
                                .frame(width: geometry.size.width, height: geometry.size.height)
                                .clipped()
                                .cornerRadius(8)
                        @unknown default:
                            EmptyView()
                        }
                    }
                    
                    if movie.isFavorite {
                        Image(systemName: "heart.fill")
                            .foregroundColor(.white)
                            .padding(5)
                    }
                }
            }
            .aspectRatio(2 / 3, contentMode: .fit)

            Text(movie.title)
                .font(.caption)
                .lineLimit(1)
                .padding(5)
        }
    }
}
