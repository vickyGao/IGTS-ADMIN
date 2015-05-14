rootApp.controller('RootController', function ($scope) {
    $scope.$on('event:loginRequired', function () {
        window.location.href = 'login.html';
    });
    $scope.$on('event:showViewAdminDetailModalRequest', function (event, adminId) {
        $scope.$broadcast('event:showViewAdminDetailModal', adminId);
    });
    $scope.$on('event:showUpdatePasswordModalRequest', function (event, adminId) {
        $scope.$broadcast('event:showUpdatePasswordModal', adminId);
    });
});

rootApp.controller('headerController', function ($scope, AdminService, AuthorizationService) {
    AdminService.getByToken().success(function (data) {
        $scope.admin = data.admin;
    });
    $scope.doLogout = function () {
        AuthorizationService.logout();
    }
    $scope.doViewDetail = function (adminId) {
        $scope.$emit('event:showViewAdminDetailModalRequest', adminId);
    }
    $scope.doUpdatePassword = function (adminId) {
        $scope.$emit('event:showUpdatePasswordModalRequest', adminId);
    }
});

rootApp.controller('ViewAdminDetailController', function ($scope, AdminService) {
    $scope.$on('event:showViewAdminDetailModal', function (event, adminId) {
        AdminService.getDetail(adminId).success(function (data) {
            $scope.admin = data.admin;
        });
        $('#viewAdminDetailModal').modal('show');
    });
});

rootApp.controller('UpdatePasswordController', function ($scope, AdminService) {
    $scope.$on('event:showUpdatePasswordModal', function (event, adminId) {
        AdminService.getDetail(adminId).success(function (data) {
            $scope.admin = data.admin;
            $scope.admin.adminpassword = '';
        });
        $('#updatePasswordModal').modal('show');
    });
    $scope.doSubmit = function () {
        if ($scope.admin.newPwd1 == $scope.admin.newPwd2) {
            var request = {
                "admin": $scope.admin
            };
            AdminService.update(request).success(function () {
                $('#updatePasswordModal').modal('hide');
                var config = {
                    search_term: commoditySearchTerm,
                    page: pageNumber
                }
                $scope.$emit('event:flushAdminListRequest', config);
            });
        } else {
            showDialog('Warning', '新密码不一致');
        }
    }
});

var commoditySearchTerm = '';
var currentCommodityPageGlobal = 0;
var adminSearchTerm = '';
var currentAdminPageGlobal = 0;
var userSearchTerm = '';
var currentUserPageGlobal = 0;
var sensitiveWordSearchTerm = '';
var currentSensitiveWordPageGlobal = 0;
