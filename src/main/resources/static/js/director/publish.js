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
	var _pageSize; // 存储用于搜索
	
	
	
	// 根据公告标题、页面索引、页面大小获取用户列表
	function getCourseByName(pageIndex, pageSize) {
		
		 $.ajax({ 
			 url: "/director/publishCourseList", 
			 contentType : 'application/json',
			 data:{
				 "async":true, 
				 "pageIndex":pageIndex,
				 "pageSize":pageSize,
				 "name":$("#searchName").val()
			 },
			 success: function(data){
				 $("#mainContainer").html(data);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	}
	
	// 分页
	$.tbpage("#mainContainer", function (pageIndex, pageSize) {
		getCourseByName(pageIndex, pageSize);
		_pageSize = pageSize;
	});
   
	// 搜索
	$("#searchNameBtn").click(function() {
		getCourseByName(0, _pageSize);
	});
	
	// 获取增加课程负责人的界面
	$("#rightContainer").on("click",".director-add-teacher", function () { 
		$.ajax({ 
			 url: "/director/addTeacher/" + $(this).attr("courseId"), 
			 success: function(data){
				 
				 $("#modalLabel").text("编辑课程负责人");
				 $("#formContainer").html(data);
				 
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
		
	});
	
	// 获取增加指导老师的页面
	$("#rightContainer").on("click",".director-add-supervisor", function () { 
		$.ajax({ 
			 url: "/director/addSupervisors/" + $(this).attr("courseId"), 
			 success: function(data){
				 $("#modalLabel").text("编辑指导老师");
				 $("#formContainer").html(data);
				 
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	
	// 提交变更后，清空表单
	$("#submitEdit").click(function() {
		$.ajax({ 
			 url: "/director/addTeacher", 
			 type: 'POST',
			 data:$('#teacherForm').serialize(),
			 success: function(data){
				 
				 $('#teacherForm')[0].reset();  
				 
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
	
	$("#teacherDepartment").change(function() {
		$.ajax({ 
			 url: "/director/addSupervisor/" + $(this).children('option:selected').val(), 
			 success: function(data){
				 alert(123);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	
	});
	
});