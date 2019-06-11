const express = require('express')
const verify = require('./verifyToken');
const payService = require('../services/pay.service');
const router = express.Router();
const Customer = express('../models/customer.js');
const {paymentValidation}= require('../validation');



router.post('/deposit',verify, async (req,res,next)=>{

 
    try {
      const {error} =paymentValidation(req.body) ;
      if(error) return res.status(400).send(error.details[0].message);
        const paymentResponse = await payService.debitCard(Customer.accountNumber, req.body.card, req.body.amount);
        res.json(paymentResponse);
      } catch (error) {
        next(error);
      }



});
module.exports=router;
