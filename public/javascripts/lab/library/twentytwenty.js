'use strict';

$(document).ready(function () {

	$("#twentytwentyContainer").twentytwenty({
		default_offset_pct: 0.95,
		before_label: Messages(MessageKeys.BEFORE),
		after_label: Messages(MessageKeys.AFTER)
	});
});