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


    <script>

        $(function () {
            $("#userId").jqGrid({

                styleUI: "Bootstrap",
                url: "${path}/yxuser/showAll",//指定 服务端地址
                datatype: "json",

                rowList: [2, 10, 20, 30],

                colNames: ['编号', '昵称', '手机号', '头像', '简介', '学分', '创建时间', '状态'],
                colModel: [
                    {name: 'id'},
                    {name: 'nick_name', editable: true},
                    {name: 'phone', editable: true},
                    {
                        name: 'pic_img',
                        formatter: function (value, row) {
                            return "<img src='${pageContext.request.contextPath}/bootstrap/img/" + value + "' style='height: 80px'/>"

                        }
                    },
                    {name: 'brief', editable: true},
                    {name: 'score', editable: true},
                    {name: 'create_date', editable: true},
                    {
                        name: 'start', editable: true, formatter: function (value, option, row) {
                            if (value == 1) {
                                return "<button type=\"button\" class=\"btn btn-danger\"  onclick='updateStatus(\"" + value + "\",\"" + row.id + "\")'>冻结</button>";
                            }
                            return "<button type=\"button\" class=\"btn btn-warning\"  onclick='updateStatus(\"" + value + "\",\"" + row.id + "\")'>正常</button>";
                        }
                    },


                ],
                pager: "#userPage",
                rowNum: 2,
                viewrecords: true,
                autowidth: true,
            });


        })
        ;

        //修改用户状态
        function updateStatus(start, id) {
            $.post("${pageContext.request.contextPath}/yxuser/updateStatus", {
                "start": start = start == 1 ? 2 : 1,
                "id": id
            });
            $("#userId").trigger("reloadGrid");
        }

    </script>
    <script>
        $(function () {
            //给按钮加点击事件
            $("#aliyun").click(function () {
                //先获取输入框输入的手机号
                var phone = $("#phone").val();

                //发送ajax请求
                $.post("${pageContext.request.contextPath}/yxuser/code", {phone: phone}, function (res) {
                    if (res.status == "200") {
                        alert(res.message);
                    } else {
                        alert(res.message);
                    }
                }, "JSON")
            });
        });


    </script>


    <script>
        $(function () {
            $("#poi").click(function () {
                $.get("${pageContext.request.contextPath}/yxuser/poi", {}, function (res) {
                    if (res.message == "success") {
                        alert("导出成功");
                    } else {
                        alert("导出失败");
                    }
                })

            })
        })

        $(function () {
            $("#doi").click(function () {
                $.get("${pageContext.request.contextPath}/yxuser/doi", {}, function (res) {
                    if (res.message == "success") {
                        alert("导入成功");
                    } else {
                        alert("导入失败");
                    }
                })

            })
        })
    </script>


<body>

<%--设置面板--%>
<div class="panel panel-info">

    <%--面板头--%>
    <div class="panel panel-heading">
        <a href="javascript:$('#main').load('${pageContext.request.contextPath}/main/loginshow.jsp')">用户信息</a>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">用户管理</a></li>
    </ul>


    <div>
        <div class="pull-left">
            <button class="btn btn-info" id="poi">导出用户信息</button>
            <button class="btn btn-success" id="doi">导入用户</button>
            <button class="btn btn-danger">测试按钮</button>
        </div>

        <div class="pull-right col-sm-5">
            <form>
                <div class="col-md-4 col-md-offset-6" style="padding: 0px;">
                    <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入手机号..." required
                           minlength="11">
                </div>
                <div class="col-md-2 pull-right" style="padding: 0px;">
                    <button type="button" id="aliyun" class="btn btn-info btn-block">发送验证码</button>
                </div>
            </form>
        </div>
    </div>
    <br>
    <div style="margin-top: 20px;">
        <%--表单--%>
        <table id="userId"/>

        <%--分页工具栏--%>
        <div id="userPage"/>
    </div>
</div>
</body>
</html>