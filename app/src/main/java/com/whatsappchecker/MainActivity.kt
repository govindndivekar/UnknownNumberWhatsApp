package com.whatsappchecker

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.whatsappchecker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectedCountryCode: CountryCode? = null
    private var backPressedTime: Long = 0
    private val backPressInterval: Long = 2000 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCountryCodeDropdown()
        setupClickListeners()
        setupBackPressHandler()
    }

    private fun setupCountryCodeDropdown() {
        val countries = CountryCode.getAllCountries()
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            countries
        )

        binding.countryCodeDropdown.setAdapter(adapter)
        binding.countryCodeDropdown.setOnItemClickListener { _, _, position, _ ->
            selectedCountryCode = countries[position]
        }

        // Set default country (India)
        val defaultCountry = countries.find { it.code == "+91" }
        defaultCountry?.let {
            binding.countryCodeDropdown.setText(it.toString(), false)
            selectedCountryCode = it
        }
    }

    private fun setupClickListeners() {
        binding.btnSubmit.setOnClickListener {
            validateAndCheckWhatsApp()
        }
    }

    private fun validateAndCheckWhatsApp() {
        val phoneNumber = binding.etPhoneNumber.text.toString().trim()

        // Validate country code
        if (selectedCountryCode == null) {
            showError(getString(R.string.error_select_country))
            return
        }

        // Validate phone number
        if (phoneNumber.isEmpty()) {
            binding.phoneNumberLayout.error = getString(R.string.error_empty_phone)
            return
        }

        // Clear error
        binding.phoneNumberLayout.error = null

        // Validate phone number format (10-15 digits)
        if (phoneNumber.length < 10 || phoneNumber.length > 15 || !phoneNumber.matches(Regex("^[0-9]+$"))) {
            binding.phoneNumberLayout.error = getString(R.string.error_invalid_phone)
            return
        }

        // Open WhatsApp
        openWhatsApp(phoneNumber)
    }

    private fun openWhatsApp(phoneNumber: String) {
        try {
            // Remove any leading zeros from phone number
            val cleanNumber = phoneNumber.trimStart('0')

            // Construct WhatsApp URL
            val countryCode = selectedCountryCode?.code?.replace("+", "") ?: ""
            val whatsappUrl = "https://wa.me/$countryCode$cleanNumber"

            // Create intent to open WhatsApp/Browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(whatsappUrl))

            // Try to start the activity
            try {
                startActivity(intent)
                showSuccess(getString(R.string.success_user_found))
            } catch (e: ActivityNotFoundException) {
                showError(getString(R.string.error_whatsapp_not_installed))
            }
        } catch (e: Exception) {
            showError("Error: ${e.localizedMessage}")
        }
    }

    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(getColor(R.color.error))
            .setTextColor(getColor(R.color.white))
            .show()
    }

    private fun showSuccess(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
            .setBackgroundTint(getColor(R.color.success))
            .setTextColor(getColor(R.color.white))
            .show()
    }

    private fun setupBackPressHandler() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (backPressedTime + backPressInterval > System.currentTimeMillis()) {
                    // Second back press - show exit dialog
                    showExitDialog()
                } else {
                    // First back press - show toast
                    Toast.makeText(this@MainActivity, getString(R.string.exit_toast), Toast.LENGTH_SHORT).show()
                }
                backPressedTime = System.currentTimeMillis()
            }
        })
    }

    private fun showExitDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.exit_dialog_title))
            .setMessage(getString(R.string.exit_dialog_message))
            .setPositiveButton(getString(R.string.exit_dialog_yes)) { _, _ ->
                finishAffinity() // Close the app
            }
            .setNegativeButton(getString(R.string.exit_dialog_no)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(true)
            .show()
    }
}
