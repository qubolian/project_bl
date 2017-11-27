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
			 url: "/super/leaderMemberResponsibilityList", 
			
			 contentType : 'application/json',
			 
			 data:{
				 "leader":$("#leader").val(),
				 "member":$("#member").val()
			 },
			 success: function(data){
				 console.log(data);
				 CKEDITOR.instances.leader.setData(data.body.leaderResponsibility);
				 CKEDITOR.instances.member.setData(data.body.memberResponsibility);
				 $("#leader").val(data.body.leaderResponsibility);
				 $("#member").val(data.body.memberResponsibility);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	}
	
	
	
	
	
});