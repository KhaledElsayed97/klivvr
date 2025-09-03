# Klivvr Android Assignment

This assignment loads a list of ~200k cities, allows fast prefix-based search with real-time updates and initiates a google maps intent on clicking one of the cities.

## How to Run
1. Clone this repo
2. Open in Android Studio
3. Run on any emulator or device (min SDK 21)

## Tech Stack
- Kotlin
- Jetpack Compose
- Hilt DI
- Kotlinx Serialization (JSON parsing)
- MVVM with ViewModel + StateFlow

## Architecture
- **Data Layer**: Repository loads JSON from `res/raw/cities.json`.
- **Domain Layer**: UseCase for for pre processing the cities data into a trie structure.
- **Presentation Layer**: ViewModel manages state, UI is built with Compose.

## Search Algorithm
I used a **Trie** data structure here because it allows fast prefix lookups in O(length of prefix) time,
instead of scanning every city name, we can just walk down the characters of the query (i.e a -> l -> b) until we reach the matching node,
and from there we can instantly get all the cities under this node/prefix.

Which is much faster than scanning or filtering a large list (200k entries) on each keystroke since the requirement is to keep updating while typing
, this keeps the UI responsive and fast as the user types.

## Extra
- Sticky headers for grouped city names and a connecting line between them
- Animations for search bar, empty and loading states of the app
