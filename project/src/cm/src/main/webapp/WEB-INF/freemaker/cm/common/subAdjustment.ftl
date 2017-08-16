<style type="text/css">
	table.hovertable {
		font-size:11px;
		color:#333333;
		border-width: 1px;
		border-color: #999999;
		border-collapse: collapse;
		margin-bottom:5px;
		margin-top:5px;
		background-color:#F5F5F5; 
	}
	table.hovertable td {
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #383838;
	}
	td.bgg{
		background-color:#E5E5E5; 
		text-align:right;
	}
	    
	div.yellowdiv{
		background-color:#FFEC8B;
		padding:10px;
		border:2px solid #FFA500;
	}
	table.whiteborder td {
		border-color: white;
	}
	table.orange td {
		border-width:0px;
	}
	table.orange{
		background-color:#FFE4B5;
	}
	table.table-platcarmessage{
		background-color:#F5F5F5;
	}
	table.table-bordered.carmodel{
		background-color:#F5F5F5;
	}
</style>
<script type="text/javascript">
	requirejs([ "cm/manualadjustment/Adjustment" ]);
</script>
<!--人工报价子页面-->
<!--引入保单信息公共页面-->
<#include "cm/common/insurancePolicyInfo.ftl" />
<#include "cm/common/commomPlatInfo.ftl" />
<!--子流程id-->
<input type="hidden" id="subInstanceId${carInsTaskInfo_index}" name="subInstanceId" value="${carInsTaskInfo.subInstanceId}"/>
<div class="row">
 	<div class="col-md-12">
		<table border="1px" class="hovertable col-md-12">
		    <tr>
		    	<td class="col-md-12" colspan="2"  style="background-color:#8A8A8A;color:white;">
		    		<div class="row">
 						<div class="col-md-9">
 							投保单号
 						</div>
 						<div class="col-md-3" align="right">
 							<a href="javascript:window.parent.openDialogForCM('business/manualadjustment/showOrEditInsureRecordNumber?taskid=${carInsTaskInfoList[0].carInfo.taskid}');"><font color="white">修改</font></a>
 						</div>
 					</div>
		    	</td>
		    </tr>
		    <tr>
			    <td class="bgg col-md-2">商业险投保单号:</td>
			    <td class="col-md-10">${proposalInfo.businessProposalFormNo}</td>
		    </tr>
		    <tr>
			    <td class="bgg col-md-2">交强险投保单号:</td>
			    <td class="col-md-10">${proposalInfo.businessPolicyNum}</td>
		    </tr>
		    <tr>
			    <td class="bgg col-md-2">支付号:</td>
			    <td class="col-md-10">${paymentinfo.insurecono}</td>
		    </tr>
		    <tr>
			    <td class="bgg col-md-2">校验码:</td>
			    <td class="col-md-10">${paymentinfo.checkcode}</td>
		    </tr>
	    </table>
	</div>
</div>
<div class="task_fixbottom">
	<!-- 备注信息页面引入-->
	<#include "cm/common/remarksInfo.ftl" />
	<!--按钮行-->
  	<div class="col-md-12">
 		<button class="mybtn btn btn-primary passInsurance" type="button" name="adjustment" id="adjustment${carInsTaskInfo_index}" title="调整完成" >调整完成</button>
 		<button class="mybtn btn btn-primary backEditInsurance" type="button" name="backEdit" id="backEdit${carInsTaskInfo_index}" title="退回修改" >退回修改</button>
 		<button class="mybtn btn btn-primary refuseInsurance" type="button" name="refuseInsurance" id="refuseInsurance${carInsTaskInfo_index}" title="拒绝承保" >拒绝承保</button>
 		<button class="mybtn btn btn-primary backTask" type="button" name="backTask" id="backTask${carInsTaskInfo_index}" title="打回任务" >打回任务</button>
 		<a class="btn btn-primary" href="/cm/business/mytask/queryTask">返回我的任务</a>
 	</div>
</div>