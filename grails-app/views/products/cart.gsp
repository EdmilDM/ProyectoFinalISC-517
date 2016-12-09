<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Listar productos ~ MiniZONE</title>
</head>
<body>
    <div class="row">
        <div class="col-md-12">
            <div class="box box-solid box-primary">
                <div class="box-header">
                    <h3 class="box-title"><i class="fa fa-shopping-cart"></i> Shopping cart</h3>
                </div>
                <div class="box-body">
                    <g:if test="${ user.cart.cartItems.size() > 0}">
                    <exa:datatable id="cart_datatable" items="${ user.cart.cartItems }"
                        hidden="id, belongsTo, cart"
                        reorder="product, quantity, total"
                        add="extra"
                    >
                        <exa:customHeader name="id" value="Id"/>
                        <exa:customHeader name="product" value="Product"/>
                        <exa:customHeader name="quantity" value="Quantity"/>
                        <exa:customHeader name="Total" value="Total"/>
                       <exa:customColumn name="product">
                            <a href="${createLink(controller: "products", action:"view", id: it.product.id)}">${it.product.name}</a>
                        </exa:customColumn>
                        <exa:customColumn name="extra" value="">
                            <button class="btn btn-xs btn-danger delete_cart_item" rel="${it.id}">Delete</button>
                        </exa:customColumn>
                    </exa:datatable>
                    </g:if>
                    <g:else>
                        Your cart is empty! <a href="/products" class="btn btn-success">Go and buy some!</a>
                    </g:else>
                </div>
                <g:if test="${ user.cart.cartItems.size() > 0}">
                <div class="box-footer">
                    <form action="/products/process_cart">
                        <button class="btn btn-success" id="process_cart"><i class="fa fa-play"></i> Process</button>
                    </form>
                </div>
                </g:if>
            </div>
        </div>
    </div>
</body>
</html>