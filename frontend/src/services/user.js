import axios from 'axios'
//import config from '../config'

// export async function register(firstName, lastName, email, phone, password) {
//   // body parameters
//   const body = {
//     firstName,
//     lastName,
//     email,
//     phone,
//     password,
//   }

//   // make API call
//   const response = await axios.post(`${config.url}/user/register`, body)

//   // read JSON data (response)
//   return response.data
// }

export async function login(username, password) {
  // body parameters
  const body = {
    username,
    password,
  }

  // make API call
  const response = await axios.post(`https://fakestoreapi.com/auth/login`, body)

  // read JSON data (response)
  return response.data
}