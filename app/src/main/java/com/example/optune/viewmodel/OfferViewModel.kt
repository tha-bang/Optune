package com.example.optune.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.optune.api.OfferApi
import com.example.optune.data.model.Offer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfferViewModel @Inject constructor(
    private val offerApi: OfferApi
) : ViewModel() {

    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    val offers: StateFlow<List<Offer>> = _offers

    init {
        fetchOffers()
    }

    private fun fetchOffers() {
        viewModelScope.launch {
            try {
                _offers.value = offerApi.getOffers()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
