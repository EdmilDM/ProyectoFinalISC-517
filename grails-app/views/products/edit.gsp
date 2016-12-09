<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="row">
            <form action="/products/update" method="post">
                <input type="hidden" name="id" value="${product.id}" />
                <div class="col-md-8">
                    <div class="form-group">
                        <label>Name</label>
                        <input class="form-control" name="name" value="${product.name}"/>
                    </div>

                    <div class="form-group">
                        <label>Price</label>
                        <input class="form-control" name="price" step="0.01" type="number" min="0" value="${product.price}"/>
                    </div>

                    <div class="form-group">
                        <label>Quantity Available</label>
                        <input class="form-control" name="quantity_available" min="0" value="${product.quantity_available}"/>
                    </div>

                    <div class="form-group">
                        <label>Description</label>
                        <textarea class="form-control" name="description">${product.description}</textarea>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="box box-solid box-primary">
                        <div class="box-header">
                            <h3 class="box-title">Picture</h3>
                        </div>
                        <div class="box-body">
                            <input type="hidden" data-tabs="url file" value="${product.image}" name="image" id="product_image" role="uploadcare-uploader" />
                        </div>
                    </div>
                    <button type="submit" name="save" class="btn btn-primary"><i class="fa fa-save"></i> Save</button>
                </div>
            </form>
        </div>
    </body>
</html>
