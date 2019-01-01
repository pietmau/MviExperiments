# Marvel MVI

This project is a little experiment of MVI pattern implementation in Android.

It is based on Badoo's [MVICore](https://github.com/badoo/MVICore) framework.
Other libraries follow very similar approaches and provide equivalent capabilities (for example Spotify's [Mobius](https://github.com/spotify/mobius)).

## Documentation

[How it works](documentation/how_it_works.md)

[Project modules](documentation/modules.md)

## Libraries used
* Dagger
* RxJava
* Retrofit
* Architecture Components:
   * Room
   * ViewModel
* Picasso
* Leak Canary
* Mokito/Mokito-Kotlin
* Espresso

## Tests

### Unit tests
Unit tests can be found in the [**usecases**](usecases/src/test/java/com/pppp/usecases/) and [**data**]( data/src/test/java/com/pppp/database/) modules.

### Espresso tests
Espresso tests can be found in the [**ui**](ui/src/androidTest/java/com/pppp/mvicoreapp/) module.

## Persistence and offline mode
Via Room.

## In case Marvel is down
the *mock* build type points to a mock AWS server.
