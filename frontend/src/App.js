
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
import ViewCustomers from "./pages/ViewCustomers";
import ViewSellers from "./pages/ViewSellers";
import Contact from './pages/Contact';
import About from './components/About';
import AboutPage from './pages/AboutPage';
import AddProperty from './pages/AddProperty';
import UserProfilePage from './pages/UserProfile';
import UserProfileForm from './pages/UserProfileForm';
import SellerDashboard from './pages/SellerDashboard';
import AddProductPage from './pages/AddProductPage';
import SearchResults from "./pages/SearchResults";
function App() {
  return (
    <div className="container">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/Home" element={<Home />} />
        <Route path="/login" element={<LoginUser />} />
        <Route path="/registerSeller" element={<RegisterSeller />} />
        <Route path="/registerCustomer" element={<RegisterCustomer />} />
        <Route path="/Contact" element={<Contact />} />
        {/* <Route path="/AddProduct" element={<AddProperty />} /> */}
        <Route path="/UserProfile" element={<UserProfilePage />} />
        <Route path="/UserProfileForm" element={<UserProfileForm />} />
        <Route path="/SellerDashboard" element={<SellerDashboard />} />
        <Route path="/About" element={<AboutPage />} />
        <Route path="/AdminDashboardPage" element={<AdminDashboardPage />} />
        <Route
          path="/AdminDashboardPage/viewCustomers"
          element={<ViewCustomers />}
        />
        <Route
          path="/AdminDashboardPage/viewSellers"
          element={<ViewSellers />}
        />
        <Route path="/search-results" element={<SearchResults />} />
        <Route path="add-product/:sellerId" element={<AddProductPage />} />
        <Route
          path="/product-details/:productId"
          element={<ProductDetails />}
        />
      </Routes>

      <ToastContainer />
    </div>
  );
}

export default App;
