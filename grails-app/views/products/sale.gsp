<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>View ~ MiniZone</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <exa:datatable id="transactions_datatable" items="${sale.items}" class="table table-stripped" style="background: #FFF;"
            hidden="id"
            reorder="product, quantity, total"
        >
            <exa:customHeader name="id" value="Id"/>
            <exa:customHeader name="product" value="Product"/>
            <exa:customHeader name="quantity" value="Quantity"/>
            <exa:customHeader name="total" value="Total"/>
            <exa:customColumn name="product">
                <a href="/products/view/${it.product.id}">${it.product.name}</a>
            </exa:customColumn>
        </exa:datatable>
    </div>
</div>
</body>
</html>