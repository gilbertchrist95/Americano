package com.mokapos.americano

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mokapos.americano.data.Product
import com.mokapos.americano.data.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private var productMutableLiveData = MutableLiveData<List<Product>>()
    fun getProducts(): LiveData<List<Product>> = productMutableLiveData



    fun populateData() {
        viewModelScope.launch(Dispatchers.IO) {
            val count = productRepository.countProduct()
            if (count <= 0) {
                productRepository.insertAll(
                    arrayOf(
                        Product(0, "Gilt desk trio",  120, "001.jpg"),
                        Product(0, "Whitney belt", 35, "001.jpg"),
                        Product(0, "Garden strand", 98, "001.jpg"),
                        Product(0, "Strut earrings", 34, "001.jpg"),
                        Product(0, "Varsity socks", 12, "001.jpg"),
                        Product(0, "Weave keyring", 16, "001.jpg"),
                        Product(0, "Gatsby hat", 40, "001.jpg"),
                        Product(0, "Shrug bag", 98, "001.jpg"),
                        Product(0, "Gilt desk trio", 58, "001.jpg"),
                        Product(0, "Copper wire rack", 18, "001.jpg"),
                        Product(0, "Soothe ceramic set", 28, "001.jpg"),
                        Product(0, "Hurrahs tea set", 34, "001.jpg"),
                        Product(0, "Blue stone mug", 18, "001.jpg"),
                        Product(0, "Rainwater tray", 27, "001.jpg"),
                        Product(0, "Chambray napkins", 16, "001.jpg"),
                        Product(0, "Succulent planters", 16, "001.jpg"),
                        Product(0, "Quartet table", 75, "001.jpg"),
                        Product(0, "Kitchen quattro", 29, "001.jpg"),
                        Product(0, "Clay sweater", 48, "001.jpg"),
                        Product(0, "Sea tunic", 45, "001.jpg"),
                        Product(0, "Plaster tunic", 38, "001.jpg"),
                        Product(0, "White pinstripe shirt", 70, "001.jpg"),
                        Product(0, "Chambray shirt", 70, "001.jpg"),
                    )
                )
            }

            productMutableLiveData.postValue(productRepository.getAll())
        }
    }

}