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
                    $scope.$emit('event:flushTagListRequest');
                });
                return true;
            },
            cancel: function () {
                return false;
            }
        });
    }
    $scope.$on('event:flushTagList', function () {
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
                $scope.$emit('event:flushTagListRequest');
            });
        } else if ($scope.saveTagDialogName == '创建标签') {
            TagService.create(request).success(function () {
                $('#saveTagModal').modal('hide');
                $scope.$emit('event:flushTagListRequest');
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
    $scope.$on('event:flushTagListRequest', function () {
        $scope.$broadcast('event:flushTagList');
    });
});

rootApp.controller('CreateTagController', function ($scope) {
    $scope.doCreate = function () {
        $scope.$emit('event:createTagRequest');
    }
});
