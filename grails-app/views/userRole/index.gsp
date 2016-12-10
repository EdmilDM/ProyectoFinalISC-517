<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userRole.label', default: 'UserRole')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="row">
            <div class="col-md-12">
                <exa:datatable id="products_datatable" items="${userRoleList}" class="table table-stripped" style="background: #FFF;"
                               hidden=""
                               reorder="id"
                               add="">
                    <exa:customHeader name="id" value="Id"/>
                    <exa:customHeader name="role" value="Role"/>
                    <exa:customHeader name="user" value="User"/>
                    <exa:customHeader name="complete_name" value="role"/>
                    <exa:customColumn name="id">
                        <a href="${createLink(controller: "userRole", action:"show", id: it.id)}">${it.id}</a>
                    </exa:customColumn>
                    <exa:customColumn name="user">
                        ${it.user.username}
                    </exa:customColumn>
                    <exa:customColumn name="role">
                        ${it.role.authority}
                    </exa:customColumn>
                </exa:datatable>
            </div>
        </div>
    </body>
</html>