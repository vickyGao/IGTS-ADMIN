var rootApp = angular.module('RootApp', ['ngCookies', 'ngRoute']);

function indexRouteConfig($routeProvider) {
    $routeProvider.
        when('/index', {
            templateUrl: 'template/dashboard.html'
        }).
        when('/adminManagement', {
            templateUrl: 'template/admin-management.html'
        }).
        when('/userManagement', {
            templateUrl: 'template/user-management.html'
        }).
        when('/commodityManagement', {
            templateUrl: 'template/commodity-management.html'
        }).
        when('/homePageManagement', {
            templateUrl: 'template/home-page-management.html'
        }).
        when('/tagManagement', {
            templateUrl: 'template/tag-management.html'
        }).
        when('/sensitiveWordManagement', {
            templateUrl: 'template/sensitive-word-management.html'
        }).
        when('/adminDetail', {
            templateUrl: 'template/admin-detail.html'
        }).
        when('/editPassword', {
            templateUrl: 'template/edit-password.html'
        }).
        when('/upload', {
            templateUrl: 'template/upload.html'
        }).
        when('/backup', {
            templateUrl: 'template/backup.html'
        }).
        otherwise({
            redirectTo: '/index'
        });
}

rootApp.config(indexRouteConfig);

rootApp.controller('RootController', function ($scope, $location) {
    $scope.$on('event:loginRequired', function () {
        window.location.href = 'login.html';
    });
});

rootApp.controller('headerController', function ($scope, $http, $cookieStore) {
    var token = $cookieStore.get('x-auth-token');
    console.log(token);
    $http.get('api/admin/detail/token', {
        headers: {'x-auth-token': token}
    }).success(function (data) {
        $scope.admin = data.admin;
    });
});

/* Register the interceptor */
rootApp.config(function ($httpProvider) {
    $httpProvider.interceptors.push('errorHttpInterceptor');
});

/* Deal with exceptions in login.html */
rootApp.factory('errorHttpInterceptor', function ($q, $rootScope) {
    return {
        'response': function (response) {
            return response;
        },
        'responseError': function (rejection) {
            if (rejection.status === 401) {
                $rootScope.$broadcast('event:loginRequired');
            } else if (rejection.status === 412) {
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
        quickClose: true
    });
    d.show();
}
