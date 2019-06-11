const express = require('express');
const app = express();
const mongoose = require('mongoose');
const dotenv = require('dotenv');

const bodyParser = require('body-parser');
//Import Routes
const authRoute = require('./routes/auth');
const postRoute = require('./routes/post');
const walletRoute = require('./routes/ewalletRoutes');


//app.set('view engine', pug);
dotenv.config();
//Connect to db
mongoose.connect(process.env.DB_CONNECT,
{ useNewUrlParser: true },() => console.log('Connected to Mongoose database'));

//Middleware

app.use(bodyParser.urlencoded({
    extended:false
}));
app.use(bodyParser.json());

//Creating routes middleware
app.use('/api/googi/v1',authRoute); 
app.use('/api/googi/v1',postRoute);
app.use('/wallet',walletRoute);
/* app.use(app.router);
authRoute.initialize(app);
postRoute.initialize(app);
walletRoute.initialize(app); */





app.listen(5000, () => console.log('The server is listening at port 5000'));

