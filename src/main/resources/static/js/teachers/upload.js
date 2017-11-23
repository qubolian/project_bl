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
		var div = $('#1');
		var del = $(".teachers-delete-outline");

		if($("#outlinelabel").length > 0){
			toastr.error("请先删除现有大纲！");
		}else if($("#file1").val() == ""){
			toastr.error("请先选择大纲！");
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
						 div.prepend(
									 "<label id='outlinelabel' class='col-form-label'><strong style='color:red'>*&thinsp;</strong><i id='outlineName'>"+data.body+"</i></label>" 
						 );
						 del.show();
						 
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
		var div = $('#2');
		var del = $(".teachers-delete-schedule");
		if($("#schedulelabel").length > 0){
				toastr.error("请先删除现有教学进度表！");
		}else if($("#file2").val() == ""){
			toastr.error("请先选择教学进度表！");
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
						 div.prepend(
							"<label id='schedulelabel' class='col-form-label'><strong style='color:red'>*&thinsp;</strong><i id='outlineName'>"+data.body+"</i></label>" 
						 );
						 del.show();
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
	
	// 删除大纲
	$(".teachers-delete-outline").on("click",function(){
		$.ajax({ 
			 url: "/teachers/deleteOutline", 
			 //type: 'POST',
			 data:{
				 "id":$("#id1").val()
			 },
			 success: function(data){
				 
				 $('#scheduleForm')[0].reset(); 
				 
				 if (data.success) {
					 // 从新刷新主界面
					 $("#outlinelabel").remove();
					 $(".teachers-delete-outline").hide();
				 } else {
					 toastr.error(data.message);
				 }

		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// 删除教学进度表
	$(".teachers-delete-schedule").on("click",function(){
		$.ajax({ 
			 url: "/teachers/deleteSchedule", 
			 //type: 'POST',
			 data:{
				 "id":$("#id2").val()
			 },
			 success: function(data){
				 
				 $('#scheduleForm')[0].reset(); 
				 
				 if (data.success) {
					 // 从新刷新主界面
					 $("#schedulelabel").remove();
					 $(".teachers-delete-schedule").hide();
					 
				 } else {
					 toastr.error(data.message);
				 }

		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
});