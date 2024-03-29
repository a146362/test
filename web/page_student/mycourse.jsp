<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/09/27
  Time: 上午 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="../lid/layui-v2.5.5/css/layui.css" media="all">
    <link rel="stylesheet" href="../css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">退课</a>
        </script>

    </div>
</div>
<script src="../lid/layui-v2.5.5/layui.js" charset="utf-8"></script>
<script src="../js/config.js"></script>
<script>
    var data =1;
    layui.use(['form', 'table'], function (obj) {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;
        studentId = 29;

        table.render({
            elem: '#currentTableId',
            url: realm_name + '/c2s/course/bystudent',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],

            parseData:function(res){
                return {
                    "code":res.code,
                    "msg":res.message,
                    "count":res.extend.pageInfo.total,
                    "data":res.extend.pageInfo.list
                }
            },
            cols: [[
                {type: "checkbox", width: 150},
                {field: 'id', width: 110, title: '课程编号', sort: true,
                    templet:function(res){
                        return res.course.id;
                    }
                },
                {field: 'name', width: 150, title: '课程名称',
                    templet:function(res){
                        return res.course.name;
                    }
                },
                {field: 'teacher',width: 180, title: '授课老师',
                    templet:function(res){
                        return res.teacher.name;
                    }
                },
                {field: 'teacher',width: 180, title: '老师电话',
                    templet:function(res){
                        return res.teacher.phone;
                    }
                },
                {field: 'classHour', width: 100, title: '课时',
                    templet:function(res){
                        return res.course.classHour;
                    }
                },
                {field: 'score', width: 90, title: '学分', sort: true,
                    templet:function(res){
                        return res.course.score;
                    }
                },
                {field: 'required', width: 160, title: '是否必修',
                    templet: function(d){
                        // alert(d.course.required);
                        if(d.course.required == true){
                            return '是';
                        }else{
                            return '否';
                        }
                    }
                },

                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 15,
            page: true,
            skin: 'line'
        })

        table.on('tool(currentTableFilter)', function (obj) {
            data = obj.data;
            if (obj.event === 'edit') {
                layer.confirm('真的退课 ' + data.course.name + ' 吗?', function (index) {
                    $.ajax({
                        url: realm_name + '/c2s/' + data.id,
                        method: "DELETE",
                        contentType:"application/json;charset=UTF-8", //发送json
                        // dataType: 'json',接受json
                        //成功
                        success:function(res){
                            layer.alert(res.message);
                        },
                        //失败
                        error:function(res){
                            layer.alert(res.message);
                        }
                    })
                    //刷新表格
                    obj.del();
                });

                return false;
            }
        });

    });
</script>

</body>
</html>
