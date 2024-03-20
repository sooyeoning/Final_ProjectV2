$(document).ready(function() {
	$('#loginModal').on('shown.bs.modal', function() {
		$('#username').focus();
	});

	$('#loginModal').on('hidden.bs.modal', function() {
		$('#username, #password').val('');
	});

	$('#loginModal button[type="button"]').click(function() {
		var username = $('#username').val();
		var password = $('#password').val();

		// 여기서 로그인 처리를 할 수 있습니다.

		$('#loginModal').modal('hide');
	});
});

