package proyectofinal

import grails.transaction.Transactional

@Transactional
class NCFService {

    def serviceMethod() {

    }

    NFC nfc =  new NFC( );

    String get( String user_type ) {
        def sales = Sale.list( );
        if ( sales.size( ) == 0 ){
            nfc.parseNCF( "A0100100100000000" )
        }
        else{
            Sale sale = sales.get( sales.size( ) - 1 );
            if( !sale.NCF ) nfc.parseNCF( "A0100100100000000" );
            nfc.parseNCF( sale.NCF );
        }

        return nfc.nextNCF( user_type );
    }
}

class NFC {
    protected String [ ] serieValues = ["A","B","C","D","E","F","G","H","I","J"]
    Integer serie;
    protected String business_div = "01";
    protected String emition_point = "001";
    protected String print_area ="001";
    protected String NCF_type;
    protected Integer sequence;

    void parseNCF( String voucher ){
        for (int i =0 ; i < serieValues.size() ; i ++ ){
            if (voucher[0] == serieValues[i]){
                serie = i;
            }
        }

        String buff= "";
        for(int i = 9; i < voucher.size() ; i++){
            buff += voucher[i];
        }
        println buff
        sequence = Integer.valueOf(buff);
    }

    String nextNCF( String user_type ){
        String r ="";
        sequence++;
        if (sequence >= 100000000){
            sequence =1;
            serie++;
        }
        if (user_type=="final") NCF_type = "02";
        else NCF_type ="01";
        r += serieValues[serie];
        r += business_div;
        r += emition_point;
        r += print_area;
        r += NCF_type.toString();
        r += String.format("%08d",sequence);
        return r;
    }
}