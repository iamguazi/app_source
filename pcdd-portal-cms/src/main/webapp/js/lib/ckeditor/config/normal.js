/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	$.extend(config, {
		toolbar: 'Basic',
		uiColor: '#9AB8F3',
//		uiColor: '#EEEEEE',
		uiColor: '#F1F4FB',
//		width: "100%",
		height: 300,
		toolbarCanCollapse: true, 		// 允许工具栏收缩
		toolbar : [
		           { name: 'document', 		items: [ 'Source', '-', 'NewPage', 'Preview', '-', 'Templates' ] },
		           { name: 'clipboard', 	items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ] },
		           { name: 'editing', 		items: [ 'Find', 'Replace', 'Selection'] },
		           { name: 'basicstyles', 	items: [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ] },
		           '/',
		           { name: 'paragraph', 	items: [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ] },
		           { name: 'links', 		items: [ 'Link','Unlink','Anchor' ] },
		           { name: 'insert', 		items: [ 'Table', "Image" ] },
		           '/',
		           { name: 'styles', 		items: [ 'Styles','Format','Font','FontSize' ] },
		           { name: 'colors', 		items: [ 'TextColor','BGColor' ] },
		           { name: 'tools', 		items: [ 'Maximize', 'ShowBlocks','-','About' ] }
		           ],
		baseFloatZIndex: 1500,			// z-index
		image_previewText : "图片预览",
		filebrowserImageUploadUrl : fullPath + "backstage/multipart/ckeditorUpload",
		language : "zh-cn",
		allowedContent : true
	});
};


$.fn.modal.Constructor.prototype.enforceFocus = function () {
    var $modalElement = this.$element;
    $(document).on('focusin.modal', function (e) {
        var $parent = $(e.target.parentNode);
        if ($modalElement[0] !== e.target && !$modalElement.has(e.target).length
            // add whatever conditions you need here:
            &&
            !$parent.hasClass('cke_dialog_ui_input_select') && !$parent.hasClass('cke_dialog_ui_input_text')) {
            $modalElement.focus()
        }
    })
};