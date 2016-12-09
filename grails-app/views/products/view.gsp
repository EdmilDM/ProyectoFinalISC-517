<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>View product ~ MiniZone</title>
</head>
<body>
    <div class="row">
        <div class="col-md-8">
            <div class="box box-primary box-solid">
                <div class="box-header">
                    <h3 class="box-title">${product.name}</h3>
                    <span class="pull-right">Available: <strong id="quantity_available">${product.quantity_available}</strong></span>
                </div>
                <div class="box-body">
                    ${product.description}
                </div>
                <div class="box-footer">
                    Price: <strong>${product.price}</strong>
                </div>
                <div class="box-footer">
                    <a style="margin-top:10px;" type="button" id="modify"  href="/products/edit/${product.id}" class="btn btn-success"><i class="fa fa-edit"></i> Modify product</a>
                </div>
            </div>

            <!-- IMAGES -->
        </div>
        <div class="col-md-4">
            <div class="box box-primary box-solid">
                <div class="box-header">
                    <h3 class="box-title">Agregar a carrito</h3>
                </div>
                <div class="box-body">
                    <img src="${product.image}" style="width: 100%; float: left;" />
                    <label>Quantity</label>
                    <input class="form-control" min="0" step="1" type="number" placeholder="How many do you want?" id="product_qty" />
                    <button style="margin-top:10px;" type="button" id="add_to_cart" rel="${product.id}" class="btn btn-success"><i class="fa fa-shopping-cart"></i> Add to cart</button>

                </div>
            </div>
        </div>
    </div>
</body>
</html>