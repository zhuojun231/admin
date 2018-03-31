<%@page pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>AdminJingLuu | Dashboard</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@ include file="../common/style.jsp"%>
  <!-- DataTables -->
  <link rel="stylesheet" href="${ctx}/assets/bower_components/bootstrap-datatables/css/dataTables.bootstrap.min.css">
  <!-- Google Font -->
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <%@include file="../common/header.jsp"%>

  <!-- Left side column. contains the logo and sidebar -->
  <%@include file="../common/leftmenu.jsp"%>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Dashboard
        <small>Version 2.0</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Dashboard</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Data Table With Full Features</h3>
                <a id="queryBtn" href="javaScript:void(0);" class="btn btn-success">查询</a>
              <shiro:hasPermission  name="USER:MGT:LIST">
                <a id="addUser" href="javaScript:void(0);" class="btn btn-default">新增</a>
              </shiro:hasPermission>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="userList" class="table table-bordered table-striped">
                <thead>
                <tr>
                  <th>账号</th>
                  <th>昵称</th>
                  <th>创建时间</th>
                  <th>最后登录时间</th>
                  <th>状态</th>
                </tr>
                </thead>
                <tbody id="pageDataList"></tbody>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->

  </div>
  <!-- /.content-wrapper -->

  <%@include file="../common/footer.jsp"%>

  <%@include file="../common/settings.jsp"%>

</div>
<!-- ./wrapper -->

<%@ include file="../common/script.jsp"%>
<!-- DataTables -->
<script src="${ctx}/assets/bower_components/bootstrap-datatables/js/jquery.dataTables.min.js"></script>
<script src="${ctx}/assets/bower_components/bootstrap-datatables/js/dataTables.bootstrap.min.js"></script>
<script src="${ctx}/assets/js/admin/pagination.js"></script>
<script src="${ctx}/assets/js/admin/user/user-list.js"></script>
</body>


<!-- page script -->
<script>
    $(function () {
        $('#userList').DataTable({
            'paging'      : false,
            'lengthChange': false,
            'searching'   : false,
            'ordering'    : false,
            'info'        : false,
            'autoWidth'   : false
        })

        $("#addUser").click(function () {
            $.ajax({
                type : "POST",
                url : ctx + "/user/add",
                dataType : "json",
                data : {
                    username :"",
                    password : ""
                },
                success : function (result) {
                    console.log(result);
                    if(result.status == 0){
                        alert("success");
                    }else {
                        alert("message: " + result.message);
                    }
                },
                error : function (xmlHttpRequest, textStatus, errorThrown) {
                    alert("error:" + xmlHttpRequest.responseText);
                }
            });
        });
    })
</script>
</html>
