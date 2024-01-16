package com.example.roomdao30

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.roomdao30.AllFragments.*

class MainActivity : AppCompatActivity(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateTo(MainFragment.newInstance())

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)


    }

    override fun onSupportNavigateUp(): Boolean {

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.container)
        if (currentFragment != null) {
            replaceFragment(currentFragment)
        }
        return true
    }


    override fun onBackPressed() {
        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.container)
        if (currentFragment != null) {
            replaceFragment(currentFragment)
        }
    }


    fun replaceFragment(currentFragment: Fragment) {
        if (currentFragment
                    is ClientFragment || currentFragment
                    is RatingFragment || currentFragment
                    is AllRequestRating || currentFragment
                    is AuthorFragment || currentFragment
                    is AllRequestAuthor || currentFragment
                    is ProductFragment || currentFragment
                    is AllRequestProductsRating || currentFragment
                    is BookShopFragment || currentFragment
                    is EmployeeFragment || currentFragment
                    is CarFragment || currentFragment
                    is AllRequestCar || currentFragment
                    is SaleFragment || currentFragment
                    is AllRequestEmployeeAndProduct || currentFragment
                    is ProviderFragment || currentFragment
                    is SupplyFragment || currentFragment
                    is ProvidersSupplyProducts || currentFragment
                    is PricesFragment || currentFragment
                    is PurchasesPricesFragment|| currentFragment
                    is Purchases||currentFragment is PurchaseStatuseFragment
                   ||currentFragment is PhoneNumberFragment

        ) {
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, MainFragment.newInstance())
            transaction.commit()

        } else if (currentFragment is MainFragment) {
            finish()
        }
    }

    override fun navigateTo(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }


    //        val count = supportFragmentManager.backStackEntryCount
//        if (count > 1) {
//            repeat(count - 1) { supportFragmentManager.popBackStack() }
//        } else {
//            finish()
//        }
//    }
}