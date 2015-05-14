rootApp.controller('ImageManagementController', function ($scope) {
    $scope.$on('event:DisplayMyImageListRequest', function () {
        $scope.$broadcast('event:DisplayMyImageList');
    });
    $scope.$on('event:ViewImageRequest', function (event, imageId) {
        $scope.$broadcast('event:ViewImage', imageId);
    });
    $scope.$on('event:UpdateImageRequest', function (event, imageId) {
        $scope.$broadcast('event:UpdateImage', imageId);
    });
});

rootApp.controller('ImageListController', function ($scope, ImageService) {
    ImageService.getByToken().success(function (data) {
        $scope.imageList = data.images;
    });
    $scope.$on('event:DisplayMyImageList', function () {
        ImageService.getByToken().success(function (data) {
            $scope.imageList = data.images;
        });
    });
    $scope.doView = function (imageId) {
        $scope.$emit('event:ViewImageRequest', imageId);
    }
    $scope.doUpdate = function (imageId) {
        $scope.$emit('event:UpdateImageRequest', imageId);
    }
    $scope.doDelete = function (imageId) {
        showConfirmDialog("确认删除？", {
            ok: function (dialog) {
                dialog.title("提交中...");
                ImageService.delete(imageId).success(function () {
                    $scope.$emit('event:DisplayMyImageListRequest');
                });
                return true;
            },
            cancel: function () {
                return false;
            }
        });
    }
});

// The button to flush the Image list
rootApp.controller('ImageDisplayListController', function ($scope) {
    $scope.doDisplay = function () {
        $scope.$emit('event:DisplayMyImageListRequest');
    }
});

rootApp.controller('ViewImageController', function ($scope, ImageService) {
    $scope.$on('event:ViewImage', function (event, imageId) {
        ImageService.getById(imageId).success(function (data) {
            $scope.image = data.image;
            $('#viewImageModal').modal('show');
        });
    });
});

rootApp.controller('UpdateImageController', function ($scope, ImageService) {
    $scope.$on('event:UpdateImage', function (event, imageId) {
        ImageService.getById(imageId).success(function (data) {
            $scope.image = data.image;
            $('#updateImageModal').modal('show');
        });
    });
    $scope.doSubmit = function () {
        var request = {
            "image": $scope.image
        };
        ImageService.update(request).success(function () {
            $('#updateImageModal').modal('hide');
            $scope.$emit('event:DisplayMyImageListRequest');
        });
    }
});
