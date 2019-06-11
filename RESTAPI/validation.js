const Joi = require('@hapi/joi');

//Register Validation
//Validation
const registerValidation =data => {
    const schema ={
        name:Joi.string().min(3).required(),
        email:Joi.string().min(6).required(),
        phone:Joi.string().min(10).required(),
        password:Joi.string().min(8).required()
      };

      return Joi.validate(data,schema);
};

//Login Validation
const loginValidation = data => {
    const schema ={
        
        email:Joi.string().min(6).required(),
        //phone:Joi.string().min(10).required(),
        password:Joi.string().min(8).required()
      };

      return Joi.validate(data,schema);
}

const paymentValidation = data => {
  const schema={
    card:Joi.string().min(16).required(),
    amount:Joi.string().max(5).required()
  }
}
module.exports.registerValidation = registerValidation;
module.exports.loginValidation= loginValidation;
module.exports.paymentValidation= paymentValidation;

