'use strict';

$(document).ready(function () {

	$('#imageFile').on('change', function(e){

		if(!this.files.length){

			document.imageForm.inputName.value = '';
			$('#imagePreview').css('background-image', '');
			return;
		}

		var file = $(this).prop('files')[0];

		if(!/^image.*/.test(file.type)){

			document.imageForm.inputName.value = '';
			$('#imagePreview').css('background-image', '');
			return;
		}

		if(window.FileReader){

			var reader = new FileReader();
			reader.onload = function(){

				var imageData = reader.result;
				$('#imagePreview').css('background-image', 'url('+imageData+')');
			};
			reader.readAsDataURL(file);
		}
	});

	if(!window.FileReader){

		$('#imagePreview').hide();
	}

	//

	$("#ajaxSuccess").append("<span></span>").hide();
	$("#ajaxWarning").append("<span></span>").hide();

	$("#ajaxSuccess>button").on('click', function(e){

		$("#ajaxSuccess").hide();
		return false;
	});

	$("#ajaxWarning>button").on('click', function(e){

		$("#ajaxWarning").hide();
		return false;
	});

	$('#ajaxSubmit').on('click', function(e){

		$('#ajaxDialog').one('shown.bs.modal', function (e) {

			var form = $('#ajaxForm');
			var url = form.attr('action');
			var formData = new FormData(form[0]);
			$.ajax({
				method:"POST",
				url:url,
				data:formData,
				processData: false,
				contentType: false,
				dataType: "json",
				timeout: 3 * 1000
			})
			.then(
				function(responseJson) {

					$('#ajaxDialog').modal('hide');

					var status = responseJson.status;
					var message = responseJson.message;
					if("success" == status){

						$("#ajaxSuccess>span").html(message);
						$("#ajaxSuccess").show();
					}else if("warning" == status){

						$("#ajaxWarning>span").html(message);
						$("#ajaxWarning").show();
					}
				},
				function( jqXHR, textStatus, errorThrown) {

					$('#ajaxProgress>.progress-bar').addClass('bg-danger');
					$('#ajaxCancel').prop('disabled', false);
					$('#ajaxMessage').text(messages(MessageKeys.FAILURE));
					$('#ajaxDescription').html(messages(MessageKeys.STATUS) + '&nbsp;<strong>'+textStatus+'</strong>&nbsp;-&nbsp;' + messages(MessageKeys.ERROR) + '&nbsp;<strong>'+errorThrown+'</strong>');
				}
			);
		})

		$('#ajaxProgress>.progress-bar').removeClass('bg-danger');
		$('#ajaxCancel').prop('disabled', true);
		$('#ajaxMessage').text(messages(MessageKeys.PLEASE__WAIT));
		$('#ajaxDescription').text('-');

		$('#ajaxDialog').modal('show');

		return false;
	});

	bsCustomFileInput.init();
})