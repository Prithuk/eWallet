const mongoose= require('mongoose');

const gatewayTransactionSchema =mongoose.Schema ({

    transactionId: {
        type:String,
        //required:true
    },
    status:{
        type:String,
       // required:true
    },
    paymentDate: {
        type: Date,
      //  required: true,
      },
      amount: {
        type: Number,
        default: 0,
       // required: true,
      },
      authorizationCode: {
        type: Number,
       // required: true,
      },
    }, {
      timestamps: true,
});

gatewayTransactionSchema.method({

    transform(){
        const transformed ={};
        const fields = ['transactionId', 'status', 'paymentDate', 'amount', 'authorizationCode'];

        fields.forEach((field)=>{
            transformed[field] = this[field];
        });
        return transformed;
    }


});

module.exports = mongoose.model('GatewayTransaction', gatewayTransactionSchema);