$(function() {
    function rest(restUrl, httpMethod, param, contenttype, datatype) {
        var request = jQuery.ajax({
                type : httpMethod,
                url : restUrl,
                data : param,
                contentType : contenttype,
                dataType : datatype
        });
        request.success(function(data) {
            try {
                if (data === null || data === undefined) {
                    console.log('no result');
                } else {
                    console.log(data);
                }
            } catch (e) {
                console.log('exception');
            }
        });
        request.fail(function(exception, errorThrown) {
            var message = exception.responseText;
            console.log(message);
            var div = $('.login-warn-message');
            div.text(message);
            div.css("display", "block");
        });
    }

    function displayException(exception) {
        var message = exception.responseText;
        var originalDiv = $('body .alert');
        if (originalDiv != null) {
            originalDiv.remove();
        }
        $('body')
                .append(
                        "<div class='alert alert-success' role='alert'><button class='close'  data-dismiss='alert' type='button' >&times;</button><p>"
                                + message + "</p></div>");
        var div = $('body .alert');
        div.fadeOut(3000, function() {
            div.remove();
        });
    }

    /* POST */
    function doPost() {
        var path = contextURL + '/login';
        var contentType = "application/json";
        var postData;
        var usernamevalue = $("#userName").val();
        var passwordvalue = $("#password").val();
        postData = JSON.stringify({
                username : usernamevalue,
                password : passwordvalue
        });
        rest(path, 'POST', postData, contentType, 'json');
    }
    ;

    /* PUT */
    function doPut() {
        var path = '';
        var contentType = "application/json";
        var putData;
        rest(path, 'PUT', putData, contentType, 'json');
    }

    /* DELETE */
    function doDelete() {
        var path = '';
        rest(path, 'DELETE', null, null, 'json');
    }

    /* GET */
    function doGet() {
        var path = '';
        rest(path, 'GET', null, null, 'json');
    }

    $(function() {
        $('.btn').click(function() {
            doPost();
        });
    });

    $('#userName').tooltip();
    $('#password').tooltip();
});
