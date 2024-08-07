// 
import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { login } from '../services/user';
import { toast } from 'react-toastify';
import '../css/Login.css'; // Ensure you have this CSS file for additional styles

function LoginUser() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const navigate = useNavigate();

  const onLogin = async () => {
    try {
      
      const result = await login(username, password);
      if (result && result.token) {
        const { token, data: name } = result;

        sessionStorage.setItem('token', token);
        sessionStorage.setItem('name', name);

        toast.success("Login successful");
        navigate('/Home');
      } else {
        toast.error('Invalid email or password');
      }
    } catch (error) {
      if (error.response && error.response.status === 401) {
        toast.error('Authentication failed. Please check your credentials.');
      } else {
        toast.error('An error occurred during login. Please try again.');
        console.error('Login error:', error);
      }
    }
  };

  return (
    <div className="container">
      <div className="row justify-content-center align-items-center min-vh-100">
        <div className="col-md-6">
          <div className="card shadow-lg p-4">
            <h2 className="text-center mb-4">Login</h2>
            <div className="form">
              <div className="mb-3">
                <label htmlFor="username" className="form-label">Username</label>
                <input
                  id="username"
                  onChange={(e) => setUsername(e.target.value)}
                  type='email'
                  className='form-control'
                  placeholder='Enter your email'
                />
              </div>
              <div className="mb-3">
                <label htmlFor="password" className="form-label">Password</label>
                <input
                  id="password"
                  onChange={(e) => setPassword(e.target.value)}
                  type='password'
                  className='form-control'
                  placeholder='Enter your password'
                />
              </div>
              <div className="mb-3 text-center">
                <div className="mb-2">
                  Don't have an account yet? <Link to='/register'>Register here</Link>
                </div>
                {/* <button onClick={onLogin} className='btn btn-success w-100'>
                  Login
                </button> */}
                 <button onClick={onLogin} className=' button'>
                <span class="transition"></span>
                <span class="gradient"></span>
                <span class="label">LOGIN</span>
              </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default LoginUser;