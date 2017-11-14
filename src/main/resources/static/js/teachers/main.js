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
			 url: "/teachers/courseList", 
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
	
	
	
	// 上传文件
	$("#rightContainer").on("click",".teachers-upload-files", function () { 
		$.ajax({ 
			 url: "/teachers/upload/" + $(this).attr("courseId"), 
			 success: function(data){
				 console.log(data);
				 $("#modalLabel").text("上传文件");
				 $("#formContainer").html(data);
				 
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
		
	});

	// 编辑评分标准
	$("#rightContainer").on("click",".teachers-edit-standard", function () { 
		
		$.ajax({ 
			 url: "/teachers/editStandard/" + $(this).attr("courseId"), 
			 success: function(data){
				 $("#modalLabel").text("编辑课程评分标准");
				 $("#formContainer").html(data);
				 
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// 开启课程
	$("#rightContainer").on("click",".teachers-start-course", function () { 
		$.ajax({ 
			 url: "/teachers/startCourse/" + $(this).attr("courseId"), 
			 success: function(data){
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
	
	
});