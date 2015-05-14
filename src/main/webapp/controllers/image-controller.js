rootApp.controller('ImageManagementController', function ($scope) {
    $scope.$on('DisplayMyImageListRequest', function () {
        $scope.$broadcast('DisplayMyImageList');
    });
});

rootApp.controller('ImageListController', function ($scope, ImageService) {
    ImageService.getByToken().success(function (data) {
        $scope.imageList = data.images;
    });
    $scope.$on('DisplayMyImageList', function () {
        ImageService.getByToken().success(function (data) {
            $scope.imageList = data.images;
        });
    });
});

rootApp.controller('ImageDisplayListController', function ($scope) {
    $scope.doDisplay = function () {
        $scope.$emit('DisplayMyImageListRequest');
    }
});
