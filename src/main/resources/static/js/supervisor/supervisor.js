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
	
    CKEDITOR.replace( 'expert',{ height: '400px', width: 'auto' } );
    CKEDITOR.replace( 'expectStudent',{ height: '400px', width: 'auto' } );
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
	
	
	
	
	
});