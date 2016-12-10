var url = 'http://localhost:8080'
$( document ).ready( function( ) {
    $.ajax( {
        type: "POST",
        url: url + '/api/list_pending_orders',
    } ).done( function( data, textStatus, jqXHR ) {
        displayMessage( data.message, data.status );
        for( var i=0; i<data.items.length ; i++){

            var item = data.items[i];
            $( "#pending_orders" ).append( "<tr id=\"order_"+item.id+"\">"+
                "<td>"+item.user.username+"</td>"+
                "<td>"+item.total+"</td>"+
                "<td>"+item.DateCreated+"</td>"+
                "<td><button class=\"mark_as_given\" rel=\""+item.id+"\"> Mark as given </button></td>"+
                
              "</tr>" );
        }

        if( data.status == 'success' ){
            location.reload( )
        }
    } );
} );

$( '.mark_as_given' ).click( function( ) {
        var id = $( this ).attr( 'rel' );
        url: url + '/api/mark_as_given',
        $.ajax( {
            type: "POST",
            data: {
                'id' : id
            }
        } ).done( function( data, textStatus, jqXHR ) {
            $("#order_" + id).remove();
        } );
    } );
