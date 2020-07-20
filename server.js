const express = require('express');
const app = express();
// const bodyParser = require('body-parser');
const port = 80;

// app.use(bodyParser.urlencoded({extended: true}));
// require('./public/')(app);
app.use(express.static('public'));
app.listen(port, () => {
    console.log('Server started at 80' + port)
});

