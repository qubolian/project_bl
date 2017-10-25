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
	
	
	
	// 根据用户名、页面索引、页面大小获取用户列表
	function getDepartmentListByDepartment(pageIndex, pageSize) {
		
		 $.ajax({ 
			 url: "/super/departmentListList", 
			 contentType : 'application/json',
			 data:{
				 "async":true, 
				 "pageIndex":pageIndex,
				 "pageSize":pageSize,
				 "department":$("#searchName").val()
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
		getDepartmentListByDepartment(pageIndex, pageSize);
		_pageSize = pageSize;
	});
   
	// 搜索
	$("#searchNameBtn").click(function() {
	
		getDepartmentListByDepartment(0, _pageSize);
	});
	
	
	// 获取添加用户的界面
	$("#addDepartmentList").click(function() {
		$.ajax({ 
			 url: "/super/addDepartmentList", 
			 success: function(data){
				
				 $("#formContainer").html(data);
		     },
		     error : function(data) {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// 获取编辑信息的界面
	$("#rightContainer").on("click",".super-edit-departmentList", function () { 
		$.ajax({ 
			 url: "/super/editDepartmentList/" + $(this).attr("departmentListId"), 
			 success: function(data){
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
			 url: "/super/addDepartmentList", 
			 type: 'POST',
			 data:$('#departmentListForm').serialize(),
			 success: function(data){
				 $('#departmentListForm')[0].reset();  
				 
				 if (data.success) {
					 // 从新刷新主界面
					 getDepartmentListByDepartment(0, _pageSize);
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
	$("#rightContainer").on("click",".super-delete-departmentList", function () { 
		// 获取 CSRF Token 
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		$.ajax({ 
			 url: "/super/departmentList/" + $(this).attr("departmentListId") , 
			 type: 'DELETE', 
			 beforeSend: function(request) {
                 request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token 
             },
			 success: function(data){
				 if (data.success) {
					 // 从新刷新主界面
					 getDepartmentListByDepartment(0, _pageSize);
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