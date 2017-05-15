(function () {

  'use strict';

  angular
    .module('mainApp')
    .service('authService', authService);

  authService.$inject = ['lock', 'authManager', '$q', '$cookies'];

  function authService(lock, authManager, $q, $cookies) {
    var profileCookies = $cookies.get('profile') || null;
    var userProfile = JSON.parse(profileCookies) || null;
    var deferredProfile = $q.defer();
    var auth = false;
    
    if (userProfile) {
      auth = true;
      deferredProfile.resolve(userProfile);
    }

    function login() {
      lock.show();
    }
    
    function getUserRoles(){
      return (userProfile) ? userProfile.roles : null;
    }

    // Logging out just requires removing the user's
    // id_token and profile
    function logout() {
      deferredProfile = $q.defer();
      $cookies.remove('id_token');
      $cookies.remove('profile');
      authManager.unauthenticate();
      userProfile = null;
      auth = false;
    }

    // Set up the logic for when a user authenticates
    // This method is called from app.run.js
    function registerAuthenticationListener() {
      lock.on('authenticated', function (authResult) {
        $cookies.put('id_token', authResult.idToken);
        authManager.authenticate();

        lock.getProfile(authResult.idToken, function (error, profile) {
          if (error) {
            return console.log(error);
          }
          auth = true;
          $cookies.put('profile', JSON.stringify(profile));
          deferredProfile.resolve(profile);
        });
      });

      lock.on('authorization_error', function (err) {
        console.log(err);
      });
    }
    
    function isAuthenticated(){
      return auth;
    }
    
    function returnLogInfo(){
      return {user: userProfile.email, token: $cookies.get('id_token')};
    }

    function getProfileDeferred() {
      return deferredProfile.promise;
    }

    return {
      login: login,
      logout: logout,
      registerAuthenticationListener: registerAuthenticationListener,
      getProfileDeferred: getProfileDeferred,
      isAuthenticated: isAuthenticated,
      getUserRoles: getUserRoles,
      returnLogInfo:returnLogInfo
    };
  }
})();
