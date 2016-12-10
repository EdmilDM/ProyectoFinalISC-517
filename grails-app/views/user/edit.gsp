<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="row">
            <form action="/user/updates" method="post">
                <input type="hidden" name="id" value="${user.id}" />
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Complete name</label>
                        <input required class="form-control" name="name" value="${user.complete_name}"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Password*</label>
                        <input class="form-control" type="password" name="password" value=""/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Email</label>
                        <input required class="form-control" type="email" name="email" value="${user.email}"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Username</label>
                        <input required class="form-control" name="username" value="${user.username}"/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Address</label>
                        <textarea required class="form-control" name="address">${user.address}</textarea>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label>Is Final Person?</label>
                        <select class="form-control" name="is_entity">
                        <g:if test="${user.is_entity}">
                            <option value="1" selected>Yes</option>
                            <option value="0">No</option>
                        </g:if>
                        <g:else>
                            <option value="1">Yes</option>
                            <option value="0" selected>No</option>
                        </g:else>
                        </select>
                    </div>
                </div>
                <div class="col-md-6">
                    <button type="submit" name="save" class="btn-block btn btn-primary"><i class="fa fa-save"></i> Save</button>
                </div>
            </form>
        </div>
    </body>
</html>
