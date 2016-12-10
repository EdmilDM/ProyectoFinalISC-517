<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
<body>
<div class="row">
    <form action="/user/save" method="post">
        <input type="hidden" name="id" value="${user.id}" />
        <div class="col-md-6">
            <div class="form-group">
                <label>Complete name</label>
                <input required class="form-control" name="name" value=""/>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group">
                <label>Password*</label>
                <input class="form-control" type="password" required name="password" value=""/>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group">
                <label>Email</label>
                <input required class="form-control" type="email" name="email" value=""/>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group">
                <label>Username</label>
                <input required class="form-control" name="username" value=""/>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group">
                <label>Address</label>
                <textarea required class="form-control" name="address"></textarea>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group">
                <label>Is Final Person?</label>
                <select class="form-control" name="is_entity">
                    <option value="1">Yes</option>
                    <option selected value="0">No</option>
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
