<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
    <div class="row">
        <form action="/products/save" method="post">
            <div class="col-md-8">
                <div class="form-group">
                    <label>Name</label>
                    <input class="form-control" name="name" value=""/>
                </div>

                <div class="form-group">
                    <label>Price</label>
                    <input class="form-control" name="price" step="0.01" type="number" min="0" value=""/>
                </div>

                <div class="form-group">
                    <label>Quantity Available</label>
                    <input class="form-control" name="quantity_available" min="0" value=""/>
                </div>

                <div class="form-group">
                    <label>Description</label>
                    <textarea class="form-control" name="description"></textarea>
                </div>
            </div>
            <div class="col-md-4">
                <div class="box box-solid box-primary">
                    <div class="box-header">
                        <h3 class="box-title">Picture</h3>
                    </div>
                    <div class="box-body">
                        <input type="hidden" data-tabs="url file" value="" name="image" id="product_image" role="uploadcare-uploader" />
                    </div>
                </div>
                <button type="submit" name="save" class="btn btn-success"><i class="fa fa-save"></i> Save</button>
            </div>
        </form>
    </div>
    </body>
</html>
