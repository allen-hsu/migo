Migo App
============================

It's sample project, include mvvm architecture, network status handle.

> Folder structure options and naming conventions for software projects

### Android src top-level directory layout

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

+ Network: Okhttp + Retrofit
+ DI Framework: Koin
+ Ktx: for easy write

### About the architecture

> + Using Mvvm architecture to implement, And use dependency injection to decrease coupling,
> + And just use one Activity with multiple Fragment to implement all project,
> + Use LiveData(Observer Pattern) to implement Single Data Flow and observer data changing that  to change ui state
> + Use Strategy Pattern to implement different algorithm within pass provider and using Builder Pattern to Factory correct pass provider

### Mock Logic
> Just Mock Passprovid to valid expired circumstance, if you want to test, you can uncomment this code section.

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

