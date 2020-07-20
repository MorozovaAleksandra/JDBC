const usersRoutes = require('./users_routes');
const requestRoutes = require('./request_routes');
const requestsRoutes = require('./requests_routes');
module.exports = function (app) {
    usersRoutes(app);
    requestRoutes(app);
    requestsRoutes(app);
};


 // const express = require('express');
 // const bodyParser = require('body-parser').json();
 //
 // const app = express();
 // const urlencodedParser = bodyParser.urlencoded({extended: false});




//  app.post('ModalWindow.html', urlencodedParser, function (req, res) {
//     if(!req.body) return res.sendStatus(400);
//     console.log(req.body);
//     res.render('ModalWindow');
// });




//
// 'use strict';
// let fs = require('fs');
//
// fs.readFile('readme.txt', 'utf8', function (err, data) {
//     console.log(data);
// } );

// var message = "Helloooo!\n"+fileReaded;
// fs.writeFileSync('readme.txt', message, err => {
//     if (err) throw err
// });
