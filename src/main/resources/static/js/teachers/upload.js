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
		var div = $('#outlineDiv');
		if($("#1").length > 0){
				toastr.error("请先删除现有大纲！");
		}else if($("#file1").val() == null){
			toastr.error("请先上传大纲！");
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
					 console.log(data.body);
					 $('#outlineForm')[0].reset(); 
					 if (data.success) {
						 div.append(
								 "<div style='margin-left:15px' id='1'>" +
									 "<label class='col-form-label'><strong style='color:red'>*&thinsp;</strong><i id='outlineName'>"+data.body+"</i></label>" +
									 "&nbsp;&nbsp;" +
									 "<a class='teachers-delete-outline' role='button'> " +
									 "<i class='fa fa-times-circle fa-lg text-danger' title='删除大纲'></i>" +
									 "</a>" +
								 "</div>"
						 );
						 
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
		var div = $('#scheduleDiv');
		if($("#2").length > 0){
				toastr.error("请先删除现有教学进度表！");
		}else if($("#file2").val() == null){
			toastr.error("请先上传教学进度表！");
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
						 div.append(
								 "<div style='margin-left:15px' id='2'>" +
									 "<label class='col-form-label'><strong style='color:red'>*&thinsp;</strong><i id='outlineName'>"+data.body+"</i></label>" +
									 "&nbsp;&nbsp;" +
									 "<a class='teachers-delete-schedule' role='button'> " +
									 "<i class='fa fa-times-circle fa-lg text-danger' title='删除教学进度表'></i>" +
									 "</a>" +
								 "</div>"
						 );
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
				 "outlineName":$("#outlineName").text(),
				 "id":$("#id1").val()
			 },
			 success: function(data){
				 
				 $('#scheduleForm')[0].reset(); 
				 
				 if (data.success) {
					 // 从新刷新主界面
					 $("#1").remove();
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
				 "scheduleName":$("#scheduleName").text(),
				 "id":$("#id2").val()
			 },
			 success: function(data){
				 
				 $('#scheduleForm')[0].reset(); 
				 
				 if (data.success) {
					 // 从新刷新主界面
					 $("#2").remove();
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