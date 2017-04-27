(function () {

  'use strict';

  angular
    .module('mainApp')
    .service('authService', authService);

  authService.$inject = ['$state', 'angularAuth0', 'authManager','lock', '$q', '$rootScope'];

  function authService($state, angularAuth0, authManager, lock, $q, $rootScope) {

    function login(username, password) {
      angularAuth0.redirect.loginWithCredentials({
        connection: 'Username-Password-Authentication',
        username: username,
        password: password,
      }, function(err) {
        if (err) return alert(err.description);
      });
    }

    function signup(username, password) {
      angularAuth0.redirect.signupAndLogin({
        connection: 'Username-Password-Authentication',
        email: username,
        password: password
      }, function(err) {
        if (err) return alert(err.description);
      });
    }
    
    function handleParseHash() {
      angularAuth0.parseHash(
        { _idTokenVerification: false },
        function(err, authResult) {
        if (err) {
          console.log(err);
        }
        if (authResult && authResult.idToken) {
          setUser(authResult);
        }
      });
    }

    function logout() {
      localStorage.removeItem('access_token');
      localStorage.removeItem('id_token');
    }
    
    function setUser(authResult) {
      localStorage.setItem('access_token', authResult.accessToken);
      localStorage.setItem('id_token', authResult.idToken);
    }

    function isAuthenticated() {
      return authManager.isAuthenticated();
    }
    
    function getProfileDeferred() {
      return deferredProfile.promise;
    }

    function isAdmin() {
      return userProfile && userProfile.app_metadata
        && userProfile.app_metadata.roles
        && userProfile.app_metadata.roles.indexOf('admin') > -1;
    }

    $rootScope.$on('$stateChangeStart', function(event, nextRoute) {
        
      if (nextRoute.controller === 'AdminController') {
        if (!isAdmin()) {
          alert('You are not allowed to see the Admin content');
          return event.preventDefault();
        }
      }
    });

    return {
      login: login,
      signup: signup,
      handleParseHash: handleParseHash,
      logout: logout,
      isAuthenticated: isAuthenticated,
      registerAuthenticationListener: registerAuthenticationListener,
      getProfileDeferred: getProfileDeferred,
      isAdmin: isAdmin
    }
  }
})();
