(function () {

  'use strict';

  angular
    .module('mainApp')
    .controller('LoginController', loginController);

  loginController.$inject = ['authService'];

  function loginController(authService) {

    var vm = this;
    vm.auth = authService;

  }

})();
