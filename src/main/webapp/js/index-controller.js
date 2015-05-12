rootApp.controller('RootController', function ($scope) {
    $scope.$on('event:loginRequired', function () {
        window.location.href = 'login.html';
    });
    $scope.$on('event:showViewAdminDetailModalRequest', function (event, adminId) {
        $scope.$broadcast('event:showViewAdminDetailModal', adminId);
    });
    $scope.$on('event:showUpdatePasswordModalRequest', function (event, adminId) {
        $scope.$broadcast('event:showUpdatePasswordModal', adminId);
    });
});

rootApp.controller('headerController', function ($scope, AdminService, AuthorizationService) {
    AdminService.getByToken().success(function (data) {
        $scope.admin = data.admin;
    });
    $scope.doLogout = function () {
        AuthorizationService.logout();
    }
    $scope.doViewDetail = function (adminId) {
        $scope.$emit('event:showViewAdminDetailModalRequest', adminId);
    }
    $scope.doUpdatePassword = function (adminId) {
        $scope.$emit('event:showUpdatePasswordModalRequest', adminId);
    }
});

rootApp.controller('TagListController', function ($scope, TagService) {
    TagService.listDetail().success(function (data) {
        $scope.tags = data.tags;
    });
    $scope.doEditTag = function (tagId) {
        $scope.$emit('event:editTagRequest', tagId);
    }
    $scope.doDeleteTag = function (tagId) {
        showConfirmDialog("确认删除？", {
            ok: function (dialog) {
                dialog.title("提交中...");
                TagService.delete(tagId).success(function () {
                    $scope.$emit('event:flushListRequest');
                });
                return true;
            },
            cancel: function () {
                return false;
            }
        });
    }
    $scope.$on('event:flushList', function () {
        TagService.listDetail().success(function (data) {
            $scope.tags = data.tags;
        });
    });
});

/* Deal with save Tag, update Tag and show dialog */
rootApp.controller('SaveTagController', function ($scope, TagService) {
    $scope.$on('event:editTag', function (event, tagId) {
        $('#saveTagModal').modal('show');
        TagService.getDetail(tagId).success(function (data) {
            $scope.tag = data.tag;
            TagService.list().success(function (data) {
                $scope.topLevelTags = data.tags;
                angular.forEach($scope.topLevelTags, function (data, index, array) {
                    if (data.id == $scope.tag.parentid) {
                        $scope.firstTag = data;
                    }
                });
            });
        });
        $scope.saveTagDialogName = '编辑标签';
        $scope.isDisabled = true;
    });
    $scope.$on('event:createTag', function () {
        $scope.tag = null;
        $('#saveTagModal').modal('show');
        $scope.saveTagDialogName = '创建标签';
        $scope.isDisabled = false;
        TagService.list().success(function (data) {
            $scope.firstTag = null;
            $scope.topLevelTags = data.tags;
        });
    });
    $scope.doSubmit = function () {
        if ($scope.tag != null && $scope.firstTag != null) {
            $scope.tag.parentid = $scope.firstTag.id;
        }
        var request = {
            "tag": $scope.tag
        };
        if ($scope.saveTagDialogName == '编辑标签') {
            TagService.update(request).success(function () {
                $('#saveTagModal').modal('hide');
                $scope.$emit('event:flushListRequest');
            });
        } else if ($scope.saveTagDialogName == '创建标签') {
            TagService.create(request).success(function () {
                $('#saveTagModal').modal('hide');
                $scope.$emit('event:flushListRequest');
            });
        }
    }
    $scope.doSelect = function (tagParam) {
        $scope.firstTag = tagParam;
    }
});

/* Base controller of Tag management */
rootApp.controller('TagManagementController', function ($scope) {
    $scope.$on('event:editTagRequest', function (event, tagId) {
        $scope.$broadcast('event:editTag', tagId);
    });
    $scope.$on('event:createTagRequest', function () {
        $scope.$broadcast('event:createTag');
    });
    $scope.$on('event:flushListRequest', function () {
        $scope.$broadcast('event:flushList');
    });
});

rootApp.controller('CreateTagController', function ($scope) {
    $scope.doCreate = function () {
        $scope.$emit('event:createTagRequest');
    }
});

rootApp.controller('CommodityListController', function ($scope, CommodityService) {
    CommodityService.query().success(function (data) {
        $scope.commodityList = data.queryresult.content;
        var currentPage = data.queryresult.currentpage;
        var totalPages = data.queryresult.totalpages;
        $scope.$emit('event:showPaginationRequest', currentPage, totalPages);
    });
    $scope.$on('event:flushCommodityList', function (event, config) {
        CommodityService.query(config).success(function (data) {
            $scope.commodityList = data.queryresult.content;
            var currentPage = data.queryresult.currentpage;
            var totalPages = data.queryresult.totalpages;
            $scope.$emit('event:showPaginationRequest', currentPage, totalPages);
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

rootApp.controller('PaginationController', function ($scope) {
    var totalPageNumber = 0;
    $scope.$on('event:showPagination', function (event, currentPage, totalPages) {
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
        if (currentPage == totalPages - 1) {
            $scope.isLastPage = true;
        }
        currentCommodityPageGlobal = currentPage;
    });
    $scope.doJumpPage = function (pageNumber) {
        pageNumber -= 1;
        if (pageNumber < 0) {
            showDialog('Warning', '当前为首页');
        } else if (pageNumber >= totalPageNumber) {
            showDialog('Warning', '当前为尾页');
        } else {
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
    $scope.$on('event:showPaginationRequest', function (event, currentPage, totalPages) {
        $scope.$broadcast('event:showPagination', currentPage, totalPages);
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

rootApp.controller('ViewAdminDetailController', function ($scope, AdminService) {
    $scope.$on('event:showViewAdminDetailModal', function (event, adminId) {
        AdminService.getDetail(adminId).success(function (data) {
            $scope.admin = data.admin;
        });
        $('#viewAdminDetailModal').modal('show');
    });
});

rootApp.controller('UpdatePasswordController', function ($scope, AdminService) {
    $scope.$on('event:showUpdatePasswordModal', function (event, adminId) {
        AdminService.getDetail(adminId).success(function (data) {
            $scope.admin = data.admin;
            $scope.admin.adminpassword = '';
        });
        $('#updatePasswordModal').modal('show');
    });
    $scope.doSubmit = function () {
        if ($scope.admin.newPwd1 == $scope.admin.newPwd2) {
            var request = {
                "admin": $scope.admin
            };
            AdminService.update(request).success(function () {
                $('#updatePasswordModal').modal('hide');
            });
        } else {
            showDialog('Warning', '新密码不一致');
        }
    }
});

var commoditySearchTerm = '';
var currentCommodityPageGlobal = 0;
