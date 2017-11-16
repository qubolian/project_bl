/*!
  * Bolg main JS.
 * 
 * @since: 1.0.0 2017/3/9
 * @author Way Lau <https://waylau.com>
 */
"use strict";
//# sourceURL=main.js
 
// DOM 加载完再执行
$(function() {
	
	
	
	// 提交变更后，清空表单
	$("#submitEdit").on("click",function(){
			$.ajax({ 
				 url: "/teachers/courseStandard/", 
				 type: 'POST',
				 data:$('#courseForm').serialize(),
				 success: function(data){
					 
					 $('#courseForm')[0].reset();  
					 
					 if (data.success) {
						 // 从新刷新主界面
						 getCourseByName(0, _pageSize);
					 } else {
						 toastr.error(data.message);
					 }
	
			     },
			     error : function() {
			    	 toastr.error("error!");
			     }
			 });
	});
	
	

	function addPercentage() {
		var a = 0;
		for(var i = 1;i<7;i++){
			if($("#percentage"+i).val() != ""){
				a += parseInt($("#percentage"+i).val());
			}
		}
		return a;
	}
	
	
	$("#percentage1").change(function(){
		var a = addPercentage();
		if(a>100){
			toastr.error("百分比上限为100");
		}else{
			$("#2").show();
		}
	});
	$("#percentage2").change(function(){
		var a = addPercentage();
		if(a>100){
			toastr.error("百分比上限为100");
		}else{
			$("#3").show();
		}
	});
	$("#percentage3").change(function(){
		var a = addPercentage();
		if(a>100){
			toastr.error("百分比上限为100");
		}else{
			$("#4").show();
		}
	});
	$("#percentage4").change(function(){
		var a = addPercentage();
		if(a>100){
			toastr.error("百分比上限为100");
		}else{
			$("#5").show();
		}
	});
	$("#percentage5").change(function(){
		var a = addPercentage();
		if(a>100){
			toastr.error("百分比上限为100");
		}else{
			$("#6").show();
			$("#percentage6").val(100-a);
		}
	});
	$("#percentage6").change(function(){
		var a = addPercentage();
		if(a!=100){
			toastr.error("百分比之和必须为100");
		}
	});
	
});