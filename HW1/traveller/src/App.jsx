import { useState } from 'react'

import './App.css'
import BookingForm from './components/BookingForm/BookingForm'

function App() {

  const [searchData, setSearchData] = useState([]);

  const SearchTravelHandler = (data) => {
    setSearchData(data);
    console.log(data[1])
  };

  return (
    <div className="container">
      <BookingForm searchCallback={SearchTravelHandler} />
      <div className="travel-list">
        {searchData.length === 0 ? (
          <p className="no-results">No search results available.</p>
        ) : (
          <ul>
            {searchData.map((item) => (
              <li key={item.id} className="travel-item">
                <div className="travel-details">
                  <p className="from-to">
                    From {item.fromcity} to {item.tocity}
                  </p>
                  <p className="date">Departure Date: {item.departure}</p>
                  <p className="date">Arrival Date: {item.arrive}</p>
                  <p className="seats">Number of Seats: {item.numseats}</p>
                  <p className="price">Price: ${item.price.toFixed(2)}</p>
                  <button className='buttonItem'>Purchase</button>
                </div>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
}

export default App
