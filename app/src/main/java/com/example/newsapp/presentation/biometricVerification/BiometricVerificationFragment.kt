package com.example.newsapp.presentation.biometricVerification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.databinding.FragmentBiometricVerificationBinding

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BiometricVerificationFragment : Fragment(), BiometricCallbacks {


    private lateinit var binding: FragmentBiometricVerificationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBiometricVerificationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        BiometricHelper.showFingerPrintPromptIfAvailable(this, this)
    }

    override fun onFingerPrintAuthNotAvailable() {
        //User does not have finger print either on device or has not configured yet
        findNavController().navigate(
            BiometricVerificationFragmentDirections.actionBiometricVerificationFragmentToNewsHeadlinesListFragment()
        )
    }


    override fun onFingerprintAuthSucceeded() {
        //Fingerprint auth succeeded, navigate to next Listing fragment
        findNavController().navigate(
            BiometricVerificationFragmentDirections.actionBiometricVerificationFragmentToNewsHeadlinesListFragment()
        )
    }

    override fun onFingerprintAuthCancelled() {
        //Finish activity and close app because fingerprint authentication is required
        requireActivity().finish()
    }
}