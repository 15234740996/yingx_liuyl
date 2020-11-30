<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>
    $(function () {
        //初始化表单属性
        $("#LTable").jqGrid({
            url: "${path}/log/queryAllPage",  //分页查询   page  rows  total recoreds  page  rows
            <%--editurl: "${path}/video/edit",--%>
            datatype: "json",
            rowNum: 4,  //每页展示是条数
            rowList: [2, 4, 10, 20, 30],
            pager: '#LPager',
            styleUI: "Bootstrap",
            height: "auto",
            autowidth: true,
            viewrecords: true,  //是否展示数据总条数
            colNames: ['ID', '名字', '时间', '操作', '状态'],
            colModel: [
                {name: 'id', width: 55},
                {name: 'name', editable: true, width: 90},
                {name: 'times', editable: true, width: 100},
                {name: 'optioncz', width: 80, align: "center"},
                {name: 'status', width: 150, sortable: false}
            ]
        });

        //处理曾删改查操作   工具栏
        $("#LTable").jqGrid('navGrid', '#LPager',
            {edit: true, add: true, del: true, edittext: "修改", addtext: "添加", deltext: "删除"},
            {
                closeAfterEdit: true,  //关闭对话框
                beforeShowForm: function (obj) {
                    obj.find("#videoPath").attr("disabled", true);//禁用input
                }
            }, //执行修改之后的额外操作
            {
                closeAfterAdd: true, //关闭添加的对话框
                afterSubmit: function (data) {
                    $.ajaxFileUpload({
                        fileElementId: "videoPath",    //需要上传的文件域的ID，即<input type="file">的ID。
                        url: "${path}/video/uploadVdieo", //后台方法的路径
                        type: 'post',   //当要提交自定义参数时，这个参数要设置成post
                        data: {id: data.responseText},  //responseText: "74141b4c-f337-4ab2-ada2-c1b07364adee"
                        success: function () {   //提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
                            //刷新页面
                            $("#vTable").trigger("reloadGrid");
                        }
                    });
                    //必须要有返回值
                    return "hello";
                }
            }, //执行添加之后的额外操作
            {
                closeAfterDel: true, //关闭添加的对话框
            } //执行删除之后的额外操作
        );
    });


</script>

<%--初始化一个面板--%>
<div class="panel panel-success">

    <%--面板头--%>
    <div class="panel panel-heading" align="center">
        <h2>日志管理</h2>
    </div>

    <%--选项卡--%>
    <div class="nav nav-tabs">
        <li class="active"><a href="">日志管理</a></li>
    </div>

    <%--警告提示框--%>
    <div id="deleteMsg" class="alert alert-danger" style="height: 50px;width: 250px;display: none" align="center">
        <span id="showMsg"/>
    </div>

    <%--初始化表单--%>
    <table id="LTable"/>

    <%--工具栏--%>
    <div id="LPager"/>

</div>