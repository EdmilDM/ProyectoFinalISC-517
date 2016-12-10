<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>View product ~ MiniZone</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <div class="box box-primary box-solid">
            <div class="box-header">
                <h3 class="box-title">Invoice #${model.id}</h3>
                <span class="pull-right">NCF: <strong id="quantity_available">${model.NCF}</strong></span>
            </div>
            <div class="box-body">
                !
            </div>
            <div class="box-footer">
                Price: <strong>${model.total}</strong>
            </div>
        </div>

        <!-- IMAGES -->
    </div>
</div>
</body>
</html>