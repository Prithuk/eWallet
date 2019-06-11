const router = require('express').Router();
const Customer=require('../models/customer');
const mongoose = require('mongoose');
const Joi= require('@hapi/joi');
const {registerValidation,loginValidation}= require('../validation');
const bcrypt= require('bcryptjs');
const jwt = require('jsonwebtoken');



router.post('/register',async (req,res,next) => {

//Validating the data before creating a user.
const {error} = registerValidation(req.body);
if(error) return res.status(400).send(error.details[0].message);


//Check if the user exist in the database
const emailExist = await Customer.findOne({
  email:req.body.email});

  if (emailExist) { 
    return res.status(400).send("Email already exist..");
    
  }

  //Hash the password
  const salt= await bcrypt.genSalt(10);
  const hashPassword = await bcrypt.hash(req.body.password,salt);



  const  customer = new Customer({
    _id:new mongoose.Types.ObjectId(),    
    name: req.body.name,
        email:req.body.email,
        phone:req.body.phone,
        password:hashPassword
   });
  
   customer
   .save()
   .then((result) => {
    console.log("customer:",customer._id); 
   }).catch((err) => console.log(err));
   res.status(201).json({
     message:"User registered successfully",
     createCustomer:customer._id
     
   });

next();

  });
  
  

   //Login module

   router.post('/login', async (req,res,next)=>{

    const {error} = loginValidation(req.body);
    if(error) return res.status(400).send(error.details[0].message);

  //Check for user validity
  const customer = await Customer.findOne({
      email:req.body.email
  });

  try {
    if (!customer) {
    return res.status(400).send("Email is invalid");    
      }
      const validPass = await bcrypt.compare(req.body.password,customer.password);
      if(!validPass) {
        return res.status(400).send("Password is invalid");
      }
    
  } catch (error) {
    return res.status(400).send(error);
    
  }

  

  //Create and assign a webtoken
  const token = jwt.sign({_id:customer._id},process.env.TOKEN_SECRET);
  res.header('auth-token',token).send(token);
  
customer.token=token;
customer.save();



  res.status(201).send("User logged in successfully");




next();

   });

   


  






module.exports=router;