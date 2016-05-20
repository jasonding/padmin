<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Client Side Pagination in DataGrid - jQuery EasyUI Demo</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/css/easyui/themes/icon.css">
    <script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/datagrid/datagrid-scrollview.js"></script>

    <style type="text/css">
        .dv-table td{
            border:0;
        }
        .dv-label{
            font-weight:bold;
            color:#15428B;
            width:100px;
        }
    </style>

    <script type="text/javascript">
        $(function(){
            $('#dg').datagrid({
                detailFormatter:function(index,row){
                    return "   <table class=\"dv-table\" border=\"0\" style=\"width:auto;\">\n" +
                            "        <tr>\n" +
                            "            <td title='机构logo' rowspan=\"4\" style=\"width:100px;\">\n" +
                            "            <a href=\"${photoHost}"+row.logo+"\" class=\"easyui-linkbutton l-btn l-btn-small\" group=\"\" id=\"\"><span class=\"l-btn-left\"><span class=\"l-btn-text\">查看图片</span></span></a></td>\n" +
                            "            <td class=\"dv-label\" style='width: 120px;'>机构编号: </td>\n" +
                            "            <td style='width: 200px;'>"+row.provider_id+"</td>\n" +
                            "            <td title='营业执照' rowspan=\"4\" style=\"width:100px;\">\n" +
                            "            <a href=\"${photoHost}"+row.license_logo+"\" class=\"easyui-linkbutton l-btn l-btn-small\" group=\"\" id=\"\"><span class=\"l-btn-left\"><span class=\"l-btn-text\">查看图片</span></span></a></td>\n" +
                            "            <td style='width: 120px;' class=\"dv-label\">营业执照:</td>\n" +
                            "            <td style='width: 200px;'>"+row.license+"</td>\n" +
                            "            <td style='width: 100px;' class=\"dv-label\">地址:</td>\n" +
                            "            <td style='width: 200px;'>"+row.address+"</td>\n" +
                            "            <td title='公函' rowspan=\"4\" style=\"width:100px;\">\n" +
                            "            <a href=\"${photoHost}"+row.missive+"\" class=\"easyui-linkbutton l-btn l-btn-small\" group=\"\" id=\"\"><span class=\"l-btn-left\"><span class=\"l-btn-text\">查看图片</span></span></a></td>\n" +
                            "            <td style='width: 150px;' class=\"dv-label\">组织代码证号:</td>\n" +
                            "            <td style='width: 200px;'>"+row.number+"</td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "            <td class=\"dv-label\">机构名称:</td>\n" +
                            "            <td style='width: 200px;'>"+row.full_name+"</td>\n" +
                            "            <td class=\"dv-label\">联系人:</td>\n" +
                            "            <td>"+row.linkman+"</td>\n" +
                            "            <td class=\"dv-label\">邮件:</td>\n" +
                            "            <td>"+row.email+"</td>\n" +
                            "            <td class=\"dv-label\">是否领取认证:</td>\n" +
                            "            <td>"+row.is_owner+"</td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "            <td class=\"dv-label\">机构简称:</td>\n" +
                            "            <td>"+row.short_name+"</td>\n" +
                            "            <td class=\"dv-label\">手机号:</td>\n" +
                            "            <td>"+row.telephone+"</td>\n" +
                            "            <td class=\"dv-label\">所在地省:</td>\n" +
                            "            <td>"+row.province_id+"</td>\n" +
                            "            <td class=\"dv-label\">状态:</td>\n" +
                            "            <td>"+row.status+"</td>\n" +
                            "        </tr>\n" +
                            "        <tr>\n" +
                            "            <td class=\"dv-label\">网址:</td>\n" +
                            "            <td>"+row.domain+"</td>\n" +
                            "            <td class=\"dv-label\">电话:</td>\n" +
                            "            <td>"+row.phone+"</td>\n" +
                            "            <td class=\"dv-label\">所在城市:</td>\n" +
                            "            <td>"+row.city_id+"</td>\n" +
                            "            <td><a href=\"#\" class=\"easyui-linkbutton l-btn l-btn-small\" group=\"\" id=\"\"><span class=\"l-btn-left\"><span class=\"l-btn-text\">审核通过</span></span></a></td>" +
                            "            <td><a href=\"#\" class=\"easyui-linkbutton l-btn l-btn-small\" group=\"\" id=\"\"><span class=\"l-btn-left\"><span class=\"l-btn-text\">审核不通过</span></span></a></td>" +
                            "        </tr>\n" +
                            "    </table>";
                }
            }).datagrid('getPager');
            getPagination("dg");
        });

        function getFormData() {
            var serialize = $("#search_form").serializeJson();
            return  serialize;
        }

        function loadData(url) {
            var queryParams = getFormData();
            $('#dg').datagrid('options').queryParams=queryParams;
            $('#dg').datagrid('options').url=url;
            $("#dg").datagrid('reload');

        }

        function rowformater(value,row,index) {
            var opt_str = "";
            return opt_str;
        }
    </script>
</head>
<body>
<table id="dg" style="width:700px;height:250px" title="您所在的位置：用户管理－>机构列表"
       data-options="
                    fit:true,
                    toolbar:'#tb',
                    fitColumns:true,
                    singleSelect:true,
                    url:'${ctx}/manage/provider/list',
                    pagination:true,
                    pageSize:20,
                    view:scrollview,
                    onRowContextMenu: onRowContextMenu,
                    queryParams:getFormData(),
                    method:'post'"
        >
    <thead>
    <tr>
        <th data-options="field:'provider_id',width:80">机构编号</th>
        <th data-options="field:'full_name',width:100">全称</th>
        <th data-options="field:'short_name',width:100">简称</th>
        <th data-options="field:'opt',width:100,formatter:rowformater">操作</th>
    </tr>
    </thead>
</table>

<div id="tb" style="padding:0px;">
    <hr:permission url="/manage/provider/save" method="GET">
        <div style="padding:3px">
            <a href="${ctx}/manage/provider/save" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加机构</a>
        </div>
    </hr:permission>
</div>
</body>

</html>