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
	
	// 提交变更后，清空表单
	$("#leftUpdate").click(function() {
		$.ajax({ 
			 url: "/super/LeaderResponsibilityEdit", 
			 data:{
				 "leader":$("#leader").val()
			 },
			 success: function(data){
				
				 
				 if (data.success) {
					 // 更新内容
					 toastr.info("更新成功");
					 getLeaderResponsibility();
				 } else {
					 toastr.error("更新失败");
					 getLeaderResponsibility();
				 }

		     },
		     error : function() {
		    	 toastr.error("更新失败!");
		    	 getLeaderResponsibility();
		     }
		 });
	});
	
	
	$("#leftReload").click(function() {
		getLeaderResponsibility();
	});
	
	
	
	
	function getLeaderResponsibility() {
		 $.ajax({ 
			 url: "/super/LeaderResponsibilityList", 
			
			 contentType : 'application/json',
			 
			 data:{
				 "leader":$("#leader").val()
			 },
			 success: function(data){
				 $("#leader").val(data.body.leader);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	}
	
	
	// 提交变更后，清空表单
	$("#rightUpdate").click(function() {
		$.ajax({ 
			 url: "/super/MemberResponsibilityEdit", 
			 data:{
				 "member":$("#member").val()
			 },
			 success: function(data){
				
				 
				 if (data.success) {
					 // 更新内容
					 toastr.info("更新成功");
					 getMemberResponsibility();
				 } else {
					 toastr.error("更新失败");
					 getMemberResponsibility();
				 }

		     },
		     error : function() {
		    	 toastr.error("更新失败!");
		    	 getMemberResponsibility();
		     }
		 });
	});
	
	
	$("#rightReload").click(function() {
		getMemberResponsibility();
	});
	
	function getMemberResponsibility() {
		 $.ajax({ 
			 url: "/super/MemberResponsibilityList", 
			
			 contentType : 'application/json',
			 
			 data:{
				 "member":$("#member").val()
			 },
			 success: function(data){
				 $("#member").val(data.body.member);
		     },
		     error : function() {
		    	 toastr.error("error!");
		     }
		 });
	}
	
	
	
});