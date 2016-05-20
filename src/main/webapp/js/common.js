(function($){
    $.fn.serializeJson=function(){
        var serializeObj={};
        var array=this.serializeArray();
        //var str=this.serialize();
        $(array).each(function(){
            if(serializeObj[this.name]){
                if($.isArray(serializeObj[this.name])){
                    serializeObj[this.name].push(this.value);
                }else{
                    serializeObj[this.name]=[serializeObj[this.name],this.value];
                }
            }else{
                if(this.value != null && $.trim(this.value).length>0) {
                    serializeObj[this.name]=this.value;
                }
            }
        });
        return serializeObj;
    };
})(jQuery);

$.extend($.fn.validatebox.defaults.rules, {
    /*必须和某个字段相等*/
    equalTo: { validator: function (value, param) { return $(param[0]).val() == value; }, message: '字段不匹配' }
});

function edit(preUrl,rowid) {

    var datarow = $('#' + rowid).datagrid('getSelected');
    if (datarow == null || datarow.length <= 0) {
        alert("请选中要修改的行")
        return;
    }
    if (datarow.length > 1) {
        alert("请不要选多行进行修改")
        return;
    }
    window.location.href = preUrl + datarow.id;
}

function getPagination(rowid) {
    var dataRowId = $('#' + rowid);
    var p = dataRowId.datagrid('getPager');
    $(p).pagination({
        pageSize: 20,//每页显示的记录条数，默认为20
        pageList: [20,25,30,50],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
    return dataRowId;
}

function onRowContextMenu(e){
    e.preventDefault()
    //var selected=$("#dg").datagrid('getRows'); //获取所有行集合对象
    //selected[rowIndex].id; //index为当前右键行的索引，指向当前行对象
    //var idValue = selected[rowIndex].id;
    //$(this).datagrid('selectRecord', idValue);  //通过获取到的id的值做参数选中一行
    $('#mm').menu('show', {
        left:e.pageX,
        top:e.pageY
    });
}

function submitForm(formId) {
    $("#" + formId).submit();
}

function back(count) {
    return history.go(count);
}