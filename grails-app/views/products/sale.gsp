<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>View ~ MiniZone</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="box box-solid box-primary">
            <div class="box-header">
                <h3 class="box-title">Sale #${sale.id} - NCF: ${sale.NCF}</h3> <span class="pull-right">${sale.total}</span>
            </div>
        </div>
        <div class="box-body">
        <exa:datatable id="transactions_datatable" items="${sale.items}" class="table table-stripped" style="background: #FFF;"
            hidden="id, belongsTo, sale"
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
        <div class="box-footer">
            <a class="btn btn-block btn-xs btn-primary" download="invoice_${sale.id}" href="/report/invoice/${sale.id}"><i class="fa fa-wpforms "></i> Print invoice</a>
            <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_STORAGE">
                <a class="btn btn-block btn-xs btn-primary" download="storage_${sale.id}" href="/report/storage/${sale.id}"><i class="fa fa-wpforms "></i> Print Storage document</a>
                <g:if test="${sale.given == false}">
                    <button type="button" class="btn btn-block btn-xs btn-info" id="mark_as_given" rel="${sale.id}"><i class="fa fa-wpforms "></i> Mark as given</button>
                </g:if>
            </sec:ifAnyGranted>
        </div>
    </div>
</div>
</body>
</html>