/*!
  * Bolg main JS.
 * 
 * @since: 1.0.0 2017/3/9
 * @author Way Lau <https://waylau.com>
 */
"use strict";
//# sourceURL=main.js
$('#resumeName').attr('href','/supervisor/downloadResume/');
function showFileName(file) {
	var a = file.split("\\");
	$("#fileName").val(a[2]);
}
// DOM 加载完再执行
$(function() {
	
    CKEDITOR.replace( 'expert',{ height: '400px', width: 'auto' } );
    CKEDITOR.replace( 'expectStudent',{ height: '400px', width: 'auto' } );
	
	function getTeacherInformation() {
		 $.ajax({ 
			 url: "/supervisor/teacherInformation", 
			 contentType : 'application/json',
			 success: function(data){
				 console.log(data);
				 CKEDITOR.instances.expert.setData(data.body.expert);
				 CKEDITOR.instances.expectStudent.setData(data.body.expectStudent);
				 $("#expert").val(data.body.expert);
				 $("#expectStudent").val(data.body.expectStudent);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	}
	
    getTeacherInformation();

	
	// 提交变更后，清空表单
	$("#Update").on("click",function(){
		var expert = CKEDITOR.instances.expert.getData();
		var expectStudent = CKEDITOR.instances.expectStudent.getData();
		$.ajax({ 
			 url: "/supervisor/updateTeacherInformation", 
			 data:{
				 "expert":expert,
				 "expectStudent":expectStudent
			 },
			 success: function(data){

				 if (data.success) {
					 // 更新内容
					 toastr.info("更新成功");
					 //getTeacherInformation();
				 } else {
					 toastr.error("更新失败");
					 getTeacherInformation();
				 }

		     },
		     error : function() {
		    	 toastr.error("更新失败!");
		    	 getTeacherInformation();
		     }
		 });
	});
	
	
	$("#Reload").on("click",function(){
		getTeacherInformation();
	});
	
	
	// 上传履历表
	$("#uploadResume").on("click",function(){
		var div = $('#1');
		var del = $(".supervisor-delete-resume");
		var id = $("#id1").val();
		if($("#resumelabel").length > 0){
			toastr.error("请先删除现有履历表！");
		}else if($("#resume").val() == ""){
			toastr.error("请先选择履历表！");
		}else{
			var formData = new FormData($( "#resumeForm" )[0]);
			$.ajax({ 
				 url: "/supervisor/uploadResume", 
				 type: 'POST',
				 data:formData,
				 cache: false,  
		         processData: false,  
		         contentType: false,
				 success: function(data){
					 $('#resumeForm')[0].reset(); 
					 if (data.success) {
						 div.prepend(
									 "<label id='resumelabel' class='col-form-label'><strong style='color:red'>*&thinsp;</strong><a href='/supervisor/downloadResume/' id='resumeName'>"+data.body+"</a></label>" 
						 );
						 del.show();
						 
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
	
	// 删除教学进度表
	$(".supervisor-delete-resume").on("click",function(){
		$.ajax({ 
			 url: "/supervisor/deleteResume", 
			 success: function(data){
				 
				 if (data.success) {
					 // 从新刷新主界面
					 $("#resumelabel").remove();
					 $(".supervisor-delete-resume").hide();
					 
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