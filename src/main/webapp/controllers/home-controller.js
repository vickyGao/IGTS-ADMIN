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
        $scope.$broadcast('event:FlushHotCommoditiesRequest');
    });
    $scope.$on('event:showCreateHotCommodityModalRequest', function () {
        $scope.$broadcast('event:showCreateHotCommodityModal');
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

rootApp.controller('CreateHotCommodityModalController', function ($scope) {
    $scope.$on('event:showCreateHotCommodityModal', function (event, hotId) {
        $('#createHotCommodityModal').modal("show");
    })
});

function updateTabSelection(isSliceCustomizeTabFlag, isHotCustomizeTabFlag, isCustomModelCustomizeTabFlag) {
    isSliceCustomizeTab = isSliceCustomizeTabFlag;
    isHotCustomizeTab = isHotCustomizeTabFlag;
    isCustomModelCustomizeTab = isCustomModelCustomizeTabFlag;
}

var isSliceCustomizeTab = true;
var isHotCustomizeTab = false;
var isCustomModelCustomizeTab = false;
