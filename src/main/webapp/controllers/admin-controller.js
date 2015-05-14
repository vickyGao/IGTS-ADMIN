rootApp.controller('AdminManagementController', function ($scope) {
    $scope.$on('event:showAdminPaginationRequest', function (event, currentPage, totalPages) {
        $scope.$broadcast('event:showAdminPagination', currentPage, totalPages);
    });
    $scope.$on('event:flushAdminListRequest', function (event, config) {
        $scope.$broadcast('event:flushAdminList', config);
    });
    $scope.$on('event:showCreateAdminModalRequest', function () {
        $scope.$broadcast('event:showCreateAdminModal');
    });
});

rootApp.controller('AdminSearchController', function ($scope) {
    $scope.doSearch = function () {
        adminSearchTerm = $scope.searchContent;
        var config = {
            search_term: $scope.searchContent
        }
        $scope.$emit('event:flushAdminListRequest', config);
    }
});

rootApp.controller('CreateAdminController', function ($scope) {
    $scope.doCreate = function () {
        $scope.$emit('event:showCreateAdminModalRequest');
    }
});

rootApp.controller('CreateAdminModalController', function ($scope, SystemService, AdminService) {
    $scope.$on('event:showCreateAdminModal', function () {
        SystemService.get().success(function (data) {
            $scope.defaultPassword = data;
            $scope.admin = null;
            $('#createAdminModel').modal('show');
        });
    });
    $scope.doSubmit = function () {
        var request = {
            "admin": $scope.admin
        };
        AdminService.create(request).success(function () {
            $('#createAdminModel').modal('hide');
            var config = {
                search_term: adminSearchTerm,
                page: currentAdminPageGlobal
            }
            $scope.$emit('event:flushAdminListRequest', config);
        });
    }
});

rootApp.controller('AdminListController', function ($scope, AdminService) {
    AdminService.query().success(function (data) {
        $scope.AdminList = data.pagination.content;
        var currentPage = data.pagination.currentpage;
        var totalPages = data.pagination.pagecount;
        $scope.$emit('event:showAdminPaginationRequest', currentPage, totalPages);
    });
    $scope.$on('event:flushAdminList', function (event, config) {
        AdminService.query(config).success(function (data) {
            $scope.AdminList = data.pagination.content;
            var currentPage = data.pagination.currentpage;
            var totalPages = data.pagination.pagecount;
            $scope.$emit('event:showAdminPaginationRequest', currentPage, totalPages);
        });
    });
    $scope.doViewDetail = function (adminId) {
        $scope.$emit('event:showViewAdminDetailModalRequest', adminId);
    }
    $scope.doUpdate = function (adminId) {
        $scope.$emit('event:showUpdatePasswordModalRequest', adminId);
    }
    $scope.doDelete = function (adminId) {
        showConfirmDialog("确认删除？", {
            ok: function (dialog) {
                dialog.title("提交中...");
                AdminService.delete(adminId).success(function () {
                    var config = {
                        search_term: adminSearchTerm,
                        page: currentAdminPageGlobal
                    }
                    $scope.$emit('event:flushAdminListRequest', config);
                });
                return true;
            },
            cancel: function () {
                return false;
            }
        });
    }
});

rootApp.controller('AdminPaginationController', function ($scope) {
    var totalPageNumber = 0;
    $scope.$on('event:showAdminPagination', function (event, currentPage, totalPages) {
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
        currentAdminPageGlobal = currentPage;
    });
    $scope.doJumpPage = function (pageNumber) {
        pageNumber -= 1;
        if (pageNumber >= 0 && pageNumber < totalPageNumber) {
            var config = {
                search_term: adminSearchTerm,
                page: pageNumber
            }
            $scope.$emit('event:flushAdminListRequest', config);
        }
        return;
    }
});