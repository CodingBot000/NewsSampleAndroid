<h1 align="center">NewsSample</h1>

<p align="center">  
 This ToyProject make modern Android development with Compose, Dagger-Hilt, Coroutines, Jetpack based on MVI architecture.
</br>
</br>

</br>
This is an application designed to provide information for the purpose of introducing products.
</br>
News Api : https://newsapi.org/v2/top-headlines?q=book&apiKey={api-key}
</br></br>
To cache data locally while online and retrieve it when the application transitions to offline mode, you can follow these steps:
</br>
Steps to Implement Local Data Caching
</br>
Check Network Status:
</br></br>
Implement a way to check the network status (online or offline).
</br>
Fetch Data When Online:
</br>
When the application is online, fetch the data from the server.
</br>
Cache Data Locally:
</br></br>
Store the fetched data in a local database or file system for later use.
</br>
Retrieve Data When Offline:
</br></br>
When the application is offline, retrieve the data from the local cache.
</br>
</br>

## Tech stack
- [Kotlin] based, [Coroutines] + [Flow]  for asynchronous.
- Compose
- [Hilt] for dependency injection.
- Jetpack
- Room
- MVI Architecture

