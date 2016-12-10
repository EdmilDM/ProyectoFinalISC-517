<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><g:layoutTitle default="MniZone"/></title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <asset:stylesheet src="bootstrap.css"/>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <asset:stylesheet src="AdminLTE.min.css"/>
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect.
  -->

    <asset:stylesheet src="application.css"/>
    <asset:stylesheet src="skin-black.min.css"/>
    <asset:stylesheet src="general.css"/>
    <asset:javascript src="application.js" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
      <![endif]-->
    <g:layoutHead/>
</head>
<body class="hold-transition skin-black sidebar-mini">
<div class="wrapper">
    <header class="main-header">

        <!-- Logo -->
        <a href="/" class="logo">
            <span class="logo-mini"><b>M</b>Z</span>
            <span class="logo-lg"><b>Mini</b>ZONE</span>
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- User Account Menu -->
                    <li class="dropdown user user-menu">
                        <!-- Menu Toggle Button -->
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <sec:ifNotLoggedIn>
                                <span class="hidden-xs">Invitado</span>
                            </sec:ifNotLoggedIn>
                            <sec:ifLoggedIn>
                                <span class="hidden-xs">${sec.username()}</span>
                            </sec:ifLoggedIn>
                        </a>
                        <sec:ifLoggedIn>
                            <ul class="dropdown-menu">
                                <!-- The user image in the menu -->
                                <li class="user-header">
                                    <a href="/user/profile/?username=${sec.username()}">
                                        ${sec.username()}
                                    </a>
                                </li>
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <div class="pull-left">
                                        <a href="/products/cart" class="btn btn-default btn-flat"><i class="fa fa-shopping-cart"></i> Shopping Cart</a>
                                    </div>
                                    <div class="pull-right">
                                        <a href="/logout" class="btn btn-default btn-flat">Sign out</a>
                                    </div>
                                </li>
                            </ul>
                        </sec:ifLoggedIn>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <sec:ifLoggedIn>
        <section class="sidebar">
            <!-- Sidebar Menu -->
            <ul class="sidebar-menu">
                <!-- Optionally, you can add icons to the links -->
                <li class="treeview">
                    <a href="#"><i class="fa fa-shopping-bag"></i> <span>Products</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="/products"><i class="fa fa-list"></i> List</a></li>
                        <sec:ifAnyGranted roles="ROLE_ADMIN">
                        <li><a href="/products/create"><i class="fa fa-plus"></i> Create</a></li>
                        </sec:ifAnyGranted>
                    </ul>
                </li>
                <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_STORAGE">
                <li class="treeview">
                    <a href="#"><i class="fa fa-user"></i> <span>User Actions</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="/user/index"><i class="fa fa-list"></i> List</a></li>

                        <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_STORAGE">
                        <li><a href="/user/create"><i class="fa fa-user-plus"></i> Create</a></li>
                        </sec:ifAnyGranted>
                    </ul>
                </li>

                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles="ROLE_ADMIN">
                    <li class="treeview">
                        <a href="/charts"><i class="fa fa-bar-chart"></i> <span>Charts</span></a>
                    </li>
                    <li class="treeview">
                        <a href="#"><i class="fa fa-cog"></i> <span>Roles Actions</span>
                            <span class="pull-right-container">
                                <i class="fa fa-angle-left pull-right"></i>
                            </span>
                        </a>
                        <ul class="treeview-menu">
                            <li><a href="/userRole/index"><i class="fa fa-list"></i> List</a></li>
                            <li><a href="/userRole/create"><i class="fa fa-plus"></i> Create</a></li>
                        </ul>
                    </li>
                </sec:ifAnyGranted>
            </ul>
            <!-- /.sidebar-menu -->
        </section>
        </sec:ifLoggedIn>
        <!-- /.sidebar -->
    </aside>

    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <g:layoutBody/>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <footer class="main-footer">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
            MiniZONE
        </div>
        <!-- Default to the left -->
        <strong>Copyright &copy; 2016 <a href="http://www.servidoresactivos.com/">Servidores Activos</a>.</strong> All rights reserved.
    </footer>
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
<script>
    UPLOADCARE_PUBLIC_KEY = '4c4ddfa7499a852684a1';
</script>
<!-- AdminLTE App -->
<asset:javascript src="bootstrap.js"/>
<asset:javascript src="app.min.js"/>
<asset:javascript src="pnotify.custom.js" />
<asset:javascript src="general.js"/>
<script src="https://ucarecdn.com/widget/2.10.2/uploadcare/uploadcare.min.js" charset="utf-8"></script>

</body>
</html>