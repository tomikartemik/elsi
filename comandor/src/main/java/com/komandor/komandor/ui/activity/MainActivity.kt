package com.komandor.komandor.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.komandor.komandor.R
import com.komandor.komandor.cryptopro.CryproProManager
import com.komandor.komandor.cryptopro.utils.KeyStoreType
import com.komandor.komandor.databinding.ActivityMainBinding
import com.komandor.komandor.ui.dialog.WarningDialog
import dagger.hilt.android.AndroidEntryPoint
import io.github.centrifugal.centrifuge.*
import ru.cprocsp.ACSP.tools.config.Config
import timber.log.Timber
import java.nio.charset.StandardCharsets.UTF_8
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var cryproProManager: CryproProManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // setContentView(R.layout.activity_main)
        //throw RuntimeException("Test Crash") // Force a crash
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        initBottomNav()

        navController.addOnDestinationChangedListener { f1, destination, arguments ->
            Timber.d("destination = ${destination.displayName}")
            binding.tvTitle.text = destination.label
            configBottomNavBar(destination)
        }

        // 1. Инициализация провайдеров: CSP и java-провайдеров
        // (Обязательная часть).

        val r: Pair<Boolean, String> = cryproProManager.initCSPProviders()
        if (!r.first) {
            Timber.d(r.second)
            WarningDialog(r.second, {
                finish()
            }).show(supportFragmentManager, "confirm")

        }

        cryproProManager.initJavaProviders()
        cryproProManager.initCspTool()

        val readerConfig: Config = KeyStoreType.loadConfig(this)


    }

    override fun onSupportNavigateUp() = navController.navigateUp() || super.onSupportNavigateUp()

    private fun initBottomNav() {
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
    }

    private fun configBottomNavBar(destination: NavDestination) {
        //Timber.d("destination = ${destination.label}")

        if (destination.arguments.containsKey("isBottomNav")) {
            val arg = destination.arguments.get("isBottomNav")?.defaultValue as Boolean
            //Timber.d("isBottomNav = ${arg}")
            binding.bottomNavView.visibility = if (arg) {
                View.VISIBLE
            } else {
                View.GONE
            }
        } else {
            binding.bottomNavView.visibility = View.GONE
        }
    }



    fun showProgress() {
        binding.cvLoading.visibility = View.VISIBLE
    }

    fun hideProgress() {
        binding.cvLoading.visibility = View.GONE
    }

}