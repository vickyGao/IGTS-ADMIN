rootApp.controller('UserManagementController', function ($scope) {
    $scope.$on('event:showUserPaginationRequest', function (event, currentPage, totalPages) {
        $scope.$broadcast('event:showUserPagination', currentPage, totalPages);
    });
    $scope.$on('event:flushUserListRequest', function (event, config) {
        $scope.$broadcast('event:flushUserList', config);
    });
    $scope.$on('event:showViewUserModalRequest', function (event, userId) {
        $scope.$broadcast('event:showViewUserModal', userId);
    });
});

rootApp.controller('UserSearchController', function ($scope) {
    $scope.doSearch = function () {
        userSearchTerm = $scope.searchContent;
        var config = {
            search_term: $scope.searchContent
        }
        $scope.$emit('event:flushUserListRequest', config);
    }
});

rootApp.controller('UserListController', function ($scope, UserService) {
    UserService.query().success(function (data) {
        $scope.UserList = data.pagination.content;
        var currentPage = data.pagination.currentpage;
        var totalPages = data.pagination.pagecount;
        $scope.$emit('event:showUserPaginationRequest', currentPage, totalPages);
    });
    $scope.$on('event:flushUserList', function (event, config) {
        UserService.query(config).success(function (data) {
            $scope.UserList = data.pagination.content;
            var currentPage = data.pagination.currentpage;
            var totalPages = data.pagination.pagecount;
            $scope.$emit('event:showUserPaginationRequest', currentPage, totalPages);
        });
    });
    $scope.doViewDetail = function (userId) {
        $scope.$emit('event:showViewUserModalRequest', userId);
    }
    $scope.doActive = function (userId) {
        UserService.update('ACTIVE', userId).success(function () {
            var config = {
                search_term: userSearchTerm,
                page: currentUserPageGlobal
            }
            $scope.$emit('event:flushUserListRequest', config);
        });
    }
    $scope.doForbid = function (userId) {
        UserService.update('NEGATIVE', userId).success(function () {
            var config = {
                search_term: userSearchTerm,
                page: currentUserPageGlobal
            }
            $scope.$emit('event:flushUserListRequest', config);
        });
    }
    $scope.doDelete = function (userId) {
        showConfirmDialog("确认删除？", {
            ok: function (dialog) {
                dialog.title("提交中...");
                UserService.delete(userId).success(function () {
                    var config = {
                        search_term: userSearchTerm,
                        page: currentUserPageGlobal
                    }
                    $scope.$emit('event:flushUserListRequest', config);
                });
                return true;
            },
            cancel: function () {
                return false;
            }
        });
    }
});

rootApp.controller('UserPaginationController', function ($scope) {
    var totalPageNumber = 0;
    $scope.$on('event:showUserPagination', function (event, currentPage, totalPages) {
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
        currentUserPageGlobal = currentPage;
    });
    $scope.doJumpPage = function (pageNumber) {
        pageNumber -= 1;
        if (pageNumber >= 0 && pageNumber < totalPageNumber) {
            var config = {
                search_term: userSearchTerm,
                page: pageNumber
            }
            $scope.$emit('event:flushUserListRequest', config);
        }
        return;
    }
});

rootApp.controller('ViewUserDetailController', function ($scope, UserService) {
    $scope.$on('event:showViewUserModal', function (event, userId) {
        UserService.getDetail(userId).success(function (data) {
            $scope.user = data.user;
            $('#viewUserDetailModal').modal('show');
        });
    });
});
