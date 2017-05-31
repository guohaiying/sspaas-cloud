//用户合法性校验
function checkLoginName(value) {
	if (value == '') {
		document.getElementById('msgloginName').hidden = false;
		document.getElementById('msgloginName').textContent = "请输入登录名";
		document.getElementById('loginName').value = '';
	} else {
		$.ajax({
			url : './usermng/background/checkName?loginName=' + value,
			contentType : 'application/json',
			dataType : 'json',
			success : function(result) {
				if (result.ERR == 'no') {
					document.getElementById('msgloginName').hidden = false;
					document.getElementById('loginName').value = ''
					$("#msgloginName").html(result.MSG).show(100).delay(1000)
							.hide(500);
				} else {
					document.getElementById('msgloginName').hidden = false;
					document.getElementById('loginName').value = value
					$("#msgloginName").html(result.MSG).show(100).delay(1000)
							.hide(500);
				}
			}
		});
	}
}
// 密码校验
function checkpassword1(value, userid) {
	// alert(value);
	// alert(userid);
	$.ajax({
		url : '/user/checkpassword?userId=' + userid + "&passWord=" + value,
		contentType : 'application/json',
		dataType : 'json',
		success : function(result) {
			if (result.ERR == 'ok') {
				document.getElementById('msgPassword').hidden = false;
				document.getElementById('password').value = value;
				$("#msgPassword").html(result.MSG).show(100).delay(1000).hide(
						500);
			} else {
				document.getElementById('msgPassword').hidden = false;
				document.getElementById('password').value = "";
				$("#msgPassword").html(result.MSG).show(100).delay(1000).hide(
						500);
			}
		}
	});

}

// 手机号合法性校验
function checkMoile(value) {

	var ss = value;
	var re = /^1[0-9]{10}$/

	if (!re.test(ss)) {
		if ("" == ss) {
		} else {
			document.getElementById('msg').hidden = false;
			document.getElementById('msg').textContent = "请输入正确的手机号码！";
			document.getElementById('mobile').value = "";
		}
	} else {
		$.ajax({
			url : '/user/checkPhone.do?mobile=' + ss,
			contentType : 'application/json',
			dataType : 'json',
			success : function(result) {
				if (result.ERR == 'ok') {
					document.getElementById('msg').hidden = false;
					document.getElementById('mobile').value = value;
					$("#msg").html(result.MSG).show(100).delay(1000).hide(500);
				} else {
					document.getElementById('msg').hidden = false;
					document.getElementById('mobile').value = '';
					$("#msg").html(result.MSG).show(100).delay(1000).hide(500);
				}
			}

		});

	}
}
// 密码合法性校验
function checkPassword(value) {

	var ss = value;
	var re = /^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,22}$/
	if (!re.test(ss)) {
		document.getElementById('msgPassword').hidden = false;
		document.getElementById('msgPassword').textContent = "密码格式不正确,请检查密码长度";
		document.getElementById('password').value = '';
	} else {
		document.getElementById('msgPassword').hidden = false;
		document.getElementById('msgPassword').textContent = "密码可以使用";
		document.getElementById('password').value = value;
	}
}
// 姓名校验
function checkName(value) {
	var ss = value;
	var re = /^[\u4e00-\u9fa5 ]{2,20}$|^[a-zA-Z]{1,30}$/gi
	if (!re.test(ss)) {
		document.getElementById('msgfullname').hidden = false;
		document.getElementById('msgfullname').textContent = "请输入合法的姓名!";
		document.getElementById('fullname').value = ""
	} else {
		$.ajax({
			url : '/user/checkFullName.do?fullname=' + ss,
			contentType : 'application/json',
			dataType : 'json',
			success : function(result) {
				if (result.ERR == 'true') {
					document.getElementById('msgfullname').hidden = false;
					document.getElementById('fullname').value = value
					$("#msgfullname").html(result.MSG).show(100).delay(1000)
							.hide(500);
				} else {
					document.getElementById('msgfullname').hidden = false;
					document.getElementById('fullname').value = ""
					$("#msgfullname").html(result.MSG).show(100).delay(1000)
							.hide(500);
				}
			}
		});

	}
}
// E-mail合法性校验
function checkEmai(value) {
	var ss = value;
	var re = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
	if (!re.test(ss)) {
		document.getElementById('msgEmai').hidden = false;
		document.getElementById('msgEmai').textContent = "请输入正确的邮箱";
		document.getElementById('email').value = "";
	} else {
		$.ajax({
			url : '/user/checkEmail.do?email=' + ss,
			contentType : 'application/json',
			dataType : 'json',
			success : function(result) {
				if (result.ERR == 'true') {
					document.getElementById('msgEmai').hidden = false;
					/*
					 * document.getElementById('mobile').value="不可以使用！"
					 * document.getElementById('mobile')
					 */
					document.getElementById('email').value = value
					$("#msgEmai").html(result.MSG).show(100).delay(1000).hide(
							500);
				} else {
					document.getElementById('msgEmai').hidden = false;
					document.getElementById('email').value = "";
					$("#msgEmai").html(result.MSG);
				}
			}

		});
	}
}
function MyFunctionPassword() {
	document.getElementById('password').value = '';
	document.getElementById('msgPassword').hidden = true;

}
function MyFunctionLogin() {
	document.getElementById('loginName').value = '';
	document.getElementById('msgloginName').hidden = true;

}
function MyFunctionFullName() {
	document.getElementById('name').value = '';
	document.getElementById('msgfullname').hidden = true;

}
function MyFunctionPhone() {
	document.getElementById('mobile').value = '';
	document.getElementById('msg').hidden = true;

}
function MyFunctionEmail() {
	document.getElementById('email').value = '';
	document.getElementById('msgEmai').hidden = true;

}

// 至少一项必输项
function myCheck() {
	for (var i = 0; i < document.form1.elements.length - 1; i++) {
		if (document.form1.elements[i].value == "") {
			/* alert("当前表单不能有空项"); */
			$("#notnull").html("当前表单不能有空项");
			document.form1.elements[i].focus();
			return false;
		}
	}
	return true;

}
