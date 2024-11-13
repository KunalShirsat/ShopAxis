// CarouselComponent.js
import React from 'react';
import { Carousel } from 'react-responsive-carousel';
import 'react-responsive-carousel/lib/styles/carousel.min.css';
import './CarouselComponent.css';

const CarouselComponent = () => {
  return (
    <div className="carousel-container">
      <Carousel
        showArrows={true}
        autoPlay
        infiniteLoop
        showThumbs={false}
        showStatus={false}
        interval={3000}
      >
        <div>
          <img src="https://images-eu.ssl-images-amazon.com/images/G/31/2024/Gateway/November/pc_1._CB542185732_.jpg" alt="Offer 1" />
          <div className="carousel-caption">
            <h2>Starting ₹5,799</h2>
            <p>Mega offers on quick dry washing machines</p>
          </div>
        </div>
        <div>
          <img src="https://images-eu.ssl-images-amazon.com/images/G/31/img23/Beauty/GW/PCwella_1._CB542131636_.png" alt="Offer 2" />
          <div className="carousel-caption">
            <h2>Extra up to ₹13,000 off</h2>
            <p>Exclusive bank offers on credit/debit cards</p>
          </div>
        </div>
        <div>
          <img src="https://images-eu.ssl-images-amazon.com/images/G/31/img23/Wireless/OnePlus/Nord/Nord4/30thOct/D143403581_Tall_Hero_3000x1200._CB542332614_.jpg" alt="Offer 3" />
          <div className="carousel-caption">
            <h2>Deals on Electronics</h2>
            <p>Best prices on top brands</p>
          </div>
        </div>
      </Carousel>
    </div>
  );
};

export default CarouselComponent;
