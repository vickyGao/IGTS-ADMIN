rootApp.controller('RootController', function ($scope) {
    $scope.$on('event:loginRequired', function () {
        window.location.href = 'login.html';
    });
});

rootApp.controller('headerController', function ($scope, authHttp) {
    authHttp.get('api/admin/detail/token').success(function (data) {
        $scope.admin = data.admin;
    });
    $scope.doLogout = function() {
        authHttp.delete('api/authorization/logout');
    }
});

rootApp.controller('TagListController', function($scope, authHttp) {
    authHttp.get('api/tag/detail').success(function (data) {
        $scope.tags = data.tags;
        console.log(data.tags);
    });
});
