/**
 * Created by Administrator on 2015/6/20 0020.
 */
; ( function ( $, window, udfd ) {
	var defaultLoadingMsg = "拼命加载中,请稍后...";
    var template = {
        box:    "<div class='cover-box'>" +
                    "<div>" +
                        "<img src='' class='cover-img' width='28' height='28'>" +
                        '<p class="cover-title">'+defaultLoadingMsg+'</p>' +
                    "</div>" +
                "</div>",
        fixed:  "<div class='cover-fixed'></div>",
        abs:    "<div class='cover-abs'></div>"
    },
    directory = ( function () {
        var scripts = document.scripts;
        return scripts[scripts.length - 1].src.substring( 0, scripts[scripts.length - 1].src.lastIndexOf( "/" ) + 1 );
    } () ),
    defaults = {
        imgSrc: directory + "images/loading.gif"
    };




    var Cover = function ( $obj, options ) {
        var cover = this;

        var $coverContainer = $( "<div class='cover-container'></div>" );
        var $coverBox = $( template.box );
        var $coverMask = ( function () {
            if ( $obj[0] === document || $obj[0] === window || $obj[0] === document.body ) {
                $obj = $( window );
                return $( template.fixed )
            } else {
                return $( template.abs );
            }
        }() );

        $coverContainer.append( $coverBox )
            .append( $coverMask )
            .appendTo( "body" );

        $coverBox.find( ".cover-img" )
            .attr( "src", defaults.imgSrc );

        cover.show = function ( title, time ) {
            var $div = $coverBox.children( "div" );
            var ow = $obj.outerWidth();
            var oh = $obj.outerHeight();
            var bw = $coverBox.outerWidth() + $div.outerWidth();
            var bh = $coverBox.outerHeight() + $div.outerHeight();
//            if ( $coverMask.hasClass( "cover-abs" ) ) {
//                $coverContainer.css( {
//                    "left": $obj.offset().left,
//                    "top": $obj.offset().top,
//                    "width": ow,
//                    "height": oh
//                } );
//                $coverBox.offset( {
//                    left: ( ow - bw ) / 2,
//                    top: ( oh - bh ) / 2
//                } );
//            } else {
//                $coverBox.css( "position", "fixed" );
//                $coverBox.css( {
//                    left: ( ow - bw ) / 2,
//                    top: ( oh - bh ) / 2
//                } );
//            }
            
            if ( title ) {
                $coverBox.find( ".cover-title" )
                    .text( title );
            }

//            $coverContainer.fadeIn( time || 200 );
            $coverContainer.show();
        };
        cover.hide = function ( time ) {
//            $coverContainer.fadeOut( time || 200 );
            $coverContainer.hide();
        };
        return cover;
    };


    $.fn.cover = function ( title ) {
        var $this = $( this );
        var cover = $this.data( "cover-container" );
        if ( cover ) {
            cover.show( title );
        } else {
            cover = new Cover( $this );
            $this.data( "cover-container", cover );
            cover.show( title );
        }
    };
    $.fn.uncover = function () {
        var $this = $( this );
        var cover = $this.data( "cover-container" );
        if ( cover ) {
            cover.hide();
//            $(".cover-box div").find( ".cover-title" )
//            .text( defaultLoadingMsg );
        }
    }


} ( jQuery, window ) );


