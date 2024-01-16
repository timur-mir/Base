package com.example.roomdao30.AllViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao30.AllEntitys.Employee
import com.example.roomdao30.AllEntitys.Rating
import com.example.roomdao30.AllRelation.ClientWithRating
import com.example.roomdao30.AllRelation.EmployeeAndProductsRelation
import com.example.roomdao30.AllRepositorys.EmployeeRepository
import com.example.roomdao30.AllRepositorys.RatingRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class EmployeeViewModel:ViewModel() {
    private val employeeRepository = EmployeeRepository()

    private val getEmployeeSaleProduct=MutableLiveData<EmployeeAndProductsRelation>()

    val requestEmployeeProductRelation:LiveData<EmployeeAndProductsRelation>
    get() = getEmployeeSaleProduct

    private val getEmployeeMutableLiveData = MutableLiveData<Employee?>()

    val updateEmployeeLiveData: LiveData<Employee?>
        get() = getEmployeeMutableLiveData


  private val employeeMutableLiveData = MutableLiveData<List<Employee>>()

    val recordEmployeeData: LiveData<List<Employee>>
        get() = employeeMutableLiveData

    private val saveSuccessLiveEvent = MutableLiveData<Unit>()

    val saveSuccessLiveData: LiveData<Unit>
        get() = saveSuccessLiveEvent

    private val saveErrorLiveEvent = MutableLiveData<Int>()

    val saveErrorLiveData: LiveData<Int>
        get() = saveErrorLiveEvent

//    fun requestRating() {
//        viewModelScope.launch {
//            try {
//                requestRatingsForClient.postValue(ratingRepository.requestRating())
//            } catch (t: Throwable) {
//                Timber.e(t, "Ошибка в запросе рейтинга товаров $t ")
//            }
//        }
//
//    }
fun loadRequestEplProd(employeeId:Int){
    viewModelScope.launch {
        try {
            getEmployeeSaleProduct.postValue(employeeRepository.getAllEmployeeAndProductsWithRelations(employeeId))
        }catch (t:Throwable){
            Timber.e(t, "Ошибка в запросе ")
        }
    }
}
    fun loadList() {
        viewModelScope.launch {
            try {
                employeeMutableLiveData.postValue(employeeRepository.getAllEmployees())
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка в списке сотрудников ")
            }
        }
    }

    fun removeEmployeeById(emplId: Int) {
        viewModelScope.launch {
            try {
                employeeRepository.removeEmployeeById(emplId)
            } catch (t: Throwable) {
                Timber.e(t)
            }

        }
    }

    fun getEmployeeById(emplId: Int) {
        viewModelScope.launch {
            try {
                val employee = employeeRepository.getEmployeeById(emplId)
                getEmployeeMutableLiveData.postValue(employee)
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }

    fun save(employee: Employee) {
        viewModelScope.launch {
            try {
                if (employee.id == 0) {
                    employeeRepository.saveEmpl(employee)
                } else {
                    employeeRepository.updateEmployee(employee)
                }
                saveSuccessLiveEvent.postValue(Unit)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка сохранения сотрудника")
                showError(t)
            }
        }
    }

    fun updateEmployee(employee: Employee) {
        viewModelScope.launch {
            try {
                employeeRepository.updateEmployee(employee)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка обновления сотрудника")
                showError(t)

            }

        }
    }

    private fun showError(t: Throwable) {
        saveErrorLiveEvent.postValue(2500)
    }


}