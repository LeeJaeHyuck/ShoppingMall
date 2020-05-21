/**
 * 
 */
function go_next() {
	if (document.formm.okon1[0].checked == true) {
		document.formm.action = "join_form";
		document.formm.submit();
	} else if (document.formm.okon1[1].checked == true) {
		alert("약관의 동의를 해야합니다.");
	}
}

function idcheck() {
	if (document.formm.id.value == "") {
		alert("아이디를 입력해주세요.");
		document.formm.id.focus();
		return;
	}
	
	// 중복된 아이디를 확인하는 윈도우를 띄운다.
	var url = "id_check_form?id=" + document.formm.id.value;
	
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbar=yes, resizable=no, width=400, height=200");
}

function idok() {
	document.formm.action = "id_check_confirmed";
	document.formm.submit;
}


// 동이름을 입력해서 우편번호 조회 창을 띄운다.
function post_zip(){
	var url = "find_zip_num";
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbar=yes, resizable=no, width=550, height=500" );
}

function go_save(){
	if(document.formm.id.value == ""){
		alert("아이디를 입력하세요.");
		document.formm.id.focus();
	} else if (document.formm.reid.value != document.formm.id.value){
		alert("아이디체크를 해주세요.");
		document.formm.id.focus();
	} else if (document.formm.pwd.value == ""){
		alert("비밀번호를 입력해주세요.");
		document.formm.pwd.focus();
	} else if (document.formm.pwdCheck.value == ""){
		alert("비밀번호체크를 입력해주세요.");
		document.formm.pwdCheck.focus();
	} else if (document.formm.pwd.value != document.formm.pwdCheck.value){
		alert("비밀번호가 다릅니다.");
		document.formm.pwd.focus();
	} else if (document.formm.name.value == ""){
		alert("이름을 입력해주세요.");
		document.formm.name.focus();
	} else if (document.formm.email.value == ""){
		alert("이메일을 입력해주세요.");
		document.formm.email.focus();
	}
	
}



















