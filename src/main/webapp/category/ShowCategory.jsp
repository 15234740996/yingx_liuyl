<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<script>
    <%--$("#cateTable").click(function () {--%>
    <%--    var isOk = $("#cateTable").valid();--%>
    <%--    if (isOk) {--%>
    <%--        $.ajax({--%>
    <%--            url: "${path}/category/edit",--%>
    <%--            type: "post",--%>
    <%--            data: $("#cateTable").serialize(),--%>
    <%--            dataType: "JSON",--%>
    <%--            success: function (data) {--%>

    <%--                //登陆失败提示信息--%>
    <%--                $("#msgDiv").html("<span style='color: red'>" + data.message + "</span>");--%>
    <%--            }--%>

    <%--        });--%>
    <%--    }--%>
    <%--}--%>


    /*----------------------------------------------------*/
    $(function () {
        pageInit();

    });




    function pageInit() {

        $("#cateTable").jqGrid({
            url: "${path}/category/findAll",
            datatype: "json",
            rowNum: 2,
            rowList: [8, 10, 20, 30],
            pager: "#catePage",
            sortname: 'id',
            viewrecords: true,
            styleUI: "Bootstrap",
            autowidth: true,
            height: "auto",
            editurl: "${pageContext.request.contextPath}/category/edit",//用来处理修改时url路径
            colNames: ['编号', '类别名', '级别', '父类别'],
            colModel: [
                {name: 'id', index: 'id', width: 55, editable: false},
                {name: 'cate_name', index: 'invdate', width: 90, editable: true},
                {
                    name: 'levels',
                    index: 'name',
                    width: 100,
                    editable: false,
                    edittype: 'select',
                    editoptions: {value: "一级类别:一级类别;二级类别:二级类别"}
                },
                {name: 'parent_id', index: 'amount', width: 80, align: "right", editable: false},
            ],
            subGrid: true,  //开启子表格
            // subgrid_id:是在创建表数据时创建的div标签的ID
            //row_id是该行的ID
            subGridRowExpanded: function (subgrid_id, row_id) {
                addSubGrid(subgrid_id, row_id);
            }
        });
        $("#cateTable").jqGrid('navGrid', '#catePage', {add: true, edit: true, del: true, refresh: true},
            {closeAfterEdit: true, reloadAfterSubmit: true,},  //修改
            {closeAfterAdd: true, reloadAfterSubmit: true,}, //添加
            {closeAfterDelete: true,
                reloadAfterSubmit: true,
                afterSubmit:function (response) {
                if (response.responseJSON.status==200){
                    alert(response.responseJSON.message);
                }
                return "true";
            }
            }, //删除
            //删除成功之后触发的function, 接收删除返回的提示信息, 在页面做展示


        );
    }


    //开启子表格的样式
    function addSubGrid(subgridId, rowId) {

        var subgridTableTd = subgridId + "Table";
        var pagerId = subgridId + "Page";


        $("#" + subgridId).html("" +
            "<table id='" + subgridTableTd + "' />" +
            "<div id='" + pagerId + "' />"
        );


        $("#" + subgridTableTd).jqGrid({
            url: "${path}/category/findAlltwo?id=" + rowId,
            datatype: "json",
            rowNum: 2,
            pager: "#" + pagerId,
            sortname: 'num',
            sortorder: "asc",
            viewrecords: true,
            styleUI: "Bootstrap",
            autowidth: true,
            height: "auto",
            editurl: "${pageContext.request.contextPath}/category/edit",//用来处理修改时url路径
            colNames: ['编号', '类别名', '级别', '父类别'],
            colModel: [
                {name: 'id', index: 'id', width: 55},
                {name: 'cate_name', index: 'name', width: 90, editable: true},
                {name: 'levels', index: 'amount', width: 100, align: "left"},
                {
                    name: 'parent_id',
                    index: 'tax',
                    width: 80,
                    align: "left",
                    editable: true,
                    edittype: 'select',
                    editoptions: {dataUrl: "${path}/category/show"},
                }

            ],
        });

        $("#" + subgridTableTd).jqGrid('navGrid', "#" + pagerId, {add: true, edit: true, del: true},
            {
                closeAfterEdit: true,//关闭面板
                reloadAfterSubmit: true,
            },  //修改
            {
                closeAfterAdd: true,
                reloadAfterSubmit: true,
            }, //添加
            {
                //删除成功之后触发的function,接收删除返回的提示信息,在页面做展示
                closeAfterDelete: true,
                reloadAfterSubmit: true
            }//删除

        );


    }


</script>


<%--设置面板--%>
<div class="panel panel-success">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>类别信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">类别管理</a></li>
    </ul>

    <%--表单--%>
    <table id="cateTable"/>

    <%--分页工具栏--%>
    <div id="catePage"/>

    <%--
删除要有提示信息
--%>
    <span id="msgDiv"></span>

</div>



