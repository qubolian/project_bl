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
	
	// 上传大纲
	$("#submitOutline").on("click",function(){
		if($("#outlineName").val() == 0){
			toastr.error("请先删除现有大纲");
		}else{
			var formData = new FormData($( "#outlineForm" )[0]);
			$.ajax({ 
				 url: "/teachers/uploadOutline", 
				 type: 'POST',
				 data:formData,
				 cache: false,  
		         processData: false,  
		         contentType: false,
				 success: function(data){
					 
					 $('#outlineForm')[0].reset(); 
					 
					 if (data.success) {
						 // 从新刷新主界面
					 } else {
						 toastr.error(data.message);
					 }
	
			     },
			     error : function() {
			    	 toastr.error("error!");
			     }
			 });
		}
	});
	
	

	// 上传教学进度表
	$("#submitSchedule").on("click",function(){
		if($("#scheduleName").val() == 0){
			toastr.error("请先删除现有教学进度表");
		}else{
			var formData = new FormData($( "#scheduleForm" )[0]);
			$.ajax({ 
				 url: "/teachers/uploadSchedule", 
				 type: 'POST',
				 data:formData,
				 cache: false,  
		         processData: false,  
		         contentType: false,
				 success: function(data){
					 
					 $('#scheduleForm')[0].reset(); 
					 
					 if (data.success) {
						 // 从新刷新主界面
					 } else {
						 toastr.error(data.message);
					 }
	
			     },
			     error : function() {
			    	 toastr.error("error!");
			     }
			 });
		}
	});
	
	
	
});