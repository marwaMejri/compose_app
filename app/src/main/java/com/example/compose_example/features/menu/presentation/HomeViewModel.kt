package com.example.compose_example.features.menu.presentation

import androidx.lifecycle.ViewModel
import com.example.compose_example.R
import com.example.compose_example.features.menu.data.home.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()
    fun handleHomeEvent(event: HomeEvents) {
        _uiState.update { currentState ->
            when (event) {
                is HomeEvents.ToggleAnimation -> {
                    currentState.copy(initAnimation = event.value)
                }
                is HomeEvents.OnCategoryClicked -> {
                    val updateCategory = currentState.copy(clickedCategoryIndex = event.value)
                    val updatedState = updateCategory.copy(scrollToFirstItem = true)
                    val launchFavoriteAnimationState = updatedState.copy(
                        launchFavoriteAnimation = false
                    )
                    getFilteredList(launchFavoriteAnimationState, event.category)
                }
                is HomeEvents.AddToFavorite -> {
                    val updatedFavoriteList = ArrayList(currentState.favoriteList)
                    if (currentState.favoriteList.contains(event.item)) {
                        updatedFavoriteList.remove(event.item)
                    } else {
                        updatedFavoriteList.add(event.item)
                    }
                    val favoriteListState = currentState.copy(favoriteList = updatedFavoriteList)
                    val launchFavoriteAnimationState = favoriteListState.copy(
                        launchFavoriteAnimation = updatedFavoriteList.contains(event.item)
                    )
                    launchFavoriteAnimationState.copy(favoriteAnimationItemIndex = event.itemIndex)
                }
                HomeEvents.InitAnimalsList -> {
                    val list = initAnimalList()
                    val updatedState = currentState.copy(animalList = list)
                    getFilteredList(updatedState, AnimalCategory.CAT)
                }
                HomeEvents.ResetScrollValue -> {
                    currentState.copy(scrollToFirstItem = false)
                }
                HomeEvents.ResetFavoriteAnimationValue -> {
                    val state = currentState.copy(launchFavoriteAnimation = false)
                    state.copy(favoriteAnimationItemIndex = -1)
                }
                is HomeEvents.UpdateRemoveFavoriteAnimationValue -> {
                    currentState.copy(launchRemoveFavoriteAnimation = event.value)
                }

            }
        }
    }

}

private fun getFilteredList(currentState: HomeState, category: AnimalCategory): HomeState {
    val filteredList = arrayListOf<AnimalItem>()
    for (animalItem: AnimalItem in currentState.animalList) {
        if (animalItem.category == category) {
            filteredList.add(animalItem)
        }
    }
    return if (filteredList.isNotEmpty()) {
        currentState.copy(filteredAnimalList = filteredList)
    } else {
        currentState
    }

}

