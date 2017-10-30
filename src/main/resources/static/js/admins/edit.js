/**
 * Bolg main JS.
 * Created by waylau.com on 2017/3/9.
 */
"use strict";
//# sourceURL=main.js

// DOM 加载完再执行
$(function() {
	
	function roles(){
	    var roles = $(" input[ name='roles' ] ").val();
		var length = roles.length-1;
		roles = roles.substr(1,length-1);
		var rolems = roles.split(',');
		var ROLE_SUPER = 0;
		$.each(rolems, function (index, role) {
	        if(role.trim()=='ROLE_ADMIN'){
	        	$("#ROLE_ADMIN").attr('checked', true);
	        }
	        if(role.trim()=='ROLE_SUPER'){
	        	$("#ROLE_SUPER").attr('checked', true);
	        }       
	        if(role.trim()=='ROLE_TEACHER'){
	        	$("#ROLE_TEACHER").attr('checked', true);
	        }       
	        if(role.trim()=='ROLE_STUDENT'){
	        	$("#ROLE_STUDENT").attr('checked', true);
	        }       
	        if(role.trim()=='ROLE_DIRECTOR'){
	        	$("#ROLE_DIRECTOR").attr('checked', true);
	        }       
	        if(role.trim()=='ROLE_USER'){
	        	$("#ROLE_USER").attr('checked', true);
	        }       
		});
	}
	
	roles();
});

