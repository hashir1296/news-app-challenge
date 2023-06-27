package com.example.criticaltechworks_newsapp.presentation.biometricVerification

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.criticaltechworks_newsapp.R
import java.util.concurrent.Executor

/*This helper provides the necessary functions needed to show fingerprint auth popup
* */
object BiometricHelper {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private fun setExecutor(context: Context) {
        executor = ContextCompat.getMainExecutor(context)
    }

    /*This function tells if system should fingerprint auth or not
    * @param fragment to show the popup
    * @param callback to return to fragment
    * Setup biometric prompt only when it meets both our conditions
    * */

    fun showFingerPrintPromptIfAvailable(
        fragment: Fragment, callbacks: BiometricCallbacks
    ) {
        val context = fragment.requireContext()
        setExecutor(context)
        val biometricManager = BiometricManager.from(context)
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                //Setup prompt and show to user
                setupBiometricPromptFor(fragment, callbacks, context)
                biometricPrompt.authenticate(promptInfo)
            }

            else -> {
                callbacks.onFingerPrintAuthNotAvailable()
            }
        }
    }


    /*Prepare the biometric popup*/
    private fun setupBiometricPromptFor(
        fragment: Fragment, callbacks: BiometricCallbacks, context: Context
    ) {
        biometricPrompt = BiometricPrompt(fragment,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    callbacks.onFingerprintAuthSucceeded()
                }

                override fun onAuthenticationError(
                    errorCode: Int, errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    if (errorCode == BiometricPrompt.ERROR_USER_CANCELED || errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                        callbacks.onFingerprintAuthCancelled()
                    }
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder().setTitle(
            "${context.getString(R.string.biometric_login_for)} ${
                context.getString(
                    R.string.app_name
                )
            }"
        ).setSubtitle(context.getString(R.string.biometric_popup_subtitle))
            .setNegativeButtonText(context.getString(R.string.cancel))
            .setConfirmationRequired(true)
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .build()
    }
}

interface BiometricCallbacks {
    fun onFingerPrintAuthNotAvailable()

    fun onFingerprintAuthSucceeded()

    fun onFingerprintAuthCancelled()
}