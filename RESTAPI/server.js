const express = require('express');
const app = express();
const request = require('request');
const path= require('path');
const mongoose = require('mongoose');
const dotenv = require('dotenv');
const pug = require('pug');
const _ = require('lodash');
const {Donor} = require('./models/donor')
const {initializePayment, verifyPayment} = require('./services/payment.service')(request);


const bodyParser = require('body-parser');
//Import Routes
const authRoute = require('./routes/auth');
const postRoute = require('./routes/post');



dotenv.config();
//Connect to db
mongoose.connect(process.env.DB_CONNECT,
{ useNewUrlParser: true },() => console.log('Connected to Mongoose database'));

//Middleware
/*app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended:false
}));*/

app.use(express.json());

//Creating routes middleware
app.use('/api/googi/v1',authRoute); 
app.use('/api/googi/v1',postRoute);


 app.use(express.static(path.join(__dirname, 'public/')));
app.set('view engine', pug);

app.get('/payment',(req, res) => {
    res.render('index.pug');
});

app.post('/paystack/pay', (req, res) => {
    const form = _.pick(req.body,['amount','email','full_name']);
    form.metadata = {
        full_name : form.full_name
    }
    form.amount *= 100;
    
    initializePayment(form, (error, body)=>{
        if(error){
            //handle errors
            console.log(error);
            return res.redirect('/error');
            
        }
        response = JSON.parse(body);
        res.json(response);
    });
});

app.get('/paystack/callback', (req,res) => {
    const ref = req.query.reference;
    verifyPayment(ref, (error,body)=>{
        if(error){
            //handle errors appropriately
            console.log(error)
            return res.redirect('/error');
        }
        response = JSON.parse(body);        

        const data = _.at(response.data, ['reference', 'amount','customer.email', 'metadata.full_name']);

        [reference, amount, email, full_name] =  data;
        
        newDonor = {reference, amount, email, full_name}

        const donor = new Donor(newDonor)

        donor.save().then((donor)=>{
            if(!donor){
                return res.redirect('/error');
            }
            res.redirect('/receipt/'+donor._id);
        }).catch((e)=>{
            res.redirect('/error');
        })
    })
});

app.get('/receipt/:id', (req, res)=>{
    const id = req.params.id;
    Donor.findById(id).then((donor)=>{
        if(!donor){
            //handle error when the donor is not found
            res.redirect('/error')
        }
        res.render('success.pug',{donor});
    }).catch((e)=>{
        res.redirect('/error')
    })
})

app.get('/error', (req, res)=>{
    res.render('error.pug');
})



app.listen(5000, () => console.log('The server is listening at port 5000'));

