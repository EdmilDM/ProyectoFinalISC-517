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
                <g:if test="${current_user.id == user.id || current_user.isAdmin( ) }">
                    <g:if test="${user.sales.size() > 0}">
                    <exa:datatable id="transactions_datatable" items="${user.sales?user.sales:[]}" class="table table-stripped" style="background: #FFF;"
                        hidden="description, hasMany, images, saleItem, Features, dateCreated"
                        reorder="id, NCF, paypal_transaction_id, total"
                        add="actions"
                    >
                        <exa:customHeader name="id" value="Id"/>
                        <exa:customHeader name="name" value="Name"/>
                        <exa:customHeader name="paypal_transaction_id" value="Paypal Transaction"/>
                        <exa:customHeader name="total" value="Total"/>
                        <exa:customHeader name="actions" value="Actions"/>
                        <exa:customColumn name="actions">
                            <a href="/report/invoice/${it.id}"><i class="fa fa-wpforms "></i> Print invoice</a>
                            <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_STORAGE">
                                <a href="/report/storage/${it.id}"><i class="fa fa-wpforms "></i> Print Storage document</a>
                                <a href="/products/sale/${it.id}"><i class="fa fa-wpforms "></i> Print Storage document</a>
                            </sec:ifAnyGranted>
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