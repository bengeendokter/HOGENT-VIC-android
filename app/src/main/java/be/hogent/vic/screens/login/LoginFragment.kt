package be.hogent.vic.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import be.hogent.vic.R
import be.hogent.vic.databinding.FragmentLoginBinding
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var account : Auth0

    private var loggedIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        account = Auth0(
            getString(R.string.auth_client_id),
            getString(R.string.auth_domain)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.apply {
            lgnBtnLogin.setOnClickListener { loginWithBrowser() }
            lgnBtnLogout.setOnClickListener { logout() }
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        checkIfToken()
        setLoggedIn()
    }


    private fun checkIfToken(){
        val token = CredentialsManager.getAccessToken(requireContext())
        if(token != null){
            //checking if the token works...
            showUserProfile(token)
        }
//        else {
//            Toast.makeText(context, "Token doesn't exist", Toast.LENGTH_SHORT).show()
//        }
    }

    private fun setLoggedIn() {
        var bottomNav = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        binding.apply {
            if (loggedIn) {
                bottomNav.visibility = View.VISIBLE
                lgnBtnLogin.visibility = View.GONE
                lgnBtnLogout.visibility = View.VISIBLE
            } else {
                bottomNav.visibility = View.GONE
                lgnTxtWelcome.text = "Niet ingelogd"
                lgnBtnLogout.visibility = View.GONE
                lgnBtnLogin.visibility = View.VISIBLE
            }
        }
    }


    private fun loginWithBrowser() {
        // Setup the WebAuthProvider, using the custom scheme and scope.

        WebAuthProvider.login(account)
            .withScheme("demo")
            .withScope("openid profile email")
            // Launch the authentication passing the callback where the results will be received
            .start(requireContext(), object : Callback<Credentials, AuthenticationException> {
                // Called when there is an authentication failure
                override fun onFailure(exception: AuthenticationException) {
                    loggedIn = false
                }

                // Called when authentication completed successfully
                override fun onSuccess(credentials: Credentials) {
                    // Get the access token from the credentials object.
                    // This can be used to call APIs
                    val accessToken = credentials.accessToken
                    // Toast.makeText(context, accessToken, Toast.LENGTH_SHORT).show()
                    Toast.makeText(context, "Succesfully logged in", Toast.LENGTH_SHORT).show()

                    CredentialsManager.saveCredentials(requireContext(), credentials)
                    checkIfToken()
                    setLoggedIn()
                    requireView().findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                }
            })
    }
    private fun logout() {
        WebAuthProvider.logout(account)
            .withScheme("demo")
            .start(requireContext(), object: Callback<Void?, AuthenticationException> {
                override fun onSuccess(payload: Void?) {
                    Toast.makeText(context, "Succesfully logged out", Toast.LENGTH_SHORT).show()
                    loggedIn = false
                    setLoggedIn()

                }
                override fun onFailure(error: AuthenticationException) {
                    Toast.makeText(context, "Logout failed", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun showUserProfile(accessToken: String){
        var client = AuthenticationAPIClient(account)

        client.userInfo(accessToken)
            .start(object : Callback<UserProfile, AuthenticationException> {
                override fun onFailure(exception: AuthenticationException) {
                    loggedIn = false
                    setLoggedIn()
                }

                override fun onSuccess(profile: UserProfile) {
                    binding.lgnTxtWelcome.text = String.format("Welkom %s", profile.name)
                    loggedIn = true
                    setLoggedIn()
                }
            })
    }
}