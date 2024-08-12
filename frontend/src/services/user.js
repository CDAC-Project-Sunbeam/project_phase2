import axios from 'axios'

export async function registerCustomer(firstName, lastName, password, phoneNumber, email,dob) {
  // body parameters
  const body = {
    firstName,
    lastName,
    email,
    phoneNumber,
    password,
    dob,
  }

  // make API call
  const response = await axios.post(`http://localhost:8080/customer`, body)

  // read JSON data (response)
  return response.data
}
export async function registerSeller(firstName, lastName, password, phoneNumber,email,dob,bussinessName,taxId) {
  // body parameters
  const body = {
    firstName,
    lastName,
    email,
    phoneNumber,
    password,
    dob,
    taxId,
    bussinessName,
  }

  // make API call
  const response = await axios.post(`http://localhost:8080/seller`, body)

  // read JSON data (response)
  return response.data
}

export async function login(email, password) {
  // body parameters
  const body = {
    email,
    password,
  }

  // make API call
  const response = await axios.post(`http://localhost:8080/user/signin`, body)

  // read JSON data (response)
  return response.data
}

export async function showCustomers() {
  // body parameters
  

  // make API call
  const response = await axios.get(`http://localhost:8080/admin/customers`)

  // read JSON data (response)
  return response.data
}
export async function blockCustomers(id) {
  // body parameters
  

  // make API call
  const response = await axios.patch(`http://localhost:8080/admin/block/${id}`, {
    is_blocked: true,  // Set is_blocked to true
  })

  // read JSON data (response)
  return response.data
}
export async function showSellers() {
  // body parameters
  

  // make API call
  const response = await axios.get(`http://localhost:8080/admin/sellers`)

  // read JSON data (response)
  return response.data
}
export async function blockSellers(id) {
  // body parameters
  

  // make API call
  const response = await axios.patch(`http://localhost:8080/admin/block/${id}`, {
    is_blocked: true,  // Set is_blocked to true
  })

  // read JSON data (response)
  return response.data
}