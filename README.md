# Dux

This is a sample app to demonstrate the usage of [Redux](https://github.com/reactjs/redux) in Android.

## Goals

The main goal is to have a unidirectional data flow architecture in Android with immutable and predictable state. 

## Desired Technologies

Ideally, we'll be using these technologies (subject to change):

* [Kotlin](https://kotlinlang.org/) because it's great
* [Koin](https://github.com/Ekito/koin) for dependency injection 
* TBD: [Anko](https://github.com/Kotlin/anko) for programmatical views and useful extensions
* TBD: Either [Flow](https://github.com/square/flow), [SimpleStack](https://github.com/Zhuinden/simple-stack), or [Conductor](https://github.com/bluelinelabs/Conductor) for backstack handling
* [RxJava2](https://github.com/ReactiveX/RxJava), [RxKotlin](https://github.com/ReactiveX/RxKotlin), and [RxAndroid](https://github.com/ReactiveX/RxAndroid) for observable data flows and asynchronous logic
* [Retrofit](http://square.github.io/retrofit/) for network calls
* [Room](https://developer.android.com/topic/libraries/architecture/room) for local database storage
