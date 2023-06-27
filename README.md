# News-app-challenge
A smartphone app to show top headlines for a specific source (BBC news)


## Instruction to use

```
Please choose app flavour from the build build variant. News headlines depend upon the flavour user chooses
```

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
- Sdp & ssp library for responsive dimensions - it will generate its own dimen file so we don"t have to maintain ours manually
- Kotlin coil for image loading, downloading and cache
- Testing: Mockk, Mockito, Paging-testing, Coutines testing, Hilt
```

## Architecture & Structure
<img src="https://github.com/hashir1296/news-app-challenge/blob/293d1fbfd4694a965f0946b92dbb26179a8496fd/Screenshots/Package%20structure.png" width = "400" height = "400"/>


### The app has following packages:

```
di: It contains all the dependency injection related classes and interfaces.
presentation: View classes along with their corresponding ViewModel, adapters and models
utils: Utility classes.
data: All data related things like User Repository, remote APIs etc.
```


## Features

### Story1
` NewsHeadlinesListFragment` contains latest headlines from a specific news source, the ` news-source ` is dependent on type of flavour a user wshes to install all
` NewsHeadlinesViewModel` gets list using ` NewsHeadlinesPagingSource ` 
` NewsHeadlinesPagingSource` is a paging source that call the Api service to hit the server
<p>
 <img src="https://github.com/hashir1296/news-app-challenge/blob/4094baf5e5af74ec54707bcde251a7e330f44a7a/Screenshots/Screenshot_3.jpg" width = "300" height = "600"/> 
</p>

### Story2
` NewsDetailFragment` contains headlines details, details are fetched from nav args
` NewsDetailViewModel` gets argument object and sets the data and emits to  `NewsDetailFragment` 
<p float="middle">
<img src="https://github.com/hashir1296/news-app-challenge/blob/dfdfc49d66633548a64a0b245ed7aa5eb71d57e5/Screenshots/Screenshot_2.jpg" width = "300" height = "600"/>  
</p>


### Story3
`BiometricVerificationFragment` show the biometric popup
`BiometricHelper`contains the necessary functions to make biometric functional

### Story4
Build flavour - User can choose news source from build variant section in Android studio
`news-source` is the flavour dimension 
`NEWS_SOURCE_ID` is a buildConfigField
`NEWS_SOURCE_TITLE` is a buildConfigField

<p float="middle">
<img src="https://github.com/hashir1296/news-app-challenge/blob/dfdfc49d66633548a64a0b245ed7aa5eb71d57e5/Screenshots/Screenshot_2.jpg" width = "300" height = "600"/>  
</p>
