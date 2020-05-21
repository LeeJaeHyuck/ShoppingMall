/**
 * 
 */
function find_id_form(){
	var url="find_id_form";
	
	window.open(url, "_blank_1", "toolbar=no, menubar=no, scrollbars=yes, resizable=no, width=500, height=550, top=300, left=300");
}

function findMemberId(){
	if(document.findId.name.value == ""){
		alert("이름을 입력해주세요.");
		document.findId.name.focus();
	} else if (document.findId.email.value == ""){
		alert("이메일을 입력해주세요.");
		document.findId.email.focus();
	} else {
		document.findId.action = "find_id";
		document.findId.submit();
	}
}

function findPassword() {
	if(document.findPw.id.value == "") {
		alert("아이디를 입력해주세요.");
		document.findPw.id.focus();	
	} else if(document.findPw.name.value == ""){
		alert("이름을 입력해주세요.");
		document.findPw.name.focus();
	} else if (document.findPw.email.value == ""){
		alert("이메일을 입력해주세요.");
		document.findPw.email.focus();
	} else {
		document.findPw.action = "find_password";
		document.findPw.submit();
	}
}