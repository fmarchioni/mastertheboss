var express = require('express'),
    fs = require('fs'),
    app = express();

var cors = require('cors');
var app = express();
app.use(cors());
 
var ip = process.env.IP || process.env.OPENSHIFT_NODEJS_IP || '0.0.0.0';
 
 
app.get('http://quarkus-customer-service:8080/customers', function(req, res) {
    res.send(res.json());
});
 
 
app.listen(8080, ip);
 
module.exports = app;



