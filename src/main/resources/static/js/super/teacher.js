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
	
	
	
	// 根据年级、页面索引、页面大小获取组队方法列表
	function getTeacherByTeacherName(pageIndex, pageSize) {
		
		 $.ajax({ 
			 url: "/super/teacherList", 
			 contentType : 'application/json',
			 data:{
				 "async":true, 
				 "pageIndex":pageIndex,
				 "pageSize":pageSize,
				 "teacherName":$("#searchName").val()
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
		getTeacherByTeacherName(pageIndex, pageSize);
		_pageSize = pageSize;
	});
   
	// 搜索
	$("#searchNameBtn").on("click",function(){
		
		getTeacherByTeacherName(0, _pageSize);
	});
	
	
	// 获取添加用户的界面
	$("#addTeacher").on("click",function(){
		$.ajax({ 
			 url: "/super/addTeacher", 
			 success: function(data){
				
				 $("#formContainer").html(data);
		     },
		     error : function(data) {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// 获取编辑信息的界面
	$("#rightContainer").on("click",".super-edit-teacher", function () { 
		$.ajax({ 
			 url: "/super/editTeacher/" + $(this).attr("TeacherId"), 
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

		$.ajax({ 
			 url: "/super/addTeacher", 
			 type: 'POST',
			 data:$('#teacherForm').serialize(),
			 success: function(data){
				 $('#teacherForm')[0].reset();  
				 
				 if (data.success) {
					 // 从新刷新主界面
					 getTeacherByTeacherName(0, _pageSize);
				 } else {
					 toastr.error(data.message);
				 }
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// 删除信息内容
	$("#rightContainer").on("click",".super-delete-teacher", function () { 
		// 获取 CSRF Token 
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		$.ajax({ 
			 url: "/super/teacher/" + $(this).attr("teacherId") , 
			 type: 'DELETE', 
			 async:false,
			 beforeSend: function(request) {
                 request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token 
             },
			 success: function(data){
				 if (data.success) {
					 // 从新刷新主界面
					 getTeacherByTeacherName(0, _pageSize);
				 } else {
					 toastr.error(data.message);
				 }
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	$("#deleteTeacher").on("click",function(){
		$("input[name='boxs']:checked").each(function(){
			// 获取 CSRF Token 
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfHeader = $("meta[name='_csrf_header']").attr("content");
			$.ajax({ 
				 url: "/super/teacher/" + $(this).val() , 
				 type: 'DELETE', 
				 async:false,
				 beforeSend: function(request) {
	                 request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token 
	             },
				 success: function(data){
					 if (data.success) {
						 // 从新刷新主界面
						 getTeacherByTeacherName(0, _pageSize);
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
	
	
	$("#undoTeacher").on("click",function(){
		$("input[name='boxs']:checked").each(function(){
			// 获取 CSRF Token 
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfHeader = $("meta[name='_csrf_header']").attr("content");
			$.ajax({ 
				 url: "/super/undoTeacher/" + $(this).val() , 
				 type: 'POST', 
				 async:false,
				 beforeSend: function(request) {
	                 request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token 
	             },
				 success: function(data){
					 if (data.success) {
						 // 从新刷新主界面
						 getTeacherByTeacherName(0, _pageSize);
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