require(["jquery","bootstrap","zTree","zTreecheck","bootstrapdatetimepicker","bootstrapdatetimepickeri18n","public"], function ($) {
	$(function() {
		$(".table").hover(
			function(){
				$(this).find(".channelimg").attr("src","/cm/static/images/system/resource/resource2.png");
				if($(this).find("label:contains('渠道来源')").text()!="渠道来源："){
//					$(this).find("label:contains('渠道来源')").css("color","red");
				}
			},
			function(){
				$(this).find(".channelimg").attr("src","/cm/static/images/system/resource/resource1.png");
//				$(this).find("label:contains('渠道来源')").css("color","");
			}
		);
		//时间控件初始化设置
//		 $('.form_datetime').datetimepicker({
//		      language: 'zh-CN',
//		      format: "yyyy-mm-dd",
//		      weekStart: 1,
//		      todayBtn: 1,
//		      autoclose: 1,
//		      todayHighlight: 1,
//		      startView: 2,
//		      forceParse: 0,
//		      minView: 2,
//		      pickerPosition: "bottom-left"
//		      // showMeridian: 1
//		    });
		$("#mstartdate").datetimepicker({
			language: 'zh-CN',
			format: "yyyy-mm-dd",
			weekStart: 1,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
			minView: 2,
			pickerPosition: "bottom-left",
			startDate:'-3m',
			endDate:new Date()
		});
		$("#menddate").datetimepicker({
			language: 'zh-CN',
			format: "yyyy-mm-dd",
			weekStart: 1,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
			minView: 2,
			pickerPosition: "bottom-left",
			startDate:'-3m',
			endDate:new Date()
		});
		//高级搜索
		$("#mseniorsearch").on("click",function(e){
			$("#msimplequery1").hide();
			$("#mseniorquery").show();
		});
		// 搜索条件【重置】按钮
		$("#mresetbutton").on("click", function(e) {
			$("#mnameorcarno").val("");

		});
		// 高级搜索条件【重置】按钮
		$("#mseniorresetbutton").on("click", function(e) {
			$("#minsuredname").val("");
			$("#mproviderid").val("");
			$("#mprovidername").val("");
			$("#mcarnum").val("");
			$("#mtaskid").val("");
			$("#mphone").val("");
			$("#morderstatus").val("");
//			$("#musertype").val("");
			$("#mshutter").val("");
			$("#mstartdate").val("");
			$("#menddate").val("");
			$("#magentcode").val("");
			$("#mpaymentTransaction").val("");

		});
//		返回
		$("#mbackbutton").on("click",function(e){
			window.history.back(-1);
			//var agentid = $("#agentid").val();
			//$("#mqueryorder").click();
		});
		$("#mbackbutton2").on("click",function(e){
			window.history.back(-1);
			//var agentid = $("#agentid").val();
			//$("#mqueryorder").click();
		});
		//关闭高级搜索
		$("#mclosesearch").on("click",function(e){
			$("#msimplequery1").show();
			$("#mseniorquery").hide();
		});
		//快速搜索改变时
//		$("#mnameorcarno").on("change",function(e){
//			$("#mcarnumorcname").val($("#mnameorcarno").val());
//		});
		//点击搜索
		$("#msimplequerybutton").on("click",function(e){
			$("#mcarnumorcname").val($("#mnameorcarno").val());
			formsubmit();
		});
		//点击高级搜索
		$("#mseniorquerybutton").on("click",function(e){
			alert($("#magentid").val());
			$("#mcarnumorcname").val("");
			var startime = new Date($("#mstartdate").val());
			var endtime = new Date($("#menddate").val());
			if(startime > endtime){
				alert("起始时间不能大于终止时间！");
				return;
			}

			formsubmit();
		});
		//选择供应商
		$("#mcheckprovider").on("click", function(e) {
			$('#mshowprovider').modal();
			$.fn.zTree.init($("#mprovidertree"), providersetting);
		});

		//分页控制
		//首页
		$('#mfirst').on("click",function(e){
			$("#mcurrentPage").val(1);
			formsubmit();
		});
		//末页
		$('#mlast').on("click",function(e){
			$("#mcurrentPage").val($.trim($("#mlblPageCount").text()));
			formsubmit();
		});

		//上一页
		$('#mprevious').on("click",function(e){
			var pagecurrent = $("#mcurrentPage").val();
			if(pagecurrent > 0){
				pagecurrent--;
				$("#mcurrentPage").val(pagecurrent);
				formsubmit();
			}
		});

		//下一页
		$('#mnext').on("click",function(e){
			var pagecurrent = $("#mcurrentPage").val();
			var pagecount = $.trim($("#mlblPageCount").text());
			if(pagecurrent<pagecount){
				pagecurrent++;
				$("#mcurrentPage").val(pagecurrent);
				formsubmit();
			}
		});

		//修改起始时间
		$("#mstartdate").change(function(){
			var startString=$("#mstartdate").val();//获取当前显示的时间字符串
			var dateStartime=Date.parse(startString);//解析时间字符串
			var startime=new Date(dateStartime);//创建时间
			var endMonth=(startime.getMonth()+2)+"";//获取时间的月份,在月份的基础上加1个月.因为月份是从0开始,所以+2
			var enday=startime.getDate()+"";//获取日份
			if(endMonth.length<2){
				endMonth="0"+endMonth;
			}
			if(enday.length<2){
				enday="0"+enday;
			}
			var endString = startime.getFullYear()+"-"+endMonth+"-"+enday;//跨度一个月的截止时间字符串
			var dateEndTime=Date.parse(endString);
			var endTime=new Date(dateEndTime);//解析为时间类型
			if(endTime>new Date()){//若开始时间为今天,那么截止时间可以为下个月的今天,所以需要判读
				endTime=new Date();//如果最大可选时间大于当前的时间,则最大可选时间为当前时间
				var todayMonth=(endTime.getMonth()+1)+"";
				if(todayMonth.length<2){
					todayMonth="0"+todayMonth;
				}
				var todayDay=(endTime.getDate())+"";
				if(todayDay.length<2){
					todayDay="0"+todayDay;
				}
				endString=endTime.getFullYear()+"-"+todayMonth+"-"+todayDay;
			}
			$("#menddate").datetimepicker('setEndDate',endTime);
			var showEnd=$("#menddate").val();//获取当前的截止日期
			var dateShowEnd=Date.parse(showEnd);
			var currentEnd=new Date(dateShowEnd);
			var days=0;
			if(currentEnd>startime){
				days=(currentEnd-startime)/(1000*60*60*24);
			}else{
				days=(startime-currentEnd)/(1000*60*60*24);
			}
			if(days>30){//若页面显示的时间差超过一个月,则将显示的截止时间改为设置的截止时间
				$("#menddate").val(endString);
			}
		});

		//修改截止时间
		$("#menddate").change(function(){
			var endString=$("#menddate").val();
			var dateEnd=Date.parse(endString);
			var endTime=new Date(dateEnd);
			var startMonth=endTime.getMonth();
			if(startMonth==0){//当是一月的时候得到就是0,显示的时候就是0月.所以要+1
				startMonth=1;
			}
			startMonth=startMonth+"";
			if(startMonth.length<2){//改变月份显示为两位显示
				startMonth="0"+startMonth;
			}
			var startDay=endTime.getDate()+"";
			if(startDay.length<2){
				startDay="0"+startDay;
			}
			var startString=endTime.getFullYear()+"-"+startMonth+"-"+startDay;
			var startime=new Date(Date.parse(startString));
			var today=new Date();
			var beforeMonth=today.getMonth()-2;
			var beforeYear=today.getFullYear();
			if(beforeMonth<0){
				beforeMonth=13+beforeMonth;
				beforeYear=beforeYear-1;//往前推一年
			}
			beforeMonth=beforeMonth+"";
			if(beforeMonth.length<2){
				beforeMonth="0"+beforeMonth;
			}
			var beforeDay=today.getDate()+"";
			if(beforeDay.length<2){
				beforeDay="0"+beforeDay;
			}
			var beforeString=beforeYear+"-"+beforeMonth+"-"+beforeDay;
			var beforeDate=new Date(Date.parse(beforeString));//从今天往前三个月的时间
			if(startime<beforeDate){
				startime=beforeDate;
				startString=beforeString;
			}
			$("#mstartdate").datetimepicker('setStartDate',startime);
			var showStart=$("#mstartdate").val();//获取当前的截止日期
			var dateShowStart=Date.parse(showStart);
			var currentStart=new Date(dateShowStart);
			var days=0;
			if(currentStart>endTime){
				days=(currentStart-endTime)/(1000*60*60*24);
			}else{
				days=(endTime-currentStart)/(1000*60*60*24);
			}
			if(days>30){//若页面显示的时间差超过一个月,则将显示的截止时间改为设置的截止时间
				$("#mstartdate").val(startString);
			}
		});
	});

});
var providersetting = {
	async: {
		enable: true,
		url:"/cm/miniOrder/queryprovidertree",
		autoParam:["id"],
		dataType: "json",
		type: "post"
	},
	check: {
		enable: true,
		chkStyle: "radio",
		radioType: "all"
	},
	callback: {
		onCheck: providerTreeOnCheck
	}
};

//表单提交
function formsubmit(){
	alert('submit');
	$("#mqueryorder").submit();
}

function providerTreeOnCheck(event, treeId, treeNode) {
	$("#mproviderid").val(treeNode.prvcode);
	$("#mprovidername").val(treeNode.name);
	$('#mshowprovider').modal("hide");
}

function checkthisstatus(obj,status){
	$(".nav-pills li").removeClass("active");
	$(obj).addClass("active");
	$("#morderstatus").val(status);
	formsubmit();
}

function go(pagenumber){
	$("#mcurrentPage").val(pagenumber);
	formsubmit();
}

//查看详情
function searchorderdetail(orderid,pid,codename){
	if(orderid == "" && pid == ""){
		alertmsg("Connection error!");
		return false;
	}
	$("#mid").val(orderid);
	$("#mpid").val(pid);
	$("#mcodename").val(codename);
	$("#mdetailinfo").submit();
}
