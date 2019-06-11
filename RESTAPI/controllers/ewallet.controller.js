const payService = require('../services/pay.service');
const Customer= require('../models/customer');

exports.deposit = async (req, res, next) => {
    try {
      const paymentResponse = await payService.debitCard(req.Customer.accountNumber, req.body.card, req.body.amount);
      res.json(paymentResponse);
    } catch (error) {
      next(error);
    }
  };