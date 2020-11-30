<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

</head>
<body>
<!--顶部导航-->
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">应学视频App后台管理系统 <small>欢迎</small></a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">


            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎后台管理者:</a></li>
                <li><a href="#">${sessionScope.admin.username}</a></li>
                <li><a href="${pageContext.request.contextPath}/yxuser/exit">退出</a></li>
                <li><a href="#" class="glyphicon glyphicon-log-out"></a></li>
            </ul>
        </div>
    </div>
</nav>
<!--栅格系统-->
<!--左边手风琴部分-->
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">
            <!--菜单-->
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

                <!--面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                               aria-expanded="true" aria-controls="collapseOne">
                                <span class="glyphicon glyphicon-user"></span> 用户管理
                            </a>
                        </h4>
                    </div>
                    <!--面板内容-->
                    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group text-center">
                                <li class="list-group-item">
                                    <button class="btn btn-success">

                                        <a href="javascript:$('#main').load('${path}/User/showUser.jsp')">用户信息</a>

                                    </button>
                                </li>
                                <li class="list-group-item">
                                    <button class="btn btn-success">
                                        <a href="javascript:$('#main').load('${path}/test/testChina.jsp')">

                                            <a href="javascript:$('#main').load('${path}/test/testEchartsJson.jsp')">
                                                用户统计</a>

                                        </a>
                                    </button>
                                </li>
                                <li class="list-group-item">
                                    <button class="btn btn-success">用户分布</button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>


                <!--面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"
                               aria-expanded="true" aria-controls="collapseOne">
                                <span class="glyphicon glyphicon-th-list"></span> 分类管理
                            </a>
                        </h4>
                    </div>
                    <!--面板内容-->
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group text-center">
                                <li class="list-group-item">
                                    <button class="btn btn-warning">
                                        <a href="javascript:$('#main').load('${path}/category/ShowCategory.jsp')">类别信息</a>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTho">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTho"
                               aria-expanded="true" aria-controls="collapseOne">
                                <span class="glyphicon glyphicon-film"></span> 视频管理
                            </a>
                        </h4>
                    </div>
                    <!--面板内容-->
                    <div id="collapseTho" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group text-center">
                                <li class="list-group-item">
                                    <button class="btn btn-info">
                                        <a href="javascript:$('#main').load('${path}/video/showVideo.jsp')">视频信息</a>
                                    </button>
                                </li>
                                <li class="list-group-item">
                                    <button class="btn btn-info">视频展示</button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTh1">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTh1"
                               aria-expanded="true" aria-controls="collapseOne">
                                <span class="glyphicon glyphicon-list-alt"></span> 日志管理
                            </a>
                        </h4>
                    </div>
                    <!--面板内容-->
                    <div id="collapseTh1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group text-center">
                                <li class="list-group-item">
                                    <button class="btn btn-info">
                                        <a href="javascript:$('#main').load('${path}/log/Log.jsp')">日志统计</a>
                                    </button>
                                </li>
                                <li class="list-group-item">
                                    <button class="btn btn-info">日志展示</button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <%--轮播图--%>
        <div class="col-sm-10" id="main">
            <%--            巨幕--%>
            <div class="jumbotron " style="height: 200px;margin-bottom: 0px">
                <h2 align="center">バッ后台ヤード管理システムへようこそ</h2>
                <p>
                    &nbsp;&nbsp;&nbsp;あなたの眼中で、私の欠点が见えない多すぎます、多く,私のこんなにまであなたが好きです,镜の中のあなたの言った人は暖めたい唯一の爱、
                </p>
                <%--                <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>--%>
            </div>
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel"
                 style="height: 350px;margin-top: 0px">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="4"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="../bootstrap/img/1.jpg" style="width:100%;height:350px;" alt="...">
                        <div class="carousel-caption">
                            萝莉1
                        </div>
                    </div>
                    <div class="item">
                        <img src="../bootstrap/img/2.jpg" style="width:100%;height:350px;" alt="...">
                        <div class="carousel-caption">
                            萝莉2
                        </div>
                    </div>
                    <div class="item">
                        <img src="../bootstrap/img/3.jpg" style="width:100%;height:350px;" alt="...">
                        <div class="carousel-caption">
                            萝莉3
                        </div>
                    </div>
                    <div class="item">
                        <img src="../bootstrap/img/4.jpg" style="width:100%;height:350px;" alt="...">
                        <div class="carousel-caption">
                            萝莉4
                        </div>
                    </div>
                    <%--                    <h3 align="center">萝莉合集</h3>--%>
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>


        </div>


        <div class="panel panel-footer">
            <h6 align="right">@白纸教育 liuyl@knife.com</h6>
        </div>

    </div>


</div>


</body>
</html>
