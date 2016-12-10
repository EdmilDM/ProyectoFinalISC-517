<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>View Profile ~ MiniZone</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="box box-primary box-solid">
            <div class="box-header">
                <h3 class="box-title">${user.username}</h3>
            </div>
            <div class="box-body">
                Transactions:
                <g:if test="${current_user.id == user.id || current_user.hasRole( [ "ROLE_STORAGE", "ROLE_ADMIN" ] ) }">
                    <g:if test="${user.sales.size() > 0}">
                    <exa:datatable id="transactions_datatable" items="${user.sales}" class="table table-stripped" style="background: #FFF;"
                        hidden="lastUpdated, hasMany, user, items, belongsTo"
                        reorder="id, NCF, paypal_transaction_id, total"
                        add="actions"
                    >
                        <exa:customHeader name="id" value="Id"/>
                        <exa:customHeader name="name" value="Name"/>
                        <exa:customHeader name="paypal_transaction_id" value="Paypal Transaction"/>
                        <exa:customHeader name="total" value="Total"/>
                        <exa:customHeader name="actions" value="Actions"/>
                        <exa:customColumn name="actions">
                            <a class="btn btn-block btn-xs btn-primary" href="/products/sale/${it.id}"><i class="fa fa-eye "></i> View sale</a>

                        </exa:customColumn>
                    </exa:datatable>
                    </g:if>
                    <g:else>
                        No transactions...
                    </g:else>
                </g:if>
            </div>
        </div>

        <!-- IMAGES -->
    </div>
</div>
</body>
</html>