/*!
  * Bolg main JS.
 * 
 * @since: 1.0.0 2017/3/9
 * @author Way Lau <https://waylau.com>
 */
"use strict";
//# sourceURL=main.js
var a = $("input[name='boxs']").length;//用于全选

function allboxs() {
	var nn = $("#allboxs").is(":checked"); //判断th中的checkbox是否被选中，如果被选中则nn为true，反之为false
	if(nn == true) {
		$("input[name='boxs']").prop("checked",true); 
    }else{
    	$("input[name='boxs']").prop("checked",false); 
    }
}

function boxs() {
	var b = $("input[name='boxs']:checked").length;
	
	if(a==b){
		 $("#allboxs").prop("checked",true); 
	}else{
		 $("#allboxs").prop("checked",false); 
	}
}
// DOM 加载完再执行
$(function() {
	var _pageSize; // 存储用于搜索
	
	
	
	// 根据公告标题、页面索引、页面大小获取用户列表
	function getCourseByName(pageIndex, pageSize) {
		
		 $.ajax({ 
			 url: "/director/courseList", 
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
	$("#searchNameBtn").on("click",function(){
		getCourseByName(0, _pageSize);
	});
	
	
	// 获取添加用户的界面
	$("#addCourse").on("click",function(){
		$.ajax({ 
			 url: "/director/addCourse", 
			 success: function(data){
				
				 $("#formContainer").html(data);
		     },
		     error : function(data) {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// 获取编辑信息的界面
	$("#rightContainer").on("click",".director-edit-course", function () { 
		$.ajax({ 
			 url: "/director/editCourse/" + $(this).attr("courseId"), 
			 success: function(data){
				 
				 $("#formContainer").html(data);
				 
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
		
	});
	
	
	
	// 提交变更后，清空表单
	$("#submitEdit").on("click",function(){
		if($("#upperLimit").val() > $("#lowerLimit").val()){
			$.ajax({ 
				 url: "/director/addCourse", 
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
		}else{
			toastr.error("上限不能低于下限");
		}
	});

	
	// 删除信息内容
	$("#rightContainer").on("click",".director-delete-course", function () { 
		// 获取 CSRF Token 
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		$.ajax({ 
			 url: "/director/course/" + $(this).attr("courseId") , 
			 type: 'DELETE', 
			 async:false,
			 beforeSend: function(request) {
                 request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token 
             },
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
	
	// 发布课程
	$("#rightContainer").on("click",".director-publish-course", function () { 
		$.ajax({ 
			 url: "/director/publishCourse/" + $(this).attr("courseId"), 
			 async:false,
			 success: function(data){
				 if (data.success) {
					 // 从新刷新主界面
					 getCourseByName(0, _pageSize);
					 toastr.info("发布成功！");
				 } else {
					 toastr.error(data.message);
				 }
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
		
	});
	
	//批量删除课程
	$("#deleteCourse").on("click",function(){
		$("input[name='boxs']:checked").each(function(){
			// 获取 CSRF Token 
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfHeader = $("meta[name='_csrf_header']").attr("content");
			$.ajax({ 
				 url: "/director/course/" + $(this).val() , 
				 type: 'DELETE', 
				 async:false,
				 beforeSend: function(request) {
	                 request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token 
	             },
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
	
	//批量发布课程
	$("#publishCourse").on("click",function(){
		$("input[name='boxs']:checked").each(function(){
			$.ajax({ 
				 url: "/director/publishCourse/" + $(this).val(), 
				 async:false,
				 success: function(data){
					 if (data.success) {
						 // 从新刷新主界面
						 getCourseByName(0, _pageSize);
						 toastr.info("发布成功！");
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
	
});