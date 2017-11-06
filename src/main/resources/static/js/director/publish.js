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
	$("#submitEdit").click(function( ) {
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
	});
	
/*	//选择院系后获取教师列表
	function addTeacherName() {
		var item = $(this).parent().children().eq(2);
		$.ajax({ 
			 url: "/director/addSupervisor/" + $(this).children('option:selected').val(), 
			 success: function(data){
				 
				 item.empty();
				 for(var i = 0;i<data.length;i++){
					 item.append("<option value='"+data[i].id+"'>"+data[i].teacherName+"</option>");
				 }
				 item.prepend("<option value='0' selected='selected'>======请选择教师======</option>");

			 },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	}
	
	$("#teacherDepartment").on("change",function() {
		addTeacherName(1);
	});
	$("#teacherDepartment2").change(function() {
		addTeacherName(2);
	});
	$("#teacherDepartment3").change(function() {
		addTeacherName(3);
	});
	$("#teacherDepartment4").change(function() {
		addTeacherName();
	});
	$("#teacherDepartment5").change(function() {
		addTeacherName();
	});*/
	

	
		/*//增加指导老师
		$("#add-A-supervisor").click(function() {
		
		var item = $("#form-group");
		var count= add + del;
		var str="";
		$.ajax({ 
			 url: "/director/addASupervisor/", 
			 //data:"tid":tid,
			 success: function(data){
				add=add+1;
				if(count>=6){
					toastr.error("最多增加5个指导老师");
					add=add-1;
				}
				else{
					for(var i = 0;i<data.length;i++){
						var s="<option value='"+data[i].id+"' text='"+data[i].department+"'></option>";
						str += s;
					}
					item.append("<div class='input-group col-md-13 col-xl-13' >" +
						"<label class='col-form-label'>"+count+".&nbsp;&nbsp;&nbsp;</label>" +
						"<select id='teacherDepartment"+count+"' name='department.id'  class='form-control'>" +
						"<option value='-1' selected='selected'>======请选择院系======</option>"+
						str+
						"</select>"+
						"&nbsp;&nbsp;&nbsp;&nbsp;" +
						"<select id='teacherName"+count+"' name='teacher.id'  class='form-control'>  " +
						"<option value='-1' selected='selected'>======请选择教师======</option>" +
						"</select>" +
						"</div>"+
						"<script>" +
						"$('#teacherDepartment"+count+"').change(function() {" +
							"var item = $(this).parent().children().eq(2);"+
							"$.ajax({ "+
								 "url: '/director/addSupervisor/' + $(this).children('option:selected').val(), "+
								 "success: function(data){"+
									 "item.empty();"+
									 "for(var i = 0;i<data.length;i++){"+
									 "item.append('<option value='data[i].id' text='data[i].department'></option>');}"+
									 "item.prepend('<option value='0' selected='selected'>======请选择教师======</option>');"+
								 "},"+
							     "error : function() {"+
							    	 "toastr.error('error!');"+
							     "}"+
		 					"});" +
						"});"+
						"</script>"
						);
				}
			 },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	});*/
		
		
		$("#delete-A-supervisor").click(function() {
			var count= add + del;
			del=del-1;
			if(count<=2){
				toastr.error("最少分配1个指导老师");
				del=del+1;
			}
			else{
				$("#form-group").children("div:last").remove();
			}
		});
	
});