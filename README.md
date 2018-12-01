# MoviesDbApp <img src="https://i.imgur.com/Ma3z2J6.png" width="50" alt="logo" align="center">

## App screenshots

<p>
  <img src="https://i.imgur.com/dmGitzZ.jpg" width="200" alt="feed">
  <img src="https://i.imgur.com/GDFWnQF.jpg" width="200" alt="detail1">
  <img src="https://i.imgur.com/J80bN2b.jpg" width="200" alt="detail2">
  <img src="https://i.imgur.com/33zgwXS.jpg" width="200" alt="detail3">
</p>

## Architecture & Implementation details

* Simple Android app written in Kotlin based on Clean Architecture for layer abstractions (view/domain/data)
* Model-View-Presenter for the presentation layer
* Interactors / UseCases are used by the presenters to start a data retrieval flow
* UseCases use Repository pattern to request the data needed
* Repository in this case doesn't have DataSources because there's no database/cache/other sources to retrieve data than just Network. Repository in this project directly request data to the API
* Two different data objects, DTO (Data transfer object) to parse API's response, and Business Object as our own data model.
* DI with Dagger2
* Asynchronous handled by RxJava2 (RxKotlin)
* Image loading handled by Picasso

## Error states

There are two possible network-failure scenarios in this app. 
Either when requesting the feed of movies or requesting recommendations for a certain show. In the first scenario a error view is shown where the user is able to tap *Retry* button, for the second scenario the recommendations layout is just hided.

**How it looks like?**
<p>
  <img src="https://i.imgur.com/qyJtzql.jpg" width="200" alt="error1">
  <img src="https://i.imgur.com/jerRVvA.jpg" width="200" alt="error2">
</p>

## API

* API used: **themoviedb.org V3** (you can check the api documentation <a href="https://developers.themoviedb.org/">here</a>).
* Endpoints used: *"tv/popular"*, *"tv/{tv_id}/similar"*

## Third-Party Libraries

* RxKotlin https://github.com/ReactiveX/RxKotlin
* Dagger2 https://google.github.io/dagger/
* Picasso http://square.github.io/picasso/
* Lottie https://airbnb.design/lottie/
* Retrofit2 https://square.github.io/retrofit/
* OkHttp3 https://github.com/square/okhttp
* Mockito https://github.com/nhaarman/mockito-kotlin

## TODOs

* Instrumentation Testing

## Support & contact

<img src="https://icon2.kisspng.com/20180413/fde/kisspng-g-suite-gmail-computer-icons-google-email-e-mail-5ad074ba3e72d7.9148494615236108102558.jpg" width="20" align="center"> <a href="mailto:marcgdiez@gmail.com"> marcgdiez@gmail.com</a>

<img src="https://img.icons8.com/cotton/2x/twitter.png" width="20" align="center"> <a href="https://twitter.com/marcgdiez"> @marcgdiez </a>
