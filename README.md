# news-app-challenge
A smartphone app to show top headlines for a specific source (BBC news)


<p float="middle">
<img src="https://github.com/hashir1296/news-app-challenge/blob/dfdfc49d66633548a64a0b245ed7aa5eb71d57e5/Screenshots/Screenshot_2.jpg" width = "300" height = "600"/>  
<img src="https://github.com/hashir1296/news-app-challenge/blob/4094baf5e5af74ec54707bcde251a7e330f44a7a/Screenshots/Screenshot_3.jpg" width = "300" height = "600"/>
</p>


## Requirements

```
Android Studio Flamingo | 2022.2.1 Patch 1
Kotlin : 1.4.0 
Minimum android version : API LEVEL 26 (Android v8.0 Oreo) 
```

## Dependencies & Technologies

```
- Hilt dependency injection
- Network : Coroutines, retrofit, moshi, gson and okhttp
- Kotlin
- Coroutines
- Kotlins Flows
- Paging 3
- Lifecycle
- Navigation Component
- Kotlin coil for image loading, downloading and cache
- Testing: Mockk, Mockito, Paging-testing, Coutines testing, Hilt
```

## Architecture & Structure
<img src="" width = "300" height = "300"/>
<img src="" width = "277" height = "600"  align=left/>

### The app has following packages:

```
di: It contains all the dependency injection related classes and interfaces.
presentation: View classes along with their corresponding ViewModel, adapters and models
utils: Utility classes.
data: All network related things like User Repository, remote APIs etc.
```


## Features

### Story1
` NewsHeadlinesListFragment` contains latest headlines from a specific news source, the ` news-source ` is dependent on type of flavour a user wshes to install all

` NewsHeadlinesViewModel` gets statistics using ` NewsHeadlinesPagingSource 
