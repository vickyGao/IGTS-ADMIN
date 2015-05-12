var rootApp = angular.module('RootApp', ['ngCookies', 'ngRoute']);

/* Add authHttp to send request, will add token into header automatically */
rootApp.factory('authHttp', function ($http, $cookieStore) {
    var authHttp = {};

    var extendHeaders = function (config) {
        config.headers = config.headers || {};
        config.headers['x-auth-token'] = $cookieStore.get('x-auth-token');
    };

    angular.forEach(['get', 'delete', 'head'], function (name) {
        authHttp[name] = function (url, config) {
            config = config || {};
            extendHeaders(config);
            return $http[name](url, config);
        };
    });
    angular.forEach(['post', 'put'], function (name) {
        authHttp[name] = function (url, data, config) {
            config = config || {};
            extendHeaders(config);
            return $http[name](url, data, config);
        };
    });
    return authHttp;
});

function indexRouteConfig($routeProvider) {
    $routeProvider.
        when('/', {
            redirectTo: '/index'
        }).
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
            controller: 'CommodityListController',
            templateUrl: 'template/commodity-management.html'
        }).
        when('/homePageManagement', {
            templateUrl: 'template/home-page-management.html'
        }).
        when('/tagManagement', {
            controller: 'TagListController',
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
        quickClose: true,
        zIndex: 9999
    });
    d.show();
}

/* Display a dialog with ok/cancel */
function showConfirmDialog(content, callback) {
    var d = dialog({
        title: '提示',
        content: content,
        okValue: '确定',
        ok: function () {
            callback.ok(this);
        },
        cancelValue: '取消',
        quickClose: true,
        zIndex: 9999,
        cancel: function () {
            callback.cancel();
        }
    });
    d.show();
}

/* Services about CRUD of Tag */
rootApp.factory('TagService', function (authHttp) {
    return {
        getDetail: function (tagId) {
            var path = 'api/tag/entity/' + tagId;
            return authHttp.get(path);
        },
        listDetail: function () {
            return authHttp.get('api/tag/detail');
        },
        list: function () {
            return authHttp.get('api/tag/entity');
        },
        create: function (tag) {
            return authHttp.post('api/tag/entity', tag);
        },
        update: function (tag) {
            return authHttp.put('api/tag/entity', tag);
        },
        delete: function (tagId) {
            var path = 'api/tag/entity/' + tagId;
            return authHttp.delete(path);
        }
    };
});

rootApp.factory('CommodityService', function (authHttp) {
    return{
        getDetail: function (commodityId) {
            var path = 'api/commodity/detail/' + commodityId;
            return authHttp.get(path);
        },
        query: function (conditions) {
            var config = {params: conditions};
            return authHttp.get('api/commodity/search_term', config);
        },
        updateActiveState: function (requestState, commodityId) {
            var path = 'api/commodity/activestate/' + requestState + '/' + commodityId;
            return authHttp.put(path);
        }
    }
});

rootApp.factory('AdminService', function (authHttp) {
    return {
        getDetail: function (adminId) {
            var path = 'api/admin/detail/' + adminId;
            return authHttp.get(path);
        },
        update: function (admin) {
            return authHttp.put('api/admin/entity', admin);
        },
        getByToken: function () {
            return authHttp.get('api/admin/detail/token');
        },
        create: function (admin) {
            return authHttp.post('api/admin/entity', admin);
        },
        delete: function (adminId) {
            var path = 'api/admin/entity/' + adminId;
            return authHttp.delete(path);
        },
        query: function (conditions) {
            var config = {params: conditions};
            return authHttp.get('api/admin/search_term', config);
        }
    }
});

rootApp.factory('AuthorizationService', function (authHttp) {
    return {
        logout: function () {
            return authHttp.delete('api/authorization/logout');
        }
    }
});

rootApp.factory('SystemService', function (authHttp) {
    return {
        get: function () {
            return authHttp.get('api/system/defaultpassword');
        }
    }
});

rootApp.factory('UserService', function (authHttp) {
    return {
        update: function (activeYn, userid) {
            var path = 'api/user/entity/' + activeYn + '/' + userid;
            return authHttp.put(path);
        },
        delete: function (userid) {
            var path = 'api/user/entity/' + userid;
            return authHttp.delete(path);
        },
        getDetail: function (userid) {
            var path = 'api/user/detail/' + userid;
            return authHttp.get(path);
        },
        query: function (conditions) {
            var config = {params: conditions};
            return authHttp.get('api/user/search_term', config);
        }
    }
});
