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
	
    CKEDITOR.replace( 'leader',{ height: '400px', width: 'auto' } );
    CKEDITOR.replace( 'member',{ height: '400px', width: 'auto' } );
    

	
	// 提交变更后，清空表单
	$("#Update").on("click",function(){
		var leader = CKEDITOR.instances.leader.getData();
		var member = CKEDITOR.instances.member.getData();
		$.ajax({ 
			 url: "/super/leaderMemberResponsibilityEdit", 
			 data:{
				 "leader":leader,
				 "member":member
			 },
			 success: function(data){

				 if (data.success) {
					 // 更新内容
					 toastr.info("更新成功");
					 //getLeaderMemberResponsibility();
				 } else {
					 toastr.error("更新失败");
					 getLeaderMemberResponsibility();
				 }

		     },
		     error : function() {
		    	 toastr.error("更新失败!");
		    	 getLeaderMemberResponsibility();
		     }
		 });
	});
	
	
	$("#Reload").on("click",function(){
		getLeaderMemberResponsibility();
	});
	
	
	
	
	function getLeaderMemberResponsibility() {
		 $.ajax({ 
			 url: "/super/leaderMemberResponsibilityList", 
			
			 contentType : 'application/json',
			 
			 data:{
				 /*"leader":$("#leader").val(),
				 "member":$("#member").val()*/
			 },
			 success: function(data){
				 console.log(data);
				 CKEDITOR.instances.leader.setData(data.body.leaderResponsibility);
				 CKEDITOR.instances.member.setData(data.body.memberResponsibility);
				/* $("#leader").val(data.body.leaderResponsibility);
				 $("#member").val(data.body.memberResponsibility);*/
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	}
	
	
	
	
	
});