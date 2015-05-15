rootApp.controller('HomeManagementController', function ($scope) {
    $scope.$on('event:DisplaySliceCustomizeRequest', function () {
        $scope.$broadcast('event:DisplaySliceCustomize');
    });
    $scope.$on('event:DisplayHotCustomizeRequest', function () {
        $scope.$broadcast('event:DisplayHotCustomize');
    });
    $scope.$on('event:DisplayCustomModelCustomizeRequest', function () {
        $scope.$broadcast('event:DisplayCustomModelCustomize');
    });
    $scope.$on('event:FlushHotCommoditiesRequest', function () {
        console.log('get flush hot request');
        $scope.$broadcast('event:FlushHotCommodities');
    });
    $scope.$on('event:showCreateHotCommodityModalRequest', function () {
        $scope.$broadcast('event:showCreateHotCommodityModal');
    });
    $scope.$on('event:ShowCreateSliceModalRequest', function () {
        $scope.$broadcast('event:ShowCreateSliceModal');
    });
    $scope.$on('event:FlushSlicesRequest', function () {
        $scope.$broadcast('event:FlushSlices');
    });
    $scope.$on('event:ShowDeleteSliceModalRequest', function () {
        $scope.$broadcast('event:ShowDeleteSliceModal');
    });
});

rootApp.controller('TabController', function ($scope) {
    $scope.isSliceCustomizeTab = isSliceCustomizeTab;
    $scope.isHotCustomizeTab = isHotCustomizeTab;
    $scope.isCustomModelCustomizeTab = isCustomModelCustomizeTab;
    $scope.doDisplaySliceCustomize = function () {
        updateTabSelection(true, false, false);
        $scope.isSliceCustomizeTab = isSliceCustomizeTab;
        $scope.isHotCustomizeTab = isHotCustomizeTab;
        $scope.isCustomModelCustomizeTab = isCustomModelCustomizeTab;
        $scope.$emit('event:DisplaySliceCustomizeRequest');
    }
    $scope.doDisplayHotCustomize = function () {
        updateTabSelection(false, true, false);
        $scope.isSliceCustomizeTab = isSliceCustomizeTab;
        $scope.isHotCustomizeTab = isHotCustomizeTab;
        $scope.isCustomModelCustomizeTab = isCustomModelCustomizeTab;
        $scope.$emit('event:DisplayHotCustomizeRequest');
    }
    $scope.doDisplayCustomModelCustomize = function () {
        updateTabSelection(false, false, true);
        $scope.isSliceCustomizeTab = isSliceCustomizeTab;
        $scope.isHotCustomizeTab = isHotCustomizeTab;
        $scope.isCustomModelCustomizeTab = isCustomModelCustomizeTab;
        $scope.$emit('event:DisplayCustomModelCustomizeRequest');
    }
});

rootApp.controller('SliceCustomizeController', function ($scope, $location, SliceService) {
    SliceService.getAll().success(function (data) {
        $scope.myInterval = 5000;
        $scope.slices = data.slices;
    });
    $scope.doViewCommodityDetail = function (commodityId) {
        $location.path('/commodityDetail/' + commodityId).replace();
    }
    $scope.doCreateSlice = function () {
        $scope.$emit('event:ShowCreateSliceModalRequest');
    }
    $scope.$on('event:FlushSlices', function () {
        SliceService.getAll().success(function (data) {
            $scope.myInterval = 5000;
            $scope.slices = data.slices;
        });
    });
    $scope.doDeleteSlice = function () {
        $scope.$emit('event:ShowDeleteSliceModalRequest');
    }
});

rootApp.controller('HotCustomizeController', function ($scope, $location, HotService) {
    HotService.getAll().success(function (data) {
        $scope.hotList = data.hotcommodities;
    });
    $scope.doViewCommodityDetail = function (commodityId) {
        $location.path('/commodityDetail/' + commodityId).replace();
    }
    $scope.doCreateHot = function () {
        $scope.$emit('event:showCreateHotCommodityModalRequest');
    }
    $scope.doDelete = function (hotId) {
        showConfirmDialog("确认删除？", {
            ok: function (dialog) {
                dialog.title("提交中...");
                HotService.delete(hotId).success(function () {
                    $scope.$emit('event:FlushHotCommoditiesRequest');
                });
                return true;
            },
            cancel: function () {
                return false;
            }
        });
    }
    $scope.$on('event:FlushHotCommodities', function () {
        HotService.getAll().success(function (data) {
            $scope.hotList = data.hotcommodities;
        });
    });
});

rootApp.controller('CustomModelCustomizeController', function ($scope) {

});

