<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>奖品管理</title>
<link href="${staticRoot}/css/appmodule.css" rel="stylesheet">
<script data-ver="${jsver}" data-main="${staticRoot}/js/load" src="${staticRoot}/js/lib/require.js?ver=${jsver}"></script>
<script type="text/javascript">
		requirejs([ "zzbconf/prizeinfo" ]);
</script>
<body>
	<div class="container-fluid">
		<div class="panel panel-default m-bottom-5">
		  <div class="panel-heading padding-5-5">查询</div>
		  <div class="panel-body">
		    <div class="row">
			<div class="col-md-12">
				<form role="form" id="policymanagent">
					<div class="form-group form-inline col-md-4">
						<label for="exampleInputCode">奖品名称</label> <input type="text"
							class="form-control m-left-5" id="name" name="name" placeholder="">
					</div>
					<div class="form-group form-inline col-md-4">
						<label for="exampleInputCode">奖品类型</label> <input type="text"
							class="form-control m-left-5" id="type" name="type" placeholder="">
					</div>
					<div class="form-group form-inline col-md-4">
						<label for="exampleInputName">奖品生效方式</label> <input type="text"
							class="form-control m-left-5" id="counts" name="counts" placeholder="">
					</div>
					<div class="form-group form-inline col-md-4">
						<label for="exampleInputOrgName">奖品生效时间</label> <input type="text"
							<input type="text"	class="form-control form_datetime" id="effectivetime" name="effectivetime" readonly placeholder="" >
					</div>
                    <div class="form-group form-inline col-md-4">
                        <label for="exampleInputOrgName">奖品失效时间</label> <input type="text"
                        <input type="text"	class="form-control form_datetime" id="invalidtime" name="invalidtime" readonly placeholder="" >
                    </div>
				</form>
			</div>
		</div>
		  </div>
		  <div class="panel-footer">
						<button id="querybutton" type="button" name="querybutton"
											class="btn btn-primary">查询</button>
						<button id="save" type="button" name="save"
											class="btn btn-primary">新增</button>
						<button id="edit" type="button" name="edit"
											class="btn btn-primary">编辑</button>
						<button id="delete" type="button" name="delete"
											class="btn btn-primary">删除</button>
						<button id="resetbutton" type="button" name="resetbutton"
											class="btn btn-primary">重置</button>
						<button class="btn btn-primary" type="button" name="refresh"
											title="Refresh" id="refresh">
											<i class="glyphicon glyphicon-refresh icon-refresh"></i>
										</button>
						<button class="btn btn-primary" type="button" name="toggle" title="Toggle" id="toggle">
												<i class="glyphicon glyphicon-list-alt icon-list-alt"></i>
										</button>
			</div>
		</div>
		<div class="panel panel-default">
		  <div class="panel-heading padding-5-5">
		  <div class="row">
				<div class="col-md-2">
					结果
					</div>
			</div>
			</div>
		    <div class="row">
				<div class="col-md-12">
					<table id="table-list"></table>
				</div>
		  </div>
		</div>
	</div>
	<div class="modal fade" id="providersource" tabindex="-1" role="dialog"	aria-labelledby="myModalLabel" data-backdrop="false" data-keyboard="false" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" id="modal-content">
			<form id="form1" action="save" method="post">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
					 <input type="hidden" id="id" name="id" >
					 
					 
					 
					 <table  border="5" >
					 
					 <tr>
						 <td>奖品名称</td>
						 <td><input type="text" id="name2" name="name" ></td>
						 <td>有效天数</td><td><input type="text" id="effectiveDates2" name="effectiveDates" ></td>
					 </tr>
						 <tr><td>奖品类型</td><td><input type="text" id="type2" name="type" ></td>
						 <td>奖品生效方式</td><td><input type="text"id="counts2" name="counts" ></td>
					 </tr>
					 <tr><td>奖品生效时间</td><td><input type="text"	class="form-control form_datetime" id="effectivetime2" name="effectivetime" readonly ></td>
					 <td>奖品失效时间</td><td><input type="text"	class="form-control form_datetime" id="invalidtime2" name="invalidtime" readonly></td>
					 </tr>
					
					 </table>
                    <div class="modal-body " style="overflow: auto; height: 400px;">
						<button class="btn btn-primary" type="button" id="checkProid" title="showAll">确定</button>
						<button class="btn btn-primary" type="button" id="closeProid">取消</button>
	   				</div>
				</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
