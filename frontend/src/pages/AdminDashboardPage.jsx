// src/pages/AdminDashboardPage.js
import React from 'react';

import Navbar from '../components/Navbar';
import '../components/Button/button.css'
//import Footer from '../components/Footer';
import DashboardButtons from '../components/DashboardButtons';

const AdminDashboardPage = () => {
    return (
        <div className="admin-dashboard">
            <Navbar />
            <div className="content">
                <DashboardButtons />
            </div>
            {/* <Footer /> */}
        </div>
    );
};

export default AdminDashboardPage;
