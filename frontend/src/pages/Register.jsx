import { useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import { toast } from "react-toastify"

function Register(){
    const [Name,SetName] = useState('')
    const [Username, setUsername] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [confirmPassword, setConfirmPassword] = useState('')
    const [address, setAddress] = useState('')
    const [mobile, setMobile] = useState('')
    

    // get the navigation hook
    const navigate = useNavigate()

    const onRegister = () => {
        if(Name.length == 0) {
            toast.error('Hey! Enter your name')

        }
      else if (Username.length == 0){
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
       else if (mobile.length == 0 && mobile.length >10){
            toast.error('Enter correct mobile no')
        }
        else if (address.length == 0){
            toast.error('Hey! Enter your name')
        }
        else{
            // call register api, check the status
            // if success back to login screen
            //dynamic navigation
            toast.success('Successfully registered a new user')
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
                        onChange={(e)=>SetName(e.target.value)}
                        type="text" className="form-control"/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="">User Name</label>
                        <input
                         onChange={(e)=>setUsername(e.target.value)}
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
                        onChange={(e)=>setMobile(e.target.value)}
                        type="Number" className="form-control"/>
                    </div><div className="mb-3">
                        <label htmlFor="">Address</label>
                        <input 
                        onChange={(e)=>setAddress(e.target.value)}
                        type="text" className="form-control"/>
                    </div>
                    <div className="mb-3">
                        <div>Already have an account? <Link to='/login'>Login here</Link></div>
                        <br />  
                        <br />
                        {/* <button onClick={onRegister} className="btn btn-success mt-2">Register</button> */}
                        <button onClick={onRegister} className=' button'>
                <span class="transition"></span>
                <span class="gradient"></span>
                <span class="label">REGISTER</span>
              </button>
                    </div>
                </div>
            </div>
            <div className="col"></div>
        </div>

    </div>
    )
}

export default Register