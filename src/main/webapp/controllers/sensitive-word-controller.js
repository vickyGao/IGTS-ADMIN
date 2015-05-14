rootApp.controller('SensitiveWordManagementController', function ($scope) {
    $scope.$on('event:showSensitiveWordPaginationRequest', function (event, currentPage, totalPages) {
        $scope.$broadcast('event:showSensitiveWordPagination', currentPage, totalPages);
    });
    $scope.$on('event:flushSensitiveWordListRequest', function (event, config) {
        $scope.$broadcast('event:flushSensitiveWordList', config);
    });
    $scope.$on('event:showCreateSensitiveWordModalRequest', function () {
        $scope.$broadcast('event:showCreateSensitiveWordModal');
    });
});

rootApp.controller('SensitiveWordSearchController', function ($scope) {
    $scope.doSearch = function () {
        sensitiveWordSearchTerm = $scope.searchContent;
        var config = {
            search_term: $scope.searchContent
        }
        $scope.$emit('event:flushSensitiveWordListRequest', config);
    }
});

rootApp.controller('CreateSensitiveWordController', function ($scope) {
    $scope.doCreate = function () {
        $scope.$emit('event:showCreateSensitiveWordModalRequest');
    }
});

rootApp.controller('SensitiveWordListController', function ($scope, SensitiveWordService) {
    SensitiveWordService.query().success(function (data) {
        $scope.SensitiveWordList = data.pagination.content;
        var currentPage = data.pagination.currentpage;
        var totalPages = data.pagination.pagecount;
        $scope.$emit('event:showSensitiveWordPaginationRequest', currentPage, totalPages);
    });
    $scope.$on('event:flushSensitiveWordList', function (event, config) {
        SensitiveWordService.query(config).success(function (data) {
            $scope.SensitiveWordList = data.pagination.content;
            var currentPage = data.pagination.currentpage;
            var totalPages = data.pagination.pagecount;
            $scope.$emit('event:showSensitiveWordPaginationRequest', currentPage, totalPages);
        });
    });
    $scope.doDelete = function (sensitiveWordId) {
        showConfirmDialog("确认删除？", {
            ok: function (dialog) {
                dialog.title("提交中...");
                SensitiveWordService.delete(sensitiveWordId).success(function () {
                    var config = {
                        search_term: sensitiveWordSearchTerm,
                        page: currentSensitiveWordPageGlobal
                    }
                    $scope.$emit('event:flushSensitiveWordListRequest', config);
                });
                return true;
            },
            cancel: function () {
                return false;
            }
        });
    }
});

rootApp.controller('CreateSensitiveWordModalController', function ($scope, SensitiveWordService) {
    $scope.$on('event:showCreateSensitiveWordModal', function () {
        $scope.sensitiveword = null;
        $('#createSensitiveWordModel').modal('show');
    });
    $scope.doSubmit = function () {
        var request = {
            "sensitiveword": $scope.sensitiveword
        };
        SensitiveWordService.create(request).success(function () {
            $('#createSensitiveWordModel').modal('hide');
            var config = {
                search_term: sensitiveWordSearchTerm,
                page: currentSensitiveWordPageGlobal
            }
            $scope.$emit('event:flushSensitiveWordListRequest', config);
        });
    }
});

rootApp.controller('SensitiveWordPaginationController', function ($scope) {
    var totalPageNumber = 0;
    $scope.$on('event:showSensitiveWordPagination', function (event, currentPage, totalPages) {
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
        currentSensitiveWordPageGlobal = currentPage;
    });
    $scope.doJumpPage = function (pageNumber) {
        pageNumber -= 1;
        if (pageNumber >=0 && pageNumber < totalPageNumber) {
            var config = {
                search_term: sensitiveWordSearchTerm,
                page: pageNumber
            }
            $scope.$emit('event:flushSensitiveWordListRequest', config);
        }
        return;
    }
});


