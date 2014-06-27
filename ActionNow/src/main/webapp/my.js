var appDomain="/actionnow/actnow";

function queryString(key)
{
    var regex_str = "^.+\\?.*?\\b"+ key +"=(.*?)(?:(?=&)|$|#)";
    var regex = new RegExp(regex_str,"i");
    var url = window.location.toString();
    if(regex.test(url)) return RegExp.$1;
    return undefined;
}

function loadProvince(){
        var url =appDomain+"/area/provinces";
        jQuery.ajax( {
            type : "GET",
            dataType : "json",
            url : url,
            cache : false,
            success : function(dataResult) {
                    if(dataResult !=null){
                        var provinceHtml="<option>请选择</option>";
                        var data;
                        for(var i=0; i<dataResult.length; i++){
                        	data = dataResult[i];
                            provinceHtml=provinceHtml+"<option value='"+data.index+"'>"+data.name+"</option>";
                        }
                        $("#province").html(provinceHtml);
                        $("#city").html("<option value='0'>请选择</option>");
                        $("#county").html("<option value='0'>请选择</option>");
                    } else {
                    	$("#province").html("<option value='0'>请选择</option>");
                    	$("#city").html("<option value='0'>请选择</option>");
                    	$("#county").html("<option value='0'>请选择</option>");
                    }
            },
            error : function(XMLHttpResponse) {
            }
        }); 
}

function loadCity(provinceid){
    if(provinceid>0){
        var url =appDomain+"/area/cities";
        jQuery.ajax( {
            type : "GET",
            dataType : "json",
            url : url,
            data : "provinceid="+provinceid,
            cache : false,
            success : function(dataResult) {
                    var cityHtml="<option value='0'>请选择</option>";
                    if(dataResult !=null){
                    	var data;
                        for(var i=0; i<dataResult.length; i++){
                        	data = dataResult[i];
                        	cityHtml=cityHtml+"<option value='"+data.index+"'>"+data.name+"</option>";
                        }
                    }
                    $("#city").html(cityHtml);
                    $("#county").html("<option value='0'>请选择</option>");
                    $("#townDiv").hide();
            },  
            error : function(XMLHttpResponse) {
            }
        }); 
    }else{
        $("#cityDiv").html("<option value='0'>请选择</option>");
        $("#countyDiv").html("<option value='0'>请选择</option>");
        $("#townDiv").hide();
    }
}

function loadCounty(cityid){
    if(cityid>0){
        var url =appDomain+"/area/counties";
        jQuery.ajax( {
            type : "GET",
            dataType : "json",
            url : url,
            data : "cityid="+cityid,
            cache : false,
            success : function(dataResult) {
                    if(dataResult !=null){
                        var countyHtml="<option value='0'>请选择</option>";
                    	var data;
                        for(var i=0; i<dataResult.length; i++){
                        	data = dataResult[i];
                        	countyHtml=countyHtml+"<option value='"+data.index+"'>"+data.name+"</option>";
                        }
                        $("#county").html(countyHtml);
                        $("#town").hide();
                    }
            },
            error : function(XMLHttpResponse) {
            }
        }); 
    }else{
        $("#county").html("<option value='0'>请选择</option>");
        $("#town").hide();
    }

}

