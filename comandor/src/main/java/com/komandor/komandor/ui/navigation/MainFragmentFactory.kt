package com.komandor.komandor.ui.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.komandor.komandor.ui.fragments.ChatsFragment
import javax.inject.Inject
// https://medium.com/supercharges-mobile-product-guide/fragmentfactory-with-dagger-and-hilt-31ee17babf73
class MainFragmentFactory @Inject constructor(
    //... your dependencies
) : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment = when(className) {
        ChatsFragment::class.java.name -> ChatsFragment()
        // other fragments

        else -> super.instantiate(classLoader, className)
    }
}