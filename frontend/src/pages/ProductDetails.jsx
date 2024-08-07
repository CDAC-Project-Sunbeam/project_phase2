import { Link, useParams } from 'react-router-dom'
import { getProductDetails } from '../services/products'
//import { toast } from 'react-toastify'
import { useEffect, useState } from 'react'
//import config from '../config'

function ProductDetails() {
  const { propertyId } = useParams()
  const [details, setDetails] = useState(undefined)

  // collect all the amenities
  const [amenities, setAmenities] = useState([])

  const loadProductDetails = async () => {
    const result = await getProductDetails(propertyId)
    
      

      // clear the array
      
      setDetails(result)
     
    } 
  

  useEffect(() => {
    loadProductDetails()
  }, [])

  return (
    <div>
      {details && (
        <div className='mt-5'>
          <h3 style={{ fontSize: 26 }}>{details.title}</h3>
          <hr />

          <img
            className='mt-2'
            style={{ height: 300 }}
            src={details.image}
          />
          <div className='mt-2'>
            <h4 className='mt-2'> {details.description}</h4>
            
          </div>
          <hr />

          <div className='mt-3'>
            <div>
              category:{' '}
              <span style={{ fontWeight: 'bold' }}>{details.category}</span>
            </div>
            <div>rating :{details.rating['rate']}</div>
            <hr />
            <div>stock available :{details.rating['count']}</div>
            <hr />
          </div>
          <div className='mt-3'>
            <h4>Price</h4>
            <div>{details.price}</div>
            <hr />
          </div>

          <div className='mt-3'>
            <h4>This place offers</h4>
            <div className='row'>
              <div className='col'>
                {amenities.map((amenity) => {
                  return (
                    <div>
                      <i
                        className={`bi bi-${amenity.icon}`}
                        style={{ fontSize: 30 }}
                      />
                      <span
                        className='ms-3'
                        style={{
                          textDecoration:
                            amenity.status == 0 ? 'line-through' : 'none',
                        }}
                      >
                        {amenity.title}
                      </span>
                    </div>
                  )
                })}
              </div>
              <div className='col'></div>
              <div className='col'></div>
            </div>

            <div className='mt-5'>
              <Link to='/home' className='btn btn-primary'>
                Back
              </Link>
            </div>
          </div>
        </div>
      )}
    </div>
  )
}

export default ProductDetails