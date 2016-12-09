<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>View ~ MiniZone</title>
</head>
<body>
<div class="row">
    <div class="col-md-12">
        <form action="https://www.paypal.com/cgi-bin/webscr" method="post">
            <input type="hidden" name="cmd" value="_cart">
            <input type="hidden" name="business" value="test@servidoresactivos.com">
            <input type="hidden" name="item_name" value="Invoice">
            <input type="hidden" name="item_number" value=" ${sale.id}">
            <input type="hidden" name="amount" value="${sale.total}">
            <input type="hidden" name="first_name" value="${sale.user.complete_name}">
            <input type="hidden" name="email" value="${sale.user.email}">
            <input type="image" name="submit"
                   src="https://www.paypalobjects.com/en_US/i/btn/btn_buynow_LG.gif"
                   alt="PayPal - The safer, easier way to pay online">
        </form>
    </div>
</div>
</body>
</html>