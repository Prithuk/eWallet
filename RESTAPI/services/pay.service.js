const GatewayTransaction = require('../models/gatewayTransaction.model');
const httpStatus = require('http-status');
const Transaction = require('../models/transaction.model');
const Customer=require('../models/customer');



async function simulateGateWayCall(card, amount){

    let status = 'success';
    if(card == '1212121212121212'){
        status ='failure';
    }
}

exports.debitCard = async(accountNumber, card, amount) => {
const gatewayResponse = await simulateGateWayCall(card, amount);
const gatewayTransaction = new GatewayTransaction (gatewayResponse);
const savedGatewayTransaction = await gatewayTransaction.save();

if(savedGatewayTransaction.status==='failure'){
    throw new APIError({
        message:"Payment Rejected",
        status: httpStatus.PAYMENT_REQUIRED
    });
}

const transcation = new Transaction();
transcation.amount= amount;
transcation.operation= 'deposit';
transcation.accountNumber= accountNumber;
transcation.reference= `payment_gateway_transaction:${savedGatewayTransaction.transcationId}`;
const savedTransaction = await transcation.save();
const savedCustomer =await Customer.findOne({accountNumber});
const response ={
    transaction:savedTransaction.transform(),
    customer : savedCustomer.transformBalance()
};
return response;




}
