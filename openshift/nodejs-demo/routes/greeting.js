var express = require('express');
var router = express.Router();

/* GET greeting */
router.get('/', function(req, res, next) {
  res.send(
    { "greeting": "Hello, world!" }
  );
});

module.exports = router;
