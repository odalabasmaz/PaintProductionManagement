﻿testTypes = {
    "name": "String"
};

emitXmlHeader = function () {
    var headerRow =  '<ss:Row>\n';
    for (var colName in testTypes) {
        headerRow += '  <ss:Cell>\n';
        headerRow += '    <ss:Data ss:Type="String">';
        headerRow += colName + '</ss:Data>\n';
        headerRow += '  </ss:Cell>\n';
    }
    headerRow += '</ss:Row>\n';
    return '<?xml version="1.0"?>\n' +
        '<ss:Workbook xmlns:ss="urn:schemas-microsoft-com:office:spreadsheet">\n' +
        '<ss:Worksheet ss:Name="Sheet1">\n' +
        '<ss:Table>\n\n' + headerRow;
};

emitXmlFooter = function() {
    return '\n</ss:Table>\n' +
        '</ss:Worksheet>\n' +
        '</ss:Workbook>\n';
};

jsonToSsXml = function (jsonObject) {
    var row;
    var col;
    var xml;
    var data = typeof jsonObject != "object" ? JSON.parse(jsonObject) : jsonObject;

    xml = emitXmlHeader();

    for (row = 0; row < data.length; row++) {
        xml += '<ss:Row>\n';

        for (col in data[row]) {
            xml += '  <ss:Cell>\n';
            xml += '    <ss:Data ss:Type="' + testTypes[col]  + '">';
            xml += data[row][col] + '</ss:Data>\n';
            xml += '  </ss:Cell>\n';
        }

        xml += '</ss:Row>\n';
    }

    xml += emitXmlFooter();
    return xml;
};

download = function (content, filename, contentType) {
    if (!contentType) contentType = 'application/octet-stream';
    var a = document.getElementById('export');
    var blob = new Blob([content], {
        'type': contentType
    });
    a.href = window.URL.createObjectURL(blob);
    a.download = filename;
};
