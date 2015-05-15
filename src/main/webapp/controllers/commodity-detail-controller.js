rootApp.controller('CommodityDetailController', function ($scope, $location, $routeParams) {
    $scope.commodityDetailUrl = '#' + $location.url();
});

rootApp.controller('ViewCommodityDetailController', function ($scope, $location, $routeParams, $sce, CommodityService, MessageService) {
    var commodityId = $routeParams.commodityId;

    CommodityService.getDetail(commodityId).success(function (data) {
        var commodityDescriptionMarked = '';
        var commodity = '';
        if (data != null) {
            if (data.commodity != null) {
                coverList = data.commodity.covers;
                commodityDescriptionMarked = $sce.trustAsHtml(marked(data.commodity.description));
                commodity = data.commodity;
            }
        }
        if (data == null || data.commodity == null) {

        }


        $scope.myInterval = 5000;
        $scope.covers = coverList;
        $scope.commodityDescriptionMarked = commodityDescriptionMarked;
        $scope.commodity = commodity;
    });
    var config = {
        page: 0,
        size: 10,
        commodityid: $routeParams.commodityId
    }
    MessageService.getByCommodityId(config).success(function (data) {
    });


});

rootApp.controller('CommodityTabController', function ($scope) {
    $scope.isDescriptionTab = isDescriptionTab;
    $scope.isMessageTab = isMessageTab;
    $scope.doDisplayDescription = function () {
        updateCommodityTabSelection(true, false);
        $scope.isDescriptionTab = isDescriptionTab;
        $scope.isMessageTab = isMessageTab;
    }
    $scope.doDisplayMessage = function () {
        updateCommodityTabSelection(false, true);
        $scope.isDescriptionTab = isDescriptionTab;
        $scope.isMessageTab = isMessageTab;
    }

});

function updateCommodityTabSelection(isDescriptionFlag, isMessageFlag) {
    isDescriptionTab = isDescriptionFlag;
    isMessageTab = isMessageFlag;
}

rootApp.controller('CommodityDesAndMessageTabController', function ($scope) {

});

rootApp.controller('CommodityDescriptionController', function ($scope) {
});

rootApp.controller('CommodityMessageController', function ($scope, MessageService, $routeParams) {
    var conditions = {
        page: 0,
        size: 20,
        commodityid: $routeParams.commodityId
    };
    MessageService.getByCommodityId(conditions).success(function (data) {
        $scope.messageList = data.pagination.content;
    });

    $scope.textPercent = '0/' + maxMessageLength;
    $scope.messageLength = 0;
    $scope.checkText = function () {
        var textLength = $scope.messageText.length;
        if (textLength > maxMessageLength) {
            $scope.messageLength = maxMessageLength;
            $scope.messageText = $scope.messageText.substr(0, maxMessageLength);
        } else {
            $scope.messageLength = textLength;
            $scope.textPercent = textLength + '/' + maxMessageLength;
        }
    }
});

var coverList = {};
var isDescriptionTab = true;
var isMessageTab = false;
var maxMessageLength = 150;
