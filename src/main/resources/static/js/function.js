function login(username, password) {
    $.ajax({
        url: '/oauth/token?username=' + username + '&password=' + password + '&grant_type=password&scope=all',
        type: 'post',
        beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("Authorization", "Basic a2l0czpraXRz");
        },
        // contentType: 'application/json',
        // data: JSON.stringify({
        //   promulgator: $('.promulgator').val(),
        //   publishStartTime: $('.form_datetime_start').val(),
        //   publishEndTime: $('.form_datetime_end').val()
        // }),
        success: function (data) {
            alert('登录成功！')
            $.cookie('access_token', data.access_token, { expires: 7 });
            $(location).attr('href', '/kits/index')
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(textStatus)
        }
    })
}
