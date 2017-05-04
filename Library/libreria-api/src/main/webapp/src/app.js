/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
(function (ng) {
    var mod = ng.module('mainApp', [
        //'ngCrudMock',
        'auth0.auth0', 
        'angular-jwt',
        'auth0.lock',
        'ngCrud',
        'ui.router',
        'ui.grid',
        'authorModule',
        'bookModule',
        'reviewModule',
        'prizeModule',
        'authModule'
    ]);

    mod.constant('baseUrl', 'api');

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);
    
    mod.config(['lockProvider','angularAuth0Provider','jwtOptionsProvider', function (lockProvider, angularAuth0Provider, jwtOptionsProvider) {
            lockProvider.init({
                clientID: AUTH0_CLIENT_ID,
                domain: AUTH0_DOMAIN,
                options: {
                  _idTokenVerification: false
                }
              });
              
            angularAuth0Provider.init({
                clientID: AUTH0_CLIENT_ID,
                domain: AUTH0_DOMAIN,
                responseType: 'token id_token',
                redirectUri: AUTH0_CALLBACK_URL
              });
              
            jwtOptionsProvider.config({
                tokenGetter: ['options', function (options) {
                  if (options && options.url.substr(options.url.length - 5) == '.html') {
                    return null;
                  }
                  return localStorage.getItem('id_token');
                }],
                whiteListedDomains: ['localhost'],
                unauthenticatedRedirectPath: '/login'
              });
        }]);

    mod.config(['RestangularProvider', 'baseUrl', function (rp, baseUrl) {
            rp.setBaseUrl(baseUrl);
            rp.setRequestInterceptor(function (elem, operation) {
                if (operation === "remove") {
                    return null;
                }
                return elem;
            });
            rp.addResponseInterceptor(function (data, operation, what, url, response) {
                if (operation === "getList") {
                    data.totalRecords = parseInt(response.headers("X-Total-Count")) || 1;
                }
                return data;
            });
        }]);

    mod.config(['$urlRouterProvider', function ($urlRouterProvider) {
                $urlRouterProvider.otherwise('/');
        }]);

    /*
     * When there's an error changing state, ui-router doesn't raise an error
     * This configuration allows to print said errors
     */
    mod.run(['$rootScope', '$log', 'authService', 'lock', 'authManager', function ($rootScope, $log, authService, lock, authManager) {
            $rootScope.$on('$stateChangeError', function (event, toState, toParams, fromState, fromParams, error) {
                $log.warn(error);
            });
            $rootScope.authService = authService;
            // Use the authManager from angular-jwt to check for
            // the user's authentication state when the page is
            // refreshed and maintain authentication
            authManager.checkAuthOnRefresh();

            // Register synchronous hash parser
            authService.handleParseHash();
        }]);
})(window.angular);
var AUTH0_CLIENT_ID='OongmQR5vXowQwx3AWGL9apCtLJXgW8c'; 
var AUTH0_DOMAIN='eaperador.auth0.com'; 
var AUTH0_CALLBACK_URL=window.location.href;