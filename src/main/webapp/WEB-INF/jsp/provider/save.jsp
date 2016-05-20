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

    <script type="text/javascript">
        $(function(){

           $("#province_select_id").change(function(){
               var pid = this.value;
               getCitySelect(pid)
           });

           var pid = $("#province_select_id").val();
           getCitySelect(pid);
        });

        function getCitySelect(pid) {
            $.post(
                    "${ctx}/manage/area/list/city/" + pid,
                    null,
                    function(data){
                        var cityList = data.rows;
                        var options = ""
                        for(var i=0;i<cityList.length;i++) {
                            var city = cityList[i];
                            options += "<option value=\""+city.id+"\" >"+city.name+"</option>"
                        }
                        var html = " 选择城市:<select name=\"cityId\">"+options+"</select>"
                        $("#city_div_id").html(html);
                    },
                    "json"
            );
        }
    </script>
</head>
<body>
<div class="easyui-panel" title="您所在的位置：用户管理－>机构添加页面" style="width:80%" data-options="fit:true">
        <form:form id="ff" action="${ctx}/manage/provider/save" commandName="provider" method="post" enctype="multipart/form-data">
            <table cellpadding="5" align="center">
                <tr>
                    <td width="80px;">平台logo:</td>
                    <td>
                        <input name="logoFile" class="easyui-filebox" style="width: 200px;">
                    </td>
                </tr>
                <tr>
                    <td width="80px;">机构简称:</td>
                    <td>
                        <form:input path="simpleName" class="easyui-textbox" required="required" ></form:input>
                        <span style="color:#FF3333"><form:errors path="simpleName"></form:errors></span>
                    </td>
                </tr>
                <tr>
                    <td width="80px;">机构全称:</td>
                    <td>
                        <form:input path="fullName" class="easyui-textbox"  required="required"  ></form:input>
                        <span style="color: #FF3333"><form:errors path="fullName"></form:errors></span>
                    </td>
                </tr>
                <tr>
                    <td width="80px;">官网:</td>
                    <td>
                        <form:input path="domain" class="easyui-textbox"  validType="url" ></form:input>
                        <span style="color: #FF3333">请以http://开头 <form:errors path="domain"></form:errors></span>
                    </td>
                </tr>
                <tr>
                    <td width="80px;">所在地:</td>
                    <td>
                        <form:select id="province_select_id" path="provinceId" style="float: left">
                            <c:forEach items="${provinceList}" var="province">
                                <form:option value="${province.id}">${province.name}</form:option>
                            </c:forEach>
                        </form:select>
                        <div id="city_div_id" style="float: left">

                        </div>
                    </td>
                </tr>
                <tr>
                    <td width="80px;">业务类型:</td>
                    <td>
                        <table>
                            <tr>
                                <c:forEach varStatus="st" items="${providerTypeList}" var="typeWapper">
                                    <td>
                                        <form:checkbox path="providerTypeIdSet"  value="${typeWapper.parentProviderType.id}" />${typeWapper.parentProviderType.name}<br/>
                                        <c:forEach items="${typeWapper.childProviderTypeList}" var="childtype">
                                            --<form:checkbox path="providerTypeIdSet" value="${childtype.id}" />${childtype.name}<br/>
                                        </c:forEach>
                                    </td>
                                    <c:if test="${st.count % 4 eq 0}"></tr><tr></c:if>
                                </c:forEach>
                            </tr>
                        </table>

                    </td>
                </tr>
            </table>
        </form:form>
        <div style="text-align:center;padding:5px">
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm('ff')">提交</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="back(-1)">返回</a>
        </div>
    </div>

</body>

</html>