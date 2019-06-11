const mongoose = require('mongoose');
const customerSchema = new mongoose.Schema({
    _id: mongoose.Schema.Types.ObjectId,
name:{
    type:String,
    require:true,
    min:3
},
email:{
    type:String,
    require:true,
    min:3,
    max:255
    
},
phone:{
    type:String,
    require:true,
    min:10,
    max:10
},
password:{
    type:String,
    require:true,
    min:8
    
},
date:{
    type:Date,
    default:Date.now
},
balance:{
    type:Number
},
token:{
    type:String
},
socialId:{
    type:String
}


});

customerSchema.method({
    transformBalance(){
        const transformed={};
        const fields =['id','accountNumber','email','phone','balance','token','socialId','date'];

        fields.forEach((field) => {
            transformed[field] = this[field];
          });
      
          return transformed;    
    }
});

module.exports = mongoose.model('Customer', customerSchema);