//function loadAllArea(provinceId,cityId,countyId,townId){
//
//    var url =appDomain+"/address/loadAllArea.action";
//
//    jQuery.ajax( {
//
//        type : "POST",
//
//        dataType : "json",
//
//        url : url,
//
//        data : "addressInfoParam.provinceId="+provinceId
//
//                    +"&addressInfoParam.cityId="+cityId
//
//                    +"&addressInfoParam.townId="+townId+"&addressInfoParam.countyId="+countyId,
//
//        cache : false,
//
//        success : function(dataResult) {
//
//                if(isUserNotLogin(dataResult)){
//
//                    goToLogin();
//
//                    return;
//
//                }
//
//                if(dataResult !=null){
//
//                    var provinceHtml="<option>��ѡ��</option>";
//
//                    var provinceInfo = dataResult.province;
//
//                    var cityInfo = dataResult.city;
//
//                    var countyInfo = dataResult.county;
//
//                    var townInfo = dataResult.town;
//
//                    for(var key in provinceInfo){
//
//                        if(provinceId == key){
//
//                            provinceHtml=provinceHtml+"<option value='"+key+"' selected>"+provinceInfo[key]+"</option>";
//
//                        }else{
//
//                            provinceHtml=provinceHtml+"<option value='"+key+"'>"+provinceInfo[key]+"</option>";
//
//                        }
//
//                    }
//
//                    $("#provinceDiv").html(provinceHtml);
//
//                    var cityHtml="<option value='0'>��ѡ��</option>";
//
//                    for(var key in cityInfo){
//
//                        if(cityId==key){
//
//                            cityHtml=cityHtml+"<option value='"+key+"' selected>"+cityInfo[key]+"</option>";
//
//                        }else{
//
//                            cityHtml=cityHtml+"<option value='"+key+"'>"+cityInfo[key]+"</option>";
//
//                        }
//
//                    }
//
//                    $("#cityDiv").html(cityHtml);
//
//                    var countyHtml="<option value='0'>��ѡ��</option>";
//
//                    for(var key in countyInfo){
//
//                        if(key == countyId){
//
//                            countyHtml=countyHtml+"<option value='"+key+"' selected>"+countyInfo[key]+"</option>";
//
//                        }else{
//
//                            countyHtml=countyHtml+"<option value='"+key+"'>"+countyInfo[key]+"</option>";
//
//                        }
//
//                    }
//
//                    $("#countyDiv").html(countyHtml);
//
//                    if(!isEmpty(townInfo)){
//
//                        var hasTown =0;
//
//                        for(var key in townInfo){
//
//                            hasTown++;
//
//                        }
//
//                        if(hasTown>0){
//
//                            var townHtml="<option value='0'>��ѡ��</option>";
//
//                            for(var key in townInfo){
//
//                                if(townId == key){
//
//                                    townHtml=townHtml+"<option value='"+key+"' selected>"+townInfo[key]+"</option>";
//
//                                }else{
//
//                                    townHtml=townHtml+"<option value='"+key+"'>"+townInfo[key]+"</option>";
//
//                                }
//
//                            }
//
//                            $("#townDiv").html(townHtml);
//
//                            $("#townDiv").show();
//
//                        }
//
//                        var provinceName = $("#provinceDiv").find("option:selected").text();
//
//                        var cityName = $("#cityDiv").find("option:selected").text();
//
//                        var countyName = $("#countyDiv").find("option:selected").text();
//
//                        var townName="";
//
//                        if(hasTown>0){
//
//                            townName= $("#townDiv").find("option:selected").text();
//
//                        }
//
//                        var areaName = provinceName+ cityName+countyName+townName;
//
//                        $("#areaName").text(areaName);
//
//                    }
//
//                    
//
//                }
//
//        },
//
//        error : function(XMLHttpResponse) {
//
//        }
//
//    }); 
//
//}
function loadCategory(){
//    var url =appDomain+"/type/Categories";
//    jQuery.ajax( {
//        type : "GET",
//        dataType : "json",
//        url : url,
//        cache : false,
//        success : function(dataResult) {
//                if(dataResult !=null){
//                    var categoryHtml="<option>请选择</option>";
//                    var data;
//                    for(var i=0; i<dataResult.length; i++){
//                    	data = dataResult[i];
//                    	categoryHtml=categoryHtml+"<option value='"+data.index+"'>"+data.name+"</option>";
//                    }
//                    $("#category").html(categoryHtml);
//                    $("#type").html("<option value='0'>请选择</option>");
//                } else {
//                	$("#category").html("<option value='0'>请选择</option>");
//                    $("#type").html("<option value='0'>请选择</option>");
//                }
//        },
//        error : function(XMLHttpResponse) {
//        }
//    }); 
}

function loadType(categoryid){
//    var url =appDomain+"/type/Types";
//	if(categoryid > 0) {
//		jQuery.ajax( {
//	        type : "GET",
//	        dataType : "json",
//	        url : url,
//	        data :"categoryid=" + categoryid,
//	        cache : false,
//	        success : function(dataResult) {
//	                if(dataResult !=null){
//	                    var typeHtml="<option>请选择</option>";
//	                    var data;
//	                    for(var i=0; i<dataResult.length; i++){
//	                    	data = dataResult[i];
//	                    	typeHtml=typeHtml+"<option value='"+data.index+"'>"+data.name+"</option>";
//	                    }
//	                    $("#type").html(typeHtml);
//	                } else {
//	                    $("#type").html("<option value='0'>请选择</option>");
//	                }
//	        },
//	        error : function(XMLHttpResponse) {
//	        }
//	    }); 
//	}
}

/**
 * 查询选定类型和区域的已有事件
 */
function loadEvent(){
	$("#events").css("display","block");
//	$("#events").attr("display","block");
}
function submit(){
	var url=appDomain+"/basicinfo";
	jQuery.ajax( {
		type : "POST",
		dataType : "json",
		url : url,
		//data :"openid=" + $("#openid") + "&category=" + $("#category")
		//			+ "&type=" + $("#type") + "&district=" + $("#city")
		//			+ "&businesscircle=" + $("#county")
		//			+ "&address=" + $("#address")
		//			+ "&longitude=0"
		//			+ "&latitude=0",
		data :"openid=" + queryString("openid") +"&category=" + $("#category").val() + "&type=" + $("#type").val()
					+ "&district="+ $("#city").val() +"&businesscircle=" +  $("#county").val()
					+ "&address=" + $("#address").val()
					+ "&longitude=0"
					+ "&latitude=0",			
		cache : false,
		success : function(result) {
			//alert("提交是否成功");
			window.open('','_self');
			window.close();
			WeixinJSBridge.call('closeWindow');
		},
		error : function(XMLHttpResponse) {
		}
	}); 
}
function cancel(){
	window.top.opener = null;
	//window.open('','_self');
	window.close();
	WeixinJSBridge.call('closeWindow');
}
$(function() {
	loadProvince();
	loadCategory();
});