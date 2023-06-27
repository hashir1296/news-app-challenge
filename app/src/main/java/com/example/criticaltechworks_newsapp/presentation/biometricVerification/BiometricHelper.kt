package com.example.criticaltechworks_newsapp.presentation.biometricVerification

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.util.concurrent.Executor

object BiometricHelper {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private fun setExecutor(context: Context) {
        executor = ContextCompat.getMainExecutor(context)
    }

    fun showFingerPrintPromptIfAvailable(
        fragment: Fragment, callbacks: BiometricCallbacks
    ) {
        val context = fragment.requireContext()
        setExecutor(context)
        val biometricManager = BiometricManager.from(context)
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                //Setup prompt and show to user
                setupBiometricPromptFor(fragment, callbacks)
                biometricPrompt.authenticate(promptInfo)
                callbacks.authPromptShownOnce()
            }

            else -> {
                callbacks.authNotAvailable()
            }
        }
    }

    private fun setupBiometricPromptFor(
        fragment: Fragment, callbacks: BiometricCallbacks
    ) {
        biometricPrompt = BiometricPrompt(fragment,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    callbacks.onAuthSucceeded()
                }

                override fun onAuthenticationError(
                    errorCode: Int, errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    if (errorCode == BiometricPrompt.ERROR_USER_CANCELED || errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                        callbacks.onAuthCancelled()
                    }
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for News App")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Cancel").setConfirmationRequired(true)
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .build()
    }
}

interface BiometricCallbacks {
    fun authNotAvailable()

    fun authPromptShownOnce()

    fun onAuthSucceeded()

    fun onAuthCancelled()
}