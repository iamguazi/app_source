/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	 config.language = 'zh-cn';							// ����
	 config.resize_enabled = false;					// ��ֹ��ק
	  config.autoUpdateElement = true;			//  ���ύ�����д˱༭���ı�ʱ���Ƿ��Զ�����Ԫ���ڵ�����
	  config.baseHref = '';									// ������ʹ�þ���Ŀ¼�������Ŀ¼��Ϊ��Ϊ���Ŀ¼
	  config.baseFloatZIndex = 100;				//	�༭����z-indexֵ
	  config.keystrokes = [								// ���ÿ�ݼ�

       [ CKEDITOR.ALT + 121 /*F10*/, 'toolbarFocus' ], //��ȡ����

        [ CKEDITOR.ALT + 122 /*F11*/, 'elementsPathFocus' ], //Ԫ�ؽ���

       [ CKEDITOR.SHIFT + 121 /*F10*/, 'contextMenu' ], //�ı��˵�

       [ CKEDITOR.CTRL + 90 /*Z*/, 'undo' ], //����

        [ CKEDITOR.CTRL + 89 /*Y*/, 'redo' ], //����

        [ CKEDITOR.CTRL + CKEDITOR.SHIFT + 90 /*Z*/, 'redo' ], //

        [ CKEDITOR.CTRL + 76 /*L*/, 'link' ], //����

        [ CKEDITOR.CTRL + 66 /*B*/, 'bold' ], //����

        [ CKEDITOR.CTRL + 73 /*I*/, 'italic' ], //б��

        [ CKEDITOR.CTRL + 85 /*U*/, 'underline' ], //�»���

        [ CKEDITOR.ALT + 109 /*-*/, 'toolbarCollapse' ]

    ]
    //�Ƿ�Ա༭���������Ⱦ plugins/editingblock/plugin.js

    config.editingBlock = true;
		 //�༭���лس������ı�ǩ

    config.enterMode = CKEDITOR.ENTER_P; //��ѡ��CKEDITOR.ENTER_BR��CKEDITOR.ENTER_DIV

    //�Ƿ�ʹ��HTMLʵ�������� plugins/entities/plugin.js

    config.entities = true;
 //�Ƿ�ת��һЩ������ʾ���ַ�Ϊ��Ӧ��HTML�ַ� plugins/entities/plugin.js

    config.entities_greek = true;
//�Ƿ�ת��һЩ�����ַ�ΪHTML plugins/entities/plugin.js

    config.entities_latin = true;
//ʹ������ʱ�ĸ���ɫ plugins/find/plugin.js

    config.find_highlight = {

        element : 'span',

        styles : { 'background-color' : '#ff0', 'color' : '#00f' }

    };
config.tabSpaces = 4;



};
