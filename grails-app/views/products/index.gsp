<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Listar productos ~ MiniZONE</title>
</head>
<body>
    <div class="row">
        <div class="col-md-12">
            <exa:datatable id="products_datatable" items="${items}" class="table table-stripped" style="background: #FFF;"
                           hidden="description, hasMany, saleItem"
                           reorder="id, image, name,price,quantity_available">
                <exa:customHeader name="id" value="Id"/>
                <exa:customHeader name="name" value="Name"/>
                <exa:customHeader name="image" value="Preview"/>
                <exa:customHeader name="price" value="Price"/>
                <exa:customHeader name="quantity_available" value="Quantity Available"/>
                <exa:customColumn name="id">
                    <a href="${createLink(controller: "products", action:"view", id: it.id)}">${it.id}</a>
                </exa:customColumn>
                <exa:customColumn name="name">
                    <a href="${createLink(controller: "products", action:"view", id: it.id)}">${it.name}</a>
                </exa:customColumn>
                <exa:customColumn name="image">
                    <img src="${it.image}-/resize/250x250/" />
                </exa:customColumn>
            </exa:datatable>
        </div>
    </div>
</body>
</html>