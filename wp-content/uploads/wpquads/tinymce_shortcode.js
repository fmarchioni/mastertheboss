(function() {
	tinymce.create( 'tinymce.plugins.quads_shortcode', {
		/**
		 * Initializes the plugin
		 *
		 * @param {tinymce.Editor} ed Editor instance that the plugin is initialized in.
		 * @param {string} url Absolute URL to where the plugin is located.
		 */
		init : function( ed, url ) {
			ed.addButton( 'quads_shortcode_button', {
				title: ed.getLang( 'quads_shortcode.title', 'WPQuads ads shortcodes' ),
				image : '../wp-content/uploads/wpquads/wpquads_classic_icon.png',
				classes: 'quads-tinymce-content-button', 
				cmd: 'quads_shortcode_command'
			});
			
			ed.addCommand( 'quads_shortcode_command', function() {
					ed.windowManager.open({
						title: ed.getLang( 'quads_shortcode.title', 'Quads Ads shortcodes' ),
						inline: 1,
						body: [{
							id: 'quads-shortcode-modal-container',
							type: 'container',
							minWidth: 220,
							minHeight: 20,
							html: '<span class="spinner quads-ad-parameters-spinner quads-spinner"></span>',
						}],
						buttons: [{
							text: ed.getLang( 'quads_shortcode.ok', 'Add shortcode' ),
							id: 'quads-shortcode-button-insert-wrap',
							
							onclick: function( e ) {
								if ( jQuery( '#quads-shortcode-modal-container-body #quads-select-for-shortcode' ).length > 0 ) {
									var item = jQuery( '#quads-select-for-shortcode option:selected' ).val();
									item = item.split( 'ad' );
									if ( item ) {
										console.log(item[1]);
											ed.insertContent( '[quads id=' + item[1] + ']' );
									}
								}
								ed.windowManager.close();
							},
						},
						{
							text: ed.getLang( 'quads_shortcode.cancel', 'Cancel' ),
							onclick: 'close'
						}],
						
					});

				append_select_field();

			});
		},         
	});
 
	// Register the plugin
	tinymce.PluginManager.add( 'quads_shortcode', tinymce.plugins.quads_shortcode );

	function append_select_field() {
		var insert_button_wrap = jQuery( '#quads-shortcode-button-insert-wrap' ),
			insert_button      = jQuery( '#quads-shortcode-button-insert-wrap button' ),
			container_body     = jQuery( '#quads-shortcode-modal-container-body' );

		insert_button_wrap.addClass( 'mce-disabled' );
		insert_button.prop( 'disabled', true );

		jQuery.ajax({
			type: 'POST',
			url: ajaxurl,
			data: {
				'action': 'wpquads_ads_for_shortcode',
				'wpquads_security_nonce' :quads.nonce
			}
		})
		.done( function( data, textStatus, jqXHR ) {
			container_body.html( data );
  
			jQuery( '#quads-select-for-shortcode' ).on( 'change', function() {
				if ( jQuery( this ).prop( 'selectedIndex' ) === 0 ) {
					insert_button_wrap.addClass( 'mce-disabled' );
					insert_button.prop( 'disabled', true );
				} else {
					insert_button_wrap.removeClass( 'mce-disabled' );
					insert_button.prop( 'disabled', false );					
				}
			});

		})
		.fail( function( jqXHR, textStatus, errorThrown ) {
			container_body.html( errorThrown );
		});
	}
})();