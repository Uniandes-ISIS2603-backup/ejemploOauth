(function (ng) {

  var mod = ng.module('authModule', ['ui.router', 'checklist-model', 'ngMessages', 'ui.bootstrap']);

  mod.config(['$stateProvider', function ($sp) {
      $sp.state('login', {
        url: '/login',
        views: {
          mainView: {
            templateUrl: 'src/modules/loginAuth0/login.tpl.html',
            controller: 'LoginController',
            controllerAs: 'vm'
          }
        }
      });
    }]);
  /*
   mod.config(['$httpProvider', function ($httpProvider) {
   $httpProvider.interceptors.push(['$q', '$injector', function ($q, $injector) {
   return {
   'responseError': function (rejection) {
   var authService = $injector.get('authService');
   if (rejection.status === 401) {
   authService.goToLogin();
   }
   if (rejection.status === 403) {
   authService.goToForbidden();
   }
   return $q.reject(rejection);
   },
   request: function (config) {
   config.withCredentials = true;
   return config;
   },
   response: function (res) {
   return res;
   }
   };
   }]);
   
   mod.run(['authService', '$rootScope', function (auth, $rootScope) {
   auth.userAuthenticated().then(function (response) {
   if (response.status === 200 && response.data) {
   $rootScope.$broadcast('logged-in', response.data);
   }
   });
   }]);
   }]);*/
})(window.angular);

