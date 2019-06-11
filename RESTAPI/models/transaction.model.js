const mongoose = require('mongoose');


const transactionSchema = new mongoose.Schema({
    operation: {
      type: String,
      required: true
    
    },
    accountNumber: {
      type: 'Number',
      ref: 'Customer',
      required: true,
    },
    destinationAccountNumber: {
      type: 'Number',
      ref: 'Customer',
    },
    amount: {
      type: Number,
      default: 0,
      required: true,
    },
    reference: {
      type: String,
    },
  }, {
    timestamps: true,
  });
  


  transactionSchema.method({
      transform(){
            const transformed = {};
            const fields = ['id', 'accountNumber', 'destinationAccountNumber', 'operation', 'amount', 'reference', 'createdAt'];

            fields.forEach((field)=>{
                transformed[field]= this[field];

            });
            return transformed;
      }
  });

  module.exports = mongoose.model('Transaction',transactionSchema);