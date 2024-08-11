
import './App.css';
import { Route, Routes } from 'react-router-dom'
import Home from './pages/Home';
import ProductDetails from './pages/ProductDetails';
import LoginUser from './pages/login'
import { ToastContainer } from 'react-toastify'

import 'react-toastify/dist/ReactToastify.css'
import RegisterSeller from './pages/RegisterSeller';
import RegisterCustomer from './pages/RegisterCustomer';
import cart from './pages/Cart';
import AdminDashboardPage from './pages/AdminDashboardPage';
import Contact from './pages/Contact';
import About from './components/About';
import AboutPage from './pages/AboutPage';
import AddProperty from './pages/AddProperty';
import UserProfilePage from './pages/UserProfile';
import UserProfileForm from './pages/UserProfileForm';
import Dashboard from './pages/SellerDashboard';
function App() {
  return (
    <div className='container'>
      
      <Routes>
          <Route path='/' element={<Home/>} />
          <Route path='/Home' element={<Home/>} />
          <Route path='/login' element={<LoginUser />} />
          <Route path='/registerSeller' element={<RegisterSeller/>} />
          <Route path='/registerCustomer' element={<RegisterCustomer/>} />
          <Route path='/Contact' element={<Contact/>} />
          <Route path='/AddProduct' element={<AddProperty/>} />
          <Route path='/UserProfile' element={<UserProfilePage/>} />
          <Route path='/UserProfileForm' element={<UserProfileForm/>} />
          <Route path='/SellerDashboard' element={<Dashboard/>} />
          <Route path='/About' element={<AboutPage/>} />  
          <Route path='/AdminDashboardPage' element={<AdminDashboardPage/>} /> 
          
          <Route
                path='/product-details/:propertyId'
                element={<ProductDetails />}
              />
      </Routes>

      <ToastContainer />
  </div>
  );
}

export default App;
