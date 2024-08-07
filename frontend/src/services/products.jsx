import axios from 'axios'
//import config from '../config'

export async function getProducts() {
  // read the token from sessionStorage
  // const token = sessionStorage.token
  // const token = sessionStorage['token']
  const token = sessionStorage.getItem('token')

  const response = await axios.get('https://fakestoreapi.com/products/category/electronics')
  return response.data
}
export async function getProductDetails(id) {
    const token = sessionStorage.getItem('token')
  
    const response = await axios.get(`https://fakestoreapi.com/products/${id}`)
                                                                            
    return response.data
  }
