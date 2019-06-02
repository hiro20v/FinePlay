'use strict';

var MessageKeys = {
	ABANDONED: "abandoned",
	ABOUT__X: "about_x",
	ACCESS: "access",
	ADD: "add",
	ADOBE_READER_LINK: "adobe.reader.link",
	AFTER: "after",
	ALERT: "alert",
	ALIGNMENT: "alignment",
	ALL: "all",
	AMBIGUOUS: "ambiguous",
	ANALYSES: "analyses",
	ANSWER: "answer",
	APPLICATION: "application",
	ARRANGE: "arrange",
	ARROW: "arrow",
	AUDIO: "audio",
	AUTHENTICATION: "authentication",
	AUTHORIZATION: "authorization",
	AUTO: "auto",
	BACK: "back",
	BADGE: "badge",
	BATCH: "batch",
	BEFORE: "before",
	BOOTBOX_LANG: "bootbox.lang",
	BOOTSTRAP: "bootstrap",
	BOTTOM: "bottom",
	BROWSE: "browse",
	BROWSER: "browser",
	BULK: "bulk",
	BUTTON: "button",
	CAMERA: "camera",
	CAMPAIGN: "campaign",
	CANCEL: "cancel",
	CARD: "card",
	CENTER: "center",
	CHANGE: "change",
	CHANGEUSER: "changeuser",
	CHANGEUSER_DECISION_COMPLETE: "changeuser.decision.complete",
	CHANGEUSER_DECISION_COMPLETE_DESCRIPTION: "changeuser.decision.complete.description",
	CHANGEUSER_DECISION_MAIL_SUBJECT: "changeuser.decision.mail.subject",
	CHANGEUSER_PLEASE__CHANGE: "changeuser.please_change",
	CHANGEUSER_PLEASE__CHANGE_DESCRIPTION: "changeuser.please_change.description",
	CHANGEUSER_RESERVE_COMPLETE: "changeuser.reserve.complete",
	CHANGEUSER_RESERVE_COMPLETE_DESCRIPTION: "changeuser.reserve.complete.description",
	CHANGEUSER_RESERVE_MAIL_SUBJECT: "changeuser.reserve.mail.subject",
	CHARACTER: "character",
	CHAT: "chat",
	CHECKBOX: "checkbox",
	CLEAR: "clear",
	CLIENT: "client",
	CLOCK: "clock",
	CLOSE: "close",
	CODEPOINT: "codepoint",
	COLOR: "color",
	COLUMN: "column",
	COMPANY: "company",
	COMPANY_NAME: "company.name",
	COMPANY_NAME_DESCRIPTION: "company.name.description",
	COMPLETE: "complete",
	COMPLETED: "completed",
	COMPONENT: "component",
	CONDITION: "condition",
	CONSISTENT: "consistent",
	CONSTRAINT_EMAIL: "constraint.email",
	CONSTRAINT_MAX: "constraint.max",
	CONSTRAINT_MAXLENGTH: "constraint.maxLength",
	CONSTRAINT_MIN: "constraint.min",
	CONSTRAINT_MINLENGTH: "constraint.minLength",
	CONSTRAINT_PATTERN: "constraint.pattern",
	CONSTRAINT_REQUIRED: "constraint.required",
	CONSULT: "consult",
	CONTACT: "contact",
	CONTENT: "content",
	CONVERT: "convert",
	COOPERATE: "cooperate",
	COPY: "copy",
	COUNTRY_FLAG: "country.flag",
	CREATE: "create",
	CREATOR: "creator",
	CUT: "cut",
	DANGER: "danger",
	DATATABLES_LANG: "datatables.lang",
	DATE: "date",
	DATETIME: "datetime",
	DAY: "day",
	DAYLIGHT__SAVING__TIME: "daylight_saving_time",
	DEFAULT: "default",
	DELETE: "delete",
	DESCRIPTION: "description",
	DESELECT: "deselect",
	DESIGN: "design",
	DETAIL: "detail",
	DEVELOPMENT: "development",
	DIRECTION: "direction",
	DISPLAY: "display",
	DOCUMENT: "document",
	DOWN: "down",
	DOWNLOAD: "download",
	DRAG: "drag",
	DRAWER: "drawer",
	DROP: "drop",
	ECHARTS_LANG: "echarts.lang",
	EDIT: "edit",
	EMAIL: "email",
	EMPTY: "empty",
	END: "end",
	ENTER: "enter",
	ENVIRONMENT: "environment",
	ERROR: "error",
	ERROR_DATE: "error.date",
	ERROR_EMAIL: "error.email",
	ERROR_EXPECTED_DATE: "error.expected.date",
	ERROR_EXPECTED_DATE_ISOFORMAT: "error.expected.date.isoformat",
	ERROR_EXPECTED_ENUMSTRING: "error.expected.enumstring",
	ERROR_EXPECTED_JSARRAY: "error.expected.jsarray",
	ERROR_EXPECTED_JSBOOLEAN: "error.expected.jsboolean",
	ERROR_EXPECTED_JSNUMBER: "error.expected.jsnumber",
	ERROR_EXPECTED_JSNUMBERORJSSTRING: "error.expected.jsnumberorjsstring",
	ERROR_EXPECTED_JSOBJECT: "error.expected.jsobject",
	ERROR_EXPECTED_JSSTRING: "error.expected.jsstring",
	ERROR_EXPECTED_KEYPATHNODE: "error.expected.keypathnode",
	ERROR_EXPECTED_TIME: "error.expected.time",
	ERROR_EXPECTED_UUID: "error.expected.uuid",
	ERROR_EXPECTED_VALIDENUMVALUE: "error.expected.validenumvalue",
	ERROR_INVALID: "error.invalid",
	ERROR_INVALID_JAVA_UTIL_DATE: "error.invalid.java.util.Date",
	ERROR_MAX: "error.max",
	ERROR_MAXLENGTH: "error.maxLength",
	ERROR_MAX_STRICT: "error.max.strict",
	ERROR_MIN: "error.min",
	ERROR_MINLENGTH: "error.minLength",
	ERROR_MIN_STRICT: "error.min.strict",
	ERROR_NUMBER: "error.number",
	ERROR_PATH_EMPTY: "error.path.empty",
	ERROR_PATH_MISSING: "error.path.missing",
	ERROR_PATH_RESULT_MULTIPLE: "error.path.result.multiple",
	ERROR_PATTERN: "error.pattern",
	ERROR_REAL: "error.real",
	ERROR_REAL_PRECISION: "error.real.precision",
	ERROR_REQUIRED: "error.required",
	ERROR_UUID: "error.uuid",
	ESSENTIAL: "essential",
	ETERNAL: "eternal",
	EXAMPLE: "example",
	FAILED: "failed",
	FAILURE: "failure",
	FAQ: "faq",
	FIELD: "field",
	FILE: "file",
	FILE_NAME: "file.name",
	FILTER: "filter",
	FIRST: "first",
	FIRST__TIME: "first_time",
	FONT: "font",
	FONTAWESOME: "fontawesome",
	FONT_HIRAGINOSANS__W3: "font.hiraginosans_w3",
	FONT_HIRAMINPRON__W3: "font.hiraminpron_w3",
	FONT_IPAMJMINCHO: "font.ipamjmincho",
	FOOTER: "footer",
	FORMAT: "format",
	FORMATS_DATE: "formats.date",
	FORMAT_DATE: "format.date",
	FORMAT_NUMERIC: "format.numeric",
	FORMAT_REAL: "format.real",
	FORMAT_UUID: "format.uuid",
	FORWARD: "forward",
	FRAMEWORK: "framework",
	FRAPPEGANTT_LANG: "frappegantt.lang",
	FULLCALENDAR_LANG: "fullcalendar.lang",
	FULL__NAME: "full_name",
	FULL__SCREEN: "full_screen",
	FULL__TEXT: "full_text",
	GENERAL: "general",
	GET: "get",
	GROUP: "group",
	HALF__DAY: "half_day",
	HANDSONTABLE_LANG: "handsontable.lang",
	HEADER: "header",
	HELP: "help",
	HEX: "hex",
	HOME: "home",
	HORIZONTAL: "horizontal",
	HTTP: "http",
	ICON: "icon",
	ID: "id",
	IMAGE: "image",
	INFO: "info",
	INFORMATION: "information",
	INITIALIZE: "initialize",
	INPUT: "input",
	INQUIRY: "inquiry",
	INQUIRY_SEND: "inquiry.send",
	INQUIRY_SEND_COMPLETE: "inquiry.send.complete",
	INQUIRY_SEND_COMPLETE_DESCRIPTION: "inquiry.send.complete.description",
	INQUIRY_SEND_DESCRIPTION: "inquiry.send.description",
	INSERT__COLUMN__LEFT: "insert_column_left",
	INSERT__COLUMN__RIGHT: "insert_column_right",
	INSERT__ROW__ABOVE: "insert_row_above",
	INSERT__ROW__BELOW: "insert_row_below",
	INTRO: "intro",
	JAVA: "java",
	JAVASCRIPT: "javascript",
	JAVA_ERROR_ASSERTFALSE: "java.error.AssertFalse",
	JAVA_ERROR_ASSERTTRUE: "java.error.AssertTrue",
	JAVA_ERROR_DECIMALMAX: "java.error.DecimalMax",
	JAVA_ERROR_DECIMALMIN: "java.error.DecimalMin",
	JAVA_ERROR_DIGITS: "java.error.Digits",
	JAVA_ERROR_EMAIL: "java.error.Email",
	JAVA_ERROR_FUTURE: "java.error.Future",
	JAVA_ERROR_FUTUREORPRESENT: "java.error.FutureOrPresent",
	JAVA_ERROR_MAX: "java.error.Max",
	JAVA_ERROR_MIN: "java.error.Min",
	JAVA_ERROR_NEGATIVE: "java.error.Negative",
	JAVA_ERROR_NEGATIVEORZERO: "java.error.NegativeOrZero",
	JAVA_ERROR_NOTBLANK: "java.error.NotBlank",
	JAVA_ERROR_NOTEMPTY: "java.error.NotEmpty",
	JAVA_ERROR_NOTNULL: "java.error.NotNull",
	JAVA_ERROR_NULL: "java.error.Null",
	JAVA_ERROR_PAST: "java.error.Past",
	JAVA_ERROR_PASTORPRESENT: "java.error.PastOrPresent",
	JAVA_ERROR_PATTERN: "java.error.Pattern",
	JAVA_ERROR_POSITIVE: "java.error.Positive",
	JAVA_ERROR_POSITIVEORZERO: "java.error.PositiveOrZero",
	JAVA_ERROR_SIZE: "java.error.Size",
	JUSTIFY: "justify",
	KEY: "key",
	KEYBOARD: "keyboard",
	LAB: "lab",
	LANGUAGE: "language",
	LAST: "last",
	LAYOUT: "layout",
	LEFT: "left",
	LIBRARY: "library",
	LICENSE: "license",
	LINK: "link",
	LIST: "list",
	LOAD: "load",
	LOCAL: "local",
	LOCAL_NAME: "local.name",
	LOCAL_NAME_DESCRIPTION: "local.name.description",
	MAIL: "mail",
	MAINTENANCE: "maintenance",
	MANAGE: "manage",
	MAP: "map",
	MAP_ROTATE_GAMMA: "map.rotate.gamma",
	MAP_ROTATE_LAMBDA: "map.rotate.lambda",
	MAP_ROTATE_PHI: "map.rotate.phi",
	MASTER: "master",
	MAX: "max",
	MAX__CASE: "max_case",
	MENU: "menu",
	MESSAGE: "message",
	MESSAGE_CODE: "message.code",
	MIDDLE: "middle",
	MIN: "min",
	MIN__CASE: "min_case",
	MONTH: "month",
	MYCOMPANY_LINK: "mycompany.link",
	MYCOMPANY_NAME: "mycompany.name",
	NAME: "name",
	NAME_DESCRIPTION: "name.description",
	NATURALEARTHDATA_LANG: "naturalearthdata.lang",
	NETWORK: "network",
	NEW: "new",
	NEXT: "next",
	NOTIFICATION: "notification",
	OK: "ok",
	OLD: "old",
	OPEN: "open",
	OPENJDK: "openjdk",
	OPERATION: "operation",
	OPTIONAL: "optional",
	ORDER: "order",
	ORGANIZATION: "organization",
	ORGANIZATIONUNIT: "organizationunit",
	ORGANIZATIONUNIT_NAME: "organizationunit.name",
	ORGANIZATIONUNIT_NAME_DESCRIPTION: "organizationunit.name.description",
	OTHER: "other",
	OUTLINE: "outline",
	OUTPUT: "output",
	OVERVIEW: "overview",
	PAGE: "page",
	PAPER: "paper",
	PARAMETER: "parameter",
	PARSLEY_LANG: "parsley.lang",
	PART: "part",
	PASTE: "paste",
	PAUSE: "pause",
	PAY: "pay",
	PDF: "pdf",
	PDFJS_LANG: "pdfjs.lang",
	PHONE: "phone",
	PHOTO: "photo",
	PICKADATE_LANG: "pickadate.lang",
	PLEASE__SELECT: "please_select",
	PLEASE__WAIT: "please_wait",
	PREVIEW: "preview",
	PREVIOUS: "previous",
	PRICE: "price",
	PRINT: "print",
	PROCESS: "process",
	PRODUCT: "product",
	PROGRESS: "progress",
	QUARTER__DAY: "quarter_day",
	QUESTION: "question",
	RADIO: "radio",
	RANGE: "range",
	READ: "read",
	READ__ONLY: "read_only",
	RECEIPT: "receipt",
	RECEIVE: "receive",
	RECOVERY: "recovery",
	REDO: "redo",
	REGIST: "regist",
	REGISTUSER: "registuser",
	REGISTUSER_PLEASE__REGIST: "registuser.please_regist",
	REGISTUSER_PLEASE__REGIST_DESCRIPTION: "registuser.please_regist.description",
	REGISTUSER_PROVISIONAL_COMPLETE: "registuser.provisional.complete",
	REGISTUSER_PROVISIONAL_COMPLETE_DESCRIPTION: "registuser.provisional.complete.description",
	REGISTUSER_PROVISIONAL_MAIL_SUBJECT: "registuser.provisional.mail.subject",
	REGISTUSER_REGULAR_COMPLETE: "registuser.regular.complete",
	REGISTUSER_REGULAR_COMPLETE_DESCRIPTION: "registuser.regular.complete.description",
	REGISTUSER_REGULAR_MAIL_SUBJECT: "registuser.regular.mail.subject",
	RELOAD: "reload",
	REMOVE: "remove",
	REMOVE__COLUMN: "remove_column",
	REMOVE__ROW: "remove_row",
	REPORT: "report",
	REQUEST: "request",
	REQUIRED: "required",
	RESET: "reset",
	RESETUSER: "resetuser",
	RESETUSER_CHANGE_COMPLETE: "resetuser.change.complete",
	RESETUSER_CHANGE_COMPLETE_DESCRIPTION: "resetuser.change.complete.description",
	RESETUSER_CHANGE_MAIL_SUBJECT: "resetuser.change.mail.subject",
	RESETUSER_FORGET__PASSWORD: "resetuser.forget_password",
	RESETUSER_PLEASE__CHANGE: "resetuser.please_change",
	RESETUSER_PLEASE__CHANGE_DESCRIPTION: "resetuser.please_change.description",
	RESETUSER_PLEASE__RESET: "resetuser.please_reset",
	RESETUSER_PLEASE__RESET_DESCRIPTION: "resetuser.please_reset.description",
	RESETUSER_REQUEST_COMPLETE: "resetuser.request.complete",
	RESETUSER_REQUEST_COMPLETE_DESCRIPTION: "resetuser.request.complete.description",
	RESETUSER_REQUEST_MAIL_SUBJECT: "resetuser.request.mail.subject",
	RESPONSE: "response",
	RESTART: "restart",
	RESULT: "result",
	RIGHT: "right",
	ROW: "row",
	SAMPLE: "sample",
	SAVE: "save",
	SCIENCE: "science",
	SEARCH: "search",
	SECTION: "section",
	SECURE: "secure",
	SELECT: "select",
	SELECT2_LANG: "select2.lang",
	SELECT__FILE: "select_file",
	SEND: "send",
	SERVER: "server",
	SET: "set",
	SETTING: "setting",
	SHARE: "share",
	SHEET: "sheet",
	SLIDE: "slide",
	SMS: "sms",
	SORT: "sort",
	SPEECH_SYNTHESIS_LANG: "speech.synthesis.lang",
	START: "start",
	STARTED: "started",
	STARTING: "starting",
	STATUS: "status",
	STOP: "stop",
	STOPPED: "stopped",
	STOPPING: "stopping",
	STRICT: "strict",
	STYLE: "style",
	SUBTOTAL: "subtotal",
	SUCCESS: "success",
	SUMMARY: "summary",
	SUMMERNOTE_LANG: "summernote.lang",
	SUPPORT: "support",
	SWITCH: "switch",
	SYSTEM: "system",
	SYSTEM_CONSTRAINT_HIRAGANA: "system.constraint.hiragana",
	SYSTEM_CONSTRAINT_KATAKANA: "system.constraint.katakana",
	SYSTEM_CONSTRAINT_PASSWORD: "system.constraint.password",
	SYSTEM_CONSTRAINT_USERID: "system.constraint.userid",
	SYSTEM_DESCRIPTION: "system.description",
	SYSTEM_ERROR_DATA_ILLEGAL: "system.error.data.illegal",
	SYSTEM_ERROR_DATA_UNSAVE: "system.error.data.unsave",
	SYSTEM_ERROR_EXECUTION_EXCEPTION: "system.error.execution.exception",
	SYSTEM_ERROR_EXPIRETIME_PAST: "system.error.expiretime.past",
	SYSTEM_ERROR_HIRAGANA: "system.error.hiragana",
	SYSTEM_ERROR_KATAKANA: "system.error.katakana",
	SYSTEM_ERROR_PASSWORD: "system.error.password",
	SYSTEM_ERROR_PASSWORD_NOTEQUAL: "system.error.password.notequal",
	SYSTEM_ERROR_STATE_NOTCONSISTENT: "system.error.state.notconsistent",
	SYSTEM_ERROR_STATE_UPDATEOTHERUSER: "system.error.state.updateotheruser",
	SYSTEM_ERROR_USER: "system.error.user",
	SYSTEM_ERROR_USERID: "system.error.userid",
	SYSTEM_ERROR_USERID_EXIST: "system.error.userid.exist",
	SYSTEM_ERROR_USERID_NOTEXIST: "system.error.userid.notexist",
	SYSTEM_ERROR_X_EXIST: "system.error.x.exist",
	SYSTEM_ERROR_X_ILLEGAL: "system.error.x.illegal",
	SYSTEM_ERROR_X_NOTENABLE: "system.error.x.notenable",
	SYSTEM_ERROR_X_NOTEXIST: "system.error.x.notexist",
	SYSTEM_ERROR_X__CASE__DATA_ILLEGAL: "system.error.x_case_data.illegal",
	SYSTEM_ERROR_X__DATA_ILLEGAL: "system.error.x_data.illegal",
	SYSTEM_NAME: "system.name",
	TABLE: "table",
	TALK: "talk",
	TARGET: "target",
	TASK: "task",
	TAX: "tax",
	TEMPLATE: "template",
	TEXT: "text",
	THANK__YOU: "thank_you",
	THANK__YOU__VERY__MUCH: "thank_you_very_much",
	THEME: "theme",
	THEME_BUSINESS: "theme.business",
	THEME_DEFAULT: "theme.default",
	THEME_JAPAN: "theme.japan",
	THEME_NATURAL: "theme.natural",
	THEME_PRETTY: "theme.pretty",
	TIME: "time",
	TIMELINE: "timeline",
	TIMEZONE: "timezone",
	TITLE: "title",
	TOP: "top",
	TOTAL: "total",
	TRANSLATE: "translate",
	TREE: "tree",
	TROUBLESHOOT: "troubleshoot",
	TYPE: "type",
	UI: "ui",
	UNDO: "undo",
	UP: "up",
	UPDATE: "update",
	UPLOAD: "upload",
	USER: "user",
	USER_CHANGE__USERID: "user.change_userid",
	USER_EXPIRE: "user.expire",
	USER_NEWUSERID: "user.newuserid",
	USER_NEWUSERID_DESCRIPTION: "user.newuserid.description",
	USER_PASSWORD: "user.password",
	USER_PASSWORD_DESCRIPTION: "user.password.description",
	USER_PLEASE__SIGNIN: "user.please_signin",
	USER_REPASSWORD: "user.repassword",
	USER_REPASSWORD_DESCRIPTION: "user.repassword.description",
	USER_ROLE: "user.role",
	USER_ROLE_ADMIN: "user.role.admin",
	USER_ROLE_CUSTOMER: "user.role.customer",
	USER_ROLE_GUEST: "user.role.guest",
	USER_SIGNIN: "user.signin",
	USER_SIGNOUT: "user.signout",
	USER_STORE__ACCOUNT: "user.store_account",
	USER_USERID: "user.userid",
	USER_USERID_DESCRIPTION: "user.userid.description",
	UX: "ux",
	VALUE: "value",
	VARIATION: "variation",
	VERSION: "version",
	VERTICAL: "vertical",
	VIDEO: "video",
	VIEWER: "viewer",
	WARNING: "warning",
	WEB: "web",
	WEEK: "week",
	WELCOME: "welcome",
	WIKIPEDIA_LANG: "wikipedia.lang",
	WINDOW: "window",
	WORK: "work",
	WORLD: "world",
	X__AND__X: "x_and_x",
	X__CASE__SELECTED: "x_case_selected",
	X__IS__X: "x_is_x",
	X__OF__X: "x_of_x",
	X__OR__X: "x_or_x",
	X__X: "x_x",
	YOUR__X__IS__X: "your_x_is_x",
	ZOOMIN: "zoomin",
	ZOOMOUT: "zoomout",
}
