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
	
	//获取指导老师页面时插入已指派指导老师
	function getSelects() {
		
		var deptmentSelect = new Array(); 
		var teacherSelect = new Array(); 
		deptmentSelect = $("#deptmentSelect").val().split(",");
		teacherSelect = $("#teacherSelect").val().split(",");
		
		if(deptmentSelect[0] > 0){
			for(var i = 0;i<(deptmentSelect.length-1);i++){
				$("#"+(i+1)).show();
				$("#teacherDepartment"+(i+1)).val(deptmentSelect[i]);
				
				var id = $("#teacherList"+i+" option").map(function(){return $(this).val();}).get();
				var name = $("#teacherList"+i+" option").map(function(){return $(this).text();}).get();
				for(var j = 0;j<id.length;j++){
					if(id[j] != teacherSelect[i] && ( id[j] == teacherSelect[0] || id[j] == teacherSelect[1] || id[j] == teacherSelect[2] || id[j] == teacherSelect[3] || id[j] == teacherSelect[4] )){
						continue;}
					$("#teacherName"+(i+1)).append("<option value='"+id[j]+"'>"+name[j]+"</option>");
				}
				$("#teacherName"+(i+1)).val(teacherSelect[i]);
				
			}
		}
	}

	getSelects();
	
	//增加指导老师
	$("#add-A-supervisor").click(function() {
		var count= add + del;
		add=add+1;
		if($("#teacherList0 option").length > 0){
			var deptmentSelect = new Array(); 
			deptmentSelect = $("#deptmentSelect").val().split(",");
			count += (deptmentSelect.length-2);
		}
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
		if($("#teacherList0 option").length > 0){
			var deptmentSelect = new Array(); 
			deptmentSelect = $("#deptmentSelect").val().split(",");
			count += (deptmentSelect.length-2);
		}
		if(count<2){
			toastr.error("最少分配1个指导老师");
			del=del+1;
		}
		else{
			$("#"+count).css("display","none");
			$("#"+count).children().eq(1).val("0");
			$("#"+count).children().eq(2).empty();
			$("#"+count).children().eq(2).append("<option value='0' selected='selected'>======请选择教师======</option>");
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
				 var teacher = JSON.parse(data.body);
				 $("#teacherName"+select).empty();
				 for(var i = 0;i<teacher.length;i++){
					if(teacher[i].id == a[0] || teacher[i].id == a[1] || teacher[i].id == a[2] || teacher[i].id == a[3] || teacher[i].id == a[4]){
						continue;}
					$("#teacherName"+select).append("<option value='"+teacher[i].id+"'>"+teacher[i].teacherName+"</option>");
				 }
				 $("#teacherName"+select).prepend("<option value='0' selected='selected'>======请选择教师======</option>");
			 
			 },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	}
	
	$("#teacherDepartment1").change(function() {
		var select = 1;
		var deptId = $(this).children('option:selected').val();
		addTeacherName(select,deptId);
	});
	$("#teacherDepartment2").change(function() {
		var select = 2;
		var deptId = $(this).children('option:selected').val();
		addTeacherName(select,deptId);
	});
	$("#teacherDepartment3").change(function() {
		var select = 3;
		var deptId = $(this).children('option:selected').val();
		addTeacherName(select,deptId);
	});
	$("#teacherDepartment4").change(function() {
		var select = 4;
		var deptId = $(this).children('option:selected').val();
		addTeacherName(select,deptId);
	});
	$("#teacherDepartment5").change(function() {
		var select = 5;
		var deptId = $(this).children('option:selected').val();
		addTeacherName(select,deptId);
	});	
	
	//选择教师后刷新教师列表
	function deleteTeacherName(teacherName, deptId, thisTeacher) {
		for(var i = 0;i<5;i++){
			if($("#teacherDepartment"+(i+1)).children('option:selected').val() == deptId){
				for(var j = 0 ;j<$("#teacherName"+(i+1)).children().length;j++){
					if((i+1) != thisTeacher){
						if($("#teacherName"+(i+1)).children().eq(j).val() == teacherName){
							$("#teacherName"+(i+1)).children().eq(j).remove();
						}
					}					
				}
			}
		}
	}
	
	$("#teacherName1").change(function() {
		var deptId = $(this).parent().children().eq(1).val();
		var teacherName = $(this).children('option:selected').val();
		var thisTeacher = 1;
		deleteTeacherName(teacherName,deptId, thisTeacher);
	});
	$("#teacherName2").change(function() {
		var deptId = $(this).parent().children().eq(1).val();
		var teacherName = $(this).children('option:selected').val();
		var thisTeacher = 2;
		deleteTeacherName(teacherName,deptId, thisTeacher);
	});
	$("#teacherName3").change(function() {
		var deptId = $(this).parent().children().eq(1).val();
		var teacherName = $(this).children('option:selected').val();
		var thisTeacher = 3;
		deleteTeacherName(teacherName,deptId, thisTeacher);	});
	$("#teacherName4").change(function() {
		var deptId = $(this).parent().children().eq(1).val();
		var teacherName = $(this).children('option:selected').val();
		var thisTeacher = 4;
		deleteTeacherName(teacherName,deptId, thisTeacher);	});
	$("#teacherName5").change(function() {
		var deptId = $(this).parent().children().eq(1).val();
		var teacherName = $(this).children('option:selected').val();
		var thisTeacher = 5;
		deleteTeacherName(teacherName,deptId, thisTeacher);	});	
	
	
	
	
});