import { useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import { toast } from "react-toastify"
import { registerCustomer, registerSeller } from "../services/user"

function RegisterCustomer(){
    const [firstName,SetFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [confirmPassword, setConfirmPassword] = useState('')
    const [address, setAddress] = useState('')
    const [phoneNumber, setPhoneNumber] = useState('')
    const [dob, setDob] = useState('')
   
    

    // get the navigation hook
    const navigate = useNavigate()

   
    const onRegisterCustomer = async() => {
        if(firstName.length == 0) {
            toast.error('Hey! Enter your name')

        }
      else if (lastName.length == 0){
            toast.error('Enter correct  username')
        }
        else if (email.length == 0){
            toast.error('Hey! Enter your email')
        }
        else if (password.length == 0){
            toast.error('Hey! Enter a password')
        }
        else if (confirmPassword.length == 0){
            toast.error('Hey! Confirm your password')
        }
        else if (password != confirmPassword){
            toast.error('password does not match!')
        }
       else if (phoneNumber.length == 0 && phoneNumber.length >10){
            toast.error('Enter correct mobile no')
        }
        else if (address.length == 0){
            toast.error('Hey! Enter your name')
        }
        else{
            const result = await registerCustomer(firstName,lastName,password,phoneNumber,email,dob);
            toast.success('Successfully registered a new customer')
            navigate('/login')
        }
        
    }
    return (
    <div>

        <h2 className="page-header text-center">Register</h2>
        <div className="row">
            <div className="col"></div>
            <div className="col">
                <div className="form">
                <div className="mb-3">
                        <label htmlFor="">Name</label>
                        <input 
                        onChange={(e)=>SetFirstName(e.target.value)}
                        type="text" className="form-control"/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="">User Name</label>
                        <input
                         onChange={(e)=>setLastName(e.target.value)}
                         type="text" className="form-control"/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="">Email</label>
                        <input 
                        onChange={(e)=>setEmail(e.target.value)}
                        type="email" className="form-control"/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="">Password</label>
                        <input 
                        onChange={(e)=>setPassword(e.target.value)}
                        type="password" className="form-control"/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="">Confirm Password</label>
                        <input 
                        onChange={(e)=>setConfirmPassword(e.target.value)}
                        type="password" className="form-control"/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="">Mobile No</label>
                        <input 
                        onChange={(e)=>setPhoneNumber(e.target.value)}
                        type="Number" className="form-control"/>
                    </div><div className="mb-3">
                        <label htmlFor="">Address</label>
                        <input 
                        onChange={(e)=>setAddress(e.target.value)}
                        type="text" className="form-control"/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="">Birthdate</label>
                        <input 
                        onChange={(e)=>setDob(e.target.value)}
                        type="date" className="form-control"/>
                    </div>
                    <div className="mb-3">
                        <div>Already have an account? <Link to='/login'>Login here</Link></div>
                        <br />  
                        <br />
                        {/* <button onClick={onRegister} className="btn btn-success mt-2">Register</button> */}
                        <button onClick={onRegisterCustomer} className=' button'>
                <span class="transition"></span>
                <span class="gradient"></span>
                <span class="label">REGISTER AS CUSTOMER</span>
              </button>
        
                    </div>
                </div>
            </div>
            <div className="col"></div>
        </div>

    </div>
    )
}

export default RegisterCustomer