rootApp.controller('CustomizeController', function ($scope) {
    $scope.$on('event:DisplaySliceCustomize', function () {
        $scope.isSliceCustomizeTab = isSliceCustomizeTab;
        $scope.isHotCustomizeTab = isHotCustomizeTab;
        $scope.isCustomModelCustomizeTab = isCustomModelCustomizeTab;
    });
    $scope.$on('event:DisplayHotCustomize', function () {
        $scope.isSliceCustomizeTab = isSliceCustomizeTab;
        $scope.isHotCustomizeTab = isHotCustomizeTab;
        $scope.isCustomModelCustomizeTab = isCustomModelCustomizeTab;
    });
    $scope.$on('event:DisplayCustomModelCustomize', function () {
        $scope.isSliceCustomizeTab = isSliceCustomizeTab;
        $scope.isHotCustomizeTab = isHotCustomizeTab;
        $scope.isCustomModelCustomizeTab = isCustomModelCustomizeTab;
    });
});

rootApp.controller('CreateHotCommodityModalController', function ($scope, ImageService, CommodityService, HotService) {
    $scope.$on('event:showCreateHotCommodityModal', function () {
        ImageService.getAll().success(function (data) {
            $scope.images = data.images;
            CommodityService.getAll().success(function (data) {
                $scope.commodityList = data.commodities;
                $scope.selectedImage = null;
                $scope.selectedCommodity = null;
                $scope.sliceDesc = null;
                $('#createHotCommodityModal').modal("show");
            });
        });
    });
    $scope.doSelect = function (image) {
        $scope.selectedImage = image;
    }
    $scope.doSelectCommodity = function (commodity) {
        $scope.selectedCommodity = commodity;
    }
    $scope.doSubmit = function () {
        var imageId = $scope.selectedImage.id;
        var commodityId = $scope.selectedCommodity.id;
        var desc = $scope.sliceDesc;
        var hotBody = {
            imageid: imageId,
            commodityid: commodityId,
            description: desc
        }
        var hot = {
            hot: hotBody
        }
        HotService.create(hot).success(function () {
            $('#createHotCommodityModal').modal("hide");
            $scope.$emit('event:FlushHotCommoditiesRequest');
        });
    }
});

rootApp.controller('CreateSliceModalController', function ($scope, ImageService, CommodityService, SliceService) {
    $scope.$on('event:ShowCreateSliceModal', function () {
        ImageService.getAll().success(function (data) {
            $scope.images = data.images;
            CommodityService.getAll().success(function (data) {
                $scope.commodityList = data.commodities;
                $scope.selectedImage = null;
                $scope.selectedCommodity = null;
                $scope.sliceDesc = null;
                $('#createSliceModal').modal("show");
            });
        });
    });
    $scope.doSelect = function (image) {
        $scope.selectedImage = image;
    }
    $scope.doSelectCommodity = function (commodity) {
        $scope.selectedCommodity = commodity;
    }
    $scope.doSubmit = function () {
        var imageId = $scope.selectedImage.id;
        var commodityId = $scope.selectedCommodity.id;
        var desc = $scope.sliceDesc;
        var sliceBody = {
            imageid: imageId,
            commodityid: commodityId,
            description: desc
        }
        var slice = {
            slice: sliceBody
        }
        SliceService.create(slice).success(function () {
            $('#createSliceModal').modal("hide");
            $scope.$emit('event:FlushSlicesRequest');
        });
    }
});

rootApp.controller('DeleteSliceModalController', function ($scope, SliceService) {
    $scope.$on('event:ShowDeleteSliceModal', function () {
        SliceService.getAll().success(function (data) {
            $scope.sliceList = data.slices;
            $('#deleteSliceModal').modal("show");
        });
    });
    $scope.doDeleteSlice = function (sliceId) {
        showConfirmDialog("确认删除？", {
            ok: function (dialog) {
                dialog.title("提交中...");
                SliceService.delete(sliceId).success(function () {
                    $('#deleteSliceModal').modal("hide");
                    $scope.$emit('event:FlushSlicesRequest');
                });
                return true;
            },
            cancel: function () {
                return false;
            }
        });
    }
});

function updateTabSelection(isSliceCustomizeTabFlag, isHotCustomizeTabFlag, isCustomModelCustomizeTabFlag) {
    isSliceCustomizeTab = isSliceCustomizeTabFlag;
    isHotCustomizeTab = isHotCustomizeTabFlag;
    isCustomModelCustomizeTab = isCustomModelCustomizeTabFlag;
}

var isSliceCustomizeTab = true;
var isHotCustomizeTab = false;
var isCustomModelCustomizeTab = false;
