module.exports = function (app) {
    app.get('/requests', (request, response) => {
        const {Client} = require('pg');

        const client = new Client({
            user: 'postgres',
            host: 'localhost',
            database: 'postgres',
            password: 'qwerty007',
        });
        client.connect();
        client.query('select * from postgres.public.message', (err, res) => {
            response.send(JSON.stringify(res.rows));
        });
    })
};