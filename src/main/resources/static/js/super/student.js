/*!
  * Bolg main JS.
 * 
 * @since: 1.0.0 2017/3/9
 * @author Way Lau <https://waylau.com>
 */
"use strict";
//# sourceURL=main.js

function showFileName(file) {
	var a = file.split("\\");
	$("#fileName").val(a[2]);
}

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
	function getStudentByName(pageIndex, pageSize) {
		
		 $.ajax({ 
			 url: "/super/studentList", 
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
		getStudentByName(pageIndex, pageSize);
		_pageSize = pageSize;
	});
   
	// 搜索
	$("#searchNameBtn").on("click",function(){
		
		getStudentByName(0, _pageSize);
	});
	
	
	// 获取添加用户的界面
	$("#addStudent").on("click",function(){
		$.ajax({ 
			 url: "/super/addStudent", 
			 success: function(data){
				
				 $("#formContainer").html(data);
		     },
		     error : function(data) {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// 获取编辑信息的界面
	$("#rightContainer").on("click",".super-edit-student", function () { 
		$.ajax({ 
			 url: "/super/editStudent/" + $(this).attr("id"), 
			 success: function(data){
				 $("#formContainer").html(data);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
		
	});
	
	
	// 删除信息内容
	$("#rightContainer").on("click",".super-delete-student", function () { 
		// 获取 CSRF Token 
		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		$.ajax({ 
			 url: "/super/student/" + $(this).attr("id") , 
			 type: 'DELETE', 
			 async:false,
			 beforeSend: function(request) {
                 request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token 
             },
			 success: function(data){
				 if (data.success) {
					 // 从新刷新主界面
					 getStudentByName(0, _pageSize);
				 } else {
					 toastr.error(data.message);
				 }
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// 获取上传学生名单
	$("#uploadStudentList").on("click",function(){
		$.ajax({ 
			 url: "/super/uploadStudentList/", 
			 success: function(data){
				 $("#modalLabel").text("上传学生名单");
				 $("#formContainer").html(data);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});
	
	// 提交变更后，清空表单
	$("#submitEdit").on("click",function(){
		
		if($("#id").length > 0){
			$.ajax({ 
				 url: "/super/addStudent", 
				 type: 'POST',
				 data:$('#studentForm').serialize(),
				 success: function(data){
					 $('#studentForm')[0].reset();  
					 
					 if (data.success) {
						 // 从新刷新主界面
						 getStudentByName(0, _pageSize);
					 } else {
						 toastr.error(data.message);
					 }
			     },
			     error: function() {
			    	 toastr.error("error!");
			     }
			 });
		}else{
			var formData = new FormData($( "#uploadStudentForm" )[0]);
			$.ajax({ 
				 url: "/super/uploadStudent", 
				 type: 'POST',
				 data:formData,
				 cache: false,  
		         processData: false,  
		         contentType: false,
				 success: function(data){
					 $('#uploadStudentForm')[0].reset();  
					 
					 if (data.success) {
						 // 从新刷新主界面
						 getStudentByName(0, _pageSize);
						 toastr.info("更新成功！");
					 } else {
						 toastr.error(data.message);
					 }

			     },
			     error : function() {
			    	 toastr.error("error!");
			     }
			 });
			toastr.info("正在更新学生名单，请等待！");
			var startTime = new Date().getTime();
			var interval = setInterval(function(){
					if(new Date().getTime() - startTime > 60000){
					clearInterval(interval);
					return;
					}
					getStudentByName(0, _pageSize);
					}, 1000
				);
			
		}
	});
	



	
	
	$("#deleteStudent").on("click",function(){
		$("input[name='boxs']:checked").each(function(){
			// 获取 CSRF Token 
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfHeader = $("meta[name='_csrf_header']").attr("content");
			$.ajax({ 
				 url: "/super/student/" + $(this).val(), 
				 type: 'DELETE', 
				 async:false,
				 beforeSend: function(request) {
	                 request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token 
	             },
				 success: function(data){
					 if (data.success) {
						 // 从新刷新主界面
						 getStudentByName(0, _pageSize);
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