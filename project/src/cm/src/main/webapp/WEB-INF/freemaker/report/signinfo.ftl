<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>打卡情况</title>
    <link href="${staticRoot}/css/appmodule.css" rel="stylesheet">
    <link rel="stylesheet" href="${staticRoot}/css/modelinsurance/date.css">
    <script data-ver="${jsver}" data-main="${staticRoot}/js/load" src="${staticRoot}/js/lib/require.js?ver=${jsver}"></script>
    <script type="text/javascript">
        requirejs([ "cm/report/report"]);
    </script>
</head>
<body>
<div class="container-fluid">
    <form action="/cm/report/showReport" name="fm1" method="post" id="fm1" target="report_frame">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-4" >
                        <label>月份（yyyymm）<label>

                            <input type="text" id="startmonth" name="startmonth">
                    </div>
                    <div class="col-md-4" >
                        <label>姓名<label>

                            <input type="text" id="username" name="username">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <button type="button" name="Submit" id="Submit" class="btn btn-primary" onclick="doSearch();">查询</button>
                    </div>
                </div>
            </div>
        </div>
        <input id="raq" name="raq" value="signinfo.raq" type="hidden">
    </form>
    <script>
        function doSearch(){
            $.insLoading();
            $("#fm1").submit();
        }
    </script>
</div>
<iframe id="report_frame" name="report_frame" style="width:100%; border: none;"></iframe>
</body>

</html>
