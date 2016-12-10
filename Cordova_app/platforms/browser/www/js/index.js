var url = 'http://localhost:8080'
$( document ).ready( function( ) {
    $( "#login").click( function( ){
        if( $( "#user").val( ) == 'admin' && $( "#password").val( ) == '12345' ){
            location = 'orders.html';
        }
        else {
            alert( 'Wrong data!' );
        }
    } );
    $.ajax( {
        type: "POST",
        url: url + '/api/list_pending_orders',
    } ).done( function( data, textStatus, jqXHR ) {
        for( var i=0; i<data.items.length ; i++){

            var item = data.items[i];
            $( "#pending_orders" ).append( "<tr id=\"order_"+item.id+"\">"+
                "<td>"+item.user+"</td>"+
                "<td>"+item.total+"</td>"+
                "<td>"+item.dateCreated+"</td>"+
                "<td><button class=\"mark_as_given btn btn-success\" rel=\""+item.id+"\"><i class=\"fa fa-check\"></i> Mark as given</button></td>"+

                "</tr>" );
        }

        if( data.status == 'success' ){
            location.reload( )
        }
    } );

    $.ajax( {
        type: "POST",
        url: url + '/api/sales',
    } ).done( function( data, textStatus, jqXHR ) {
        $( "#total_sales" ).text( data.total );
        $( "#total_worked" ).text( data.worked );
        $( "#total_pending" ).text( data.pending );
    } );

    $( 'body' ).on( 'click', '.mark_as_given', function( ) {
        var id = $( this ).attr( 'rel' );
        $.ajax( {
            type: "POST",
            url: url + '/api/mark_as_given',
            data: {
                'id' : id
            }
        } ).done( function( data, textStatus, jqXHR ) {
            $("#order_" + id).remove();
            alert( 'Done!' );
            var total_pending = parseInt( $("#total_pending").text( ) );
            var total_worked = parseInt( $("#total_worked").text( ) );
            $("#total_pending").text( total_pending - 1 );
            $("#total_worked").text( total_worked + 1 );
        } );
    } );

} );