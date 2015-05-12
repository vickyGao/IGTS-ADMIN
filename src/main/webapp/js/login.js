var loginApp = angular.module('LoginApp', ['ngCookies']);

loginApp.controller('LoginController', function ($scope, $http, $cookieStore) {
    $scope.doLogin = function () {
        var postData = $scope.user;
        $http.post('api/authorization/login', postData)
            .success(function (data) {
                $cookieStore.put('x-auth-token', data.sessioncontext.token);
                window.location.href = 'index.html';
            });
    }
});

/* Register the interceptor */
loginApp.config(function ($httpProvider) {
    $httpProvider.interceptors.push('errorHttpInterceptor');
});

/* Deal with exceptions in login.html */
loginApp.factory('errorHttpInterceptor', function ($q) {
    return {
        'response': function (response) {
            return response;
        },
        'responseError': function (rejection) {
            if (rejection.status === 412) {
                showDialog('Warning', rejection.data);
            } else if (rejection.status >= 400 && rejection.status <= 500) {
                showDialog('Error', rejection.data);
            }
            return $q.reject(rejection);
        }
    }
});

/* Display a dialog, success: green, info: blue, warning: yellow, error: red */
function showDialog(type, content) {
    var className = 'dialog-default';
    if ('Success' == type) {
        className = 'dialog-success';
    } else if ('Warning' == type) {
        className = 'dialog-warning';
    } else if ('Error' == type) {
        className = 'dialog-error';
    }
    var d = dialog({
        fixed: true,
        align: 'right top',
        title: type,
        content: content,
        skin: className,
        quickClose: true,
        zIndex: 9999
    });
    d.show();
}
