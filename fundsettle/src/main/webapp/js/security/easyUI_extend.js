//============================================全局参数 Start============================================
var sy = $.extend({}, sy);
/*定义一个全局变量*/
var grid;
var tab;
//提示框变量
var title;
var message;
//获取当前网址，http://localhost:8083/optt/security/meun.jsp
var curWepappPath = window.document.location.href;
//获取主机地址之后的目录，optt/security/share/meun.jsp
var pathName = window.document.location.pathname;
var position = curWepappPath.indexOf(pathName);
//获取主机地址，http://localhost:8083
var localhostPath = curWepappPath.substring(0, position);

//分页的常量
var firstVar = 10;
var secVar = 20;
var thirdVar = 30;

//============================================全局参数 End============================================

$(document).ready(function () {

    //点击树节点，添加tab页面
    $('#tree').tree({
        onClick: function (node) {
            var requestUrl = node.attributes.url;
            if (undefined != requestUrl && "" != requestUrl) {
                if (requestUrl.indexOf("/security") == 0 || requestUrl.indexOf("/ttm") == 0) {//以/security开头
                    var url = getRootPath() + "/resource/openTab?url=" + requestUrl;//权限相关的请求统一由openTab跳转
                } else if(requestUrl.indexOf("www")==0){//以www开头
                    var url = "http://" + requestUrl;
                } else{
                    var url =  getRootPath() + requestUrl;//其他optt的请求
                }
                f_addTab(node.id, node.id, node.text, url);
            }
        }
    });

});

//js获取项目根路径，如： http://localhost:8083/security
function getRootPath() {
    //获取带"/"的项目名，如：/security
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return(projectName);
}

//添加tab
function f_addTab(name, tabId, title, url) {
    tab = $("#tab");
    if (tab.tabs('exists', title)) {
        tab.tabs('select', title);
    } else {
        tab.tabs('add', {
            id: tabId,
            title: title,
            content: '<iframe name="' + name + '" src="' + url + '" frameborder="0" style="height:100%;width:100%;"></iframe>',
            closable: true,
            cache: true
        });
    }
}

//datagrid的一些方法调用
function getKeys(rows, key) {
    var keyArr = "";//必须赋初始值，否则行第一次循环时的值为undefined
    if (rows.length != 0) {
        for (var i = 0; i < rows.length; i++) {
            if (keyArr !== "") {
                keyArr += ",";
            }
            keyArr += "'" + rows[i][key] + "'";
        }
    }
    return keyArr;
}

//判断是否有选中行
function f_getGridRows(oGrid) {
    var rows = oGrid.datagrid('getSelections');
    if (rows.length == 0) {
        showAlert('没有选中行，请选择一行进行编辑操作！');
        return false;
    } else if (rows.length > 1) {
        showAlert('你选择了多行数据，只能选择一行数据进行编辑！');
        return false;
    }
    return true;
}

//删除操作，这时的url传参数时不再用param.id形式
//后台批量删除
function f_del(gridId, urlValue, keyValue) {
    var rows = [];
    rows = $('#' + gridId).datagrid('getSelections');
    var keys = getKeys(rows, keyValue);
    if (keys != '' && keys != null) {
        $.messager.confirm('警告', '您确认要删除选中的行吗？', function (r) {
            if (r) {
                $.ajax({
                    url: urlValue, // + "?param.keys=" + keys,
                    type: "post",
                    data: {"params": keys},
                    dataType: "json",
                    success: function (data) {
                        if (data.msg) {
                            $('#' + gridId).datagrid('load', {});	//利用空参数，刷新数据
                            $('#' + gridId).datagrid('clearSelections');
                            showMsg(data.msg);
                        } else if (data.err) {
                            showAlert(data.err);
                        }

                    },
                    error: function (data) {
                        showAlert(data.err);
                    }
                });
            }
        });
    } else {
        showAlert('没有选中任何行，请选择行后再操作!');
    }
}
//在屏幕右下角弹出
function showMsg(message) {
    $.messager.show({
        title: '提示',
        msg: message,
        timeout: 5000,
        showType: 'slide'
    });
}

//在正中央弹出
function showAlert(message) {
    $.messager.alert('警告', message);
}

//将form表单内的元素序列化为对象，扩展Jquery的一个方法
sy.serializeObject = function (form) {
    var o = {};
    $.each(form.serializeArray(), function (index) {
        if (o[this['name']]) {
            o[this['name']] = o[this['name']] + "," + this['value'];
        } else {
            o[this['name']] = this['value'];
        }
    });
    return o;
};

//验证密码是否一致
$.extend($.fn.validatebox.defaults.rules, {
    eqPwd: {/* 验证两次密码是否一致功能 */
        validator: function (value, param) {
            console.info(value);
            console.info(param);
            return value == $(param[0]).val();
        },
        message: '密码不一致！'
    }
});

//解决validatebox初始化验证问题
$(function () {
    $('input.easyui-validatebox').validatebox('disableValidation')
        .focus(function () {
            $(this).validatebox('enableValidation');
        })
        .blur(function () {
            $(this).validatebox('validate');
        });
});

$.fn.tree.defaults.loadFilter = function (data, parent) {
    var opt = $(this).data().tree.options;
    var idFiled, textFiled, parentField;
    if (opt.parentField) {
        idFiled = opt.idFiled || 'id';
        textFiled = opt.textFiled || 'text';
        parentField = opt.parentField;
        var i, l, treeData = [], tmpMap = [];
        for (i = 0, l = data.length; i < l; i++) {
            tmpMap[data[i][idFiled]] = data[i];
        }
        for (i = 0, l = data.length; i < l; i++) {
            if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
                if (!tmpMap[data[i][parentField]]['children'])
                    tmpMap[data[i][parentField]]['children'] = [];
                data[i]['text'] = data[i][textFiled];
                tmpMap[data[i][parentField]]['children'].push(data[i]);
            } else {
                data[i]['text'] = data[i][textFiled];
                treeData.push(data[i]);
            }
        }
        return treeData;
    }
    return data;
};
