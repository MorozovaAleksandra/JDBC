bodyParser = require('body-parser').json();

module.exports = function (app) {
    app.post('/ModalWindow', bodyParser, (request, response) => {
        let body = request.body;
        console.log(body[name]);
        console.log('message');
        let responseBody = {
          id : Math.random(),
          "request": body["name"]
        };
        response.setHeader("Content-Type", "application/json");
        response.send(JSON.stringify(body));
    });
};


