<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Listar usuarios ~ MiniZONE</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <exa:datatable id="products_datatable" items="${userList}" class="table table-stripped" style="background: #FFF;"
                       hidden="password,hasMany,hasOne,springSecurityService,mapping,enabled,sales,cart,passwordExpired,accountExpired,accountLocked"
                       reorder="id"
                       add="profile">
            <exa:customHeader name="id" value="Id"/>
            <exa:customHeader name="is_entity" value="Final user"/>
            <exa:customHeader name="id" value="Id"/>
            <exa:customHeader name="complete_name" value="Name"/>
            <exa:customColumn name="id">
                <g:if test="${current_user.hasRole( [ 'ROLE_ADMIN' ] )}">
                <a href="${createLink(controller: "user", action:"view", id: it.id)}">${it.id}</a>
                </g:if>
                <g:else>
                    ${it.id}
                </g:else>
            </exa:customColumn>
            <exa:customColumn name="complete_name">
                <g:if test="${current_user.hasRole( [ 'ROLE_ADMIN' ] )}">
                    <a href="${createLink(controller: "user", action:"view", id: it.id)}">${it.complete_name}</a>
                </g:if>
                <g:else>
                    ${it.complete_name}
                </g:else>
            </exa:customColumn>
            <exa:customColumn name="profile">
                <a href="/user/profile/?username=${it.username}">${it.complete_name}</a>

            </exa:customColumn>
        </exa:datatable>
    </div>
</div>
</body>
</html>