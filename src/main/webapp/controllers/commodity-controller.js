rootApp.controller('CommodityListController', function ($scope, CommodityService) {
    CommodityService.query().success(function (data) {
        $scope.commodityList = data.queryresult.content;
        var currentPage = data.queryresult.currentpage;
        var totalPages = data.queryresult.totalpages;
        $scope.$emit('event:showCommodityPaginationRequest', currentPage, totalPages);
    });
    $scope.$on('event:flushCommodityList', function (event, config) {
        CommodityService.query(config).success(function (data) {
            $scope.commodityList = data.queryresult.content;
            var currentPage = data.queryresult.currentpage;
            var totalPages = data.queryresult.totalpages;
            $scope.$emit('event:showCommodityPaginationRequest', currentPage, totalPages);
        });
    });
    $scope.doViewDetail = function (commodityId) {
        CommodityService.getDetail(commodityId).success(function (data) {
            $('#viewCommodityModal').modal('show');
        });
    }
    $scope.doUnderCarriate = function (commodityId) {
        CommodityService.updateActiveState('NEGATIVE', commodityId).success(function () {
            var config = {
                search_term: commoditySearchTerm,
                page: currentCommodityPageGlobal
            }
            $scope.$emit('event:flushCommodityList', config);
        });
    }
    $scope.doGrounding = function (commodityId) {
        CommodityService.updateActiveState('ACTIVE', commodityId).success(function () {
            var config = {
                search_term: commoditySearchTerm,
                page: currentCommodityPageGlobal
            }
            $scope.$emit('event:flushCommodityList', config);
        });
    }
});

rootApp.controller('CommodityPaginationController', function ($scope) {
    var totalPageNumber = 0;
    $scope.$on('event:showCommodityPagination', function (event, currentPage, totalPages) {
        $scope.isFirstPage = false;
        $scope.isLastPage = false;
        var pageArray = [];
        totalPageNumber = totalPages;
        if (totalPages > 10) {
            for (i = 1; i <= 10; i++) {
                var currentPageFlag = false;
                if (i - 1 == currentPage) {
                    currentPageFlag = true;
                }
                pageArray[i - 1] = {
                    pageNumber: i,
                    isCurrentPage: currentPageFlag
                }
            }
        } else {
            for (i = 1; i <= totalPages; i++) {
                var currentPageFlag = false;
                if (i - 1 == currentPage) {
                    currentPageFlag = true;
                }
                pageArray[i - 1] = {
                    pageNumber: i,
                    isCurrentPage: currentPageFlag
                }
            }
        }
        $scope.previousPageNumber = currentPage;
        $scope.NextPageNumber = currentPage + 2;
        $scope.pages = pageArray;
        if (currentPage == 0) {
            $scope.isFirstPage = true;
        }
        if ((currentPage == totalPages - 1) || totalPages == 0) {
            $scope.isLastPage = true;
        }
        currentCommodityPageGlobal = currentPage;
    });
    $scope.doJumpPage = function (pageNumber) {
        pageNumber -= 1;
        if (pageNumber >= 0 && pageNumber < totalPageNumber) {
            var config = {
                search_term: commoditySearchTerm,
                page: pageNumber
            }
            $scope.$emit('event:flushCommodityListRequest', config);
        }
        return;
    }
});

rootApp.controller('CommodityManagementController', function ($scope) {
    $scope.$on('event:showCommodityPaginationRequest', function (event, currentPage, totalPages) {
        $scope.$broadcast('event:showCommodityPagination', currentPage, totalPages);
    });
    $scope.$on('event:flushCommodityListRequest', function (event, config) {
        $scope.$broadcast('event:flushCommodityList', config);
    });
});

rootApp.controller('CommoditySearchController', function ($scope) {
    $scope.doSearch = function () {
        commoditySearchTerm = $scope.searchContent;
        var config = {
            search_term: $scope.searchContent
        }
        $scope.$emit('event:flushCommodityListRequest', config);
    }
});