private fun initAnimalList(): ArrayList<AnimalItem> {
    return arrayListOf(
        AnimalItem(
            name = "Samantha",
            distance = 5.0,
            image = R.drawable.dog1,
            size = AnimalSize.Small,
            age = 1,
            category = AnimalCategory.DOG,
            description = "It's a dog that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Female,
            weight = 10.0
        ),
        AnimalItem(
            name = "Bella",
            distance = 5.0,
            image = R.drawable.dog2,
            size = AnimalSize.Medium,
            age = 1,
            category = AnimalCategory.DOG,
            description = "It's a dog that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Male,
            weight = 10.0
        ),
        AnimalItem(
            name = "Cooper",
            distance = 5.0,
            image = R.drawable.dog3,
            size = AnimalSize.Large,
            age = 1,
            category = AnimalCategory.DOG,
            description = "It's a dog that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Female,
            weight = 10.0
        ),
        AnimalItem(
            name = "Daisy",
            distance = 5.0,
            image = R.drawable.dog4,
            size = AnimalSize.Medium,
            age = 1,
            category = AnimalCategory.DOG,
            description = "It's a dog that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Male,
            weight = 10.0
        ),
        AnimalItem(
            name = "Lucy",
            distance = 5.0,
            image = R.drawable.dog5,
            size = AnimalSize.Large,
            age = 1,
            category = AnimalCategory.DOG,
            description = "It's a dog that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Male,
            weight = 10.0
        ),
        AnimalItem(
            name = "Whiskers",
            distance = 10.0,
            image = R.drawable.cat1,
            size = AnimalSize.Large,
            age = 1,
            category = AnimalCategory.CAT,
            description = "It's a cat  that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Male,
            weight = 10.0
        ),
        AnimalItem(
            name = "Felix",
            distance = 10.0,
            image = R.drawable.cat2,
            size = AnimalSize.Medium,
            age = 1,
            category = AnimalCategory.CAT,
            description = "It's a cat  that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Female,
            weight = 10.0
        ),
        AnimalItem(
            name = "Oscar",
            distance = 10.0,
            image = R.drawable.cat3,
            size = AnimalSize.Small,
            age = 1,
            category = AnimalCategory.CAT,
            description = "It's a cat  that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Male,
            weight = 7.0
        ),
        AnimalItem(
            name = "Oscar",
            distance = 7.0,
            image = R.drawable.cat4,
            size = AnimalSize.Medium,
            age = 1,
            category = AnimalCategory.CAT,
            description = "It's a cat  that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Female,
            weight = 7.0
        ),
        AnimalItem(
            name = "Oscar",
            distance = 15.0,
            image = R.drawable.cat5,
            size = AnimalSize.Medium,
            age = 1,
            category = AnimalCategory.CAT,
            description = "It's a cat  that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Male,
            weight = 10.0
        ),
        AnimalItem(
            name = "Arabella",
            distance = 10.0,
            image = R.drawable.fish1,
            size = AnimalSize.Medium,
            age = 1,
            category = AnimalCategory.FISH,
            description = "It's a fish  that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Male,
            weight = 5.0
        ),
        AnimalItem(
            name = "Bessie",
            distance = 7.0,
            image = R.drawable.fish2,
            size = AnimalSize.Small,
            age = 1,
            category = AnimalCategory.FISH,
            description = "It's a fish  that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Female,
            weight = 5.0
        ),
        AnimalItem(
            name = "Annie",
            distance = 17.0,
            image = R.drawable.fish3,
            size = AnimalSize.Small,
            age = 1,
            category = AnimalCategory.FISH,
            description = "It's a fish  that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Male,
            weight = 5.0
        ),
        AnimalItem(
            name = "Annie",
            distance = 10.0,
            image = R.drawable.fish4,
            size = AnimalSize.Small,
            age = 1,
            category = AnimalCategory.FISH,
            description = "It's a fish  that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Female,
            weight = 5.0
        ),
        AnimalItem(
            name = "Annie",
            distance = 8.0,
            image = R.drawable.fish5,
            size = AnimalSize.Small,
            age = 1,
            category = AnimalCategory.FISH,
            description = "It's a fish  that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Female,
            weight = 5.0
        ),
        AnimalItem(
            name = "Swedish",
            distance = 8.0,
            image = R.drawable.chick1,
            size = AnimalSize.Small,
            age = 1,
            category = AnimalCategory.CHICK,
            description = "It's a chick  that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Female,
            weight = 5.0
        ),
        AnimalItem(
            name = "Floater",
            distance = 8.0,
            image = R.drawable.chick2,
            size = AnimalSize.Small,
            age = 1,
            category = AnimalCategory.CHICK,
            description = "It's a chick  that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Male,
            weight = 5.0
        ),

        AnimalItem(
            name = "Chips",
            distance = 8.0,
            image = R.drawable.chick3,
            size = AnimalSize.Small,
            age = 1,
            category = AnimalCategory.CHICK,
            description = "It's a chick  that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Female,
            weight = 5.0
        ),
        AnimalItem(
            name = "Bob",
            distance = 8.0,
            image = R.drawable.chick4,
            size = AnimalSize.Small,
            age = 1,
            category = AnimalCategory.CHICK,
            description = "It's a chick  that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Male,
            weight = 5.0
        ),
        AnimalItem(
            name = "Flotsam",
            distance = 8.0,
            image = R.drawable.chick5,
            size = AnimalSize.Small,
            age = 1,
            category = AnimalCategory.CHICK,
            description = "It's a chick  that i have found on the side of the road 1 year ago, he is now cheerful and active.",
            location = "Sousse,Tunisia",
            owner = Owner(
                email = "owner@gmail.com",
                name = "Lazurenko",
                location = "Tunisia",
                phoneNumber = "24611826",
                image = R.drawable.ic_user_placeholder
            ),
            sex = Sex.Female,
            weight = 5.0
        ),
    )
}
