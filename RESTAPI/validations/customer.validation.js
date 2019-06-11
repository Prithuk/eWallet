const Joi = require('@hapi/joi');
const Customer = require('../models/customer');


module.exports ={


walletDeposit: {
    body: {
      amount: Joi.number().positive().precision(2).min(10)
        .max(50000)
        .required(),
      card: Joi.string().creditCard().required(),
    },
  }

}