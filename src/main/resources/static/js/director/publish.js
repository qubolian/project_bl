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
	
	  var add=1;//用于控制增加指导老师按钮点击次数
	  
	  var del=1;//用于控制减少指导老师按钮点击次数
	
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
		if(document.getElementById("teacherName4")) 
		{ 
			var a = "";
			for(var i = 0;i<5;i++){
			    var j = i+1;
				if($("#teacherName"+j).val() > 0){
					a = $("#teacherName"+j).val() + "," + a ;
				}
			}
			$.ajax({ 
				 url: "/director/selectSupervisor", 
				 //type: 'POST',
				 data:{
					 tid:a,
					 id:$("#courseId").val()
				 },
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
		} else{
			$.ajax({ 
				 url: "/director/selectTeacher", 
				 //type: 'POST',
				 data:{
					 id:$("#id").val(),
					 tid:$("#teacherId").val()
				 },
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
		}
	});
	
	//增加指导老师
	$("#add-A-supervisor").click(function() {
		var count= add + del;
		add=add+1;
		if(count>=6){
			toastr.error("最多增加5个指导老师");
			add=add-1;
		}
		else{
			$("#"+count).show();
		}
	});
	
	//减少指导老师
	$("#delete-A-supervisor").click(function() {
		del=del-1;
		var count= add + del;
		if(count<2){
			toastr.error("最少分配1个指导老师");
			del=del+1;
		}
		else{
			$("#"+count).css("display","none");
			$("#"+count).children().eq(2).val("0");
		}
	});
	
	//选择院系后获取教师列表
	function addTeacherName(select, deptId) {
		var a = new Array();
		for(var i = 0;i<5;i++){
			a[i] = $("#teacherName"+(i+1)).val();
		}
		$.ajax({ 
			 url: "/director/addSupervisor/" + deptId, 
			 success: function(data){
		
				 select.empty();
				 for(var i = 0;i<data.length;i++){
					if(data[i].id == a[0] || data[i].id == a[1] || data[i].id == a[2] || data[i].id == a[3] || data[i].id == a[4])
						{continue;}
					select.append("<option value='"+data[i].id+"'>"+data[i].teacherName+"</option>");
				 }
				 select.prepend("<option value='0' selected='selected'>======请选择教师======</option>");
			 
			 },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	}
	
	$("#teacherDepartment1").change(function() {
		var select = $(this).parent().children().eq(2);
		var deptId = $(this).children('option:selected').val();
		addTeacherName(select,deptId);
	});
	$("#teacherDepartment2").change(function() {
		var select = $(this).parent().children().eq(2);
		var deptId = $(this).children('option:selected').val();
		addTeacherName(select,deptId);
	});
	$("#teacherDepartment3").change(function() {
		var select = $(this).parent().children().eq(2);
		var deptId = $(this).children('option:selected').val();
		addTeacherName(select,deptId);
	});
	$("#teacherDepartment4").change(function() {
		var select = $(this).parent().children().eq(2);
		var deptId = $(this).children('option:selected').val();
		addTeacherName(select,deptId);
	});
	$("#teacherDepartment5").change(function() {
		var select = $(this).parent().children().eq(2);
		var deptId = $(this).children('option:selected').val();
		addTeacherName(select,deptId);
	});	
	
});