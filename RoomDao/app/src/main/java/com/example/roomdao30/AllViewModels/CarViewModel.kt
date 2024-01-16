package com.example.roomdao30.AllViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllEntitys.OfficialCar
import com.example.roomdao30.AllRelation.ClientWithAuthor
import com.example.roomdao30.AllRelation.EmployeeWithCar
import com.example.roomdao30.AllRepositorys.AuthorRepository
import com.example.roomdao30.AllRepositorys.CarRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class CarViewModel:ViewModel() {
    private val carRepository = CarRepository()
    private val getCarMutableLiveData = MutableLiveData<OfficialCar?>()

    val updateCarLiveData: LiveData<OfficialCar?>
        get() = getCarMutableLiveData

   private val requestCarForEmployee = MutableLiveData<List<EmployeeWithCar>>()
//
  val requestCarList: LiveData<List<EmployeeWithCar>>
       get() = requestCarForEmployee

    private val carsMutableLiveData = MutableLiveData<List<OfficialCar>>()

    val recordCarData: LiveData<List<OfficialCar>>
        get() = carsMutableLiveData

    private val saveSuccessLiveEvent = MutableLiveData<Unit>()

    val saveSuccessLiveData: LiveData<Unit>
        get() = saveSuccessLiveEvent

    private val saveErrorLiveEvent = MutableLiveData<Int>()

    val saveErrorLiveData: LiveData<Int>
        get() = saveErrorLiveEvent


    fun requestCars() {
        viewModelScope.launch {
            try {
              requestCarForEmployee.postValue(carRepository.requestCar())
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка в запросе автомобиля $t ")
            }
        }
    }

    fun loadList() {
        viewModelScope.launch {
            try {
             carsMutableLiveData.postValue(carRepository.getAllCars())
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка в списке машин ")
            }
        }
    }

    fun removeCarById(carId: Int) {
        viewModelScope.launch {
            try {
                carRepository.removeCarById(carId)
            } catch (t: Throwable) {
                Timber.e(t)
            }

        }
    }

    fun getCarById(carId: Int) {
        viewModelScope.launch {
            try {
                val car= carRepository.getCarById(carId)
                getCarMutableLiveData.postValue(car)
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }

    fun save(car:OfficialCar) {
        viewModelScope.launch {
            try {
                if (car.id == 0) {
                    carRepository.saveCar(car)
                } else {
                    carRepository.updateCar(car)
                }
                saveSuccessLiveEvent.postValue(Unit)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка сохранения машины")
                showError(t)
            }
        }
    }

    fun updateCar(car:OfficialCar) {
        viewModelScope.launch {
            try {
                carRepository.updateCar(car)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка обновления машины")
                showError(t)

            }

        }
    }

    private fun showError(t: Throwable) {
        saveErrorLiveEvent.postValue(2750)
    }
}