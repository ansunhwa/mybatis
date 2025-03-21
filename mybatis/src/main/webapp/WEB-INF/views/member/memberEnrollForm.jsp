 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>

<style>
	.outer {
		text-align: center;
	}
</style>
</head>
<body>
	 <jsp:include page="../common/menubar.jsp" />
	<div class="outer">
		<h1>회원가입</h1>

		<form method="post" action="insert.me" id="enrollForm" >
			<table align="center">
				<tr>
					<td>* ID</td>
					<td>
						<input name="userId" id="userid" required>
						<div id="checkResult" style="font-size: 0.8em; display: none;"></div>
					</td>
				</tr>
				<tr>
					<td>* PW</td>
					<td>
						<input name="userPwd" type="password" required>
					</td>
				</tr>
				<tr>
					<td>* NAME</td>
					<td><input name="userName"></td>
				</tr>
				<tr>
					<td>* EMAIL</td>
					<td><input name="email"></td>
				</tr>
				<tr>
					<td>* GENDER</td>
					<td><input name="gender" type="radio" value="M">남
						<input name="gender" type="radio" value="F">여
					</td>
				</tr>
				<tr>
					<td>* BIRTHDAY</td>
					<td><input name="birthday" placeholder="  ex.000000"></td>
				</tr>
				<tr>
					<td>*PHONE</td>
					<td><input name="phone" placeholder="-포함"></td>
				</tr>
				<tr>
					<td>*ADDRESS</td>
					<td><input name="address" size="40" id="addr"></td>
				</tr>
				<tr>

				</tr>
				<tr>
					<td colspan="2" >
						<input type="submit" value="회원가입"> &emsp;
						<input type="reset" value="초기화">
					</td>
				</tr>

			</table>
		</form>
	</div>
	
	<script>
	   $(() => {
	         const $input = $('#userid');
	         $input.keyup(function() {
	            if($input.val().length >= 5) {
	               $.ajax({
	                  url: "idCheck.me",
	                  data:{checkId: $input.val()},
	                  success: function(result) {
	                     if(result == 'idN') {
	                        $('#checkResult').show();
	                        $('#checkResult').css('color','red').text('사용할 수 없습니다');
	                        $('#enrollForm :submit').attr('disabled', true);
	                     } else {
	                        $('#checkResult').show();
	                        $('#checkResult').css('color','green').text('멋진 아이디네요');
	                        $('#enrollForm :submit').attr('disabled', false);
	                     }
	                  },
	                  error: function() {
	                     console.log("아이디 중복 ajax 통신 실패");
	                  }
	               })
	            } else {
	               $('#checkResult').hide();
	               $('#enrollForm :submit').attr('disabled', true);
	            }
	         })
	      })

	</script>
</body>
</html>