//import { useNavigate } from 'react-router-dom'
////import CategoryList from '../components/categoryList
import logo from '../images/elecbuzz.png'
import { Link, useNavigate } from 'react-router-dom'
import '../css/Navbar.css';
function Navbar() {
  

  return (
     

    <div className=' navbar topnav  fixed-top '>
   
    <div className='ms-5'> 
      {/* <h2 className='page-title '>
    <span class="colored-text">Elec </span>
    Buzzz</h2>  */}
    <img
          src={logo} height="80px"  width="200px"// Replace with the actual path to your image
          alt="Elecbuzz Logo"
          // onClick={() => navigate('/')} // Optional: Add click event handler to navigate to home
        />
    </div>
    <a className="active ms-3 " href="/home">Home</a>
    
    {/* <a className='ms-3' href="#contact">Contact</a> */}
    {/* <a href="/about">About</a> */}
    <Link to='/Contact'>Contact</Link>
    <Link to='/About'>About</Link>
    <Link to='/login'>Login here</Link>

    <input type='text' id="myInput"></input>
    <button className='btn btn-success' value='search'></button>
   
    <Link to='/Cart '> 
    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="white" class="bi bi-cart2" viewBox="0 0 16 16">
  <path d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5M3.14 5l1.25 5h8.22l1.25-5zM5 13a1 1 0 1 0 0 2 1 1 0 0 0 0-2m-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0m9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2m-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0"/>
</svg>   
    Cart</Link>
  </div>
  )
}

export default Navbar