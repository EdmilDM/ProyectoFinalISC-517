/**
 * Created by AcMined on 12/7/2016.
 */
var url = 'http://localhost:8080'
$( document ).ready( function( ) {
    function displayMessage( message, status ){
        alert( message + ' -> ' + status );
    }

    $( '.delete_cart_item' ).click( function( ) {
        var id = $( this ).attr( 'rel' );

        $.ajax( {
            type: "POST",
            url: url + '/process/delete_from_cart',
            data: {
                id: id
            },
        } ).done( function( data, textStatus, jqXHR ) {
            displayMessage( data.message, data.status );

            if( data.status == 'success' ){
                location.reload( )
            }
        } );
    } );

    $( "#add_to_cart").click( function( ){
        var quantity = parseInt( $( "#product_qty").val( ) );
        var available = parseInt( $( "#quantity_available").text( ) );
        var error = false;

        if( isNaN( quantity ) ){
            displayMessage( "Invalid quantity: " + quantity, 'error' );
            error = true;
        }

        if( quantity <= 0 ){
            error = true;
            displayMessage( "The quantity can't be negative!", 'error' );
        }

        if( quantity > available ){
            error = true;
            displayMessage( "We don't have enough stock!", 'error' );
        }

        if( error ) return;

        var product_id = $( this ).attr( 'rel' );

        $.ajax( {
            type: "POST",
            url: url + '/process/add_to_cart',
            data: {
                quantity: quantity,
                product_id: product_id
            },
        } ).done( function( data, textStatus, jqXHR ) {
            displayMessage( data.message, data.status );

            if( data.status == 'success' ){
                available -= quantity;
                $( "#quantity_available").text( available );
            }
        } );
    } );

    $( '#mark_as_given' ).click( function( ) {
        var id = $( this ).attr( 'rel' );
        $.ajax( {
            type: "POST",
            url: url + '/api/mark_as_given',
            data: {
                'id' : id
            }
        } ).done( function( data, textStatus, jqXHR ) {
            $( "#mark_as_given" ).remove();
        } );
    } );
} );