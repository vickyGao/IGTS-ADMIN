rootApp.controller('DashBoardManagementController', function ($scope, ImageService, TagService, CommodityService, AdminService, UserService) {
    $scope.totalCommodityNumber = 0;
    $scope.totalUserNumber = 0;
    $scope.totalAdminNumber = 0;
    $scope.totalTagNumber = 0;
    $scope.totalPictureNumber = 0;
    $scope.totalManagedPictureNumber = 0;
    $scope.totalPictureSize = 0;
    CommodityService.getTotalCount().success(function (data) {
        $scope.totalCommodityNumber = data;
    });
    UserService.getTotalCount().success(function (data) {
        $scope.totalUserNumber = data;
    });
    AdminService.getTotalCount().success(function (data) {
        $scope.totalAdminNumber = data;
    });
    TagService.getTotalCount().success(function (data) {
        $scope.totalTagNumber = data;
    });
    ImageService.getTotalAmount().success(function (data) {
        $scope.totalPictureNumber = data;
    });
    ImageService.getTotalManagedAmount().success(function (data) {
        $scope.totalManagedPictureNumber = data;
    });
    ImageService.getTotalPictureSize().success(function (data) {
        $scope.totalPictureSize = data;
    });
});