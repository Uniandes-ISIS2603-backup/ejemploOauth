(function (ng) {
  var appModule = angular.module('mainApp');

  appModule.factory('httpInterceptor', function ($q, authService) {
    var interceptor = {
      request: function (config) {
        if (authService.isAuthenticated()) {
          var usuario = authService.returnLogInfo().user;
          var token = authService.returnLogInfo().token;
          if (usuario && token) {
            config.headers['usuario'] = usuario;
            config.headers['jwt'] = token;
          }
          return config;
        }else{
          return config;
        }
      },
      response: function (response) {
        return response;
      },
      responseError: function (rejection) {
        console.log(rejection);
        //showError(rejection.data.mensaje);
        return $q.reject(rejection);
      }
    };
    return interceptor;
  });

  appModule.config(['$httpProvider', function ($httpProvider) {
      $httpProvider.interceptors.push('httpInterceptor');
    }]);

  function showError(message) {
    var template = `<div class="alert alert-danger">
                                        <strong>Error!</strong> ${message} </div>`;
    var alertPanel = document.getElementById('alertPanel');
    alertPanel.innerHTML = template;
    alertPanel.style.opacity = 1;

    setTimeout(function () {
      alertPanel.style.opacity = 0;
    }, 5000);
  }
})(window.angular);