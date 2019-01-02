<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="${request.contextPath }/css/bootstrap-theme.css">
    <link type="text/css" rel="stylesheet" href="${request.contextPath }/css/signUp.css">
    <link type="text/css" rel="stylesheet" href="${request.contextPath }/css/bootstrapValidator.css">
    <script type="text/javascript" src="${request.contextPath }/js/jquery-3.2.1.js"></script>
    <script type="text/javascript " src="${request.contextPath }/js/bootstrap.js"></script>
    <script type="text/javascript" src="${request.contextPath }/js/bootstrapValidator.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#registForm").bootstrapValidator({
                message: 'This value is not valid',
                verbose:'false',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    username: {
                        message: '用户名验证失败',

                        trigger:'blur',
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            },
                            stringLength: {
                                min: 6,
                                max: 18,
                                message: '用户名长度必须在6到18位之间'
                            },

                            remote: {
                                url: '${request.contextPath }/user/isUsernameExists',
                                message: '用户已存在',
                                type: 'POST'

                            },
                            regexp: {
                                regexp: /^[a-zA-Z0-9_]+$/,
                                message: '用户名只能包含大写、小写、数字和下划线'
                            }
                        }
                    },
                    password:{
                        trigger:'blur',
                        validators:{
                            notEmpty:{
                                message:'密码不能为空'
                            },

                            different:{
                                field:'username',
                                message:'密码不能与用户名相同'
                            }
                        }
                    },
                    confirmpw:{
                        trigger:'blur',
                        validators:{
                            notEmpty:{
                                message:'请再次输入密码'
                            },
                            identical:{
                                field:'password',
                                message:'两次密码输入不一致'

                            }
                        }
                    },
                    email: {
                        trigger:'blur',
                        validators: {
                            notEmpty: {
                                message: '邮箱不能为空'
                            },
                            emailAddress: {
                                message: '邮箱地址格式有误'
                            }
                        }
                    },

                    authCode:{
                        trigger:'blur',
                        validators:{
                            notEmpty:{
                                message:'请输入验证码'
                            },
                            remote: {
                                url: '${request.contextPath }/user/validCode',
                                message: '验证码错误',
                                type: 'POST'

                            },
                        }
                    }
                }
            });
            $("#registButton").click(function(){
                $.ajax({
                    type: "POST",
                    dataType:"json",
                    url:"${request.contextPath }/user/signUp",
                    data: $('#registForm').serialize(),
                    async: false,
                    error: function(request) {
                        alert("Connection error");
                    },
                    success: function(data) {
                        var msg = data.msg;
                        alert(msg);
                        var url = data.url;
                        window.location.href = "${request.contextPath }/user/showSignIn";

                    }
                });
            });

        });
    </script>
</head>
<body class="container">
<div class="header">
    <div class="welcome">欢迎注册</div>
    <div class="login">
        已有账号?
        <a href="#">请登录</a>
    </div>
</div>
<div class="main">
    <div class="form-div" >
        <form class="form-horizontal" id="registForm" action="${request.contextPath }/user/signUp" method="post">
            <div class="form-group">
                <label for="username" class="col-sm-offset-2 col-sm-2 control-label">用户名</label>
                <div class="col-sm-3">
                    <input type="username" class="form-control" id="username" placeholder="" name="username">
                </div>
                <p class="validMsg"></p>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-offset-2 col-sm-2 control-label">设置密码</label>
                <div class="col-sm-3">
                    <input type="password" class="form-control" id="password" placeholder="" name="password">
                </div>
            </div>
            <div class="form-group">
                <label for="comfirmpw" class="col-sm-offset-2 col-sm-2 control-label">确认密码</label>
                <div class="col-sm-3">
                    <input type="password" class="form-control" id="comfirmpw" placeholder="" name="confirmpw">
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-offset-2 col-sm-2 control-label">邮箱</label>
                <div class="col-sm-3">
                    <input type="email" class="form-control" id="email" placeholder="" name="email">
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-offset-2 col-sm-2 control-label">手机号</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="telephone" placeholder="" name="telephone">
                </div>
            </div>
            <div class="form-group">
                <label for="authCode" class="col-sm-offset-2 col-sm-2 control-label">验证码</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" id="authCode" placeholder="" name="authCode">
                </div>
                <img alt="验证码" src="${request.contextPath }/user/authCode" onclick="changeCode(this)">

            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-3">
                    <button type="button" class="btn btn-default btn-block" id="registButton">立即注册</button>
                </div>
            </div>

        </form>


    </div>
</div>
<div class="footer">
    <div class="link">
        <ul class="list-inline link-ul">
            <li><a href="#">关于我们</a></li>
            <li><a href="#">联系我们</a></li>
            <li><a href="#">人才招聘</a></li>
            <li><a href="#">商家入驻</a></li>
            <li><a href="#">广告服务</a></li>
            <li><a href="#">友情链接</a></li>
            <li><a href="#">销售联盟</a></li>
        </ul>
    </div>
    <div class="copyright">
        Copyright<span class="glyphicon glyphicon-copyright-mark"></span>xcx
    </div>
</div>
</body>
<script>
    function changeCode(obj){
        obj.src="${request.contextPath}/user/authCode?date=" + new Date();


    };
    function valid(){


        alert("${Session["authCode"]}");
    };

    function validUsername(){
        var username = $("#username").val();
        $.ajax({
            type: "POST",
            dataType:"json",
            url:"${request.contextPath }/user/isUsernameExists",
            data: {
                username:username
            },
            async: false,
            error: function(request) {
                alert("Connection error");
            },
            success: function(data) {
                $(".validMsg").text(data.msg);
            }

        });

    };

</script>
</html>