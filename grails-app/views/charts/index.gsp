<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>View charts</title>

</head>
<body>
    <gvisualization:apiImport/>
    <gvisualization:barChart elementId="sales" title="Sales chart" width="${500}" height="${500}"
                                 columns="${columns}" data="${data}" />
    <div id="sales"></div>
</body>
</html>
