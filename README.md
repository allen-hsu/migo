# Migo App

It's sample project, include mvvm architecture, network status handle.

### Android src top-level directory layout

> Folder structure options and naming conventions for software projects

    ├── app/src/main/java/src
      ├── constant                          # Constant
      ├── data                              # Data files, Repository/DataSource
      ├── di                                # Di files
      ├── ext                               # Kotlin Extension
      ├── framework                         # Base Class
      ├── logic                             # Business login file
      ├── network                           # Network Handle and API service for Retrofit use
      ├── ui                                # Recycle ViewHolder/Adapter, ViewPager adapter
      ├── viewmodel                         # ViewModel class
    ├── gradle/version.gradle               # Put all dependency 3rd version declare
    ├── LICENSE
    └── README.md

### 3rd party libraries

- Network: Okhttp + Retrofit
- Gson: Json parser lib
- DI Framework: Koin
- Ktx: for easy write

### About the architecture

> - MVVM architecture, and use dependency injection to decrease coupling.
> - Just use one Activity with multiple Fragment to implement all project.
> - Use LiveData(Observer Pattern) to implement Single Data Flow and observer data changing that to change UI state.
> - Use Strategy Pattern to implement different algorithm within pass provider and using Builder Pattern to Factory correct pass provider.

### Mock Logic

> Mock PassProvider to valid expired circumstance, if you want to test, you can uncomment this code section.

```kotlin
PassLocalDataSource.class
	fun createMockData
//        for (i in 0..2) {
//            passListData.apply {
//                val pass = Pass.Builder()
//                    .provider(MockExpiredPassProvider())
//                    .unitNum((i + 1))
//                    .build()
//                this.add(pass)
//            }
//        }
```

> Mock NetworkHandle to valid Wifi/Cellular circumstance, if you want to test, you can uncomment this code section, and use provideMockNetworkHandle to inject
```kotlin
ApiModule.class
//fun provideMockNetworkHandle() : NetworkHandle =
//    MockWifiNetworkHandleProvider()
//    MockCellularProvider()
//    MockNoneNetworkProvider()
```